package myhealth.api.application.dto.mealInfo;

public class CreateMealInfoDataOutputDto extends MealInfoOutputDto{
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "CreateMealInfoDataOutputDto [id=" + id + ", requestNo=" + requestNo + ", resultCode="
				+ resultCode + ", message=" + message + "]";
	}

}
