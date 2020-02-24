package myhealth.api.application.conotroller;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myhealth.api.APILogUtils;
import myhealth.api.application.dto.mealInfo.DeleteMealInfoDataInputDto;
import myhealth.api.application.dto.mealInfo.DeleteMealInfoDataOutputDto;
import myhealth.api.application.helper.controllhelper.MealInfoControllerHelper;
import myhealth.api.domain.entity.MealInfoEntity;
import myhealth.api.domain.service.MealInfoService;

/**
 * MealInfoAPIのDELETEリクエストを処理するコントローラー
 *
 */
@RestController
@RequestMapping("myhealth/api-v1.0/meal-info")
public class MealInfoDeleteController extends ApiContoroller<DeleteMealInfoDataInputDto, DeleteMealInfoDataOutputDto, MealInfoEntity>{

	@Autowired MealInfoControllerHelper helper;
	@Autowired MealInfoService service;
	
	@Override
	@DeleteMapping(produces = "application/json;charset=UTF-8")
	protected ResponseEntity<List<DeleteMealInfoDataOutputDto>> callContorollCRUD(
			@RequestBody List<DeleteMealInfoDataInputDto> inputList, String pathParam) {
		APILogUtils.info("----- DELETE -----");
		return super.contorollCRUD(inputList);
	}

	@Override
	protected DeleteMealInfoDataOutputDto prepareOutputDto(DeleteMealInfoDataInputDto input) {
		int requestNo = input.getRequestNo();
		DeleteMealInfoDataOutputDto outputDto = new DeleteMealInfoDataOutputDto();
		outputDto.setRequestNo(requestNo);
		return outputDto;
	}

	@Override
	protected void validateInput(DeleteMealInfoDataInputDto input) throws Exception {
		try {
			helper.validateId(input.getId());
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	protected MealInfoEntity newEntity() {
		return new MealInfoEntity();
	}

	@Override
	protected void mappingToEntity(DeleteMealInfoDataInputDto input, MealInfoEntity entity) throws Exception {
		entity.setId(input.getId());
	}

	@Override
	protected List<MealInfoEntity> executeService(MealInfoEntity entity) throws Exception {
		APILogUtils.info("DB DELETE処理実行");
		List<MealInfoEntity> list = service.delete(entity);
		return list;
	}

	@Override
	protected Consumer<MealInfoEntity> getMapper(DeleteMealInfoDataOutputDto output) {
		return e -> {}; // 削除済みなので返すものなし
	}

}
