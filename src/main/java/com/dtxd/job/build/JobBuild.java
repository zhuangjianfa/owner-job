package com.dtxd.job.build;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dtxd.job.config.DataflowJobConfig;
import com.dtxd.job.config.SimpleJobConfig;
import com.dtxd.job.interceptor.JobClassAspectAdvice;
import com.dtxd.job.model.DataflowJobBuilder;
import com.dtxd.job.model.SimpleJobBuilder;
import com.dtxd.job.util.BeanUtil;

/**
 * Author Version Date Changes 
 * zhuangjianfa 1.0 2017年8月31日
 * Created
 */
@Component
@Configuration
public class JobBuild {

    private Logger              logger                     = LoggerFactory.getLogger(JobBuild.class);
    
    @Autowired
    private SimpleJobConfig simpleJobConfig;
    
    @Autowired
    private DataflowJobConfig dataflowJobConfig;
    
    @SuppressWarnings("rawtypes")
	public void  addJob() {
    	logger.info("JobStart addJob start...");
        
        Set<DataflowJobBuilder> dataflowlist = JobClassAspectAdvice.dataflowjobList;
        for (DataflowJobBuilder dataflow : dataflowlist) {
        	logger.info("dataflowJobStart addJob start name={}",dataflow.getName());
            DataflowJob job = (DataflowJob)BeanUtil.getBean(dataflow.getName());
            dataflowJobConfig.dataflowJobScheduler(job,dataflow);
        	logger.info("dataflowJobStart addJob end name={}",dataflow.getName());
        }
        
        
        Set<SimpleJobBuilder> simplelist = JobClassAspectAdvice.simplejobList;
        for (SimpleJobBuilder simple : simplelist) {
        	logger.info("SimpleJobStart addJob start name={}",simple.getName());
            SimpleJob job = (SimpleJob)BeanUtil.getBean(simple.getName());
            simpleJobConfig.simpleJobScheduler(job,simple);
        	logger.info("SimpleJobStart addJob end name={}",simple.getName());
        }
        
        logger.info("JobStart addJob end...");
    }
}
