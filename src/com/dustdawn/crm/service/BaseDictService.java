package com.dustdawn.crm.service;

import java.util.List;

import com.dustdawn.crm.pojo.BaseDict;

public interface BaseDictService {
	//查询
	public List<BaseDict> selectBaseDictListByCode(String code);
}
