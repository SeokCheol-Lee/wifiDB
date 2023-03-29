package com.example.wifidb;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.example.wifidb.model.WifiInfo;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
        urlBuilder.append("/" +  URLEncoder.encode("4b41725a5a6c736331344341587048","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode("5","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        // 1. 문자열 형태의 JSON을 파싱하기 위한 JSONParser 객체 생성.
        JsonParser parser = new JsonParser();
        // 2. 문자열을 JSON 형태로 JSONObject 객체에 저장.
        JsonObject obj = (JsonObject) parser.parse(sb.toString());
        JsonObject wifi_info = (JsonObject) obj.get("TbPublicWifiInfo");
        System.out.println("총 데이터 건수 : " + wifi_info.get("list_total_count"));
        // 3. 필요한 리스트 데이터 부분만 가져와 JSONArray로 저장.
        JsonArray dataArr = (JsonArray) wifi_info.get("row");

        System.out.println("=========row============");
        for (int i = 0; i < dataArr.size(); i++) {
            JsonObject object = (JsonObject) dataArr.get(i);
            WifiInfo wifiInfo = new WifiInfo();
            wifiInfo.setWifino(String.valueOf(object.get("X_SWIFI_MGR_NO")));



            /*System.out.println("x좌표 " + object.get("LNT"));
            System.out.println("y좌표 " + object.get("LAT"));
            System.out.println();*/
        }

    }
    public float getDistance(float x1, float y1, float x2, float y2){
        float dx = (float) Math.toRadians(x2 - x1);
        float dy = (float) Math.toRadians(y2 - y1);
        float a = (float) (Math.sin(dx/2) * Math.sin(dy/2) + Math.cos(Math.toRadians(x1)) *
                        Math.cos(Math.toRadians(x2)) * Math.sin(dy/2) * Math.sin(dy/2));
        float c = (float) (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)));
        float d = 6371 * c * 1000;
        return d;
    }
}