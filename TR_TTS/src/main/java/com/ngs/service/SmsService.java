package com.ngs.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SmsService {

	public boolean smsSender(String txtOriNo, String txtTelNo, String txtHeading, String txtMessage, String txtPasswd,
			String txtUID) {
		boolean status = false;
		// String txtOriNo = "94711012725";
		// String[] txtTelNo = { "0714481873", "0717229209" };
		// String txtHeading = "Alarm System";
		// String txtMessage = "test msg";
		// String txtPasswd = "thara";
		// String txtUID = "energynet";

		try {
			
			System.out.println("1%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			String POST_URL = "http://220.247.201.100/ngsms.php";
			System.out.println("2%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
			String POST_PARAMS  = "txtOriNo=" + txtOriNo + "&txtTelNo[]=" + txtTelNo + "&txtHeading=" + txtHeading
					+ "&txtMessage=" + txtMessage + "&txtPasswd=" + txtPasswd + "&txtUID=" + txtUID + "";
			
			System.out.println("3%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			URL obj = new URL(POST_URL);
			
			System.out.println("4%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			System.out.println("5%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			con.setRequestMethod("POST");
			System.out.println("6%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			con.setDoOutput(true);
			System.out.println("7%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			OutputStream os = con.getOutputStream();
			System.out.println("8%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			os.write(POST_PARAMS.getBytes());
			System.out.println("9%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			os.flush();
			System.out.println("10%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			os.close();
			System.out.println("11%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
			int responseCode = con.getResponseCode();
			System.out.println("12%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println("POST Response Code :: " + responseCode);
			System.out.println("13%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				System.out.println("14%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				System.out.println("15%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				String inputLine;
				System.out.println("16%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				StringBuffer response = new StringBuffer();
				System.out.println("17%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				while ((inputLine = in.readLine()) != null) {
					System.out.println("18%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
					response.append(inputLine);
				}
				System.out.println("19%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				in.close();
				System.out.println("20%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				System.out.println("msg sent success.....");
				status = true;
				// print result
				System.out.println(response.toString());
			} else {
				System.out.println("@@@@@@@@@@@@@@@@@2eeeeeeeeeeeeeeeeeeeeeeeeeeee.....");
				status = false;
				System.out.println("POST request not worked");
			}
			
			
//
//			System.out.println("1%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//			
//			URL obj = new URL("http://220.247.201.100/ngsms.php");
//			
//			//URL url = new URL("https://redmine.xxx.cz/time_entries.xml");
//
//			System.out.println("2%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//			
//			//URL obj = new URL(url);
//			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//			con.setRequestMethod("POST");
//			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//			con.setConnectTimeout(5000); 
//			
//			System.out.println("3@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//			
//			String urlParameters = "txtOriNo=" + txtOriNo + "&txtTelNo=" + txtTelNo + "&txtHeading=" + txtHeading
//					+ "&txtMessage=" + txtMessage + "&txtPasswd=" + txtPasswd + "&txtUID=" + txtUID + "";
//
//			System.out.println("4%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//			
//			con.setDoOutput(true);
//			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//			wr.writeBytes(urlParameters);
//			wr.flush();
//			wr.close();
//
//			int responseCode = con.getResponseCode();
//		//	System.out.println("\nSending 'POST' request to URL : " + url);
//			System.out.println("Post parameters : " + urlParameters);
//			System.out.println("Response Code : " + responseCode);
//
//			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();

			//print result
//			System.out.println(response.toString());
			

//			System.out.println("msg sent success.....");
//			status = true;

		} catch (Exception ex) {
			status = false;

			System.out.println("Error in msg");
			ex.printStackTrace();
		}

		// https://stackoverflow.com/questions/4205980/java-sending-http-parameters-via-post-method-easily

		return status;
	}
}
