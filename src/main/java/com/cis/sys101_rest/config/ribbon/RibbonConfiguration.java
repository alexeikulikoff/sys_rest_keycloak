package com.cis.sys101_rest.config.ribbon;

import java.util.Collections;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RibbonClient(name = "loader")
public class RibbonConfiguration {

	@Bean
	public IRule loadBalancingRule() {
		new WeightedResponseTimeRule();
		new AvailabilityFilteringRule();
		new WeightedResponseTimeRule();
		return new RoundRobinRule();
	}

	@Bean
	public IPing pingConfiguration(ServerList<Server> serverList) {
		String pingPath = "/actuator/health";
		IPing ping = new PingUrl(false, pingPath);
		log.info("Configuring ping URI to [{}]", pingPath);

		return ping;
	}

	@Bean
	public ServerList<Server> serverList() {
		return RibbonHelper.serverList(Collections.singletonList(new Server("http", "172.16.0.149", 4000)));

	}

}
