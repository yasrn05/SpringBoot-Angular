package com.project.backend.components;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import com.project.backend.utils.WebUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LocalizationUtils {
    private final MessageSource messageSource;
    private final LocaleResolver localeResolverl;

    public String getLocalizationMessage(String messageKey) {
        HttpServletRequest request = WebUtils.getCurrentRequest();
        Locale locale = localeResolverl.resolveLocale(request);
        return messageSource.getMessage(messageKey, null, locale);
    }
}
