package com.dustdawn.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dustdawn.common.utils.Page;
import com.dustdawn.crm.pojo.BaseDict;
import com.dustdawn.crm.pojo.Customer;
import com.dustdawn.crm.pojo.QueryVo;
import com.dustdawn.crm.service.BaseDictService;
import com.dustdawn.crm.service.CustomerService;

/**
 * 客户管理
 * @author Administrator
 *
 */
@Controller
public class CustomerController {
	@Autowired
	private BaseDictService baseDictService;
	@Autowired
	private CustomerService customerService;
	
	@Value("${fromType.code}")
	private String fromTypeCode;
	@Value("${industryType.code}")
	private String industryTypeCode;
	@Value("${levelType.code}")
	private String levelTypeCode;
	
	
	
	//入口
	@RequestMapping(value = "/customer/list")
	public String list(QueryVo vo,Model model) {
		List<BaseDict> fromType = baseDictService.selectBaseDictListByCode(fromTypeCode);
		List<BaseDict> industryType = baseDictService.selectBaseDictListByCode(industryTypeCode);
		List<BaseDict> levelType = baseDictService.selectBaseDictListByCode(levelTypeCode);
		
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);
		
		
		//通过四个条件查询 查询分页对象
		Page<Customer> page = customerService.selectPageByQueryVo(vo);
		model.addAttribute("page", page);
		model.addAttribute("custName", vo.getCustName());
		model.addAttribute("custSource", vo.getCustSource());
		model.addAttribute("custIndustry", vo.getCustIndustry());
		model.addAttribute("custLevel", vo.getCustLevel());
		return "customer";
	}
	
	//弹往修改页面
	@RequestMapping(value="/customer/edit.action")
	public @ResponseBody
	Customer edit(Integer id) {
		return customerService.selectCustomerById(id);
	}
	
	//修改保存
	@RequestMapping(value="/customer/update.action")
	public @ResponseBody
	String update(Customer customer) {
		customerService.updateCustomerById(customer);
		return "OK";
	}
	
	//删除customer/delete.action
	@RequestMapping(value="customer/delete.action")
	public @ResponseBody
	String delete(Integer id) {
		customerService.deleteCustomerById(id);
		return "OK";
	}
}
