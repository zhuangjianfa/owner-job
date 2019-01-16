/**
 * Copyright © 2014 GZJF Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of 深圳市大头兄弟文化传播有限公司 Corp. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from 深圳市大头兄弟文化传播有限公司 Corp or an authorized sublicensor.
 */
package com.dtxd.job.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * description:DataflowJob构建参数对象
 * Author	Date	Changes
 * zhuangjianfa  2018年5月11日 Created
 */
@Getter
@Setter
@AllArgsConstructor
public class DataflowJobBuilder {
    
    /**
     * description:定时任务class
     * remark:
     */
    private String name;
    
    /**
     * description:cron表达式
     * remark:
     */
    private String corn;
    
    /**
     * description: 是否启动时禁止启动
     * remark:
     */
    private boolean disabled;
    
    /**
     * description: 每次启动是否覆盖配置
     * remark:
     */
    private boolean overwrite;
    
    /**
     * description: 分片数
     * remark:
     */
    private Integer shardingTotalCount;
    
    /**
     * description: 分片参数
     * remark:
     */
    private String  shardingItemParameters;
    
    /**
     * description: 是否线性执行
     * remark:
     */
    private boolean streamingProcess;
    
}
