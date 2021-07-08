package com.microservices.training.basics.greetings.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
//
//	@Bean
//	@LoadBalanced
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

	@Bean
	public RoundRobinLoadBalancer roundRobinLoadBalancer(LoadBalancerClientFactory balancerClientFactory,
			Environment environment) {
		String serviceId = balancerClientFactory.getName(environment);

		RoundRobinLoadBalancer roundRobinLoadBalancer = new
		RoundRobinLoadBalancer(serviceId,
				balancerClientFactory.getLazyProvider(serviceId, ServiceInstanceSupplier.class), -1);

//		 RoundRobinLoadBalancer(org.springframework.beans.factory.ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider,
//                 String serviceId)

//		RoundRobinLoadBalancer roundRobinLoadBalancer = new RoundRobinLoadBalancer(
//				balancerClientFactory.getLazyProvider(serviceId, ServiceInstanceListSupplier.class), serviceId);

		return roundRobinLoadBalancer;
	}

}
