@file:JvmName(name = "VersionComposer")
package reparator

import org.json.JSONObject
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

    private val conf_file: String
        get() = "./app_conf.json"

    fun compileSourceCode() {
        var configContent = ""
        File(conf_file).forEachLine { configContent+=it }
        var junitJar = JSONObject(configContent).getString("junit_jar")
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
