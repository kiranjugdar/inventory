package com.jugdar.inventory.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents inventory, number of items and their cost price, sell price, quantity
 */
public class Inventory {

	// Holds items in inventory. Map adds easy add/retrieve items. Tree map for sorted order of items 
	Map<String, ItemDetails> inventory = new TreeMap<String, ItemDetails>(); 
	// Holds list of items deleted from inventory
	List<ItemDetails> deletedInventory = new ArrayList<ItemDetails>();
	public Map<String, ItemDetails> getInventory() {
		return inventory;
	}
	public void setInventory(Map<String, ItemDetails> inventory) {
		this.inventory = inventory;
	}
	public List<ItemDetails> getDeletedInventory() {
		return deletedInventory;
	}
	public void setDeletedInventory(List<ItemDetails> deletedInventory) {
		this.deletedInventory = deletedInventory;
	}
	
	
		
}
