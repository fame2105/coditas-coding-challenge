package coditas.coding.challenge.integrateapis.api.service;

import coditas.coding.challenge.integrateapis.api.bean.IntegratedApiBean;

/**
 * This service gathers the response from Github and Gitlab clients and 
 * aggregates the respective responses into a new Bean of type IntegratedApiBean.
 */
public interface AggregatorService {

	IntegratedApiBean aggregate(String userName);
	 
}
