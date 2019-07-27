package com.dustdawn.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dustdawn.crm.mapper.BaseDictDao;
import com.dustdawn.crm.pojo.BaseDict;

@Service
//@Transactional
public class BaseDictServiceImpl implements BaseDictService {
	@Autowired
	private BaseDictDao baseDictDao;
	//查询
	public List<BaseDict> selectBaseDictListByCode(String code){
		return baseDictDao.selectBaseDictListByCode(code);
	}
}
