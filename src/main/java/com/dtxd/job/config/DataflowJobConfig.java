/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dtxd.job.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.dtxd.job.model.DataflowJobBuilder;
import com.dtxd.job.util.BeanUtil;

@Configuration
public class DataflowJobConfig {
    
    @Resource
    private ZookeeperRegistryCenter regCenter;
    
    @SuppressWarnings("rawtypes")
	public void dataflowJobScheduler(final DataflowJob dataflowJob,DataflowJobBuilder dataflow) {
    	if(!BeanUtil.getContext().containsBean("jobEventConfiguration")){
            new SpringJobScheduler(dataflowJob, regCenter, getLiteJobConfiguration(dataflowJob.getClass(), 
                dataflow)).init();
    	}else{
    		JobEventConfiguration jobEventConfiguration = BeanUtil.getBean("jobEventConfiguration");
    		new SpringJobScheduler(dataflowJob, regCenter, getLiteJobConfiguration(dataflowJob.getClass(), 
    		    dataflow), jobEventConfiguration).init();
    	}
    }
    
    @SuppressWarnings("rawtypes")
	private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends DataflowJob> jobClass, DataflowJobBuilder dataflow) {
        return LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder(
                jobClass.getName(), dataflow.getCorn(), dataflow.getShardingTotalCount()).shardingItemParameters(dataflow.getShardingItemParameters()).build(), 
            jobClass.getCanonicalName(), dataflow.isStreamingProcess())).disabled(dataflow.isDisabled()).overwrite(dataflow.isOverwrite()).monitorExecution(false).build();
    }
}
