package com.ats.webapi.model;

import java.util.List;

public class SpCkOrderHisListNew {
List<SpCkOrderHisNew> spOrderList;
ErrorMessage errorMessage;
public List<SpCkOrderHisNew> getSpOrderList() {
	return spOrderList;
}
public void setSpOrderList(List<SpCkOrderHisNew> spOrderList) {
	this.spOrderList = spOrderList;
}
public ErrorMessage getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(ErrorMessage errorMessage) {
	this.errorMessage = errorMessage;
}
@Override
public String toString() {
	return "SpCkOrderHisListNew [spOrderList=" + spOrderList + ", errorMessage=" + errorMessage + "]";
}







}
