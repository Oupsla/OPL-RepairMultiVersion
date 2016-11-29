package util;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;

public class GitUtils {

    public static void cloneRepo(String remoteUrl) throws IOException, GitAPIException {
        // prepare a new folder for the cloned repository
        File localPath = File.createTempFile("TestGitRepository", "");
        if(!localPath.delete()) {
            throw new IOException("Could not delete temporary file " + localPath);
        }

        // then clone
        System.out.println("Cloning from " + remoteUrl + " to " + localPath);
        Git result = Git.cloneRepository()
                .setURI(remoteUrl)
                .setDirectory(localPath)
                .call();
        System.out.println("Cloning repository: " + result.getRepository().getDirectory());
        result.pull();
        result.close();
    }
}
