package com.digitalriver.worldpayments.api;

import com.digitalriver.worldpayments.api.utils.ObjectConverter;
import com.digitalriver.worldpayments.api.utils.Parameter;
import com.digitalriver.worldpayments.api.utils.ParseUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.*;

public class ParameterAnnotationHelper {

    private static String createMapString(Map<String, String> map) {
        StringBuffer sb = new StringBuffer();
        for (String key : map.keySet()) {
            String val = map.get(key);
            sb.append(key).append("=").append(ParseUtil.escapeChar(val, '#')).append("#");
        }
        return sb.toString();
    }

    static List<Field> getAllDeclaredFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<Field>();
        while (!Object.class.equals(clazz)) {
            if (clazz.getDeclaredFields() != null)
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    private static Map<String, String> getListEntry(Map<String, String> nvp, int listPos) {
        Map<String, String> listEntry = new HashMap<String, String>();
        for (String name : nvp.keySet()) {
            if (name.endsWith("_" + listPos)) {
                String n = name.substring(0, name.length() - ("_" + listPos).length());
                listEntry.put(n, nvp.get(name));
            }
        }
        return listEntry;
    }

    @SuppressWarnings("unchecked")
    private static String getValue(Object object, Field field) {
        if (object == null)
            return "";
        if (field.getType().isAssignableFrom(byte[].class)) {
            return new BigInteger((byte[]) object).toString();
        }
        if (isMapType(field.getType())) {
            return createMapString((Map<String, String>) object);
        }
        return object.toString();
    }

    private static boolean isListType(Class<?> type) {
        return type.getName().equals(List.class.getName());
    }

    private static boolean isMapType(Class<?> type) {
        return type.getName().equals(Map.class.getName());
    }

    public static boolean mapNvpToObject(Object object, Map<String, String> nvp) throws Exception {
        return mapNvpToObject(object, nvp, getAllDeclaredFields(object.getClass()));
    }

    static boolean mapNvpToObject(Object object, Map<String, String> nvp, List<Field> fields)
            throws Exception {
        boolean invokedSetter = false;
        for (Field field : fields) {
            if (Modifier.isPrivate(field.getModifiers()))
                continue;
            field.setAccessible(true);
            Parameter param = field.getAnnotation(Parameter.class);
            if (param != null) {
                if (nvp.containsKey(param.shortName())) {
                    String value = nvp.get(param.shortName());
                    // TODO add validation
                    if (value != null && value.length() > 0) {
                        try {
                            field.set(object, ObjectConverter.convertObject(field.getType(), value));
                            invokedSetter = true;
                        } catch (Exception e) {
                                throw e;
                        }
                    }
                }
            } else {
                if (!field.getType().isPrimitive()) {
                    if (field.getType().isAssignableFrom(ArrayList.class)) {
                        ParameterizedType typ = (ParameterizedType) field.getGenericType();
                        Class<?> gt = (Class<?>) typ.getActualTypeArguments()[0];
                        List<Object> list = new ArrayList<Object>();
                        int pos = 1;
                        while (true) {
                            Map<String, String> listEntry = getListEntry(nvp, pos++);
                            if (listEntry.isEmpty()) {
                                break;
                            } else {
                                Object nytt = gt.newInstance();
                                boolean invoked = mapNvpToObject(nytt, listEntry, getAllDeclaredFields(gt));
                                if (invoked) {
                                    list.add(nytt);
                                }
                            }
                        }
                        if (!list.isEmpty()) {
                            field.set(object, list);
                        }
                    } else {

                        Object newObject = field.getType().newInstance();
                        if (mapNvpToObject(newObject, nvp, getAllDeclaredFields(newObject.getClass()))) {
                            field.set(object, newObject);
                        }

                    }
                }
            }
        }
        return invokedSetter;
    }

    public static Map<String, String> mapObjectToNvp(Object object) throws IllegalArgumentException {
        return mapObjectToNvp(object, null, getAllDeclaredFields(object.getClass()));
    }

    static Map<String, String> mapObjectToNvp(Object object, Integer pos, List<Field> fields)
            throws IllegalArgumentException {
        Map<String, String> nvp = new LinkedHashMap<String, String>();
        for (Field field : fields) {
            if (Modifier.isPrivate(field.getModifiers()))
                continue;
            try {
                Parameter param = field.getAnnotation(Parameter.class);
                Object o = field.get(object);
                if (param != null) {
                    String value = getValue(o, field);
                    Parameter.Validate.checkRequired(value, param, field.getName());
                    Parameter.Validate.evalRegEx(value, param, field.getName());
                    Parameter.Validate.checkLength(value, param, field.getName());
                    if (!Parameter.Validate.isBlank(value)) {
                        String name = param.shortName();
                        if (pos != null) {
                            name = name + "_" + pos.toString();
                        }
                        nvp.put(name, value);
                    }
                } else if (isListType(field.getType())) {
                    if (pos == null) {
                        pos = new Integer(1);
                    }
                    List<?> list = (List<?>) o;
                    if (list != null) {
                        for (Object o1 : list) {
                            if (o1 != null) {
                                nvp.putAll(mapObjectToNvp(o1, pos++, getAllDeclaredFields(o1.getClass())));
                            }
                        }
                    }
                    pos = null;
                } else {
                    if (o != null) {
                        nvp.putAll(mapObjectToNvp(o, null, getAllDeclaredFields(o.getClass())));
                    }
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        }
        return nvp;
    }

}
