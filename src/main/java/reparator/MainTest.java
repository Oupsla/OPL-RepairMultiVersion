package reparator;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;


public class MainTest {
	

	static List<Class<?>> projectClasses = new ArrayList<Class<?>>();
	static List<Class<?>> testsClasses = new ArrayList<Class<?>>();
	private static String classesToTestDir = new File("./spooned/bin/").getAbsolutePath()+"/";

	public static void main(String[] args) throws Exception {
		runAllTests();
	}

	public static void runAllTests() throws ClassNotFoundException, IOException, IllegalAccessException {
		Class[] allClasses = getClasses(classesToTestDir);
		for(Class<?> c : allClasses){
            if(c.getName().endsWith("Test")){
                testsClasses.add(c);
            }else{
                projectClasses.add(c);
            }
        }

		for(Class<?>c : projectClasses){
            System.out.println("MODIF CLASS "+c.getName());
            for(Method m : c.getDeclaredMethods()){
                try{

                    Field vfield = c.getDeclaredField((m.getName()+"_version"));
                    Field vmaxfield = c.getDeclaredField(m.getName()+"_version_max");

                    System.out.println("MODIF METHOD "+m.getName());
                     while(vfield.getInt(null) <= vmaxfield.getInt(null)){
                         runTests();
                         vfield.setInt(null, vfield.getInt(null)+1);
                     }
                }catch(NoSuchFieldException e){
                    //ne rien faire
                }

            }
        }
	}


	public static boolean runTests() {
		Class[] classes = new Class[testsClasses.size()];
		classes = testsClasses.toArray(classes);
		
		Result result = JUnitCore.runClasses(classes);
		System.out.println("runCount = "+result.getRunCount());
		System.out.println("FailureCount = "+result.getFailureCount());
		System.out.println("");
		if(result.getFailureCount()==0)
			return true;
		else
			return false ;

		
		
    }

    /**
	*@param dirPath The base package
	* @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
	private static Class[] getClasses(String dirPath)
			throws ClassNotFoundException, IOException {
		URL[] urls = {new URL("file://"+classesToTestDir)};
	URLClassLoader classLoader = URLClassLoader.newInstance(urls);
		File dir = new File(dirPath);
		File[] resources = dir.listFiles();
		ArrayList<File> dirs = new ArrayList<File>();
		ArrayList classes = new ArrayList();
		for (File file:resources ) {
			if(file.isDirectory()) {
				dirs.add(file);
			}
			else if(file.getName().endsWith(".class")){
				classes.add(Class.forName(file.getName().substring(0, file.getName().length() - 6),true,classLoader));
			}
		}
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, directory.getName()));
		}
		return (Class[])classes.toArray(new Class[classes.size()]);
	}
	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static List findClasses(File directory, String packageName) throws ClassNotFoundException {
		List classes = new ArrayList();
		URL[] urls = new URL[0];
		try {
			urls = new URL[]{new URL("file://"+classesToTestDir)};
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		URLClassLoader classLoader = URLClassLoader.newInstance(urls);

		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6),true,classLoader));
			}
		}
		return classes;
	}
}
