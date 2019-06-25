package coditas.coding.challenge.integrateapis.api.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coditas.coding.challenge.integrateapis.api.bean.IntegratedApiBean;
import coditas.coding.challenge.integrateapis.api.service.AggregatorService;
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
	
	private IntegratedApiBean collectData(String userName) {
		IntegratedApiBean integratedApiBean = new IntegratedApiBean();
		integratedApiBean.setAssociatedUser(userName);
		integratedApiBean.setDescription("Displaying projects for userName" + userName);
		
		Set<GithubProjectInfoBean>  githubProjectInfoBeans = githubClient.getGithubProjectInfoByUserName(userName);
		integratedApiBean.setGithubProjects(githubProjectInfoBeans);

		Set<GitlabProjectInfoBean>  gitlabProjectInfoBeans = gitlabClient.getGitLabProjectInfoByUserName(userName);
		integratedApiBean.setGitlabProjects(gitlabProjectInfoBeans);
		return integratedApiBean;
	}

	@Override
	public IntegratedApiBean aggregate(String userName) {
		return collectData(userName);
	}

	
}
