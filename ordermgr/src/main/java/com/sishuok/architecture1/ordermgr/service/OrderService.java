package com.sishuok.architecture1.ordermgr.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sishuok.architecture1.cartmgr.service.ICartService;
import com.sishuok.architecture1.common.service.BaseService;
import com.sishuok.architecture1.ordermgr.dao.OrderDAO;
import com.sishuok.architecture1.ordermgr.queue.QueueSender;
import com.sishuok.architecture1.ordermgr.vo.OrderModel;
import com.sishuok.architecture1.ordermgr.vo.OrderQueryModel;
import com.sishuok.architecture1.storemgr.service.IStoreService;

@Service
@Transactional
public class OrderService extends BaseService<OrderModel,OrderQueryModel> implements IOrderService{
	private OrderDAO dao = null;
	@Autowired
	private ICartService ics = null;
	@Autowired
	private IOrderDetailService iods = null;
	@Autowired
	private IStoreService iss = null;
	@Autowired
	private void setDao(OrderDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	public void order(int customerUuid) {
		//消息的 生产者
		for(int i=0;i<100;i++){
			QueueSender.sendMsg(customerUuid);
		}
		
		
//			//消息的消费者来完成如下功能
//			CartQueryModel cqm = new CartQueryModel();
//			cqm.getPage().setPageShow(1000);
//			cqm.setCustomerUuid(customerUuid);
//			
//			Page<CartModel>  page = ics.getByConditionPage(cqm);
//			//2:
//			float totalMoney = 0.0f;
//			for(CartModel cm : page.getResult()){
//				totalMoney += 10;
//			}
//			
//			OrderModel order = new OrderModel();
//			order.setCustomerUuid(customerUuid);
//			order.setOrderTime(DateFormatHelper.long2str(System.currentTimeMillis()));
//			order.setSaveMoney(0.0F);
//			order.setTotalMoney(totalMoney);
//			order.setState(1);
//	
//			create(order);
//			
//			OrderQueryModel oqm = new OrderQueryModel();
//			oqm.setOrderTime(order.getOrderTime());
//			
//			Page<OrderModel>  orderPage = getByConditionPage(oqm);
//			order = orderPage.getResult().get(0);
//			
//			//3:
//			for(CartModel cm : page.getResult()){
//				OrderDetailModel odm = new OrderDetailModel();
//				odm.setGoodsUuid(cm.getGoodsUuid());
//				odm.setOrderNum(cm.getBuyNum());
//				odm.setPrice(10.0f);
//				odm.setMoney(odm.getPrice() * odm.getOrderNum());
//				odm.setSaveMoney(0.0f);
//				odm.setOrderUuid(order.getUuid());
//				
//				iods.create(odm);
//				//4:
//				StoreModel storeModel = iss.getByGoodsUuid(cm.getGoodsUuid());
//				storeModel.setStoreNum(storeModel.getStoreNum() - odm.getOrderNum());
//				iss.update(storeModel);
//				
//				//5:
//	//			ics.delete(cm.getUuid());
//			}	
//		
//		long a2 = new Date().getTime();
//		System.out.println("NONO send one msg time==="+(a2-a));
	}
	
}