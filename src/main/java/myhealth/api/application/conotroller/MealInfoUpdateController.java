package myhealth.api.application.conotroller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myhealth.api.APILogUtils;
import myhealth.api.application.dto.mealInfo.UpdateMealInfoDataInputDto;
import myhealth.api.application.dto.mealInfo.UpdateMealInfoDataOutputDto;
import myhealth.api.application.helper.controllhelper.MealInfoControllerHelper;
import myhealth.api.config.ApiConfig;
import myhealth.api.domain.entity.MealInfoEntity;
import myhealth.api.domain.service.MealInfoService;

/**
 * MealInfoAPIのPUT(UPDATE)リクエストを処理するコントローラー
 *
 */
@RestController
@RequestMapping("myhealth/api-v1.0/meal-info")
public class MealInfoUpdateController extends ApiContoroller<UpdateMealInfoDataInputDto, UpdateMealInfoDataOutputDto, MealInfoEntity>{

	@Autowired MealInfoControllerHelper helper;
	@Autowired MealInfoService service;
	
	private SimpleDateFormat sdf = new SimpleDateFormat(ApiConfig.yyyyMMddPattern);
	
	@Override
	@PutMapping(produces = "application/json;charset=UTF-8")
	protected ResponseEntity<List<UpdateMealInfoDataOutputDto>> callContorollCRUD(
			@RequestBody List<UpdateMealInfoDataInputDto> inputList, String pathParam) {
		APILogUtils.info("----- UPDATE -----");
		return super.contorollCRUD(inputList);
	}

	@Override
	protected UpdateMealInfoDataOutputDto prepareOutputDto(UpdateMealInfoDataInputDto input) {
		int requestNo = input.getRequestNo();
		UpdateMealInfoDataOutputDto outputDto = new UpdateMealInfoDataOutputDto();
		outputDto.setRequestNo(requestNo);
		return outputDto;
	}

	@Override
	protected void validateInput(UpdateMealInfoDataInputDto input) throws Exception {
		try {
			helper.validateId(input.getId());
			helper.validateEatDate(input.getEatDateStr());
			helper.validateMealName(input.getMealName());
			helper.validateCalorie(input.getCalorie());
			helper.validateTimeZone(input.getTimeZone());
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	protected MealInfoEntity newEntity() {
		return new MealInfoEntity();
	}

	@Override
	protected void mappingToEntity(UpdateMealInfoDataInputDto input, MealInfoEntity entity) throws Exception {
		entity.setId(input.getId());
		entity.setMeal(input.getMealName());
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(ApiConfig.yyyyMMddPattern);
			entity.setEatDate(sdf.parse(input.getEatDateStr()));
		} catch (ParseException e) {
			throw e;
		}
		entity.setCalorie(input.getCalorie());
		entity.setTimeZone(input.getTimeZone());
		
	}

	@Override
	protected List<MealInfoEntity> executeService(MealInfoEntity entity) throws Exception {
		APILogUtils.info("DB UPDATE処理実行");
		List<MealInfoEntity> list = service.update(entity);
		return list;
	}

	@Override
	protected Consumer<MealInfoEntity> getMapper(UpdateMealInfoDataOutputDto output) {
		return e -> {
			output.setId(e.getId());
			output.setMealName(e.getMeal());
			output.setCalorie(e.getCalorie());
			output.setEatDateStr(sdf.format(e.getEatDate()));
			output.setTimeZone(e.getTimeZone());
		};
	}

}
