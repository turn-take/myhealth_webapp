package myhealth.api.domain.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myhealth.api.domain.entity.MealInfoEntity;

/**
 * MealInfoAPIのリポジトリクラス
 * MealInfoServiceクラスからのみ使われること
 */
@Repository
public interface MealInfoRepository extends JpaRepository<MealInfoEntity, Integer>{
	
	// MealInfoテーブルから指定した日付のデータを取得する。
	public List<MealInfoEntity> findAllByEatDate (Date eatDate);
	
}
