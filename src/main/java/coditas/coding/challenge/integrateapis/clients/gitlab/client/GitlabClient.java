package coditas.coding.challenge.integrateapis.clients.gitlab.client;

import java.util.Set;

import coditas.coding.challenge.integrateapis.clients.gitlab.bean.GitlabProjectInfoBean;

public interface GitlabClient {
	Set<GitlabProjectInfoBean> getGitLabProjectInfoByUserName(String userName);
}
