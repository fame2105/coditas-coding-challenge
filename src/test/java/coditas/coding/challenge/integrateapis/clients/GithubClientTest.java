package coditas.coding.challenge.integrateapis.clients;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import coditas.coding.challenge.integrateapis.clients.github.bean.GithubProjectInfoBean;
import coditas.coding.challenge.integrateapis.clients.github.impl.GithubClientImpl;

public class GithubClientTest {

	private static final String GET_GITHUB_PROJECT_INFO_PATH = "https://api.github.com/users/%s";

	private static final String REPOS = "/repos";
	private static final String USERNAME = "fake-userName";

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private GithubClientImpl githubClientImpl;


	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getPublicGithubProjectsForUsername() throws HttpClientErrorException {
		String url = String.format(GET_GITHUB_PROJECT_INFO_PATH + REPOS, USERNAME);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<Set<GithubProjectInfoBean>> typeRef = new ParameterizedTypeReference<Set<GithubProjectInfoBean>>() {
		};
		Set<GithubProjectInfoBean> set = new HashSet<>();
		set.add(createGithubProjectBean());
		ResponseEntity<Set<GithubProjectInfoBean>> response = new ResponseEntity<>(set, HttpStatus.OK);
		when(restTemplate.exchange(eq((url)), eq(HttpMethod.GET), eq((entity)), eq(typeRef))).thenReturn(response);
		Set<GithubProjectInfoBean> getGithubProjects = githubClientImpl.getGithubProjectInfoByUserName(USERNAME);
		GithubProjectInfoBean githubProjectInfoBean = (GithubProjectInfoBean) getGithubProjects.toArray()[0];
		assertSame("Java", githubProjectInfoBean.getLanguage());
		assertSame("fake-repoName", githubProjectInfoBean.getRepoName());
		assertSame("Testing GithubClient", githubProjectInfoBean.getDescription());
	}

	private GithubProjectInfoBean createGithubProjectBean() {
		GithubProjectInfoBean githubProjectInfoBean = new GithubProjectInfoBean();
		githubProjectInfoBean.setFork(true);
		githubProjectInfoBean.setDescription("Testing GithubClient");
		githubProjectInfoBean.setId(1l);
		githubProjectInfoBean.setLanguage("Java");
		githubProjectInfoBean.setRepoName("fake-repoName");
		return githubProjectInfoBean;
	}


}
