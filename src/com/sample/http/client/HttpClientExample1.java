package com.sample.http.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HttpClientExample1 {

	public static void main(String[] args) throws IOException {
//		if (args.length < 2) {
//			System.out.println("Syntax: <url> <file>");
//			return;
//		}		
//		String url = args[0];
//		String filePath = args[1];
		
		String url = "https://google.com";
		String filePath = "Google.html";
        
		URL urlObj = new URL(url);
		HttpURLConnection urlCon = (HttpURLConnection)urlObj.openConnection();
		 
		// Response Status
		int responseCode = urlCon.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_OK) {
		    System.out.println("Server returned response code " + responseCode + ". Download failed.");
		    System.exit(0);
		}else {
			System.out.println("Server returned response code " + responseCode);
		}
		
		// Response Headers
		Map<String, List<String>> map = urlCon.getHeaderFields();
		for (String key : map.keySet()) {
		    System.out.print(key + ":");
		 
		    List<String> values = map.get(key);
		    for (String aValue : values) {
		        System.out.print("\t" + aValue);
		    }
		    System.out.println();
		}
		
		String responseMessage = urlCon.getResponseMessage();
		String contentType = urlCon.getContentType();
		String contentEncoding = urlCon.getContentEncoding();
		int contentLength = urlCon.getContentLength();
		 
		long date = urlCon.getDate();
		long expiration = urlCon.getExpiration();
		long lastModified = urlCon.getLastModified();
		 
		System.out.println("Response Code: " + responseCode);
		System.out.println("Response Message: " + responseMessage);
		System.out.println("Content Type: " + contentType);
		System.out.println("Content Encoding: " + contentEncoding);
		System.out.println("Content Length: " + contentLength);
		System.out.println("Date: " + new Date(date));
		System.out.println("Expiration: " + new Date(expiration));
		System.out.println("Last Modified: " + new Date(lastModified));
		
		// Response Body
		InputStream inputStream = urlCon.getInputStream();
		BufferedInputStream reader = new BufferedInputStream(inputStream);
		 
		try(BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(filePath))){
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			
			while ((bytesRead = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, bytesRead);
			}
			
			reader.close();			
		}
		System.out.println("Done");
	}
}
