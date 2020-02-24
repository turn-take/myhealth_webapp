package myhealth.api.application.dto.mealInfo;

import myhealth.api.application.dto.InputDto;

public abstract class MealInfoInputDto extends InputDto{
	protected int requestNo;
	public int getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(int requestNo) {
		this.requestNo = requestNo;
	}
}
