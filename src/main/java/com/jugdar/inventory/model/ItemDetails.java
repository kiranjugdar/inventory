package com.jugdar.inventory.model;

import java.util.HashMap;
import java.util.Map;



public class ItemDetails {
	
	private Item item;
	private Double quantity;
	private Map<Integer, Double> profit;
	
	public ItemDetails(Item item, Double quantity, Double profit) {
		this.item = item;
		this.quantity = quantity;
		this.profit = new HashMap<Integer, Double>();
		this.profit.put(1, 0.0);
		this.profit.put(2, 0.0);
		
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	private void addFirstProfit(Double quantity) {
		Double profit = this.item.getFirstProfit(quantity);
		Double existingProfit = this.profit.get(1);  //TODO Check this do you need to add to existing profit
		this.profit.put(1, existingProfit + profit);
	}
	private void addSecondProfit(Double quantity) {
		Double profit = this.item.getSecondProfit(quantity);
		Double existingProfit = this.profit.get(2);
		this.profit.put(2, existingProfit + profit);
	}
	
	public void addProfits(Double quantity) {
		addFirstProfit(quantity);
		addSecondProfit(quantity);
	}
	
	public void clearProfits() {
		this.profit.put(1, 0.0);
		this.profit.put(2, 0.0);
	}
	
	public Double getFirstProfit() {
		return this.profit.get(1);
	}
	public Double getSecondProfit() {
		return this.profit.get(2);
	}
	
	public String toString() {
		return "Item Name :" + this.item.getName() + "Quantity : " + quantity + "first profit: " + this.profit.get(0);
	}
	
	

}
