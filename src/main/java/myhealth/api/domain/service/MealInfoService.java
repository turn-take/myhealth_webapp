package myhealth.api.domain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myhealth.api.domain.entity.MealInfoEntity;
import myhealth.api.domain.repository.MealInfoRepository;

/**
 * MealInfoAPIのサービスクラス
 * ビジネスロジックを記述する。
 */
@Service
public class MealInfoService {
	
	@Autowired MealInfoRepository repositry;
	
	//DB登録処理
	public List<MealInfoEntity> save(MealInfoEntity entity) {
		// entity 1個に対してリストを返すのは微妙な気がする。
		List<MealInfoEntity> list = new ArrayList<MealInfoEntity>();
		entity = repositry.save(entity);
		list.add(entity);
		return list;
	}
	
	// 日付でSELECT
	public List<MealInfoEntity> findAllByEatDate(Date eatDate) {
		List<MealInfoEntity> list = new ArrayList<MealInfoEntity>();
		list.addAll(repositry.findAllByEatDate(eatDate));
		return list;
	}
	
	// UPDATE処理
	public List<MealInfoEntity> update(MealInfoEntity entity) {
		List<MealInfoEntity> list = new ArrayList<MealInfoEntity>();
		entity = repositry.save(entity);
		list.add(entity);
		return list;
	}
	
	// DLETE処理
	// 引数のentityをそのままリストで返す。
	// TODO 改善の余地あり
	public List<MealInfoEntity> delete(MealInfoEntity entity) {
		List<MealInfoEntity> list = new ArrayList<MealInfoEntity>();
		repositry.deleteById(entity.getId());
		list.add(entity);
		return list;
	}
	
	public boolean isExistingId(int id) {
		return repositry.findById(id).isPresent();
	}
}
