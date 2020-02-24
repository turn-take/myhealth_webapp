package myhealth.api.application.helper.controllhelper;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myhealth.api.config.ApiConfig;
import myhealth.api.config.mealInfo.APIMealInfoProperties;
import myhealth.api.domain.service.MealInfoService;

/**
 * MealInfoAPIControllerのヘルパークラス
 */
@Component
public class MealInfoControllerHelper {
	
	@Autowired APIMealInfoProperties apiMealInfoProperties;
	
	public void validateEatDate(String eatDate) throws Exception{
		String errMsg ="日付はYYYYMMDDで指定してください。";
		try {
			LocalDate.parse(eatDate,
				    DateTimeFormatter.ofPattern(ApiConfig.uuuuMMddPattern).withResolverStyle(ResolverStyle.STRICT));
		} catch(Exception e) {
			throw new Exception(errMsg);
		}
	}
	
	public void validateMealName(String mealName) throws Exception{
		if(mealName.length() > apiMealInfoProperties.getMealNameLength()) {
			throw new Exception("mealNameは" + apiMealInfoProperties.getMealNameLength() + "文字以内で指定してください。");
		}
	}
	
	public void validateCalorie(int calorie) throws Exception{
		if(Integer.toString(calorie).length() > apiMealInfoProperties.getCalorieLength()) {
			throw new Exception("calorieは" + apiMealInfoProperties.getCalorieLength() + "桁以内で指定してください。");
		}
	}
	
	public void validateTimeZone(byte timeZone) throws Exception{
		for(byte t : ApiConfig.TIME_ZONE) {
			if(timeZone == t) {
				return;
			}
		}
		throw new Exception("timeZoneを確認してください。");
	}
	
	@Autowired MealInfoService mealInfoService;
	
	// IDが存在しているかをチェックする
	public void validateId(int id) throws Exception{
		if(!mealInfoService.isExistingId(id)) {
			throw new Exception("IDの確認をしてください。");
		}
	}
}
