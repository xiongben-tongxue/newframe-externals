package com.gws.newframe.utils.query;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 自定义Query语言转Specification
 * @version 
 * @author wangdong
 *
 */
public class QueryToSpecification implements Specification {
	private BaseQuery query;
	
	
	public QueryToSpecification(BaseQuery query) {
		super();
		this.query = query;
	}

	@Override
	public Predicate toPredicate(Root root, CriteriaQuery cquery, CriteriaBuilder cb) {
		return BaseQueryPredicateBuilder.getPredicate(root, cb, cquery,this.query);
	}



}
