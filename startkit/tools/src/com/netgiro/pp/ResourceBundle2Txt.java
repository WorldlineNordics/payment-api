package com.netgiro.pp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import java.util.Set;

import com.netgiro.pp.util.resourcebundle.ExternalResourceBundleControl;

public class ResourceBundle2Txt {

    // Microsux Excel calls x-UTF-16LE-BOM for Windows ANSI format
    private static CharsetEncoder encoder = Charset.forName("x-UTF-16LE-BOM").newEncoder();

    private static final String COL_SEPARATOR = "\t";
    private static final String ROW_SEPARATOR = "\r\n";

    static final String RESOURCE_FOLDER = "Templates/context/";
    static final String RESOURCE_NAME = "strings";

    private static final String OUTPUT_FILE = "Translations_x-UTF-16LE-BOM.txt";

    public ResourceBundle2Txt() throws IOException {

        final Map<String, Map<Locale, String>> map = new HashMap<String, Map<Locale, String>>();
        final Set<Locale> locales = new HashSet<Locale>();

        final ResourceBundle.Control control = new ExternalResourceBundleControl(
                RESOURCE_FOLDER);

        final ResourceBundle defaultBundle = ResourceBundle.getBundle(
                RESOURCE_NAME, Locale.ROOT, control);

        for (final String country : Locale.getISOLanguages()) {

            for (final String language : Locale.getISOLanguages()) {
                buildIt(language,country, control, defaultBundle, map, locales);
            }
        }
        writeToFile(OUTPUT_FILE, map, locales);
    }

    private static void buildIt(String language, String country, Control control,
            ResourceBundle defaultBundle, Map<String, Map<Locale, String>> map,
            Set<Locale> locales) {
        Locale locale = new Locale(language, country);

        final Locale languageLocale = new Locale(language);
        final ResourceBundle localeBundle = ResourceBundle.getBundle(
                RESOURCE_NAME, locale, control);
        final ResourceBundle languageLocaleBundle = ResourceBundle
        .getBundle(RESOURCE_NAME, languageLocale, control);

        Enumeration<String> keys = localeBundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String localeString = localeBundle.getString(key);
            String defaultString = defaultBundle.getString(key);
            String languageString = languageLocaleBundle.getString(key);
            if (defaultString != null
                    && defaultString.equals(localeString)) {
                locale = Locale.ROOT;
            } else if (languageString != null
                    && languageString.equals(localeString)) {
                locale = languageLocale;
            }
            locales.add(locale);
            insertNewLocale(locale, localeString, key, map);
        }

    }

    private static void insertNewLocale(Locale locale, String localeString,
            String key, Map<String, Map<Locale, String>> map) {
        Map<Locale, String> lmap = map.get(key);
        if (lmap == null) {
            lmap = new HashMap<Locale, String>();
        }
        lmap.put(locale, localeString);
        map.put(key, lmap);
    }

    private void writeToFile(String fileName,
            Map<String, Map<Locale, String>> map, Set<Locale> locales)
    throws IOException {

        OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(OUTPUT_FILE), encoder);

        writer.append("" + COL_SEPARATOR);

        for (Locale locale : locales) {
            if (Locale.ROOT.equals(locale)) {
                writer.append("Default");
            }
            writer.append(locale.getDisplayName(Locale.US) + " '"
                    + locale.toString() + "'" + COL_SEPARATOR);
        }
        writer.append(ROW_SEPARATOR);

        List<String> keys = new ArrayList<String>(map.keySet());
        Collections.sort(keys);

        for (String key : keys) {
            if (key != null && key.length() > 0) {
                writer.append(key + COL_SEPARATOR);
                Map<Locale, String> lmap = map.get(key);

                for (Locale locale : locales) {
                    String text = lmap.get(locale);
                    writer.append((text == null ? "" : text) + COL_SEPARATOR);
                }
                writer.append(ROW_SEPARATOR);
            }
        }
        writer.flush();
        writer.close();

    }

    public static void main(String[] args) throws IOException {
        new ResourceBundle2Txt();
    }

}
