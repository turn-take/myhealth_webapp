package myhealth.api.application.dto.mealInfo;

import myhealth.api.application.dto.OutputDto;

public abstract class MealInfoOutputDto extends OutputDto{
	
	protected int requestNo;
	
	public int getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(int requestNo) {
		this.requestNo = requestNo;
	}
}
