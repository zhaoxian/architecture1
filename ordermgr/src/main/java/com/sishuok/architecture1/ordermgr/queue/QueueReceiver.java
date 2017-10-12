package com.sishuok.architecture1.ordermgr.queue;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sishuok.architecture1.cartmgr.service.ICartService;
import com.sishuok.architecture1.cartmgr.vo.CartModel;
import com.sishuok.architecture1.cartmgr.vo.CartQueryModel;
import com.sishuok.architecture1.ordermgr.service.IOrderDetailService;
import com.sishuok.architecture1.ordermgr.service.IOrderService;
import com.sishuok.architecture1.ordermgr.vo.OrderDetailModel;
import com.sishuok.architecture1.ordermgr.vo.OrderModel;
import com.sishuok.architecture1.ordermgr.vo.OrderQueryModel;
import com.sishuok.architecture1.storemgr.service.IStoreService;
import com.sishuok.architecture1.storemgr.vo.StoreModel;
import com.sishuok.pageutil.Page;
import com.sishuok.util.format.DateFormatHelper;

@Service
public class QueueReceiver implements ServletContextListener{
	@Autowired
	private ICartService ics = null;
	@Autowired
	private IOrderService ios = null;
	@Autowired
	private IOrderDetailService iods = null;
	@Autowired
	private IStoreService iss = null;

	public void acceptMsg() {
		Connection connection = null;
		final Session session;
		try {
			ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(
					"tcp://192.168.1.106:61676");
			
			for(int i=0;i<1;i++){
				new MyT(ics,ios,iods,iss,cf).start();
			}

//			connection = cf.createConnection();
//			connection.start();
//
//			session = connection.createSession(Boolean.TRUE,
//					Session.AUTO_ACKNOWLEDGE);
//			Destination destination = session.createQueue("order-queue");
//
//			ActiveMQMessageConsumer consumer = (ActiveMQMessageConsumer) session
//					.createConsumer(destination);
//
//			consumer.setMessageListener(new MessageListener() {
//
//				public void onMessage(Message msg) {
//					try {
//						TextMessage txtMsg = (TextMessage) msg;
//System.out.println("now get msgggggggggggggggggggggggggggg");
//						int customerUuid = Integer.parseInt(txtMsg.getText());
//						
//						session.commit();
//
//						CartQueryModel cqm = new CartQueryModel();
//						cqm.getPage().setPageShow(1000);
//						cqm.setCustomerUuid(customerUuid);
//
//						Page<CartModel> page = ics.getByConditionPage(cqm);
//						// 2:
//						float totalMoney = 0.0f;
//						for (CartModel cm : page.getResult()) {
//							totalMoney += 10;
//						}
//
//						OrderModel order = new OrderModel();
//						order.setCustomerUuid(customerUuid);
//						order.setOrderTime(DateFormatHelper.long2str(System
//								.currentTimeMillis()));
//						order.setSaveMoney(0.0F);
//						order.setTotalMoney(totalMoney);
//						order.setState(1);
//
//						ios.create(order);
//
//						OrderQueryModel oqm = new OrderQueryModel();
//						oqm.setOrderTime(order.getOrderTime());
//
//						Page<OrderModel> orderPage = ios
//								.getByConditionPage(oqm);
//						order = orderPage.getResult().get(0);
//
//						// 3:
//						for (CartModel cm : page.getResult()) {
//							OrderDetailModel odm = new OrderDetailModel();
//							odm.setGoodsUuid(cm.getGoodsUuid());
//							odm.setOrderNum(cm.getBuyNum());
//							odm.setPrice(10.0f);
//							odm.setMoney(odm.getPrice() * odm.getOrderNum());
//							odm.setSaveMoney(0.0f);
//							odm.setOrderUuid(order.getUuid());
//
//							iods.create(odm);
//							// 4:
//							StoreModel storeModel = iss.getByGoodsUuid(cm
//									.getGoodsUuid());
//							storeModel.setStoreNum(storeModel.getStoreNum()
//									- odm.getOrderNum());
//							iss.update(storeModel);
//
//							// 5:
//							ics.delete(cm.getUuid());
//						}
//					} catch (Exception err) {
//						err.printStackTrace();
//					}
//				}
//			});

//			session.close();
		} catch (Exception err) {
			err.printStackTrace();
		}finally{
//			try {
//			connection.close();
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		QueueReceiver qr = (QueueReceiver)ctx.getBean("queueReceiver");
		qr.acceptMsg();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}

class MyT extends Thread{
	private ICartService ics = null;
	private IOrderService ios = null;
	private IOrderDetailService iods = null;
	private IStoreService iss = null;
	private ActiveMQConnectionFactory cf = null;
	public MyT(ICartService ics, IOrderService ios, IOrderDetailService iods,
			IStoreService iss, ActiveMQConnectionFactory cf) {
		super();
		this.ics = ics;
		this.ios = ios;
		this.iods = iods;
		this.iss = iss;
		this.cf = cf;
	}	
	
	public void run(){
		Connection connection = null;
		final Session session;
		try {
			connection = cf.createConnection();
			connection.start();

			session = connection.createSession(Boolean.TRUE,
					Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("order-queue");

			ActiveMQMessageConsumer consumer = (ActiveMQMessageConsumer) session
					.createConsumer(destination);

			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message msg) {
					try {
						TextMessage txtMsg = (TextMessage) msg;
						int customerUuid = Integer.parseInt(txtMsg.getText());
						
						session.commit();

						CartQueryModel cqm = new CartQueryModel();
						cqm.getPage().setPageShow(1000);
						cqm.setCustomerUuid(customerUuid);

						Page<CartModel> page = ics.getByConditionPage(cqm);
						// 2:
						float totalMoney = 0.0f;
						for (CartModel cm : page.getResult()) {
							totalMoney += 10;
						}

						OrderModel order = new OrderModel();
						order.setCustomerUuid(customerUuid);
						order.setOrderTime(DateFormatHelper.long2str(System
								.currentTimeMillis()));
						order.setSaveMoney(0.0F);
						order.setTotalMoney(totalMoney);
						order.setState(1);

						ios.create(order);

						OrderQueryModel oqm = new OrderQueryModel();
						oqm.setOrderTime(order.getOrderTime());

						Page<OrderModel> orderPage = ios
								.getByConditionPage(oqm);
						order = orderPage.getResult().get(0);

						// 3:
						for (CartModel cm : page.getResult()) {
							OrderDetailModel odm = new OrderDetailModel();
							odm.setGoodsUuid(cm.getGoodsUuid());
							odm.setOrderNum(cm.getBuyNum());
							odm.setPrice(10.0f);
							odm.setMoney(odm.getPrice() * odm.getOrderNum());
							odm.setSaveMoney(0.0f);
							odm.setOrderUuid(order.getUuid());

							iods.create(odm);
							// 4:
							synchronized (iss) {
								StoreModel storeModel = iss.getByGoodsUuid(cm
										.getGoodsUuid());
								storeModel.setStoreNum(storeModel.getStoreNum()
										- odm.getOrderNum());
								iss.update(storeModel);
							}
							// 5:
//							ics.delete(cm.getUuid());
						}
					} catch (Exception err) {
						err.printStackTrace();
					}
				}
			});

		} catch (Exception err) {
			err.printStackTrace();
		}
	}
}
