package com.common;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HttpClient {

	public static String get(String urlStr) {
		try {
			String temp = "";
			URL url = new URL(urlStr);
			HttpURLConnection url_con = (HttpURLConnection) url.openConnection();
			url_con.setDoOutput(true);
			url_con.setRequestMethod("GET");
			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in));
			temp = rd.readLine();//只读一行
			return new String(temp.getBytes(), "gbk");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
