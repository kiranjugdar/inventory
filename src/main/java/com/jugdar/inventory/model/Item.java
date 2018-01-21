package com.jugdar.inventory.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents Item 
 */
public class Item {
	private String name;
	private Double costPrice;
	private List<Double> sellPrice; // This holds multiple sell prices. It helps to identify last sell price
	
	public Item(String name, Double costPrice, Double sellPrice) {
		this.name = name;
		this.costPrice = costPrice;
		this.sellPrice = new ArrayList<Double>();
		this.sellPrice.add(sellPrice);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getSellPrice() {
		//Get the last updated price
		if (sellPrice != null && !sellPrice.isEmpty()) {
			return sellPrice.get(sellPrice.size() - 1);
		}
		
		return null;
	}
	
	/**
	 * This returns profit made from sell of number of quantity.
	 * It looks at last updated sell price.
	 * @param quantity - number of items sold
	 * @return profit - (last sell price - cost price) * quantity
	 */
	public Double getProfit(Double quantity) {
		if (this.getSellPrice() != null) {
			return (this.getSellPrice() - costPrice) * quantity;
		}
		else {
			return 0.0;
		}
	}
	
	public void updateSellPrice(Double sellPrice) {
		// Update the sell price by adding new sell price to array
		this.sellPrice.add(sellPrice);
	}
	
}
