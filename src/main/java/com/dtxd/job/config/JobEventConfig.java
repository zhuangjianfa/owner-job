package com.dtxd.job.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;

@Configuration
@ConditionalOnExpression("${event.trace.rdb.enable}")
public class JobEventConfig {

	@Bean
	public JobEventConfiguration jobEventConfiguration(@Value("${event.trace.rdb.driver}") final String driverClassName,
			@Value("${event.trace.rdb.url}") final String url,
			@Value("${event.trace.rdb.username}") final String userName,
			@Value("${event.trace.rdb.password}") final String password) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		return new JobEventRdbConfiguration(dataSource);
	}
}
