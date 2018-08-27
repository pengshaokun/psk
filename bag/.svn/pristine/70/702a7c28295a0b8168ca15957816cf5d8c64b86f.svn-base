package com.zhskg.bag.service;

import com.zhskg.bag.model.GroupResult;
import com.zhskg.bag.model.Stock;
import com.zhskg.bag.param.StockParam;

import java.util.List;

public interface StockService 
{
	/**
	 * 添加数据,主要用于产品初始化，有clientId验证
	 * @param entry 实体类
	 * @return 成功返回1，失败返回0
	 */
	String add(Stock entry);

	public long countByStockState(int stockState);
	/**
	 * 根据stockId修改  主要是针对待入库状态的产品
	 * @param stocks
	 * @return
	 */
	int updateById(List<Stock> stocks);
	/**
	 * 库存分组统计查询
	 * @return
	 */
	List<GroupResult> queryStock();
	/**
	 * 根据key查询
	 * @param keys
	 * @return
	 */
	List<Stock> getByKeys(List<String> keys);


	/**
	 * 自定义查询条件
	 * @param param
	 * @return
	 */
	long countByCustom(StockParam param);
	
	/**
	 * 自定义查询条件
	 * @param param
	 * @return
	 */
	List<Stock> customSelect(StockParam param);

	/**
	 * 获取最大设备编号
	 * @param createId
	 * @param productId
	 * @return
	 */
	public Stock getMaxClientId(long createId, int productId);
	/**
	 * 修改属性
	 */
	int updateProperty(List<Stock> model);
	
	int countNoPropty(String search);
}
