package coditas.coding.challenge.integrateapis.api;

import org.springframework.web.bind.annotation.PathVariable;

public interface integratedApi {
	AggregatedBean getProjects(@PathVariable("userName") String userName);
}
