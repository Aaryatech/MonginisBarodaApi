package com.ats.webapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ItemAndItemSuplement {
@Id

	Item item;
	ItemSup itemSup;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public ItemSup getItemSup() {
		return itemSup;
	}
	public void setItemSup(ItemSup itemSup) {
		this.itemSup = itemSup;
	}
	
	
}
