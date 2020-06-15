package corp.sap.internal.exp;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class ExpApplicationTests {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Before
	public void databasePrepare(){

		ClassPathResource classPathResource = new ClassPathResource("resources/java_reference_test.sql");
		try {
			File file = classPathResource.getFile();
			FileInputStream in = new FileInputStream(file);
			int size = in.available();
			byte[] buffer = new byte[size];
			in.read(buffer);
			in.close();
			String str = new String(buffer,"UTF-8");
			String[] strSplit=str.split(";");
			jdbcTemplate.batchUpdate(strSplit);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void contextLoads() {
	}

}
