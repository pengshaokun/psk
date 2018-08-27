package com.zhskg.bag.model;

import java.io.Serializable;

/**
 * 库存查询统计
 * @author lwb
 */
public class GroupResult implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int productId;
	
	private long count;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
}
