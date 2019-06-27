package coditas.coding.challenge.integrateapis.clients.github.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import coditas.coding.challenge.integrateapis.clients.github.bean.GithubProjectInfoBean;
import coditas.coding.challenge.integrateapis.clients.github.client.GithubClient;

@Service
public class GithubClientImpl implements GithubClient{
	
	private static final String GET_GITHUB_PROJECT_INFO_PATH = "https://api.github.com/users/%s";
	
	private static final String REPOS = "/repos";
	
	private RestTemplate restTemplate;

	public GithubClientImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public Set<GithubProjectInfoBean> getGithubProjectInfoByUserName(String userName) {
		String url =String.format(GET_GITHUB_PROJECT_INFO_PATH + REPOS, userName);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<Set<GithubProjectInfoBean>> typeRef = new ParameterizedTypeReference<Set<GithubProjectInfoBean>>() {};
		ResponseEntity<Set<GithubProjectInfoBean>> response = null;
		try{
			response = restTemplate.exchange(url, HttpMethod.GET, entity, typeRef);	
		} catch(HttpClientErrorException e){
			// we might get 4XX if the username doesn't exist on github, return empty set in that case
			return new HashSet<>();
		} catch (Exception e){
			return new HashSet<>();
		}
		
		if(response !=null && response.getStatusCodeValue() == 200){
			return response.getBody();	
		}
		return new HashSet<>();
	}

}
