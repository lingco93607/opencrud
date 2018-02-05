package com.z4.zhazha.forum.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.z4.zhazha.forum.dao.Dao;

@Transactional
public abstract class DaoSupport<T> implements Dao<T> {

	@PersistenceContext
	protected EntityManager em;



	public boolean del(Object obj) {

		if (!em.contains(obj))
			obj = em.merge(obj);
		em.remove(obj);
		//em.flush();
		return true;
	}

	public int execute(String sql) {
		return execute(sql, null);
	}

	public int execute(String sql, Collection paras) {
		Query query = em.createNativeQuery(sql);
		int parameterIndex = 0;
		if (paras != null && paras.size() > 0) {
			for (Object obj : paras) {
				query.setParameter(parameterIndex++, obj);
			}
		}
		return query.executeUpdate();
	}

	public T get(Class<T> clz, Serializable id) {
		return em.find(clz, id);
	}

	public T getBy(Class<T> clz, String fieldName, Serializable value) {
		Query query = em.createQuery(
				"from " + clz.getName() + " where "+fieldName+"=?");
		query.setParameter(1, value);
		return (T) query.getResultList().get(0);
	}
	
	@SuppressWarnings("unchecked")
	public T get(Class<T> clz, String scope) {
		System.out.println(clz.getSimpleName());
		scope = "select * from " + clz.getSimpleName() + " where " + scope;
		System.out.println(scope);
		Query query = em.createNativeQuery(scope, clz);
		if(query.getResultList().size() != 0) {
			T t = (T) query.getSingleResult();
			System.out.println(t.toString());
			return (T) query.getSingleResult();
		} else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public T get(Class<T> clz, String param, String field, String value) {
		String scope = "select " + param + " from " + clz.getSimpleName() + " where " + field + "=" + value;
		System.out.println(scope);
		Query query = em.createNativeQuery(scope, clz);
		List<T> list = query.getResultList();
		if (0 != query.getResultList().size()) {
			T t = (T) query.getSingleResult();
			return t;
		}
			
		return null;		
	}
	
	/**
	 * @author xintt
	 * @param clz
	 * @param params
	 * @return
	 */
	public List query(Class clz, String[] params) {
		String scope = "select new " + clz.getSimpleName() + "("; 
		if (null != params || params.length != 0) {
			for (int i=0; i<params.length; i++) {
				if (i==(params.length - 1)) {
					scope += params[i] + ")";
				} else {
					scope += params[i] + ",";
				}
			}
		}
		scope += " from " + clz.getSimpleName();
		System.out.println(scope);
		Query query = em.createNativeQuery(scope, clz);
		
		List t = query.getResultList();
		return t;
	}

	public List query(Class clz, String scope) {
		return query(clz, scope, null);
	}

	public List query(Class clz, String scope, Collection paras) {
		return query(clz, scope, null, -1, -1);
	}

	public List query(Class clz, String scope, Collection paras, int begin,
			int max) {
		Query query = em.createQuery(
				"from " + clz.getName() + " where " + scope);
		int parameterIndex = 0;
		if (paras != null && paras.size() > 0) {
			for (Object obj : paras) {
				query.setParameter(parameterIndex++, obj);
			}
		}
		if (begin >= 0 && max > 0) {
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}
		return query.getResultList();
	}

	public boolean save(Object obj) {

		em.persist(obj);
		return true;
	}

	public Object uniqueResult(String sql) {
		return uniqueResult(sql, null);
	}

	public Object uniqueResult(String sql, Collection paras) {
		Query query = em.createQuery(sql);
		int parameterIndex = 0;
		if (paras != null && paras.size() > 0) {
			for (Object obj : paras) {
				query.setParameter(parameterIndex++, obj);
			}
		}
		return query.getSingleResult();
	}

	public boolean update(Object obj) {
		em.merge(obj);
		//em.flush();
		return true;
	}
	
	/**
	 * 获得某张表的总条数
	 */
	public Long getCount(Class clz){
		String sql = "select count(1) from "+clz.getSimpleName();
		Query query = em.createNativeQuery(sql);
		return Long.valueOf(query.getResultList().get(0).toString());
	}
	
	/**
	 * 获取符合查询条件scope的表clz总条数
	 * @author xintt
	 * @date 2017.11.06
	 */
	 public Long getCount(Class clz, String scope) {
		 String sql = "select count(1) from "+clz.getSimpleName() + " where " + scope;
		 Query query = em.createNativeQuery(sql);
			return Long.valueOf(query.getResultList().get(0).toString());
	 }
	
	public List getTop(Class clz, String scope, int limit, String orderBy){
		String sql;
		if (scope.length()>1)
		{
			sql = "select * from "+clz.getSimpleName()+" "+orderBy+" where "+scope+" limit "+limit;
		}
		else{
			sql = "select * from "+clz.getSimpleName()+" "+orderBy+" limit "+limit;
		}
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
	}
	
	/**
	 * 根据记录id删除记录
	 */
	public void delById(Class<T> clz,Set<Long> set){
		StringBuffer sql = new StringBuffer("delete from " + clz.getSimpleName() + " where 1=1 ");
		if(!set.isEmpty()){
			sql.append(" and id in ("); 
			Iterator<Long> it = set.iterator();
		   while (it.hasNext()){
			  sql.append(it.next()+" , ");
		   }
		   sql.deleteCharAt(sql.lastIndexOf(","));
		   sql.append(")");
		}
		Query query = em.createQuery(sql.toString());
		query.executeUpdate();
	}
}
