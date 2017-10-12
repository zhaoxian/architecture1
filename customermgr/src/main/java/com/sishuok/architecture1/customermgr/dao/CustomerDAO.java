package com.sishuok.architecture1.customermgr.dao;

import org.springframework.stereotype.Repository;

import com.sishuok.architecture1.common.dao.BaseDAO;
import com.sishuok.architecture1.customermgr.vo.CustomerModel;
import com.sishuok.architecture1.customermgr.vo.CustomerQueryModel;

@Repository
public interface CustomerDAO extends BaseDAO<CustomerModel,CustomerQueryModel>{
	public CustomerModel getByCustomerId(String customerId);
}
