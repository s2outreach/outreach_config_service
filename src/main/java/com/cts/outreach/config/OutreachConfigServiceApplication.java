package com.cts.outreach.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.netflix.appinfo.AmazonInfo;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class OutreachConfigServiceApplication {
	
	private Logger LOGGER = LoggerFactory.getLogger(OutreachConfigServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OutreachConfigServiceApplication.class, args);
	}
	
	@Primary
	@Bean
	@Autowired
	@Profile("aws")
	public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
	    EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
	    AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("config");
	    config.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
	    config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4));
	    config.setNonSecurePort(8888);
	    config.setDataCenterInfo(info);
	    LOGGER.info(info.get(AmazonInfo.MetaDataKey.publicIpv4));
	    LOGGER.info(info.get(AmazonInfo.MetaDataKey.publicHostname));
	    return config;
	   }

}
