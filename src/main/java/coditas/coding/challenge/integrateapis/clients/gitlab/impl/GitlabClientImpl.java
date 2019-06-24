package coditas.coding.challenge.integrateapis.clients.gitlab.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
	public List<GitlabProjectInfoBean> getGitLabProjectInfoByUserName(String userName) {
		String url = String.format(GET_GITLAB_PROJECT_INFO_PATH + PROJECTS, userName);
		
		ParameterizedTypeReference<List<GitlabProjectInfoBean>> typeRef = new ParameterizedTypeReference<List<GitlabProjectInfoBean>>() {};

		ResponseEntity<List<GitlabProjectInfoBean>> response = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);

		response.getBody().stream().forEach(bean -> bean.setAssociatedUser(userName));
		return response.getBody();
	}
	
}
