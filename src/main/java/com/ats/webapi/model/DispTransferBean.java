package com.ats.webapi.model;

import java.util.List;

import com.ats.webapi.controller.RouteMaster;

public class DispTransferBean {

	
	List<Item> items;
	
	List<DispatchStationItem> reportDataList;
	
	List<RouteMaster> routeList;
	
	List<FranchiseForDispatch> frNameList;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<DispatchStationItem> getReportDataList() {
		return reportDataList;
	}

	public void setReportDataList(List<DispatchStationItem> reportDataList) {
		this.reportDataList = reportDataList;
	}

	public List<RouteMaster> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<RouteMaster> routeList) {
		this.routeList = routeList;
	}

	public List<FranchiseForDispatch> getFrNameList() {
		return frNameList;
	}

	public void setFrNameList(List<FranchiseForDispatch> frNameList) {
		this.frNameList = frNameList;
	}
	
	
	
}
