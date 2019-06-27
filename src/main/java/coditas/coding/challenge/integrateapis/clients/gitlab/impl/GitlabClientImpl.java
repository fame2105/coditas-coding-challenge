package coditas.coding.challenge.integrateapis.clients.gitlab.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import coditas.coding.challenge.integrateapis.clients.gitlab.bean.GitlabProjectInfoBean;
import coditas.coding.challenge.integrateapis.clients.gitlab.client.GitlabClient;

@Service
public class GitlabClientImpl implements GitlabClient {

	private static final String GET_GITLAB_PROJECT_INFO_PATH = "https://gitlab.com/api/v4/users/%s";
	private static final String PROJECTS = "/projects";

	private RestTemplate restTemplate;

	@Autowired
	public GitlabClientImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public Set<GitlabProjectInfoBean> getGitLabProjectInfoByUserName(String userName) {
		String url = String.format(GET_GITLAB_PROJECT_INFO_PATH + PROJECTS, userName);
		
		ParameterizedTypeReference<Set<GitlabProjectInfoBean>> typeRef = new ParameterizedTypeReference<Set<GitlabProjectInfoBean>>() {};
		ResponseEntity<Set<GitlabProjectInfoBean>> response = null;
		try{ 
			response = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);	
		}catch(HttpClientErrorException e){
			// we might get 4XX if the username doesn't exist on github, return empty set in that case
			return new HashSet<>();
		}catch(Exception e){
			return new HashSet<>();
		}
		if(response != null && response.getStatusCodeValue() == 200){
			return response.getBody();	
		}
		return new HashSet<>();
	}
	
}
