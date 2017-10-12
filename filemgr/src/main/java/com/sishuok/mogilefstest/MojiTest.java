package com.sishuok.mogilefstest;

import java.io.InputStream;
import java.util.List;

import fm.last.moji.MojiFile;
import fm.last.moji.spring.SpringMojiBean;

public class MojiTest {
	
	public static void main(String[] args)throws Exception {
		SpringMojiBean moji = new SpringMojiBean();
		
		moji.setAddressesCsv("192.168.1.106:7001");
		moji.setDomain("d1");
		moji.initialise();
		moji.setTestOnBorrow(true);
		
		MojiFile mf = moji.getFile("k3");
		
//		OutputStream out = null;
//		try {
//			out = mf.getOutputStream();
//			out.write("only test file".getBytes());
//			out.flush();
//		} finally {
//			out.close();
//		}
		
		InputStream in = null;
		try {
			in = mf.getInputStream();
			byte[] bs = new byte[in.available()];
			in.read(bs);
			System.out.println("the content===="+new String(bs));
		} finally {
			in.close();
		}

		
//		System.out.println("path==="+mf.getPaths());
		
//		System.out.println("attr==="+mf.getAttributes());
		
//		System.out.println("len==="+mf.length());
		
//		moji.copyToMogile(new File("test.txt"), mf);
		
//		mf.copyToFile(new File("k3.txt"));
		
//		mf.rename("k5");
		
//		mf.delete();
		
		List<MojiFile> list = moji.list("k");
		for(MojiFile m : list){
			System.out.println("mf==="+m);
		}
		
	}

}
