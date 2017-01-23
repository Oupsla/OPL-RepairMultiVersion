import org.junit.*
import util.GitUtils
/**
 * Created by Apolloch on 29/12/2016.
 *to launch tests : mvn test
 */
class TestGitUtils {

    @Test
    fun testCloneRepository() {
        Assert.assertEquals(GitUtils.cloneRepo("https://github.com/Oupsla/OPL-TestRepo", "test-repo"), "C:\\Users\\Apolloch\\IdeaProjects\\OPL-RepairMultiVersion\\tmp\\opltest-repo/master/.git")
    }

}
