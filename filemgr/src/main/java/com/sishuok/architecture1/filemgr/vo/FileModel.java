package com.sishuok.architecture1.filemgr.vo;

import com.sishuok.architecture1.common.vo.BaseModel;


public class FileModel extends BaseModel{
	private String fileName;
	private String remotePaths;
	
	public void setFileName(String obj){
		this.fileName = obj;
	}
	public String getFileName(){
		return this.fileName;
	}
	
	public void setRemotePaths(String obj){
		this.remotePaths = obj;
	}
	public String getRemotePaths(){
		return this.remotePaths;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+"[fileName=" + this.getFileName() + ",remotePaths=" + this.getRemotePaths() + ",]";
	}	
}
