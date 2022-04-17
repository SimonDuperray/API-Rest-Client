package com.requester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;

public class Requester {

	private static String readAll(Reader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while((cp=reader.read())!=-1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	public JSONArray readJsonFromURL(String url) throws IOException, JSONException {
		InputStream input = new URL(url).openStream();
		try {
			BufferedReader buffer = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
			String jsonT = readAll(buffer);
			JSONArray json = new JSONArray(jsonT);
			return json;
		} finally {
			input.close();
		}
	}
	
}
