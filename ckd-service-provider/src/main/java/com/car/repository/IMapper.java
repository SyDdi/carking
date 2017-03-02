/**
 * 
 */
package com.car.repository;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的Mapper
 */
public interface IMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
