package com.mascix.config;

import com.mascix.starter.LiqpView;
import com.mascix.starter.LiqpViewResolver;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;

import java.io.File;
import java.util.Locale;


@Slf4j
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.mascix","com.mascix.starter"})
public class WebConfig implements ApplicationContextAware, WebMvcConfigurer {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/robots.txt").addResourceLocations("/robots.txt");
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//        String templatesPath = new File("./templates/").getAbsolutePath();
//        log.info("tttt:" + templatesPath);
//        registry.addResourceHandler("/templates/**").addResourceLocations("file:" + templatesPath);
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }


    /*@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix("");
        return viewResolver;
    }*/

    /*@Bean
    public ViewResolver resourceBundleViewResolver() {
        ResourceBundleViewResolver bean = new ResourceBundleViewResolver();
        bean.setBasename("templates");
        return bean;
    }*/
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);

        return slr;
    }

    /*@Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".html");
        return resolver;
    }*/
    @Bean
    public LiqpViewResolver liqpViewResolver() {
        LiqpViewResolver viewResolver = new LiqpViewResolver();
        viewResolver.setOrder(0);
        viewResolver.setViewClass(LiqpView.class);
        viewResolver.setViewClass(LiqpView.class);
        viewResolver.setPrefix("/templates/");
//        viewResolver.setSuffix(".liqp");
        viewResolver.setViewNames("*.liqp");
        viewResolver.setRequestContextAttribute("rc");
        viewResolver.setCache(false);
        viewResolver.setApplicationContext(this.applicationContext);
        viewResolver.setContentType("text/html;charset=UTF-8");


        return viewResolver;
    }


    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {

        registry.viewResolver(liqpViewResolver());
        registry.enableContentNegotiation();
//        registry.scriptTemplate();
//        registry.jsp("/templates/", ".liqp");
    }
}
