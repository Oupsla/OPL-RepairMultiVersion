import org.junit.*
import reparator.VersionSniper

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


    @Ignore
    @Test
    fun testT2() {
        println("exec Test2")
        Assert.assertEquals(1,2)
    }

}
