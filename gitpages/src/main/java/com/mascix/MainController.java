package com.mascix;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.storage.file.FileBasedConfig;
import org.eclipse.jgit.util.FS;
import org.eclipse.jgit.util.FS_POSIX;
import org.eclipse.jgit.util.FS_Win32;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class MainController {
    @RequestMapping("/")
    String index() {
        return "index.html";
    }

    @RequestMapping(value = "/gr", params = {"RepoName"})
    String cloneTheRepoAndBring(@RequestParam("RepoName") String repoName) throws Exception {
        File r = new File("./static/" + repoName);
        if (!Files.exists(r.toPath())) {
            File configFile = new File("./gitconfig");
            FileBasedConfig fileConfig = new FileBasedConfig(configFile, FS.DETECTED);
            Git git = Git.cloneRepository()
                    .setFs(FS.detect().newInstance().setGitSystemConfig(configFile))
//                    .setGitDir(new File(r + "/.git"))
                    .setURI("https://github.com/" + repoName + ".git") // eclipse/jgit
                    .setDirectory(r)
                    .call();
        } else {
            Git.open(r).pull().call();
        }

        return "repo.html";

    }
}
