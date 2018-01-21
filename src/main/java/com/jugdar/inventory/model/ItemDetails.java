package com.jugdar.inventory.model;

/**
 * Represents Item and it's quantity and profit made from sell.
 */
public class ItemDetails {
	
	private Item item;
	private Double quantity; // Holds quantity of items
	private Double profit; // Holds profit made from sell
	
	public ItemDetails(Item item, Double quantity, Double profit) {
		this.item = item;
		this.quantity = quantity;
		this.profit = profit;
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
	
	public void addProfits(Double quantity) {
		Double profit = this.item.getProfit(quantity);
		Double existingProfit = this.profit;  
		this.profit = existingProfit + profit;
	}
	
	public void updateSellPrice(Double sellPrice) {
		this.item.updateSellPrice(sellPrice);
	}
	
	/**
	 * After report we need reset profits
	 */
	public void clearProfits() {
		this.profit = 0.0;
	}
	
	public Double getProfit() {
		return this.profit;
	}
	
	
	
}
