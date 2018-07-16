package com.gws.newframe.utils.query;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @version 
 * @author wangdong
 * 
 */
@Data
public abstract class BaseQuery {
	//排序对象
	private Sort sort;
	private Pageable Page;


	
}
