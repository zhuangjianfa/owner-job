package com.dtxd.job.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.dtxd.job.model.SimpleJobBuilder;
import com.dtxd.job.util.BeanUtil;

@Configuration
public class SimpleJobConfig {
    
    @Resource
    private ZookeeperRegistryCenter regCenter;
    
    public void simpleJobScheduler(final SimpleJob simpleJob, SimpleJobBuilder simpleJobBuilder) {
    	if(!BeanUtil.getContext().containsBean("jobEventConfiguration")){
            new SpringJobScheduler(simpleJob, regCenter, getLiteJobConfiguration(simpleJob.getClass(), simpleJobBuilder)).init();
    	}else{
    		JobEventConfiguration jobEventConfiguration = BeanUtil.getBean("jobEventConfiguration");
    		new SpringJobScheduler(simpleJob, regCenter, getLiteJobConfiguration(simpleJob.getClass(), simpleJobBuilder), jobEventConfiguration).init();
    	}
    }
    
    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,SimpleJobBuilder simpleJobBuilder) {
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
                jobClass.getName(), simpleJobBuilder.getCorn(), simpleJobBuilder.getShardingTotalCount())
            .shardingItemParameters(simpleJobBuilder.getShardingItemParameters()).build(), jobClass.getCanonicalName()))
                .disabled(simpleJobBuilder.isDisabled()).overwrite(simpleJobBuilder.isOverwrite()).monitorExecution(false).build();
    }
}
