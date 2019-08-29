package com.mascix.starter;

import com.mascix.Dana;
import com.mascix.MainController;
import liqp.Template;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class LiqpView extends AbstractTemplateView {
    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        File templateFile = new File("." + getUrl());
        if (templateFile.exists()) {
            Template template = Template.parse(templateFile);
            String rendered = template.render(model);
            log.debug("m:" + model);
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
