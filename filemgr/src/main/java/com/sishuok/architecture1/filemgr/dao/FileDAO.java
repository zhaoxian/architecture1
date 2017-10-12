package com.sishuok.architecture1.filemgr.dao;

import org.springframework.stereotype.Repository;

import com.sishuok.architecture1.common.dao.BaseDAO;
import com.sishuok.architecture1.filemgr.vo.FileModel;
import com.sishuok.architecture1.filemgr.vo.FileQueryModel;

@Repository
public interface FileDAO extends BaseDAO<FileModel,FileQueryModel>{
}
