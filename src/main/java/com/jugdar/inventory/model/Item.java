package com.jugdar.inventory.model;

import java.util.ArrayList;
import java.util.List;

public class Item {
	private String name;
	private Double costPrice;
	private List<Double> sellPrice;
	
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

	public Double getFirstSellPrice() {
		if (sellPrice != null && !sellPrice.isEmpty() && sellPrice.size() >= 1) {
			return sellPrice.get(0);
		}
		
		return 0.0;
	}
	
	public Double getSecondSellPrice() {
		if (sellPrice != null && !sellPrice.isEmpty() && sellPrice.size() >= 2) {
			return sellPrice.get(1);
		}
		
		return 0.0;
	}
	
	public Double getFirstProfit(Double quantity) {
		return (this.getFirstSellPrice() - costPrice) * quantity;
	}
	
	public Double getSecondProfit(Double quantity) {
		return (this.getSecondSellPrice() - costPrice) * quantity;
	}
	
	

}
