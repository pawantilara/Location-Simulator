package com.location.simulator;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Security.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulatorApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
//		throws
//	} KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
			TrustStrategy acceptingTrustStrategy;
//		acceptingTrustStrategystStrategy = (X509Certificate[] chain, String authType) -> true;
//		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
//					.loadTrustMaterial(null, acceptingTrustStrategy)
//					.build();
//
//			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//
//			CloseableHttpClient httpClient = HttpClients.custom()
//					.setSSLSocketFactory(csf)
//					.build();
//
//			HttpComponentsClientHttpRequestFactory requestFactory =
//					new HttpComponentsClientHttpRequestFactory();
//
//			requestFactory.setHttpClient(httpClient);
//			RestTemplate restTemplate = new RestTemplate(requestFactory);
//			return restTemplate;
		return new RestTemplate();
	}
}
