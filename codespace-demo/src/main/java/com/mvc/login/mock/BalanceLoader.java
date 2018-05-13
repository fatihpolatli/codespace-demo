package com.mvc.login.mock;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class BalanceLoader {
	
	public static Boolean loadBalance() {
		
		try {
			HttpEntity<String> request = new HttpEntity<>(new String());

			Object result = new RestTemplate().postForObject("http://localhost:1080/loadBalance", request, Object.class);
			
			return true;


		} catch (Exception e) {
			
			e.printStackTrace();

		}
		
		return false;
	}

}
