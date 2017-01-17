package reparator;

import spoon.Launcher;
import spoon.reflect.factory.Factory;

public class VersionSniper {
	

    private Launcher spoon;
    private  int numero;
    private String pathToSource;

    {
        spoon = new Launcher();
    }

    public VersionSniper(String gitProject, String version, String srcPath, String classPath,int numero) {
        this.pathToSource = App.TMPFOLDER + gitProject + "/" + version + "/" + srcPath;
        this.numero = numero;
		System.out.println("Adding sniper to " + pathToSource);
		System.out.println("with classPath = " + classPath);
        System.out.flush();
		
        spoon.run(new String[]{"-i", pathToSource, "--source-classpath", classPath});
    }

    public int getId(){
        return numero;
    }
    
    public Factory getFactory(){
    	return this.spoon.getFactory();
    }


}
