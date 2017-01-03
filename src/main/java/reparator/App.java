package reparator;

import org.apache.commons.cli.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.json.JSONObject;
import spoon.Launcher;
import util.GitUtils;

import java.io.*;
import java.util.ArrayList;

public class App {

	// Folder to store git revisions
	public static final String TMPFOLDER = "C:\\Users\\Apolloch\\IdeaProjects\\OPL-RepairMultiVersion\\tmp\\opl";

	private static ArrayList<VersionSniper> snipers;

	static {
		snipers = new ArrayList<VersionSniper>();
	}

	public static void main(String[] args) {

		// Uncomment this to try with something else
		String configContent = "";

		File confFile = new File("app_conf.json");
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(confFile));
			String line ;
			while((line = fileReader.readLine()) != null) {
				configContent+=line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject config = new JSONObject(configContent);

		reparator(config.getString("github_user_name"),config.getString("github_project"), config.getString("src_path_in_project"), config.getString("junit_jar"), 3);

		if(true)
			return;


		// create Options object
    	Options options = new Options();
    	// add t option
    	options.addOption("gitUsername", true, " git username of the project");
		options.addOption("gitProject", true, " git project name of the project");
    	options.addOption("sourcePath",true, " Relative source path from folder position (ex: if the project path is /tmp/project and the sources are in /tmp/project/src, the sourcepath may be src");
    	options.addOption("classPath", true, " An optional classpath to be passed to the internal Java compiler when building or compiling the input sources.");
    	options.addOption("nbrCommit", true, " Number of commits to use to generate the new project");

    	CommandLineParser parser = new DefaultParser();
    	try {
    		CommandLine cmd = parser.parse( options, args);
        	if(!cmd.hasOption("-gitUsername")) {
        		HelpFormatter formatter = new HelpFormatter();
        		formatter.printHelp( "list of parameters", options );
        		System.exit(0);
        	}

        	reparator(cmd.getOptionValue("gitUsername"),
					cmd.getOptionValue("gitProject"),
					cmd.getOptionValue("sourcePath"),
					cmd.getOptionValue("classPath"),
					Integer.parseInt(cmd.getOptionValue("nbrCommit")));

        }
        catch( ParseException exp ) {
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
        }
	}


	public static void reparator(String gitUsername, String gitProject, String srcPath, String classPath, int nbrCommitMax){

		System.out.println("gitUsername = " + gitUsername);
		System.out.println("gitProject = " + gitProject);
		System.out.println("srcPath = " + srcPath);
		System.out.println("classPath = " + classPath);
		System.out.println("number of commits = " + nbrCommitMax);

		final String gitUrl = "https://github.com/" + gitUsername + "/" + gitProject;

		System.out.println("Calling Git to generate " + nbrCommitMax + " folders from : " + gitUrl);

		// Calling git to get all commit (till nbrCommitMax)
		try {
			GitUtils.getAll(gitUrl, gitProject, nbrCommitMax);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}

		// Listing all versions subfolder
		File file = new File(TMPFOLDER + gitProject);
		String[] directories = file.list(new FilenameFilter() {
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		});

		// Adding sniper to version subfolder
		for(String version: directories){
			if(!version.toLowerCase().contains("master"))
				snipers.add(new VersionSniper(gitProject, version, srcPath, classPath));
		}

		System.out.println("Spoon the master as the new project template = "
				+ TMPFOLDER + gitProject + "/master" +  "/" + srcPath);
		System.out.println("with classPath = " + classPath);
		
		Launcher spoon = new Launcher();
		spoon.addProcessor(new MethodVersioningProcessor(snipers));
        spoon.run(new String[]{"-i", TMPFOLDER + gitProject + "/master" +  "/" + srcPath, "--source-classpath", classPath});
		VersionComposer.compileSourceCode();
		try {
			MainTest.runAllTests();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println("---- End of program ----");
        
	}

}
