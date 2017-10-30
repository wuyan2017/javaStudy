package com.mobanker.auth.barrier.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mobanker.auth.barrier.entity.FakeEntity;


/**
 * @author 管黎明
 *
 *         All rights reserved.
 */
@Repository
public interface FakeDao {

	FakeEntity query(final String code);
	
	List<FakeEntity> queryList(final String code);
	
	boolean insert(final FakeEntity user);
	
	void executeSql(Map<String, String> sql1);

	void dropTable(@Param("tableName")String tableName);

}
