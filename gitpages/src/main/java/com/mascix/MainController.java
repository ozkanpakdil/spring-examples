package com.mascix;

import liqp.Template;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.storage.file.FileBasedConfig;
import org.eclipse.jgit.util.FS;
import org.eclipse.jgit.util.FS_POSIX;
import org.eclipse.jgit.util.FS_Win32;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class MainController {

    @RequestMapping("/")
    String index(final ModelMap model) {
        List ll = new ArrayList();
        for (int i = 0; i < 5; i++) {
            Dana t = new Dana();
            t.setSpeakerName("" + i);
            t.setTitle("tit" + i);
            ll.add(t);
        }

        model.put("presentations", ll);

        String rendered = Template.parse("{% for i in presentations %}{{ p.title }}-{% endfor %}")
                // .withProtectionSettings(protectionSettings)
                .render(model);

        log.info(rendered);
        model.put("tt", new Date());
        return "index.liqp";
    }

    @RequestMapping(value = "/gr", params = { "RepoName" })
    String cloneTheRepoAndBring(@RequestParam("RepoName") String repoName) throws Exception {
        File r = new File("./templates/" + repoName);
        if (!Files.exists(r.toPath())) {
            File configFile = new File("./gitconfig");
            FileBasedConfig fileConfig = new FileBasedConfig(configFile, FS.DETECTED);
            Git git = Git.cloneRepository().setFs(FS.detect().newInstance().setGitSystemConfig(configFile))
                    // .setGitDir(new File(r + "/.git"))
                    .setURI("https://github.com/" + repoName + ".git") // eclipse/jgit
                    .setDirectory(r).call();
        } else {
            Git.open(r).pull().call();
        }
        // Path lnk = Path.of("src/main/template/" + repoName);
        // if (!Files.exists(lnk))
        // Files.createSymbolicLink(lnk, r.toPath());
        return repoName + "/index.liqp";
        // return repoName + "/index.liqp";
        // return repoName + "/index.html";

    }
}
