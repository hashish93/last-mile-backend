package com.appzoneltd.lastmile.microservice.abstractconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 * @author Alaa Nabil Core Application Class has configuration bean extended in
 *         all services
 **/
public abstract class CoreApplicationConfig {
    /**
     * Local resolver bean to be autowired
     */

    @Value("${localization.directory:#{null}}")
    private String localizationDirectory;

    @Bean
    protected LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver headerLocalResolver = new AcceptHeaderLocaleResolver();
        return headerLocalResolver;
    }

    /**
     * Message Source bean to be autowired
     */
    @Bean
    protected ReloadableResourceBundleMessageSource messageSource() {
        String baseName = "classpath:locale/";

        if (localizationDirectory != null)
            baseName = "file:" + localizationDirectory;

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(baseName + "messages");
        messageSource.setCacheSeconds(3600);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
