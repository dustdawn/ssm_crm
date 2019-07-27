package com.dustdawn.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dustdawn.common.utils.Page;
import com.dustdawn.crm.mapper.CustomerDao;
import com.dustdawn.crm.pojo.Customer;
import com.dustdawn.crm.pojo.QueryVo;

/**
 * 客户管理
 * @author Administrator
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;
	//根据条件查询分页对象
	public Page<Customer> selectPageByQueryVo(QueryVo vo){
		
		Page<Customer> page = new Page<Customer>();
		vo.setSize(5);
		page.setSize(5);
		if(null != vo) {
			if(null != vo.getPage()) {
				page.setPage(vo.getPage());
				vo.setStartRow((vo.getPage()-1)*vo.getSize());
			}
			//处理空条件
			if(null != vo.getCustName() && !"".equals(vo.getCustName().trim())) {
				vo.setCustName(vo.getCustName().trim());
			}
			if(null != vo.getCustSource() && !"".equals(vo.getCustSource().trim())) {
				vo.setCustSource(vo.getCustSource().trim());
			}
			if(null != vo.getCustIndustry() && !"".equals(vo.getCustIndustry().trim())) {
				vo.setCustIndustry(vo.getCustIndustry().trim());
			}
			if(null != vo.getCustLevel() && !"".equals(vo.getCustLevel().trim())) {
				vo.setCustLevel(vo.getCustLevel().trim());
			}
			//总条数
			page.setTotal(customerDao.customerCountByQueryVo(vo));
			//查询结果集
			page.setRows(customerDao.selectCustomerListByQueryVo(vo));
		}
		
		return page;
	}
	
	//通过id查询客户
	public Customer selectCustomerById(Integer id) {
		return customerDao.selectCustomerById(id);
	}
	
	//通过id修改客户
	public void updateCustomerById(Customer customer) {
		customerDao.updateCustomerById(customer);
	}
	
	//通过id删除客户
	public void deleteCustomerById(Integer id){
		customerDao.deleteCustomerById(id);
	}
}
