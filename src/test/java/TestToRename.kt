import org.junit.*
import util.GitUtils
/**
 * Created by Apolloch on 29/12/2016.
 *to launch tests : mvn test
 */
class TestToRename {

    @Before
    fun beforeTesting() {
        println("------------------")
        println("avant test")
    }

    @After
    fun afterTesting(){
        println("apres test")
        println("------------------")
    }

    @Test
    fun testT1() {
        println("exec Test1")
        Assert.assertEquals(1, 1 * 1)
    }

    @Test
    fun testGitUtils() {
        Assert.assertEquals(GitUtils.cloneRepo("https://github.com/Oupsla/OPL-TestRepo", "test-repo"), "C:\\Users\\Apolloch\\IdeaProjects\\OPL-RepairMultiVersion\\tmp\\opltest-repo/master/.git")
    }

    @Ignore
    @Test
    fun testT2() {
        println("exec Test2")
        Assert.assertEquals(1,2)
    }

}
