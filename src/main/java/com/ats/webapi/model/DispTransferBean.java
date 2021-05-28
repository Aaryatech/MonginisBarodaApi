package com.ats.webapi.model;

import java.util.List;

import com.ats.webapi.controller.RouteMaster;
import com.ats.webapi.model.report.SpDispatchReport;
//Sachin 23-02-2021
public class DispTransferBean {

	
	List<Item> items;
	
	List<DispatchStationItem> reportDataList;
	
	List<DispatchStationItem> regSpDataList;//Sac 28-05-2021
	
	
	
	public List<DispatchStationItem> getRegSpDataList() {
		return regSpDataList;
	}

	public void setRegSpDataList(List<DispatchStationItem> regSpDataList) {
		this.regSpDataList = regSpDataList;
	}

	List<RouteMaster> routeList;
	
	List<FranchiseForDispatch> frNameList;
	
	List<SpDispatchReport> spDispList; //Sachin 24-02-2021
	List<SpecialCake> spList;//Sachin 24-02-2021
//List<String> newItemList;
	
List<SpDispatchReport> newItemList;

	public List<SpDispatchReport> getNewItemList() {
		return newItemList;
	}

	public void setNewItemList(List<SpDispatchReport> newItemList) {
		this.newItemList = newItemList;
	}
	
	

	public List<SpDispatchReport> getSpDispList() {
		return spDispList;
	}

	public void setSpDispList(List<SpDispatchReport> spDispList) {
		this.spDispList = spDispList;
	}

	public List<SpecialCake> getSpList() {
		return spList;
	}

	public void setSpList(List<SpecialCake> spList) {
		this.spList = spList;
	}

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

	@Override
	public String toString() {
		return "DispTransferBean [items=" + items + ", reportDataList=" + reportDataList + ", routeList=" + routeList
				+ ", frNameList=" + frNameList + ", spDispList=" + spDispList + ", spList=" + spList + "]";
	}
	
	
	
	
}
