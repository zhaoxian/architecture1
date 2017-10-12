package com.sishuok.architecture1.goodsmgr.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.pageutil.Page;

//@Service
public class GoodsMongoDBImpl implements GoodsMongoDBDAO{
//	@Autowired
	private MongoTemplate mt = null;
	private final String COL_NAME = "goods";
	
	public void create(GoodsModel m) {
		mt.insert(m,COL_NAME);
	}

	public void update(GoodsModel m) {
		DBCollection goods = mt.getCollection(COL_NAME);
		
		goods.update(new BasicDBObject("uuid",m.getUuid()), 
				new BasicDBObject("uuid",m.getUuid())
					.append("name", m.getName())
					.append("imgPath", m.getImgPath())
					.append("description", m.getDescription())
				);
	}

	public void delete(int uuid) {
		Criteria c = new Criteria("uuid").is(uuid);		
		mt.remove(new Query(c), COL_NAME);
	}
	public GoodsModel getByUuid(int uuid) {
		Criteria c = new Criteria("uuid").is(uuid);
		List<GoodsModel> list = mt.find(new Query(c),GoodsModel.class,COL_NAME);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public Page<GoodsModel> getByCondition(GoodsQueryModel qm){
		Criteria c = new Criteria();
		if(qm.getUuid()!=null && qm.getUuid()>0){
			c.andOperator(new Criteria("uuid").is(qm.getUuid()));
		}else if(qm.getName()!=null && qm.getName().trim().length()>0){
			c.andOperator(new Criteria("name").regex(qm.getName()));
		}else if(qm.getDescription()!=null && qm.getDescription().trim().length()>0){
			c.andOperator(new Criteria("description").regex(qm.getDescription()));
		}
		
		int count = (int)mt.count(new Query(c), COL_NAME);
		
		qm.getPage().setTotalCount(count);
		
		List<GoodsModel> list = mt.find(new Query(c).skip(qm.getPage().getStart()).limit(qm.getPage().getPageShow())
				,GoodsModel.class,COL_NAME);
		
		qm.getPage().setResult(list);
		
		return qm.getPage();
	}

	public List<Integer> getIdsByConditionPage(GoodsQueryModel gqm) {
		return null;
	}

	public List<GoodsModel> getByIds(String ids) {
		List<Integer> uuids = new ArrayList<Integer>();
		String[] ss = ids.split(",");
		
		for(String s : ss){
			uuids.add(Integer.parseInt(s));
		}
		
		Criteria c = new Criteria("uuid").in(uuids);
		
		List<GoodsModel> list = mt.find(new Query(c),GoodsModel.class,COL_NAME);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<GoodsModel> getByConditionPage(GoodsQueryModel qm) {
		return this.getByCondition(qm).getResult();
	}
}
