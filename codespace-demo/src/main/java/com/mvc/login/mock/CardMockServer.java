package com.mvc.login.mock;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CardMockServer {

	private ClientAndServer mockServer;

	@PostConstruct
	public void startServer() {
		mockServer = startClientAndServer(1080);
		// new MockServerClient("", 8088)
		mockServer.when(
				request().withMethod("POST").withPath("/loadBalance").withHeader("\"Content-type\", \"application/json\"")
		
		).respond(response().withStatusCode(200)
				.withHeaders(new Header("Content-Type", "application/json; charset=utf-8"),
						new Header("Cache-Control", "public, max-age=86400"))
				.withBody("{ \"result\": true }").withDelay(TimeUnit.SECONDS, 1));

		
	}

}
