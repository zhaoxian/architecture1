package com.sishuok.architecture1.goodsmgr.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.pageutil.Page;

//@Service
//@Primary
public class MemcachedImpl implements GoodsDAO{
	private final String MEM_PRE = "Goods";
//	@Autowired
	private MemCachedClient mcc;
//	@Autowired
	private GoodsMongoDBDAO dao = null;

	public void create(GoodsModel m) {
		dao.create(m);
	}

	public void update(GoodsModel m) {
		dao.update(m);
		//查询缓存里面是否有这条数据，有的话，需更新缓存
		Object obj = mcc.get(MEM_PRE+m.getUuid());
		if(obj!=null){
			mcc.set(MEM_PRE+m.getUuid(), m);
		}
	}

	public void delete(int uuid) {
		dao.delete(uuid);
		//查询缓存里面是否有这条数据，有的话，需从缓存中删除
		Object obj = mcc.get(MEM_PRE+uuid);
		if(obj!=null){
			mcc.delete(MEM_PRE+uuid);
		}
	}

	public GoodsModel getByUuid(int uuid) {
		GoodsModel gm = null;
		//1：查缓存，如果有就从缓存取值并返回
		Object obj = mcc.get(MEM_PRE+uuid);
		if(obj!=null){
			gm = (GoodsModel)obj;
			return gm;
		}
		//2：缓存没有，从db中取
		gm = dao.getByUuid(uuid);
		//3：把这个数据设置到缓存中
		mcc.set(MEM_PRE+uuid,gm);
		
		return gm;
	}

	public List<GoodsModel> getByConditionPage(GoodsQueryModel qm) {
		
//		//1:根据条件去查询出所有的 ids
//		List<Integer> ids = dao.getIdsByConditionPage(qm);
//		//2：在内存中找 这些id对应的对象
//		List<GoodsModel> listGm1 = new ArrayList<GoodsModel>();
////		String noIds = "";
//		List<Integer> noIds = new ArrayList<Integer>();
//		for(Integer id : ids){
//			Object obj = mcc.get(MEM_PRE+id);
//			if(obj!=null){
//				GoodsModel gm = (GoodsModel)obj;
//				listGm1.add(gm);
//			}else{
////				noIds += id+",";
//				noIds.add(id);
//			}
//		}
//		System.out.println("noIds==="+noIds);
//		//3：内存中找不到对应对象的id，从数据库里面获取，并设置到缓存中
////		List<GoodsModel> listGm2 = null; 
////		if(noIds.trim().length() > 0){
////			noIds = noIds.substring(noIds.length() - 1);
////			listGm2 = mapper.getByIds(noIds);
////			listGm1.addAll(listGm2);
//		if(noIds.size()>0){
//			for(Integer id : noIds){
//				GoodsModel gm = dao.getByUuid(id);
//				
//				mcc.set(MEM_PRE+id,gm);
//				
//				listGm1.add(gm);
//			}
//		}
		return dao.getByConditionPage(qm);
	}

//	public Page<GoodsModel> getByCondition(GoodsQueryModel qm) {
//		return dao.getByCondition(qm);
//	}
}
