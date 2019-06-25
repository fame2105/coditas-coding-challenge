package coditas.coding.challenge.integrateapis.api;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coditas.coding.challenge.integrateapis.clients.github.bean.GithubProjectInfoBean;
import coditas.coding.challenge.integrateapis.clients.github.client.GithubClient;
import coditas.coding.challenge.integrateapis.clients.gitlab.bean.GitlabProjectInfoBean;
import coditas.coding.challenge.integrateapis.clients.gitlab.client.GitlabClient;

@Service
public class AggregatorServiceImpl implements AggregatorService {
	
	private GithubClient githubClient;
	
	private GitlabClient gitlabClient;

	@Autowired
	public AggregatorServiceImpl(GithubClient githubClient, GitlabClient gitlabClient) {
		this.githubClient = githubClient;
		this.gitlabClient = gitlabClient;
	}
	
	private AggregatedBean collectData(String userName) {
		AggregatedBean aggregatedBean = new AggregatedBean();
		aggregatedBean.setAssociatedUser(userName);
		aggregatedBean.setDescription("Displaying projects for userName" + userName);
		
		Set<GithubProjectInfoBean>  githubProjectInfoBeans = githubClient.getGithubProjectInfoByUserName(userName);
		aggregatedBean.setGithubProjects(githubProjectInfoBeans);

		Set<GitlabProjectInfoBean>  gitlabProjectInfoBeans = gitlabClient.getGitLabProjectInfoByUserName(userName);
		aggregatedBean.setGitlabProjects(gitlabProjectInfoBeans);
		return aggregatedBean;
	}

	@Override
	public AggregatedBean aggregate(String userName) {
		return collectData(userName);
	}

	
}
