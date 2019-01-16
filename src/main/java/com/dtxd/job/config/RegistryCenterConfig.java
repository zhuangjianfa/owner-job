package com.dtxd.job.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

@Configuration
public class RegistryCenterConfig {
    
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(@Value("${elastic.job.serverList}") final String serverList, @Value("${elastic.job.namespace}") final String namespace) {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }
}