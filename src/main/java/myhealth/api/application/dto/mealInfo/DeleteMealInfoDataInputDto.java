package myhealth.api.application.dto.mealInfo;

public class DeleteMealInfoDataInputDto extends MealInfoInputDto{
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "DeleteMealInfoDataInputDto [id=" + id + ", requestNo=" + requestNo + "]";
	}
}
