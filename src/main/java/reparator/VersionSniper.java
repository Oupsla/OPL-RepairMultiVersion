package reparator;

import spoon.Launcher;
import spoon.reflect.factory.Factory;

public class VersionSniper {
	

    private Launcher spoon;
    private int numero;
    private String pathToSource;

    {
        spoon = new Launcher();
    }

    /**
     * Constructor
     * @param projectPath
     * @param numero
     */
    public VersionSniper(String projectPath, String innerProjectPath, String classPath, int numero) {
        this.numero = numero;
        this.pathToSource = projectPath+'_'+numero+'/'+innerProjectPath;

        
		System.out.println("spoon sources "+pathToSource);
		System.out.println("with classPath = "+classPath);
		
        spoon.run(new String[]{"-i",pathToSource,"--source-classpath",classPath});
    }

    public int getId(){
    	return this.numero;
    }
    
    public Factory getFactory(){
    	return this.spoon.getFactory();
    }


}
