package com.mascix.starter;

import java.io.File;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.servlet.view.AbstractTemplateView;

import liqp.Template;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LiqpView extends AbstractTemplateView {
    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        File templateFile = new File("." + getUrl());
        if (templateFile.exists()) {
            Template template = Template.parse(templateFile);
            String rendered = template.render(model);
            log.info("m:" + model);
            log.info("-------------------:" + rendered);
            response.getWriter().write(rendered);
        } else {
            log.info("Not Found:" + getUrl());
        }
    }

    @Override
    public boolean checkResource(Locale locale) throws Exception {
        log.debug(getUrl());
        return getUrl().endsWith(".liqp");
    }

    @Override
    protected void initServletContext(ServletContext servletContext) throws BeansException {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug(getUrl());
    }
}
