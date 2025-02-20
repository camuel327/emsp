package com.example.emsp.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class I18nUtils {

    private static final String[] BASE_NAMES = new String[]{"i18n/system"};
    private static final MessageSource MESSAGE_SOURCE;

    static {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames(BASE_NAMES);
        source.setDefaultEncoding("UTF-8");
        source.setFallbackToSystemLocale(false);
        MESSAGE_SOURCE = source;
    }

    public static String format(final String pattern, final Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        String msg = MESSAGE_SOURCE.getMessage(pattern, args, pattern, locale);
        return msg != null ? msg : pattern;
    }

}
