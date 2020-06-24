package corp.sap.internal.exp;

import corp.sap.internal.exp.service.DataBaseOperationService;
import corp.sap.internal.exp.service.DataPreparationService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	@Autowired
	DataBaseOperationService dataBaseOperationService;
	@Autowired
	DataPreparationService dataPreparationService;
	@Value("${test.data.scale}")
	int len;

	@Before
	public void databasePrepare(){
		dataBaseOperationService.setDataBase("resources/java_reference_test.sql");

	}

	@Test
	void contextLoads() {
	}

}
