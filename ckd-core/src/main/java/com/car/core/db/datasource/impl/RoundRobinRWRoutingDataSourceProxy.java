package com.car.core.db.datasource.impl;

import com.car.core.db.datasource.AbstractReadRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

/**
 * 
 * @author chenlei
 * 简单实现读数据源负载均衡
 *
 */
public class RoundRobinRWRoutingDataSourceProxy extends AbstractReadRoutingDataSource {

	private AtomicInteger count = new AtomicInteger(0);

	@Override
	protected DataSource loadBalance() {
		int index = Math.abs(count.incrementAndGet()) % getReadDsSize();
		return getResolvedReadDataSources().get(index);
	}

}
