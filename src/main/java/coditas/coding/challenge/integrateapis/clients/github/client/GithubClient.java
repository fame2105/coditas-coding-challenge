package coditas.coding.challenge.integrateapis.clients.github.client;

import java.util.List;

import coditas.coding.challenge.integrateapis.clients.github.bean.GithubProjectInfoBean;

public interface GithubClient {
	List<GithubProjectInfoBean> getGithubProjectInfoByUserName(String userName);
}
