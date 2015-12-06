package com.unicom.hb;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class DownPic {
	public void downPic(ArrayList<String> list,int pages){
		try {
			for(int i = 0;i<list.size();i++){
				String picurl = list.get(i);
				
				URL url = new URL(picurl);
				HttpURLConnection hc = (HttpURLConnection) url.openConnection();
				
				InputStream dis = hc.getInputStream();
				
				File file = new File("D://pic/" + pages + i + ".jpg");
				byte[] bs = new byte[1024];
				
				OutputStream os = new FileOutputStream(file);
				
				int len;
				
				while((len = dis.read(bs)) != -1){
					os.write(bs, 0, len);
				}
				os.flush();
				os.close();
				dis.close();
				hc.disconnect();
				

				System.out.println(list.get(i) + " ÒÑÏÂÔØÖÁ" + file.getPath());
				
				Thread.sleep(1000);
				
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
