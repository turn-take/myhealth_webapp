package myhealth.api.application.conotroller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myhealth.api.APILogUtils;
import myhealth.api.application.dto.mealInfo.CreateMealInfoDataInputDto;
import myhealth.api.application.dto.mealInfo.CreateMealInfoDataOutputDto;
import myhealth.api.application.helper.controllhelper.MealInfoControllerHelper;
import myhealth.api.config.ApiConfig;
import myhealth.api.domain.entity.MealInfoEntity;
import myhealth.api.domain.service.MealInfoService;

/**
 * MealInfoAPIのPOST(CREATE)リクエストを処理するコントローラー
 *
 */
@RestController
@RequestMapping("myhealth/api-v1.0/meal-info")
public class MealInfoCreateContoroller extends ApiContoroller<CreateMealInfoDataInputDto, CreateMealInfoDataOutputDto, MealInfoEntity>{

	@Autowired MealInfoControllerHelper helper;
	@Autowired MealInfoService service;
	
	@Override
	@PostMapping(produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<CreateMealInfoDataOutputDto>> callContorollCRUD(@RequestBody List<CreateMealInfoDataInputDto> inputList,
			@PathVariable(required = false) String pathParam) {
		APILogUtils.info("----- CREATE -----");
		return super.contorollCRUD(inputList);
	}
	
	@Override
	public CreateMealInfoDataOutputDto prepareOutputDto(CreateMealInfoDataInputDto input) {
		int requestNo = input.getRequestNo();
		CreateMealInfoDataOutputDto outputDto = new CreateMealInfoDataOutputDto();
		outputDto.setRequestNo(requestNo);
		return outputDto;
	}

	@Override
	public void validateInput(CreateMealInfoDataInputDto input) throws Exception{
		try {
			helper.validateEatDate(input.getEatDateStr());
			helper.validateMealName(input.getMealName());
			helper.validateCalorie(input.getCalorie());
			helper.validateTimeZone(input.getTimeZone());
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public void mappingToEntity(CreateMealInfoDataInputDto input, MealInfoEntity entity) throws Exception{
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
	public List<MealInfoEntity> executeService(MealInfoEntity entity) {
		APILogUtils.info("DB INSERT処理実行");
		List<MealInfoEntity> list = service.save(entity);
		return list;
	}

	@Override
	public Consumer<MealInfoEntity> getMapper(CreateMealInfoDataOutputDto output) {
		return e -> {
			output.setId(e.getId());
		};
	}

	@Override
	public MealInfoEntity newEntity() {
		return new MealInfoEntity();
	}
}
