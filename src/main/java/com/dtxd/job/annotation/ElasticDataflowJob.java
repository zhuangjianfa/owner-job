package com.dtxd.job.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 
 * @Description:SimpleJob注解
 * Author	Version		Date		Changes
 * zhuangjianfa 	1.0  		2017年8月24日 	Created
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface ElasticDataflowJob{
    
    /**
     * 
     * description: 默认每分钟跑一次 
     * @return
     * createdBy:zhuangjianfa           
     * created:2018年5月11日
     */
    String corn() default "0 0/1 * * * ?";
    
    /**
     * 
     * description: 是否启动时禁止启动
     * @return
     * createdBy:zhuangjianfa           
     * created:2018年5月11日
     */
    boolean disabled() default true;
    
    /**
     * description: 分片数
     * remark:
     */
    int shardingTotalCount() default 1;
    
    /**
     * description: 分片参数
     * remark:
     */
    String  shardingItemParameters() default "0=0";
    
    /**
     * 
     * description: 每次启动是否覆盖配置
     * @return
     * createdBy:zhuangjianfa           
     * created:2018年5月11日
     */
    boolean overwrite() default true;
    
    /**
     * 
     * description: 是否流式线性执行 
     * @return
     * createdBy:zhuangjianfa           
     * created:2018年5月11日
     */
    boolean streamingProcess() default true;
    
}
