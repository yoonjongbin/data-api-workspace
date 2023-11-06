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
	public static final String serviceKey = "EqqpKclOS1YdE%2FXP2yRIzHwjmNC%2BD5XoGI2Me8bzEaCmpqHWctFaaW6POXJTq4u3tBRCTIN5clVaKuzMtFXJwQ%3D%3D";
	
	@ResponseBody
	@RequestMapping(value="api.do", produces="application/json; charset=utf-8")
	public String apiInfo(String stnId) throws IOException{
		// URL (key값 제외한 주소)
		String url = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst";
		
		
		// 여기서 파라미터로 던져줄 값들 중 필수는 꼭 넣어주어야 한다!!!(홈페이지에서 잘 확인할 것)
		url += "?serviceKey=" + serviceKey; 
		url += "&tmFc=201310170600"; 
		url += "&dataType=JSON"; // 기본값 xml, json으로 지정 가능
		url += "&stnId=" + stnId;	
		
		// 만약 파라미터가 한글이면?
//		String stnName = "서울";
//		url += "&stnName=" + URLEncoder.encode(stnName, "UTF-8");	// UTF-8로 인코딩
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("GET"); // 요청 방식

		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = null;
		
		String responseText = "";
		
		while((line = br.readLine())!=null) {
			responseText += line;
		} 
		
		
		br.close();
		urlConnection.disconnect();
		
		System.out.println(responseText);
		return responseText;
	}
}