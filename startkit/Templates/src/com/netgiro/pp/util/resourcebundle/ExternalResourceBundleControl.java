/* -----------------------------------------------------------------------------
 * Created on 26 mar 2008 by jholmstrom
 * Copyright NetGiro Systems AB
 * Version: $Id: ExternalResourceBundleControl.java,v 1.1 2014/10/27 12:40:26 kkarlberg Exp $
 * --------------------------------------------------------------------------- */
package com.netgiro.pp.util.resourcebundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Replaces the default {@link java.util.ResourceBundle.Control}, which is
 * confined to the classpath when locating resources.
 * 
 * Instances of this class are thread safe and may be re-used.
 * 
 * @author jholmstrom
 */
public class ExternalResourceBundleControl extends ResourceBundle.Control
{
    /**
     * Use external by default, but use standard java.properties as fall back
     * solution
     */
    public static final List<String> FORMAT_EXTERNAL = Collections.unmodifiableList(Arrays.asList("external",
        "java.properties"));

    /** Default ResourceBundle file suffix */
    public static final String DEFAULT_SUFFIX = "properties";

    public static final long DEFAULT_CACHE_REFRESH_INTERVAL = 10000L;

    /** Base folder for resources in this context */
    private final File iBaseFolder;

    /** Resource bundle property files suffix */
    private final String iSuffix;

    /** Time in milliseconds between checking cache content status */
    private final long iCacheRefreshInterval;

    public ExternalResourceBundleControl(String aBaseFolderName)
    {
        this(new File(aBaseFolderName));
    }

    public ExternalResourceBundleControl(File aBaseFolder)
    {
        this(aBaseFolder, DEFAULT_SUFFIX, DEFAULT_CACHE_REFRESH_INTERVAL);
    }

    public ExternalResourceBundleControl(File aBaseFolder, String aSuffix,
            long aCacheRefreshInterval)
    {
        if (!aBaseFolder.exists() || !aBaseFolder.isDirectory())
        {
            throw new IllegalArgumentException("No such directory: "
                    + aBaseFolder.getAbsolutePath());
        }
        if (aSuffix == null)
        {
            throw new IllegalArgumentException("aSuffix cannot be null!");
        }

        iBaseFolder = aBaseFolder;
        iSuffix = aSuffix;
        iCacheRefreshInterval = aCacheRefreshInterval;
    }

    @Override
    public ResourceBundle newBundle(String aBaseName, Locale aLocale,
            String aFormat, ClassLoader aLoader, boolean aReload)
            throws IllegalAccessException, InstantiationException, IOException
    {
        if ("external".equals(aFormat))
        {
            File file = findExternalResourceBundle(aBaseName, aLocale);

            if (file != null)
            {
                return new PropertyResourceBundle(new FileInputStream(file));
            }
        }
        return super.newBundle(aBaseName, aLocale, aFormat, aLoader, aReload);
    }

    @Override
    public List<String> getFormats(String aBaseName)
    {
        if (aBaseName == null)
        {
            throw new NullPointerException();
        }
        return FORMAT_EXTERNAL;
    }

    @Override
    public boolean needsReload(String aBaseName, Locale aLocale,
            String aFormat, ClassLoader aLoader, ResourceBundle aBundle,
            long aLoadTime)
    {
        if ("external".equals(aFormat))
        {
            boolean result = false;

            File file = findExternalResourceBundle(aBaseName, aLocale);

            if (file != null)
            {
                return file.lastModified() > aLoadTime;
            }
            return result;
        }
        return super.needsReload(aBaseName,
            aLocale,
            aFormat,
            aLoader,
            aBundle,
            aLoadTime);
    }

    /**
     * Finds a resource bundle file
     * 
     * @param aBaseName
     *            the resource bundle base name
     * @param aLocale
     *            the locate to use
     * @return the file or null if no file was found
     * @throws NullPointerException
     *             if aBaseName or aLocale is null
     */
    protected File findExternalResourceBundle(String aBaseName, Locale aLocale)
    {
        File file = null;
        String baseFileName;
        String bundleName;
        String resourceName;

        // Ignore any leading folder names, as we already have base folder
        int divPos = Math.max(aBaseName.lastIndexOf('.'),
            aBaseName.lastIndexOf('/'));

        if (divPos > 0)
        {
            baseFileName = aBaseName.substring(divPos + 1);
        }
        else
        {
            baseFileName = aBaseName;
        }

        bundleName = toBundleName(baseFileName, aLocale);
        resourceName = toResourceName(bundleName, iSuffix);

        file = new File(iBaseFolder, resourceName);

        if (file.exists() && file.isFile() && file.canRead())
        {
            return file;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public long getTimeToLive(@SuppressWarnings("unused")
    String aBaseName, @SuppressWarnings("unused")
    Locale aLocale)
    {
        return iCacheRefreshInterval;
    }

    @Override
    public Locale getFallbackLocale(@SuppressWarnings("unused")
    String aBaseName, @SuppressWarnings("unused")
    Locale aLocale)
    {
        /*
         * Don't use fallback locale, since we don't want to force Swedish (nor
         * english for that matter) when there is no resource bundle available
         * for a language/country that was submitted by a merchant. Instead the
         * default resource bundle should be used.
         */
        return null;
    }
}
