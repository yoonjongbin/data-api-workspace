package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {
	
	private static final String serviceKey = "UNmnLkcNtzgMKivBzvyb3TdrsqmthwquJWOHYpkKXK6aXtSdhG1gbTQ6EOqATL5t3ApCJ2hySkej4pCTXuaAZg%3D%3D";

	@ResponseBody
	@RequestMapping(value = "air.do", produces = "application/json; charset=utf-8")
	public String airPollution(String location) throws IOException{
		
		//https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=EqqpKclOS1YdE%2FXP2yRIzHwjmNC%2BD5XoGI2Me8bzEaCmpqHWctFaaW6POXJTq4u3tBRCTIN5clVaKuzMtFXJwQ%3D%3D&returnType=json&sidoName=%EC%84%9C%EC%9A%B8
		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		
		url += "?serviceKey=" + serviceKey;
		url += "&returnType=json";
		String sidoName = location;
		url += "&sidoName=" + URLEncoder.encode(sidoName, "UTF-8");
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		System.out.println(responseText);
		return responseText;
		
	}

}
