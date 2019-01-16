package com.dtxd.job.annotation;

import java.lang.annotation.*;

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
public @interface ElasticSimpleJob{
    
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
     * 
     * description: 每次启动是否覆盖配置
     * @return
     * createdBy:zhuangjianfa           
     * created:2018年5月11日
     */
    boolean overwrite() default false;
    
    /**
     * description: 分片数
     * remark:
     * createdBy:zhuangjianfa           
     * created:2018年5月11日
     */
    int shardingTotalCount() default 1;
    
    /**
     * description: 分片参数
     * remark:
     * createdBy:zhuangjianfa           
     * created:2018年5月11日
     */
    String  shardingItemParameters() default "0=0";
}
