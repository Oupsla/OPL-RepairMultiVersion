package util;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class GitUtils {

    private static final String TMPFOLDER = "/tmp/opl/";


    public static String cloneRepo(String remoteUrl, String folder) throws IOException, GitAPIException {
        // prepare a new folder for the cloned repository
        File directory = new File(TMPFOLDER + folder);
        directory.mkdirs();
        FileUtils.cleanDirectory(directory);

        directory = new File(TMPFOLDER + folder + "/master");

        // then clone
        System.out.println("Cloning from " + remoteUrl + " to " + directory);
        Git result = Git.cloneRepository()
                .setURI(remoteUrl)
                .setDirectory(directory)
                .call();
        System.out.println("Cloning repository: " + result.getRepository().getDirectory());
        result.pull();
        result.close();

        return result.getRepository().getDirectory().toString();
    }

    public static void getAll(String remoteUrl, String folder) throws IOException, GitAPIException {
        String localUrl = cloneRepo(remoteUrl, folder);

        Repository repo = new FileRepository(localUrl);
        Git git = new Git(repo);
        RevWalk walk = new RevWalk(repo);

        List<Ref> branches = git.branchList().setListMode( ListBranchCommand.ListMode.ALL ).call();

        for (Ref branch : branches) {
            String branchName = branch.getName();

            System.out.println("Commits of branch: " + branch.getName());
            System.out.println("-------------------------------------");

            Iterable<RevCommit> commits = git.log().all().call();

            for (RevCommit commit : commits) {
                boolean foundInThisBranch = false;

                RevCommit targetCommit = walk.parseCommit(repo.resolve(
                        commit.getName()));
                for (Map.Entry<String, Ref> e : repo.getAllRefs().entrySet()) {
                    if (e.getKey().startsWith(Constants.R_HEADS)) {
                        if (walk.isMergedInto(targetCommit, walk.parseCommit(
                                e.getValue().getObjectId()))) {
                            String foundInBranch = e.getValue().getName();
                            if (branchName.equals(foundInBranch)) {
                                foundInThisBranch = true;
                                break;
                            }
                        }
                    }
                }

                if (foundInThisBranch) {
                    File destDir = new File(TMPFOLDER + folder + "/" +  commit.getName());
                    destDir.mkdirs();

                    Git.cloneRepository().setURI( TMPFOLDER + folder + "/master/.git" ).setDirectory(destDir).call();

                    Repository repo2 = new FileRepository(destDir + "/.git");
                    Git git2 = new Git(repo2);
                    git2.checkout().setName(commit.getName()).call();

                    System.out.println("Cloning repository from commit : " + commit.getName() + " to " + destDir);
                }
            }
        }
    }
}
