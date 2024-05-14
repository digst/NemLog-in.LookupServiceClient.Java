package client;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import client.dto.AccessToken;
import client.sts.TokenFetcher;

@ComponentScan
@EnableAutoConfiguration
public class Application implements CommandLineRunner {
	private static final Logger logger = Logger.getLogger(Application.class);

	@Autowired
	private TokenFetcher tokenFetcher;

	@Autowired
	private RestTemplate restTemplate;

	private static ResponseEntity<String> restServiceResponse;
	private static String requestUrl = "https://lookupservice.devtest4-nemlog-in.dk/api/lookup/pidmatchescpr?pid=9208-2002-2-634921274895&cpr=1904481382";
	private static String tokenUrl = "https://lookupservice.devtest4-nemlog-in.dk/api/accesstoken/issue";
	private static String audience = "https://saml.wsp.lookupservice.devtest4-nemlog-in.dk";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args) throws Exception {
		
		// get the access token
		AccessToken accessToken = tokenFetcher.getAccessToken(audience, tokenUrl);

		// setup request Authorization header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Holder-of-key " + accessToken.getToken());

		// call service
		restServiceResponse = restTemplate.exchange(requestUrl, HttpMethod.POST, new HttpEntity<>("", headers), String.class);

		// should print out "Hello John"
		logger.info(restServiceResponse.toString());
	}

	public static String getRestResponse() {
		return restServiceResponse.toString();
	}

	public static void setRequestUrl(String url) {
		requestUrl = url;
	}

	public static void setTokenUrl(String tUrl) { tokenUrl = tUrl; }

	public static void setAudience(String aud) { audience = aud; }

}
