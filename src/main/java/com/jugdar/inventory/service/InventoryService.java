package com.jugdar.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.jugdar.inventory.model.Item;
import com.jugdar.inventory.model.ItemDetails;


public class InventoryService implements Inventory {
	
	Map<String, ItemDetails> inventory = new TreeMap<String, ItemDetails>();
	List<ItemDetails> deletedInventory = new ArrayList<ItemDetails>();
	

	@Override
	public void create(String name, Double costPrice, Double sellPrice) {
		Item item = new Item(name, costPrice, sellPrice);
		ItemDetails itemDetails = new ItemDetails(item, 0.0, 0.0);
		inventory.put(name, itemDetails);
	}

	@Override
	public void delete(String name) {
		ItemDetails itemDetails = inventory.get(name);
		inventory.remove(name);
		deletedInventory.add(itemDetails);
	}

	@Override
	public void updateBuy(String name, Double quantity) {
		ItemDetails itemDetails = inventory.get(name);
		
		if (itemDetails != null) {
			itemDetails.setQuantity(quantity);
			inventory.put(name, itemDetails);
		}
		else {
			//TODO: Throw ExceptionS
		}
		
	}

	@Override
	public void updateSell(String name, Double quantity) {
		ItemDetails itemDetails = inventory.get(name);
		
		if (itemDetails != null) {
			Double availableQuantity = itemDetails.getQuantity();
			//Check inventory if there is enough quantity to sell
			if (availableQuantity >= quantity) {
				itemDetails.setQuantity(availableQuantity - quantity);
				
				itemDetails.addProfits(quantity);
				
				inventory.put(name, itemDetails);
			}
			else {
				//Todo: Throw exception not enough quantity
			}
					
			
		}
		else {
			//TODO: Throw Exception
		}
		
	}
	
	public Double getTotalFirstProfit() {
		
		Double totalFirstProfit = inventory.values()
				.stream()
				.mapToDouble(o -> o.getFirstProfit())
				.sum();
		
		Double deletedItemCostPrice = deletedInventory.stream()
						.mapToDouble(o -> o.getItem().getCostPrice() * o.getQuantity())
						.sum();
							
		
		return totalFirstProfit - deletedItemCostPrice;
	}
	
	public Double getTotalValue() {
		
		Double totalValue = inventory.values()
						.stream()
						.mapToDouble(o -> o.getItem().getCostPrice() * o.getQuantity())
						.sum();
							
		
		return totalValue;
	}
	
	public ItemDetails findItem(String name) {
		return inventory.get(name);
	}

	@Override
	public void report() {
//      	INVENTORY REPORT
//Item Name 	Bought At    	Sold At       	AvailableQty    	Value
//--------- 	---------    	-------       	-----------     	-------
//Book01    	10.50          	13.79               	100    	1050.00
//Food01     	1.47           	3.98               	498     	732.06
//Med01     	30.63          	34.29               	100    	3063.00
//Tab01     	57.00          	84.98                	96    	5472.00
//---------------------------------------------------------------------------
//Total value                                                     	10317.06
//Profit since previous report                                      	116.94
		
		System.out.printf( "%10s %n", "Inventory Report");
		System.out.printf( "%10s %9s %7s %12s %6s %n", "Item Name", "Bought At", "Sold At", "AvailableQty", "Value");
		System.out.printf( "%10s %9s %7s %12s %6s %n", "---------", "---------", "-------", "------------", "-----");
		inventory.values()
				.stream()
				.forEach(o -> {
					System.out.printf( "%10s %9s %7s %12s %6s %n", o.getItem().getName(), o.getItem().getCostPrice(), o.getItem().getFirstSellPrice(), o.getQuantity(), (o.getItem().getCostPrice() *  o.getQuantity()));
				});

		System.out.printf( "%10s %9s %7s %12s %6s %n", "---------", "---------", "-------", "------------", "-----");
		System.out.printf( "%10s %9s  %n", "Total Value", this.getTotalValue());
		System.out.printf( "%10s %2f  %n", "Profit since previous report", this.getTotalFirstProfit());
		
		
		inventory.values()
		.stream()
		.forEach(o -> o.clearProfits());
		
		deletedInventory = new ArrayList<ItemDetails>();
		
	}

}
