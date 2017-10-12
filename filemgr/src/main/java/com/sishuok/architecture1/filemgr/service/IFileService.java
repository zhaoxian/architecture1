package com.sishuok.architecture1.filemgr.service;


import com.sishuok.architecture1.common.service.IBaseService;
import com.sishuok.architecture1.filemgr.vo.FileModel;
import com.sishuok.architecture1.filemgr.vo.FileQueryModel;

public interface IFileService extends IBaseService<FileModel,FileQueryModel>{
	public FileModel getByFileName(String fileName);
}

