package coditas.coding.challenge.integrateapis.clients.gitlab.client;

import java.util.List;

import coditas.coding.challenge.integrateapis.clients.gitlab.bean.GitlabProjectInfoBean;

public interface GitlabClient {
	List<GitlabProjectInfoBean> getGitLabProjectInfoByUserName(String userName);
}
