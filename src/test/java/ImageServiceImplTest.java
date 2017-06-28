import com.myspringmvc.core.ImageService;
import com.myspringmvc.core.ImageServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by sanek9 on 28.06.17.
 */
@DirtiesContext
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:dispatcher-servlet.xml", "classpath:mysql-datasource.xml",
        "classpath:applicationContext.xml" })

public class ImageServiceImplTest {


    @Autowired
    public ImageService imageService;

    @Test
    public void testSaveImage() throws IOException {
        byte[] bytes = new byte[]{1,2,3};
        String id = imageService.saveImage(bytes);
        Assert.assertNotNull(id);
    }
    @Test
    public void testLoadImage() throws IOException {
        byte[] bytes = new byte[]{1,2,3};
        String id = imageService.saveImage(bytes);
        Assert.assertNotNull(id);

        imageService.loadImage(id);
    }
}
