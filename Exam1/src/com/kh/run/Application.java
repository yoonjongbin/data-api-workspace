package com.kh.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Application {
	
	// serviceKey
	public static final String serviceKey = "EqqpKclOS1YdE%2FXP2yRIzHwjmNC%2BD5XoGI2Me8bzEaCmpqHWctFaaW6POXJTq4u3tBRCTIN5clVaKuzMtFXJwQ%3D%3D";	// url을 접근하기 위한 키
	
	public static void main(String[] args) throws IOException {
		
		// URL
		String url = "http://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo";
		
		url += "?serviceKey=" + serviceKey;	
		url += "&numOfRows=5";	// 한 페이지 결과 수
		url += "&_type=json";	// 기본값 : xml
		
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("GET"); 	// get방식 통신
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = null;
		
		StringBuffer responseBuffer = new StringBuffer();
		
		
		
		while((line = br.readLine()) != null) {
//			System.out.println(line);
			responseBuffer.append(line);
		}
		
		br.close();
		urlConnection.disconnect();
		
		// JSON 파싱 시작
		String responseData = responseBuffer.toString();
		JSONObject jsonResponse = new JSONObject(responseData);
		
		// 구조 파악
		JSONObject response = jsonResponse.getJSONObject("response");
		JSONObject body = response.getJSONObject("body");
		JSONObject items = body.getJSONObject("items");
		JSONArray item = items.getJSONArray("item");
		
//		System.out.println(item);
		
		for(int i=0; i<item.length(); i++) {
			JSONObject result = item.getJSONObject(i);
//			System.out.println(result);
			
			String careNm = result.getString("careNm");
			String careAddr = result.getString("careAddr");
			
			System.out.println("동물보호센터 명 : " + careNm);
			System.out.println("주소 : " + careAddr);
		}
		
	}

}
