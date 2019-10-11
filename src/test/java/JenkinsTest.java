import com.google.common.annotations.VisibleForTesting;
import org.junit.Assert;
import org.junit.Test;

public class JenkinsTest {

    @Test
    public void testJenkinsTrue() {
        Assert.assertEquals(5, 5);
    }

    @Test
    public void testJenkinsFalse() {
        //Test should be failed, just for test Jenkins Pipe
        Assert.assertEquals(5, 10);
    }
}
