package com.dtxd.job.interceptor;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dtxd.job.annotation.ElasticDataflowJob;
import com.dtxd.job.annotation.ElasticSimpleJob;
import com.dtxd.job.build.JobBuild;
import com.dtxd.job.model.DataflowJobBuilder;
import com.dtxd.job.model.SimpleJobBuilder;

/**
 * @Description:定时任务创建 Author Version Date Changes zhuangjianfa 1.0 2017年10月9日
 *                     Created
 */
@Component
public class JobClassAspectAdvice implements ApplicationListener<ContextRefreshedEvent> {

	private Logger logger = LoggerFactory.getLogger(JobClassAspectAdvice.class);

	/**
	 * 普通任务集合类
	 */
	public static Set<SimpleJobBuilder> simplejobList;

	/**
	 * 流式任务集合类
	 */
	public static Set<DataflowJobBuilder> dataflowjobList;

	@Autowired
	private JobBuild jobStart;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			// 获取上下文
			ApplicationContext context = event.getApplicationContext();
			simplejobList = new HashSet<SimpleJobBuilder>();
			dataflowjobList = new HashSet<DataflowJobBuilder>();
			// 获取所有beanNames
			String[] beanNames = context.getBeanNamesForType(Object.class);
			for (String beanName : beanNames) {
				ElasticSimpleJob elasticSimpleJob = context.findAnnotationOnBean(beanName, ElasticSimpleJob.class);
				//判断该类是否含有elasticSimpleJob注解      
				if (elasticSimpleJob != null) {
				    SimpleJobBuilder simple = new SimpleJobBuilder(beanName,elasticSimpleJob.corn(),elasticSimpleJob.disabled(),elasticSimpleJob.overwrite(),elasticSimpleJob.shardingTotalCount(),elasticSimpleJob.shardingItemParameters());
					simplejobList.add(simple);
				}
				ElasticDataflowJob elasticDataflowJob = context.findAnnotationOnBean(beanName,ElasticDataflowJob.class);
				//判断该类是否含有elasticSimpleJob注解      
				if (elasticDataflowJob != null) {
				    DataflowJobBuilder dataflow = new DataflowJobBuilder(beanName,elasticDataflowJob.corn(),elasticDataflowJob.disabled(),elasticDataflowJob.overwrite(),elasticDataflowJob.shardingTotalCount(),elasticDataflowJob.shardingItemParameters(),elasticDataflowJob.streamingProcess());
					dataflowjobList.add(dataflow);
				}
			}
			jobStart.addJob();
		} catch (Exception e) {
			logger.error("JobAdvice is error", e);
		}
	}

}
