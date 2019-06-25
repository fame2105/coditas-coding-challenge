package coditas.coding.challenge.integrateapis.api.bean;

import java.util.HashSet;
import java.util.Set;

import coditas.coding.challenge.integrateapis.clients.github.bean.GithubProjectInfoBean;
import coditas.coding.challenge.integrateapis.clients.gitlab.bean.GitlabProjectInfoBean;

public class IntegratedApiBean {

	private String associatedUser;
	
	private String description;
	
	private Set<GithubProjectInfoBean> githubProjects = new HashSet<>();

	private Set<GitlabProjectInfoBean> gitlabProjects = new HashSet<>();
	

	public String getAssociatedUser() {
		return associatedUser;
	}

	public void setAssociatedUser(String associatedUser) {
		this.associatedUser = associatedUser;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<GithubProjectInfoBean> getGithubProjects() {
		return githubProjects;
	}

	public void setGithubProjects(Set<GithubProjectInfoBean> githubProjects) {
		this.githubProjects = githubProjects;
	}

	public Set<GitlabProjectInfoBean> getGitlabProjects() {
		return gitlabProjects;
	}

	public void setGitlabProjects(Set<GitlabProjectInfoBean> gitlabProjects) {
		this.gitlabProjects = gitlabProjects;
	}

	@Override
	public String toString() {
		return "AggregatedBean{" +
				"associatedUser='" + associatedUser + '\'' +
				", description='" + description + '\'' +
				", githubProjects= ---" + githubProjects +
				", gitlabProjects= ---" + gitlabProjects +
				'}';
	}
}
