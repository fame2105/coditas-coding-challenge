package coditas.coding.challenge.integrateapis.api.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import coditas.coding.challenge.integrateapis.api.bean.IntegratedApiBean;

public interface IntegratedApi {
	// returns the project from all the clients
	IntegratedApiBean getProjects(@PathVariable("userName") String userName, 
	                              @RequestParam(value = "language", required = false) String language, 
	                              @RequestParam(value = "name", required = false) String projectName, 
	                              @RequestParam(value = "owned", required = false) Boolean owned);
}
