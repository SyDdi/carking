package com.car.core.db.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import com.car.core.db.datasource.ConnectionHold;
import org.mybatis.spring.transaction.SpringManagedTransaction;

/**
 * 
 * @author chenlei
 *
 */
public class RWManagedTransaction extends SpringManagedTransaction {

	public RWManagedTransaction(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * {@inheritDoc}
	 */
	public void commit() throws SQLException {
		super.commit();
		Map<String, Connection> connectionMap = ConnectionHold.ConnectionContext.get();
		if(connectionMap !=null){
			for (Connection c : connectionMap.values()) {
				if(!c.isClosed() && !c.getAutoCommit()){
					try {
						c.commit();
					} catch (Exception e) {
					}
				}
			}
		}	
	}

	/**
	 * {@inheritDoc}
	 */
	public void rollback() throws SQLException {
		super.rollback();
		Map<String, Connection> connectionMap = ConnectionHold.ConnectionContext.get();
		if(connectionMap !=null){
			for (Connection c : connectionMap.values()) {
				if(!c.isClosed() && !c.getAutoCommit()){
					try {
						c.rollback();
					} catch (Exception e) {
					}
				}
			}
		}		
	}

	/**
	 * {@inheritDoc}
	 */
	public void close() throws SQLException {
		super.close();
		Map<String, Connection> connectionMap = ConnectionHold.ConnectionContext.get();
		if(connectionMap !=null){
			for (Connection c : connectionMap.values()) {
				try {
					c.close();
				} catch (Exception e) {
				}
			}
		}	
		ConnectionHold.ConnectionContext.remove();
		ConnectionHold.currentDataSource.set(ConnectionHold.WRITE);
		ConnectionHold.FORCE_WRITE.set(false);
	}
}
