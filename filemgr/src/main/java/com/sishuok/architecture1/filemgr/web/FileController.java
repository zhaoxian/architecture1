package com.sishuok.architecture1.filemgr.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sishuok.architecture1.filemgr.service.IFileService;
import com.sishuok.architecture1.filemgr.vo.FileModel;
import com.sishuok.architecture1.filemgr.vo.FileQueryModel;
import com.sishuok.pageutil.Page;
import com.sishuok.util.json.JsonHelper;

import fm.last.moji.MojiFile;
import fm.last.moji.spring.SpringMojiBean;

@Controller
@RequestMapping(value="/file")
public class FileController {
	@Autowired
	private IFileService iservice = null;
	@Autowired
	private SpringMojiBean moji = null;

	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	  public String upload(            
	          HttpServletRequest request, @RequestParam(value = "myFile", required = false) MultipartFile[] files) {
	          try {
	          	for(int i=0;i<files.length;i++){
	          		//1：取得文件名
	          		String fileName = FileUploadUtils.upload(request, files[i]);
	          		//2：根据文件名去获取到对应的FileModel
	          		FileModel fm = getOneFileModel(fileName);
	          		
	          		//3：把文件流式上传到 mogilefs里面
	          		//4：获得 远程paths
	          		List<URL> list = this.uploadToMogilefs(fm.getUuid(), files[i]);
	          		
	          		//5：把这些paths组织一下，设置到remotepaths里面
	          		
	          		fm.setRemotePaths(""+list.get(0));
	          		
	          		iservice.update(fm);
	          	}
			} catch (Exception e) {
				e.printStackTrace();
			}
	          return "success";
	  }
	private List<URL> uploadToMogilefs(int uuid , MultipartFile file)throws Exception{
		MojiFile mf = moji.getFile("MyFileKey"+uuid);
		
		OutputStream out = null;
		InputStream in = null;
		try {
			out = mf.getOutputStream();
			in = file.getInputStream();
			
			byte[] bs = new byte[128];
			while( in.read(bs) != -1){
				String s = new String(bs,"utf-8");
				
				out.write(s.getBytes());
				
				bs = new byte[128];
			}
			
			out.flush();
		} finally {
			in.close();
			out.close();
		}
		return mf.getPaths();
	}
	private FileModel getOneFileModel(String fileName){
		//2：根据文件名去获取到对应的FileModel
  		FileModel fm = iservice.getByFileName(fileName);
  		//2.1：如果存在就取出来
  		if(fm==null){
  		//2.2：如果不存在，就新增一个
  			fm = new FileModel();
  			fm.setFileName(fileName);
  			
  			iservice.create(fm);
  			
  			fm = iservice.getByFileName(fileName);
  		}
  		return fm;
	}
	
	

	@RequestMapping(value = "download", method = RequestMethod.GET)
	public static void download(HttpServletRequest request,  
	      HttpServletResponse response, String fileName) throws Exception {  
	  response.setContentType("text/html;charset=UTF-8");  
	  request.setCharacterEncoding("UTF-8");  
	  BufferedInputStream bis = null;  
	  BufferedOutputStream bos = null;  
	  String newFileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
	  String ctxPath = request.getSession().getServletContext()  
	          .getRealPath("/") +"/" + FileUploadUtils.defaultBaseDir;  
	  String downLoadPath = ctxPath +"/"+ newFileName;  
	  long fileLength = new File(downLoadPath).length();  
	  response.setHeader("Content-disposition", "attachment; filename="
	          + new String(newFileName.getBytes("gb2312"), "ISO8859-1"));  
	  response.setHeader("Content-Length", String.valueOf(fileLength));  
	  bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
	  bos = new BufferedOutputStream(response.getOutputStream());  
	  byte[] buff = new byte[2048];  
	  int bytesRead;  
	  while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	      bos.write(buff, 0, bytesRead);  
	  }  
	  bis.close();    bos.close(); 
	} 

	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "file/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") FileModel m){
		iservice.create(m);
		return "file/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		FileModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "file/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(@ModelAttribute("m") FileModel m){
		iservice.update(m);
		return "file/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		FileModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "file/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		iservice.delete(uuid);
		return "file/success";
	}
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(@ModelAttribute("wm")FileWebModel wm,Model model){
		FileQueryModel qm = null;
		if(wm.getQueryJsonStr()==null || wm.getQueryJsonStr().trim().length()==0){
			qm =  new FileQueryModel();
		}else{
			String s = wm.getQueryJsonStr();
			s = s.replace("-", "%");
			qm = (FileQueryModel)JsonHelper.str2Object(s, FileQueryModel.class);
		}
		
		qm.getPage().setNowPage(wm.getNowPage());
		if(wm.getPageShow() > 0){
			qm.getPage().setPageShow(wm.getPageShow());
		}
		
		Page dbPage = iservice.getByConditionPage(qm);
		
		//
		model.addAttribute("wm", wm);
		model.addAttribute("page", dbPage);
				
		return "file/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "file/query";
	}	
}
