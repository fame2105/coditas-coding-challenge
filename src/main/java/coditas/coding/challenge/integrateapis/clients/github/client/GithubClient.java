package coditas.coding.challenge.integrateapis.clients.github.client;

import java.util.Set;

import coditas.coding.challenge.integrateapis.clients.github.bean.GithubProjectInfoBean;

public interface GithubClient {
	Set<GithubProjectInfoBean> getGithubProjectInfoByUserName(String userName);
}
