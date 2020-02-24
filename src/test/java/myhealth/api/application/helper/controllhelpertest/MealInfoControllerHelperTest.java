package myhealth.api.application.helper.controllhelpertest;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import myhealth.api.application.helper.controllhelper.MealInfoControllerHelper;
import myhealth.api.config.mealInfo.APIMealInfoProperties;

@SpringBootTest
class MealInfoControllerHelperTest {

	// テスト対象クラス
	@InjectMocks MealInfoControllerHelper mealInfoControllerHelper;
	
	// モック
	@Mock APIMealInfoProperties apiMealInfoProperties;
	
	//　モックにテスト用の値を設定
	@BeforeEach
	public void setup() {
		when(apiMealInfoProperties.getMealNameLength()).thenReturn(40);
		when(apiMealInfoProperties.getCalorieLength()).thenReturn(5);
	}
	
	@Test
	public void uuuuMMdd形式の日付は例外を投げない() {
		
		//uuuuMMddの文字列
		String uuuuMMdd = "20200121";
		
		assertDoesNotThrow(() -> mealInfoControllerHelper.validateEatDate(uuuuMMdd));
	}
	
	@Test
	public void uuuuMMdd形式以外の日付は例外を投げる() {
		
		//uuuuMMdd以外の文字列
		String notuuuuMMdd = "202001211";
		
		Throwable exception = assertThrows(
	            Exception.class,
	            () -> mealInfoControllerHelper.validateEatDate(notuuuuMMdd)
	        );
	    assertEquals(exception.getMessage(), "日付はYYYYMMDDで指定してください。");
	}
	
	
	@Test
	public void mealNameの文字数の境界値テスト() {
		
		int configValue = apiMealInfoProperties.getMealNameLength();
		
		//設定値 - 1
		String underLengthString = getRandomString(configValue - 1);
		assertDoesNotThrow(() -> mealInfoControllerHelper.validateMealName(underLengthString));
		
		// 設定値
		String equalLengthString = getRandomString(configValue);
		assertDoesNotThrow(() -> mealInfoControllerHelper.validateMealName(equalLengthString));
		
		// 設定値 + 1
		String overLengthString = getRandomString(configValue + 1);
		Throwable exception = assertThrows(
				Exception.class,
				() -> mealInfoControllerHelper.validateMealName(overLengthString)
			);
		assertEquals(exception.getMessage(), "mealNameは" + configValue + "文字以内で指定してください。");
	}
	
	@Test
	public void calorieの桁数の境界値テスト() {
		
		int configValue = apiMealInfoProperties.getCalorieLength();
		
		//設定値 - 1桁
		int underLengthInt = getRandomInt(configValue - 1); 
		assertDoesNotThrow(() -> mealInfoControllerHelper.validateCalorie(underLengthInt));
		
		// 設定値の桁
		int equalLengthInt = getRandomInt(configValue); 
		assertDoesNotThrow(() -> mealInfoControllerHelper.validateCalorie(equalLengthInt));
		
		// 設定値 + 1桁
		int overLengthInt = getRandomInt(configValue + 1);
		Throwable exception = assertThrows(
				Exception.class,
				() -> mealInfoControllerHelper.validateCalorie(overLengthInt)
			);
		assertEquals(exception.getMessage(), "calorieは" + configValue + "桁以内で指定してください。");
	}
	
	/**
	 * 指定された文字数のStringを返す
	 * 1文字以上指定すること
	 * @param length
	 */
	String getRandomString(int length) {
		String randomString = "a";
		for(int i = 0; i < length - 1; i++) {
			randomString += "a";
		}
		System.out.println(randomString);
		return randomString;
	}
	
	/**
	 * 指定された桁数のintを返す
	 * @param length
	 */
	int getRandomInt(int length) {
		int rtn = (int) Math.pow(10, length - 1);
		System.out.println(length + ":" +rtn);
		return rtn;
	}
}
