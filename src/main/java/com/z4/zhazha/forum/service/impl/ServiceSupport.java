package com.z4.zhazha.forum.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.dto.PaginationList;
import com.z4.zhazha.forum.service.IService;

public abstract class ServiceSupport<T> implements IService<T> {

	public abstract Dao<T> getDao();
	
	//private static final Logger LOG = LoggerFactory.getLogger(ServiceSupport.class);
	

	@Override
	public boolean save(T entity) {
		return this.getDao().save(entity);
 
	}

	@Override
	public boolean update(T entity) {
		return this.getDao().update(entity);
	}

	@Override
	public T getBy(Class<T> clz, String fieldName, Serializable value) {

		return this.getDao().getBy(clz, fieldName, value);
	}

	/**
	 * 删除持久层中的对象
	 * 
	 * @param obj
	 * @return 若删除操作成功，则返回true，否则返回false
	 */
	@Override
	public boolean del(T obj) {
		return this.getDao().del(obj);
	}

	/**
	 * 根据类及主键加载对象
	 * 
	 * @param clz
	 * @param id
	 * @return 若查找到指定主键值的持久对象，则返回该对象，否则返回null
	 */
	@Override
	public T get(Class<T> clz, Serializable id) {
		return this.getDao().get(clz, id);
	}

	/**
	 * 根据条件查询对象
	 * 
	 * @param clz
	 *            类名
	 * @param scope
	 *            查询条件
	 * @return 返回查询的记录结果记录
	 */
	@Override
	public List<T> query(Class<T> clz, String scope) {
		return this.getDao().query(clz, scope);
	}

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
	@Override
	public List<T> query(Class<T> clz, String scope, Collection paras,
			int begin, int max) {
		return this.getDao().query(clz, scope, paras, begin, max);
	}

	/**
	 * 执行sql语句，并返回一个对象,如select count(*) from tableName等
	 * 
	 * @param sql
	 *            sql语句
	 * @return 返回查询结果，若没有结果则返回null
	 */
	@Override
	public Object uniqueResult(String sql) {
		return this.getDao().uniqueResult(sql);
	}

	/**
	 * 根据sql语句及查询参数执行查询，并返回一个唯一对象，如select count(*) from tableName where filed=?
	 * 
	 * @param sql
	 * @param paras
	 * @return 返回单一的查询结果，若没有结果则返回null
	 */
	@Override
	public Object uniqueResult(String sql, Collection paras) {
		return this.getDao().uniqueResult(sql, paras);
	}

	/**
	 * 执行任意sql语句，返回受影响的记录数
	 * 
	 * @param sql
	 *            sql语句
	 * @return 返回受影响的记录数
	 */
	@Override
	public int execute(String sql) {
		return this.getDao().execute(sql);
	}

	/**
	 * 根据sql语句及参数执行数据库操作，返回受影响的记录数
	 * 
	 * @param sql
	 *            sql语句
	 * @param paras
	 *            参数
	 * @return 返回受影响的记录数
	 */
	@Override
	public int execute(String sql, Collection paras) {
		return this.getDao().execute(sql, paras);
	}

	@Override
	public List<T> query(Class<T> clz, String scope, Collection paras) {
		// TODO Auto-generated method stub
		return this.getDao().query(clz, scope, paras);
	}
	
	/**
	 * 分页查询,根据clz、sql、pagesize、pagenum,获得相应条数的记录。
	 */
	@Override
	public PaginationList queryByPage(Class<T> clz, String sql,Integer pagesize,Integer pagenum){
		PaginationList list = new PaginationList();
		int first = (pagenum==0)?0:(pagesize*pagenum+1);
		int maxNum = (++pagenum)*pagesize; 
		List<T> obj = this.getDao().query(clz, sql,null, first, maxNum);
		Long num = this.getDao().getCount(clz);
		list.setList(obj);
		list.setTotalCount(num);
		return list;		
	}
	


	@Override
	public void delById(Class<T> clz,Set<Long> set){
		this.getDao().delById(clz,set);
	}
}
