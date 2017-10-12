package com.sishuok.architecture1.filemgr.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sishuok.architecture1.common.service.BaseService;
import com.sishuok.architecture1.filemgr.dao.FileDAO;
import com.sishuok.architecture1.filemgr.vo.FileModel;
import com.sishuok.architecture1.filemgr.vo.FileQueryModel;

@Service
@Transactional
public class FileService extends BaseService<FileModel,FileQueryModel> implements IFileService{
	private FileDAO dao = null;
	@Autowired
	private void setDao(FileDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	public FileModel getByFileName(String fileName) {
		FileQueryModel qm = new FileQueryModel();
		qm.setFileName(fileName);
		
		List<FileModel> list = dao.getByConditionPage(qm);
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		
		return null;
	}
	
}