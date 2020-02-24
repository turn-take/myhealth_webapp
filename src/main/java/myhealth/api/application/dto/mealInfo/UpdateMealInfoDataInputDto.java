package myhealth.api.application.dto.mealInfo;

public class UpdateMealInfoDataInputDto extends MealInfoInputDto{
	private Integer id;
	private String eatDateStr;
	private String mealName;
	private int calorie;
	private byte timeZone;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEatDateStr() {
		return eatDateStr;
	}
	public void setEatDateStr(String eatDateStr) {
		this.eatDateStr = eatDateStr;
	}
	public String getMealName() {
		return mealName;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	public int getCalorie() {
		return calorie;
	}
	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}
	public byte getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(byte timeZone) {
		this.timeZone = timeZone;
	}
	@Override
	public String toString() {
		return "UpdateMealInfoDataInputDto [id=" + id + ", eatDateStr=" + eatDateStr + ", mealName=" + mealName
				+ ", calorie=" + calorie + ", timeZone=" + timeZone + "]";
	}
}
