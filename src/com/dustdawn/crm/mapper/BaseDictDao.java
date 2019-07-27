package com.dustdawn.crm.mapper;

import java.util.List;

import com.dustdawn.crm.pojo.BaseDict;

public interface BaseDictDao {
	
	//查询
	public List<BaseDict> selectBaseDictListByCode(String code);
}
