package com.ats.webapi.model;

import java.util.List;

public class StockTypeConfigResponse {
	
	
	List<Item> itemlist;
	
	List<StockType> stockTypelist;
	
	
	List<GetitemStockConfig> itemStockList;

	public List<Item> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<Item> itemlist) {
		this.itemlist = itemlist;
	}

	public List<StockType> getStockTypelist() {
		return stockTypelist;
	}

	public void setStockTypelist(List<StockType> stockTypelist) {
		this.stockTypelist = stockTypelist;
	}

	public List<GetitemStockConfig> getItemStockList() {
		return itemStockList;
	}

	public void setItemStockList(List<GetitemStockConfig> itemStockList) {
		this.itemStockList = itemStockList;
	}

	@Override
	public String toString() {
		return "StockTypeConfigResponse [itemlist=" + itemlist + ", stockTypelist=" + stockTypelist + ", itemStockList="
				+ itemStockList + "]";
	}

	

	
	
	
	
	

}
