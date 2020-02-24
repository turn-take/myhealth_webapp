package myhealth.api.config.mealInfotest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import myhealth.api.config.mealInfo.APIMealInfoProperties;

@SpringBootTest
@ContextConfiguration(classes = APIMealInfoPropertiesTest.class, initializers = ConfigFileApplicationContextInitializer.class)
@EnableConfigurationProperties(APIMealInfoProperties.class)
@TestPropertySource(locations="classpath:application.properties")
class APIMealInfoPropertiesTest {

	//テスト対象
	@Autowired
	APIMealInfoProperties aPIMealInfoProperties;
	
	@Test	
	public void 設定値が返ってくるか() {
		int expected = 40;
		assertEquals(expected, aPIMealInfoProperties.getMealNameLength());
		
		expected = 5;
		assertEquals(expected, aPIMealInfoProperties.getCalorieLength());
		
	}
}