package coditas.coding.challenge.integrateapis.clients;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import coditas.coding.challenge.integrateapis.clients.gitlab.bean.GitlabProjectInfoBean;
import coditas.coding.challenge.integrateapis.clients.gitlab.impl.GitlabClientImpl;

public class GitLabClientTest {
	
	private static final String GET_GITLAB_PROJECT_INFO_PATH = "https://gitlab.com/api/v4/users/%s";
	private static final String PROJECTS = "/projects";
	private static final String USERNAME = "fake-userName";

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private GitlabClientImpl gitlabClient;


	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getPublicGithubProjectsForUsername() throws HttpClientErrorException {
		String url = String.format(GET_GITLAB_PROJECT_INFO_PATH + PROJECTS, USERNAME);

		ParameterizedTypeReference<Set<GitlabProjectInfoBean>> typeRef = new ParameterizedTypeReference<Set<GitlabProjectInfoBean>>() {
		};
		
		Set<GitlabProjectInfoBean> set = new HashSet<>();
		set.add(createGitLabProjectBean());
		
		ResponseEntity<Set<GitlabProjectInfoBean>> response = new ResponseEntity<>(set, HttpStatus.OK);
		
		when(restTemplate.exchange(eq((url)), eq(HttpMethod.GET), eq((null)), eq(typeRef))).thenReturn(response);
		Set<GitlabProjectInfoBean> getGitLabProjects = gitlabClient.getGitLabProjectInfoByUserName(USERNAME);

		GitlabProjectInfoBean gitlabProjectInfoBean = (GitlabProjectInfoBean) getGitLabProjects.toArray()[0];
		assertSame("fake-Project", gitlabProjectInfoBean.getProjectName());
		assertSame("Testing GitLabClient", gitlabProjectInfoBean.getDescription());
	}

	private GitlabProjectInfoBean createGitLabProjectBean() {
		GitlabProjectInfoBean gitlabProjectInfoBean = new GitlabProjectInfoBean();
		gitlabProjectInfoBean.setDescription("Testing GitLabClient");
		gitlabProjectInfoBean.setProjectName("fake-Project");
		gitlabProjectInfoBean.setId(1l);
		return gitlabProjectInfoBean;
	}
}
