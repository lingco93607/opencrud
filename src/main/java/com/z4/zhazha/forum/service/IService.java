package com.z4.zhazha.forum.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.z4.zhazha.forum.dto.PaginationList;



public interface IService<T> {
	boolean save(T obj);

	/**
	 * 更新持久层中的对象
	 * 
	 * @param obj
	 * @return 若修改成功，则返回true，否则返回false
	 */
	boolean update(T obj);

	/**
	 * 删除持久层中的对象
	 * 
	 * @param obj
	 * @return 若删除操作成功，则返回true，否则返回false
	 */
	boolean del(T obj);

	/**
	 * 根据类及主键加载对象
	 * 
	 * @param clz
	 * @param id
	 * @return 若查找到指定主键值的持久对象，则返回该对象，否则返回null
	 */
	T get(Class<T> clz, Serializable id);

	/**
	 * 根据类、字段名及字段值加载对象，只加载一条符合条件的对象。
	 * 
	 * @param clz
	 * @param fieldName
	 * @param value
	 * @return 若查询到指定属性及值的持久对象，则返回该对象，否则返回null
	 */
	T getBy(Class<T> clz, String fieldName, Serializable value);

	/**
	 * 根据条件查询对象
	 * 
	 * @param clz
	 *            类名
	 * @param scope
	 *            查询条件
	 * @return 返回查询的记录结果记录
	 */
	List<T> query(Class<T> clz, String scope);

	/**
	 * 根据条件、条件参数查询对象
	 * 
	 * @param clz
	 *            类名
	 * @param scope
	 *            查询条件
	 * @param paras
	 *            查询参数
	 * @return 返回查询的记录结果集
	 */
	List<T> query(Class<T> clz, String scope, Collection paras);

	/**
	 * 查询符合条件的对象，从begin开始取max条记录
	 * 
	 * @param clz
	 *            Java类
	 * @param scope
	 *            查询条件
	 * @param paras
	 *            查询参数
	 * @param begin
	 *            返回有效结果开始记录数
	 * @param max
	 *            返回的最多记录数
	 * @return 返回查询的记录结果集
	 */
	List<T> query(Class<T> clz, String scope, Collection paras, int begin,
			int max);

	/**
	 * 执行sql语句，并返回一个对象,如select count(*) from tableName等
	 * 
	 * @param sql
	 *            sql语句
	 * @return 返回查询结果，若没有结果则返回null
	 */
	Object uniqueResult(String sql);

	/**
	 * 根据sql语句及查询参数执行查询，并返回一个唯一对象，如select count(*) from tableName where filed=?
	 * 
	 * @param sql
	 * @param paras
	 * @return 返回单一的查询结果，若没有结果则返回null
	 */
	Object uniqueResult(String sql, Collection paras);

	/**
	 * 执行任意sql语句，返回受影响的记录数
	 * 
	 * @param sql
	 *            sql语句
	 * @return 返回受影响的记录数
	 */
	int execute(String sql);

	/**
	 * 根据sql语句及参数执行数据库操作，返回受影响的记录数
	 * 
	 * @param sql
	 *            sql语句
	 * @param paras
	 *            参数
	 * @return 返回受影响的记录数
	 */
	int execute(String sql, Collection paras);

	/**
	 * <p>分页查询<br/>
	 * @title queryByPage<br/>
	 * @date 2014年11月26日 下午1:48:59<br/>
	 * @author dongjz<br/>
	 * @version v1.0.0
	 * </p>
	 * 
	 * @param list
	 * @param pagesize
	 * @param pagenum
	 * @return
	 * <pre>
	 * 无匹配数据：
	 * 成功：
	 * 失败：
	 * </pre>
	 */
	PaginationList queryByPage(Class<T> clz, String sql,Integer pagesize,Integer pagenum);
	
	/**
	 * 新开一个线程，返回新线程的ID
	 * 
	 * @param sql
	 *            sql语句
	 * @param paras
	 *            参数
	 * @return 返回受影响的记录数
	 */

	
	void delById(Class<T> clz,Set<Long> set);
}
