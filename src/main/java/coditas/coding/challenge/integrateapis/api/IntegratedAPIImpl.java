package coditas.coding.challenge.integrateapis.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegratedAPIImpl implements integratedApi {

	private AggregatorService aggregatorService;

	public IntegratedAPIImpl(AggregatorService aggregatorService) {
		this.aggregatorService = aggregatorService;
	}
	@Override
	
	@GetMapping(value = "/user/{userName}/projects")
	public AggregatedBean getProjects(@PathVariable String userName) {
		
		AggregatedBean aggregatedBean = aggregatorService.aggregate(userName);
		aggregatedBean.setGitlabProjects(null);
		return aggregatedBean;
	}
}
