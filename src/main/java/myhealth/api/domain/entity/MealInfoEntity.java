package myhealth.api.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="meal_info")
public class MealInfoEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "eat_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date eatDate;
	
	@Column(length = 40, nullable = false)
	private String meal;
	
	@Column(length = 5, nullable = false)
	private int calorie;
	
	@Column(name = "time_zone", length = 2, nullable = false)
	private byte timeZone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEatDate() {
		return eatDate;
	}

	public void setEatDate(Date eatDate) {
		this.eatDate = eatDate;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
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
		return "MealInfo [id=" + id + ", eatDate=" + eatDate + ", meal=" + meal + ", calorie=" + calorie
				+ ", time_zone=" + timeZone + "]";
	}
}
