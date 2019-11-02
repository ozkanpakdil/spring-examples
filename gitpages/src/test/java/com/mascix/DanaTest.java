package com.mascix;

import liqp.Template;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class DanaTest {
    @Test
    public void test() {
//        ModelMap model = new ModelMap();
        HashMap model=new HashMap();
        List ll = new ArrayList();
        for (int i = 0; i < 5; i++) {
            Dana t = new Dana();
            t.setSpeakerName("" + i);
            t.setTitle("tit" + i);
            ll.add(t);
        }

        model.put("presentations", ll);

        String templateStr="req dat:{{tt}}\n" +
                "\n" +
                "  {% for p in presentations %}\n" +
                "    <div class=\"card mb-3 shadow-sm rounded\">\n" +
                "              <div class=\"card-header\">\n" +
                "                  <h5 class=\"card-title\">title:{{ p.title }} - speaker:{{ p.speakerName }}</h5>\n" +
                "              </div>\n" +
                "              <div class=\"card-body\">\n" +
                "                  {{ p.summary }}\n" +
                "              </div>\n" +
                "          </div>\n" +
                "  {% endfor %}";
        Template template = Template.parse(templateStr);
        String rendered = template.render(model);
        log.info("m:" + model);
        log.info("-------------------:" + rendered);
    }
}