import com.myspringmvc.core.HibernateStorageServiceImpl;
import com.myspringmvc.core.StorageService;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

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
    public void testSave(){
        byte[] bytes = new byte[]{1,2,3};
        String id = storageService.save(bytes);
        Assert.assertNotNull(id);
    }


    @Test
    public void testLoad(){
        byte[] bytes = new byte[]{1,2,3};

        String id = storageService.save(bytes);

        byte[] ret = storageService.load(id);

        Assert.assertArrayEquals(bytes, ret);
//        outputStream.
    }
    @Test
    public void testSave2(){
        byte[] bytes = new byte[]{1,2,3};
        storageService.save("id", bytes);
        byte[] ret = storageService.load("id");
        Assert.assertArrayEquals(bytes, ret);
    }
}
