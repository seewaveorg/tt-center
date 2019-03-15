package com.ngs.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SmsService {

	
	
	
	public boolean smsSender(String txtTelNo, String txtMessage) {
		
		boolean returnstat = false;

		
		try {

			String msgEncord =URLEncoder.encode(txtMessage, "UTF-8");
			
			String USER_AGENT = "Mozilla/5.0";
			String GET_URL = "https://cpsolutions.dialog.lk/index.php/cbs/sms/send?destination="+txtTelNo+"&q=14763686082015&message="+msgEncord;
			
			
			URL obj = new URL(GET_URL);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result
				returnstat = true;
				System.out.println(response.toString());
			} else {
				returnstat = false;
				System.out.println("GET request not worked");
			}
			
			
		
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("error in sms send");
		}
		

		return true;
	}
}
