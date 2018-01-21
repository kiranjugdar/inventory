package com.jugdar.inventory.service;

import java.util.ArrayList;
import com.jugdar.inventory.model.Inventory;
import com.jugdar.inventory.model.Item;
import com.jugdar.inventory.model.ItemDetails;


/**
 * Service represent inventory operations 
 */
public class InventoryService implements InventoryOperations {
	
	Inventory inventory = new Inventory();

	@Override
	public void create(String name, Double costPrice, Double sellPrice) {
		Item item = new Item(name, costPrice, sellPrice);
		ItemDetails itemDetails = new ItemDetails(item, 0.0, 0.0);
		inventory.getInventory().put(name, itemDetails);
	}

	@Override
	public void delete(String name) {
		//Remove item from inventory and add to deleted items list
		ItemDetails itemDetails = inventory.getInventory().get(name);
		inventory.getInventory().remove(name);
		inventory.getDeletedInventory().add(itemDetails);
	}

	@Override
	public void updateBuy(String name, Double quantity) {
		ItemDetails itemDetails = inventory.getInventory().get(name);
		
		//Check item has been created before updating inventory
		if (itemDetails != null) {
			itemDetails.setQuantity(quantity);
			inventory.getInventory().put(name, itemDetails);
		}
		else {
			//TODO: Throw ExceptionS
		}
		
	}

	@Override
	public void updateSell(String name, Double quantity) {
		ItemDetails itemDetails = inventory.getInventory().get(name);
		
		//Check item has been created before updating inventory
		if (itemDetails != null) {
			Double availableQuantity = itemDetails.getQuantity();
			//Check inventory if there is enough quantity to sell
			if (availableQuantity >= quantity) {
				itemDetails.setQuantity(availableQuantity - quantity);
				
				//Calculate profit on sell 
				itemDetails.addProfits(quantity);
				
				inventory.getInventory().put(name, itemDetails);
			}
			else {
				//Todo: Throw exception not enough quantity
			}
					
			
		}
		else {
			//TODO: Throw Exception
		}
		
	}
	
	@Override
	public void updateSellPrice(String name, Double sellPrice) {
		
		ItemDetails itemDetails = inventory.getInventory().get(name);
		
		if (itemDetails != null) {
			
			itemDetails.updateSellPrice(sellPrice);
		}
		else {
			//TODO: Throw Exception
		}
		
	}
	
	/**
	 * This calculates profit made on all items in inventory
	 * @return total profit 
	 */
	public Double getTotalProfit() {
		
		// Sum profits made on each item
		Double totalFirstProfit = inventory.getInventory().values()
				.stream()
				.mapToDouble(o -> o.getProfit())
				.sum();
		
		// Sum cost price of deleted items
		Double deletedItemCostPrice = inventory.getDeletedInventory().stream()
						.mapToDouble(o -> o.getItem().getCostPrice() * o.getQuantity())
						.sum();
							
		
		return totalFirstProfit - deletedItemCostPrice;
	}
	
	/**
	 * 
	 * @return Total cost price of items in inventory
	 */
	public Double getTotalValue() {
		
		Double totalValue = inventory.getInventory().values()
						.stream()
						.mapToDouble(o -> o.getItem().getCostPrice() * o.getQuantity())
						.sum();
							
		
		return totalValue;
	}
	
	public ItemDetails findItem(String name) {
		return inventory.getInventory().get(name);
	}

	@Override
	public void report() {

// This would be expected format of report		
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
		inventory.getInventory().values()
				.stream()
				.forEach(o -> {
					System.out.printf( "%10s %9s %7s %12s %6s %n", o.getItem().getName(), o.getItem().getCostPrice(), o.getItem().getSellPrice(), o.getQuantity(), (o.getItem().getCostPrice() *  o.getQuantity()));
				});

		System.out.printf( "%10s %9s %7s %12s %6s %n", "---------", "---------", "-------", "------------", "-----");
		System.out.printf( "%10s %9s  %n", "Total Value", this.getTotalValue());
		System.out.printf( "%10s %2f  %n", "Profit since previous report", this.getTotalProfit());
		
		
		//After report has been printed, clear out profits and deleted inventory.getInventory()
		//This will reset profits and deleted inventory for next report
		inventory.getInventory().values()
		.stream()
		.forEach(o -> o.clearProfits());
		
		inventory.setDeletedInventory(new ArrayList<ItemDetails>());
		
	}

}
