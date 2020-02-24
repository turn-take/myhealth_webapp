package myhealth.api.application.conotroller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import myhealth.api.APILogUtils;
import myhealth.api.application.dto.InputDto;
import myhealth.api.application.dto.OutputDto;
import myhealth.api.config.ApiConfig;

/**
 * APIのコントローラークラスの基底となるクラス
 *
 * @param <I> InputDto
 * @param <O> OutputDto
 * @param E Entity
 */

@RestController
public abstract class ApiContoroller <I extends InputDto, O extends OutputDto, E>{
	/**
	 * APIでのCRUD操作のテンプレートとなるメソッド
	 * 継承先のクラスのcallContorollCRUDメソッドから呼び出されることを前提としている
	 * @param inputList
	 * @return　レスポンス
	 */
	public ResponseEntity<List<O>> contorollCRUD(List<I> inputList){
		
		List<O> outputList =  new ArrayList<>();
		String message = null;
		
		APILogUtils.info("----- 処理開始 ----");
		for(I input : inputList) {
			APILogUtils.info(input.toString());
			O output = prepareOutputDto(input);
			try {
				// 入力チェック
				validateInput(input);
				
				// inputDto → entity マッピング
				E entity = newEntity();
				mappingToEntity(input, entity);
				
				// DB処理 
				List<E> entityList = executeService(entity);
				
				// entity → outputDto マッピング
				entityList.forEach(e -> {
					getMapper(output).accept(e);
				});
				output.setResultCode(ApiConfig.RESULT_SUCCESS);
			} catch(Exception e) {
				message = e.getMessage();
				APILogUtils.warn("処理中にエラーが発生しました。");
				APILogUtils.warn(e.getStackTrace().toString());
				APILogUtils.warn(message);
				output.setMessage(message);
				output.setResultCode(ApiConfig.RESUTL_ERROR);
			}
			outputList.add(output);
			APILogUtils.info(output.toString());
		}
		APILogUtils.info("----- 処理完了 ----");
		HttpStatus status = HttpStatus.OK;
		return new ResponseEntity<>(outputList,status);
	}
	
	//----- 以下は継承先で具象化するメソッド群 ----------------------------------------------------------
	
	/**
	 * マッピング、リクエスト、レスポンスを指定するメソッド
	 * このメソッドからcontorollCRUDメソッドを呼び出すこと
	 * @param inputList　@RequestBody
	 * @return ResponseEntity<List> 
	 */
	protected abstract ResponseEntity<List<O>> callContorollCRUD(List<I> inputList, String pathParam);
	
	protected abstract O prepareOutputDto(I input);
	
	protected abstract void validateInput(I input) throws Exception;
	
	protected abstract E newEntity();
	
	protected abstract void mappingToEntity(I input, E entity) throws Exception;
	
	protected abstract List<E> executeService(E entity) throws Exception;
	
	protected abstract Consumer<E> getMapper(O output);
}
