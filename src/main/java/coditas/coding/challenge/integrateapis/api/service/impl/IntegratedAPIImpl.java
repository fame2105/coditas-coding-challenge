package coditas.coding.challenge.integrateapis.api.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coditas.coding.challenge.integrateapis.api.bean.IntegratedApiBean;
import coditas.coding.challenge.integrateapis.api.service.AggregatorService;
import coditas.coding.challenge.integrateapis.api.service.IntegratedApi;
import coditas.coding.challenge.integrateapis.clients.github.bean.GithubProjectInfoBean;
import coditas.coding.challenge.integrateapis.clients.gitlab.bean.GitlabProjectInfoBean;

@RestController
public class IntegratedAPIImpl implements IntegratedApi {
	
	private AggregatorService aggregatorService;
	
	@Autowired
	public IntegratedAPIImpl(AggregatorService aggregatorService) {
		this.aggregatorService = aggregatorService;
	}

	@Override
	@GetMapping(value = "/user/{userName}/projects")
	public IntegratedApiBean getProjects(@PathVariable String userName, 
	                                     @RequestParam(value = "language", required = false) String language,  
	                                     @RequestParam(value = "projectName", required = false) String projectName,
	                                     @RequestParam(value = "owned", required = false) Boolean owned){
		
		IntegratedApiBean integratedApiBean = aggregatorService.aggregate(userName);
		
		if (language != "" && language != null) {
			integratedApiBean = applyLanguageFilter(integratedApiBean, language);
		}
		
		if(projectName != "" && projectName != null){
			integratedApiBean = applyProjectNameFilter(integratedApiBean, projectName);
		}
		
		if(owned != null){
			integratedApiBean = applyProjectOwnerFilter(integratedApiBean, owned);
		}
		
		return integratedApiBean;
	}
	

	/**
	 *@IntegratedApiBean integratedApiBean 
	 * @String language
	 * @IntegratedApiBean modified IntegratedApiBean that contains project segregated based on the language.
	 */
	private IntegratedApiBean applyLanguageFilter(IntegratedApiBean integratedApiBean, String language) {
		// Filter projects based on language.
		// Gitlab doesn't provide language field in their responses, hence not filtering Gitlab Projects
		
		Set<GithubProjectInfoBean> filteredGithubProjects = integratedApiBean.getGithubProjects().stream().filter(
				bean -> language.equalsIgnoreCase(bean.getLanguage()))
				.collect(Collectors.toSet());
		integratedApiBean.setGithubProjects(filteredGithubProjects);
		return integratedApiBean;
	}

	/**
	 *@IntegratedApiBean integratedApiBean
	 * @String projectName/repoName
	 * @IntegratedApiBean modified IntegratedApiBean that contains project segregated based on the projectName.
	 */
	private IntegratedApiBean applyProjectNameFilter(IntegratedApiBean integratedApiBean, String projectName) {
		Set<GithubProjectInfoBean> filteredGithubRepos = integratedApiBean.getGithubProjects().stream().filter(
				bean -> projectName.equalsIgnoreCase(bean.getRepoName()))
				.collect(Collectors.toSet());
		
		Set<GitlabProjectInfoBean> filteredGitLabProjects = integratedApiBean.getGitlabProjects().stream().filter(
				bean -> projectName.equalsIgnoreCase(bean.getProjectName()))
				.collect(Collectors.toSet());
		
		integratedApiBean.setGithubProjects(filteredGithubRepos);
		integratedApiBean.setGitlabProjects(filteredGitLabProjects);
		
		return integratedApiBean;
	}

	/**
	 *@IntegratedApiBean integratedApiBean
	 * @Boolean forked
	 * @IntegratedApiBean modified IntegratedApiBean that contains Github projects that have either been forked or not.
	 */
	private IntegratedApiBean applyProjectOwnerFilter(IntegratedApiBean integratedApiBean, Boolean owned) {
		// Filter projects based on language.
		// Gitlab doesn't provide owner/forked flags in the response, hence not filtering Gitlab Projects
		
		/*As per Gitlab API Response, the ownership is determined whether or not the user has that project, not on the basis of fork, 
		  unlike Github so no way to know if a GITLAB project is forked from some other project*/
		
		Set<GithubProjectInfoBean> filteredGithubProjects = integratedApiBean.getGithubProjects().stream().filter(
				bean -> owned == !bean.isFork())
				.collect(Collectors.toSet());
		integratedApiBean.setGithubProjects(filteredGithubProjects);
		return integratedApiBean;
	}



}
