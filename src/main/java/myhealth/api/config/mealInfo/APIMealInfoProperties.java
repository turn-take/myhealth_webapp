package myhealth.api.config.mealInfo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * application.properties内のapi.mealinfoグループを扱うクラス
 */
@Component
@ConfigurationProperties(prefix = "api.mealinfo")
public class APIMealInfoProperties {
	
	private int mealNameLength;
	private int calorieLength;

	public int getMealNameLength() {
		return mealNameLength;
	}

	public void setMealNameLength(int mealNameLength) {
		this.mealNameLength = mealNameLength;
	}

	public int getCalorieLength() {
		return calorieLength;
	}

	public void setCalorieLength(int calorieLength) {
		this.calorieLength = calorieLength;
	}
}
