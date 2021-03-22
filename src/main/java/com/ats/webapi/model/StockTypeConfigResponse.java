package com.ats.webapi.model;

import java.util.List;

public class StockTypeConfigResponse {
	
	
	List<Item> itemlist;
	
	List<StockType> stockTypelist;

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

	@Override
	public String toString() {
		return "StockTypeConfigResponse [itemlist=" + itemlist + ", stockTypelist=" + stockTypelist + "]";
	}
	
	
	
	
	

}
