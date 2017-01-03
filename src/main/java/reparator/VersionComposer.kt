@file:JvmName(name = "VersionComposer")
package reparator

import java.io.File
import java.util.*
import javax.tools.ToolProvider

/**
 * Created by Apolloch on 31/12/2016.
 */
    private val sourceRoot: String
        get() = "./spooned/main/"

    private val testSourceRoot: String
        get() = "./tmp/oplOPL-TestRepo/master/src/test/"

    private val byteCodeRoot: String
        get() = "./spooned/bin/"

    val junitJar: String
        get() = "C:/Users/Apolloch/.m2/repository/junit/junit/4.12/junit-4.12.jar"

    fun compileSourceCode() {

        val sourceDir = File(sourceRoot)
        val testSourceDir = File(testSourceRoot)
        val byteCodeDir = File(byteCodeRoot)
        if (!byteCodeDir.exists())
            byteCodeDir.mkdir()
        val compiler = ToolProvider.getSystemJavaCompiler()
        var files = arrayListOf<File>()
        sourceDir.list().forEach {
            if (it.endsWith(".java")) {
                files.add(File("${sourceDir.path}/$it"))
            }
        }
        testSourceDir.list().forEach {
            if (it.endsWith(".java")) {
                files.add(File("${testSourceDir.path}/$it"))
            }
        }

        val optionList = ArrayList<String>()
        optionList.addAll(Arrays.asList("-classpath", "$byteCodeRoot;$junitJar"))
        optionList.addAll(Arrays.asList("-d", byteCodeRoot))
        val sjfm = compiler.getStandardFileManager(null, null, null)
        val fileObjects = sjfm.getJavaFileObjectsFromFiles(files)
        val task = compiler.getTask(null, null, null, optionList, null, fileObjects)
        task.call()
        sjfm.close()

    }

    fun main(args: Array<String>) {
        compileSourceCode()
    }
