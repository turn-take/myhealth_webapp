package myhealth.api.application.conotroller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myhealth.api.APILogUtils;
import myhealth.api.application.dto.mealInfo.ReadMealInfoDataInputDto;
import myhealth.api.application.dto.mealInfo.ReadMealInfoDataOutputDto;
import myhealth.api.application.helper.controllhelper.MealInfoControllerHelper;
import myhealth.api.config.ApiConfig;
import myhealth.api.domain.entity.MealInfoEntity;
import myhealth.api.domain.service.MealInfoService;

/**
 * MealInfoAPIのDELETEリクエストを処理するコントローラー
 *
 */
@RestController
@RequestMapping("myhealth/api-v1.0/meal-info")
public class MealInfoReadByDateContoroller extends ApiContoroller<ReadMealInfoDataInputDto, ReadMealInfoDataOutputDto, MealInfoEntity>{
	
	@Autowired MealInfoControllerHelper helper;
	@Autowired MealInfoService service;
	
	private String eatDateStr;
	
	private SimpleDateFormat sdf = new SimpleDateFormat(ApiConfig.yyyyMMddPattern);

	
	@Override
	@GetMapping(produces = "application/json;charset=UTF-8", value = "/{eatDateStr}")
	protected ResponseEntity<List<ReadMealInfoDataOutputDto>> callContorollCRUD(
			@RequestBody List<ReadMealInfoDataInputDto> inputList,
			@PathVariable(name = "eatDateStr",required = false) String eatDateStr) {
		APILogUtils.info("----- READ -----");
		this.eatDateStr = eatDateStr;
		return super.contorollCRUD(inputList);
	}

	@Override
	protected ReadMealInfoDataOutputDto prepareOutputDto(ReadMealInfoDataInputDto input) {
		ReadMealInfoDataOutputDto outputDto = new ReadMealInfoDataOutputDto();
		int requestNo = input.getRequestNo();
		outputDto.setRequestNo(requestNo);
		outputDto.setDataList(new ArrayList<ReadMealInfoDataOutputDto.ReadMealInfoDataOutputDtoUnit>());
		return outputDto;
	}

	@Override
	protected void validateInput(ReadMealInfoDataInputDto input) throws Exception {
		try {
			helper.validateEatDate(this.eatDateStr);
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	protected MealInfoEntity newEntity() {
		return new MealInfoEntity();
	}

	@Override
	protected void mappingToEntity(ReadMealInfoDataInputDto input, MealInfoEntity entity) throws Exception {
		
	}

	@Override
	protected List<MealInfoEntity> executeService(MealInfoEntity entity) throws Exception{
		Date eatDate;
		try {
			eatDate = sdf.parse(eatDateStr);
		} catch(Exception e) {
			// こないはず
			throw e;
		}
		APILogUtils.info("DB SELECT処理実行");
		List<MealInfoEntity> list = service.findAllByEatDate(eatDate);
		return list;
	}

	@Override
	protected Consumer<MealInfoEntity> getMapper(ReadMealInfoDataOutputDto output) {
		
		return e -> {
			ReadMealInfoDataOutputDto.ReadMealInfoDataOutputDtoUnit unit = 
					output.new ReadMealInfoDataOutputDtoUnit();
			unit.setId(e.getId());
			unit.setEatDateStr(sdf.format(e.getEatDate()));
			unit.setCalorie(e.getCalorie());
			unit.setMealName(e.getMeal());
			unit.setTimeZone(e.getTimeZone());
			output.getDataList().add(unit);
		};
	}
}
