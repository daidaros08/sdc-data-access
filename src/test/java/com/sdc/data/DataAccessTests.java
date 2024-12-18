package com.sdc.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.sdc.data.configuration.DataAccessTestsConfiguration;

@ContextConfiguration(classes = DataAccessTestsConfiguration.class)
@SpringBootTest
class DataAccessTests {

	@Test
	void contextLoads() {

	}

}
