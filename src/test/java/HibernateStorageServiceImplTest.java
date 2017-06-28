import com.myspringmvc.core.HibernateStorageServiceImpl;
import com.myspringmvc.core.StorageService;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by sanek9 on 27.06.17.
 */
@DirtiesContext
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:dispatcher-servlet.xml", "classpath:mysql-datasource.xml",
        "classpath:applicationContext.xml" })
//@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class HibernateStorageServiceImplTest {
////    @PersistenceContext
//    protected static EntityManagerFactory emf;
//
//    @BeforeClass
//    public static void createEntityManagerFactory() {
//        emf = Persistence.createEntityManagerFactory("entityManagerFactory");
//    }

    @Autowired
    private StorageService storageService;
    @Test
    public void testSave() throws IOException {
        byte[] bytes = new byte[]{1,2,3};
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        String id = storageService.save(bis);
        Assert.assertNotNull(id);
    }


    @Test
    public void testLoad() throws IOException {
        byte[] bytes = new byte[]{1,2,3};
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        String id = storageService.save(bis);
        Assert.assertNotNull(id);

        InputStream inputStream = storageService.load(id);
        Assert.assertArrayEquals(bytes, IOUtils.toByteArray(inputStream));
//        outputStream.
    }
    @Test
    public void testSave2() throws IOException {
        byte[] bytes = new byte[]{1,2,3};
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        storageService.save("id",bis);

        InputStream inputStream = storageService.load("id");
        Assert.assertArrayEquals(bytes, IOUtils.toByteArray(inputStream));
    }
}
