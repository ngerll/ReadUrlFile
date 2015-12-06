package com.unicom.hb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;


public class ReadHtml   {
	


	public ArrayList<String> read(int i){
		ArrayList<String> piclist = new ArrayList<String>();
		try {
			String  strurl = "http://jandan.net/ooxx/page-" + i + "#comments";
			System.out.println(strurl);
			HttpClient hc = HttpClients.createDefault();
			HttpGet hGet = new HttpGet(strurl);
			hGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
			
			HttpResponse hResponse = hc.execute(hGet);
			
			HttpEntity he = hResponse.getEntity();
			
			InputStreamReader isr = new InputStreamReader(he.getContent(),"utf-8");
			
			
			BufferedReader br = new BufferedReader(isr);
			
			
			
			while(br.ready()){
				String sline = br.readLine();
				System.out.println(sline);
				int s = sline.indexOf("<img src=\"");
				int e = sline.length();
				if(s > -1){
					String shttp = sline.substring(s+10,e);
					int end = shttp.indexOf("\" />");
					if(end > -1){
						String picurl = shttp.substring(0, end);
						piclist.add(picurl);
						
						
					}
				}
			}
			
			
			br.close();
			isr.close();
			hGet.releaseConnection();
			new DownPic().downPic(piclist, i);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return piclist;
			
		
	}


	
	public static void main(String[] args) {
		ReadHtml readHtml = new ReadHtml();
	         for(int i=1600;i<1637;i++){
	             readHtml.read(i);
	             try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	         }
	}

}
