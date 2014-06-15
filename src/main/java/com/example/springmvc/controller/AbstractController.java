package com.example.springmvc.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * Created by tatsuya on 2014/05/25.
 */
public abstract class AbstractController {

    private static final String VIEW_REDIRECT_PREFIX    = "redirect:";

    private static final String FLASH_ERROR_MESSAGE     = "errorMessage";
    private static final String FLASH_FEEDBACK_MESSAGE  = "feedbackMessage";

    @Resource
    MessageSource messageSource;

    protected final void addErrorMessage(RedirectAttributes redirectAttributes, String code, Object... params) {
        Locale current = LocaleContextHolder.getLocale();
        String localizedMessage = messageSource.getMessage(code, params, current);
        redirectAttributes.addFlashAttribute(FLASH_ERROR_MESSAGE, localizedMessage);
    }

    protected final void addFeedbackMessage(RedirectAttributes redirectAttributes, String code, Object... params) {
        Locale current = LocaleContextHolder.getLocale();
        String localizedMessage = messageSource.getMessage(code, params, current);
        redirectAttributes.addFlashAttribute(FLASH_FEEDBACK_MESSAGE, localizedMessage);
    }

    protected final String createRedirectViewPath(final String path) {
        StringBuilder builder = new StringBuilder();
        builder.append(VIEW_REDIRECT_PREFIX);
        builder.append(path);
        return builder.toString();
    }

}
