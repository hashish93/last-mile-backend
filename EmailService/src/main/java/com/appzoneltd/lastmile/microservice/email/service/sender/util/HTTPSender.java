package com.appzoneltd.lastmile.microservice.email.service.sender.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.core.MediaType;

import com.appzoneltd.lastmile.microservice.email.model.EmailTemplate;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class HTTPSender {

	/*
	public void sendHTTPAsync(EmailTemplate emailObject) throws IOException {

		String url = "https://api.mailgun.net/v3/sandboxb55163a7bf114c6ca603ce2d7c1a4057.mailgun.org/messages";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		//		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		con.setRequestProperty("Authorization", "Basic " + 
				Base64.getEncoder().encodeToString("api:key-8dd85ebb7362655c3e57d80e2a43c670".getBytes()));

		StringBuilder postData = new StringBuilder();
		postData.append("from=topquack@gmail.com,");
		postData.append("to=topquack@gmail.com,");
		postData.append("subject=topquack@gmail.com,");
		postData.append("text=topquack@gmail.com");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(postData.toString());
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + postData.toString());
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
	}
	*/
	
	public void sendHTTPAsync(EmailTemplate emailObject) throws IOException {
		
		Client client = Client.create();
	    client.addFilter(new HTTPBasicAuthFilter("api", "key-8dd85ebb7362655c3e57d80e2a43c670"));
	    WebResource webResource = client.resource("https://api.mailgun.net/v3/sandboxb55163a7bf114c6ca603ce2d7c1a4057.mailgun.org/messages");
	    MultivaluedMapImpl formData = new MultivaluedMapImpl();
	    formData.add("from", "Mailgun Sandbox <topquarck@gmail.com>");
	    formData.add("to", "iali <topquarck@gmail.com>");
	    formData.add("subject", "Hello iali!!!!!!");
	    formData.add("text", "Congratulations iali, you just sent an email with Mailgun!  You are truly awesome!");
	    ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
	                                        post(ClientResponse.class, formData);
	    
	    System.out.println(response.getStatus() );
	}
	
	public static void main(String[] dd) {
		HTTPSender sender = new HTTPSender();
		try {
			sender.sendHTTPAsync(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
