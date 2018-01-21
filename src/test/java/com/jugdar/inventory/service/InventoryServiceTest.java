package com.jugdar.inventory.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jugdar.inventory.model.ItemDetails;


public class InventoryServiceTest {
	
	private InventoryService inventoryService;
	
	@Before
	public void setup() {
		
		inventoryService = new InventoryService();
	}
	

	@Test
	public void X_Should_Able_Create_Item() {
		String itemName = "Book01";
		Double costPrice = 10.50;
		Double sellPrice = 13.79;
		
		inventoryService.create(itemName, costPrice, sellPrice);
		
		ItemDetails itemDetails = inventoryService.findItem(itemName);
		
		Assert.assertNotNull(itemDetails);
		Assert.assertEquals(costPrice, itemDetails.getItem().getCostPrice());
		Assert.assertEquals(sellPrice, itemDetails.getItem().getFirstSellPrice());
		
	}
	
	@Test
	public void X_Should_Able_Create_Multile_Items() {
		String itemName = "Book01";
		Double costPrice = 10.50;
		Double sellPrice = 13.79;
		
		inventoryService.create(itemName, costPrice, sellPrice);
		
		String itemName2 = "Food01";
		Double costPrice2 = 1.47;
		Double sellPrice2 = 3.98;
		inventoryService.create(itemName2, costPrice2, sellPrice2);
		
		
		ItemDetails itemDetails = inventoryService.findItem(itemName);
		
		Assert.assertNotNull(itemDetails);
		Assert.assertEquals(costPrice, itemDetails.getItem().getCostPrice());
		Assert.assertEquals(sellPrice, itemDetails.getItem().getFirstSellPrice());
		
		itemDetails = inventoryService.findItem(itemName2);
		
		Assert.assertNotNull(itemDetails);
		Assert.assertEquals(costPrice2, itemDetails.getItem().getCostPrice());
		Assert.assertEquals(sellPrice2, itemDetails.getItem().getFirstSellPrice());
		
	}
	
	@Test
	public void X_Should_Able_to_Sell_Item_And_Calculate_Profit() {
		
		String itemName = "Book01";
		Double costPrice = 10.50;
		Double sellPrice = 13.79;
		Double buyQuantity = 100.00;
		Double sellQuantity = 10.00;
		
		inventoryService.create(itemName, costPrice, sellPrice);
		inventoryService.updateBuy(itemName, buyQuantity);
		inventoryService.updateSell(itemName, sellQuantity);
		ItemDetails itemDetails = inventoryService.findItem(itemName);
		
		Double profit = itemDetails.getFirstProfit();
		Double expectedProfit = (sellPrice - costPrice) * sellQuantity;
		Assert.assertEquals(expectedProfit, profit);
		
		Assert.assertEquals(expectedProfit, inventoryService.getTotalFirstProfit());
		
	}
	
	@Test
	public void X_Should_Able_to_Sell_Multiple_Items_And_Calculate_Profit() {
		
		String itemName = "Book01";
		Double costPrice = 10.50;
		Double sellPrice = 13.79;
		Double buyQuantity = 100.00;
		Double sellQuantity = 10.00;
		
		inventoryService.create(itemName, costPrice, sellPrice);
		inventoryService.updateBuy(itemName, buyQuantity);
		inventoryService.updateSell(itemName, sellQuantity);
		
		ItemDetails itemDetails = inventoryService.findItem(itemName);
		Double profit = itemDetails.getFirstProfit();
		Double expectedProfit = (sellPrice - costPrice) * sellQuantity;
		Assert.assertEquals(expectedProfit, profit);
		Double expectedTotalProfit = expectedProfit;
		Assert.assertEquals(expectedTotalProfit, inventoryService.getTotalFirstProfit());
		
		String itemName2 = "Food01";
		Double costPrice2 = 1.47;
		Double sellPrice2 = 3.98;
		Double buyQuantity2 = 50.00;
		Double sellQuantity2 = 5.00;
		
		inventoryService.create(itemName2, costPrice2, sellPrice2);
		inventoryService.updateBuy(itemName2, buyQuantity2);
		inventoryService.updateSell(itemName2, sellQuantity2);
		
		ItemDetails itemDetails2 = inventoryService.findItem(itemName2);
		Double profit2 = itemDetails2.getFirstProfit();
		Double expectedProfit2 = (sellPrice2 - costPrice2) * sellQuantity2;
		Assert.assertEquals(expectedProfit2, profit2);
		
		expectedTotalProfit = expectedProfit +  expectedProfit2;
		
		Assert.assertEquals(expectedTotalProfit, inventoryService.getTotalFirstProfit());
		
		
		
	}
	
	@Test
	public void X_Sells_And_Deletes_Item_And_Calculates_Profit() {
	
		
		String itemName = "Book01";
		Double costPrice = 10.50;
		Double sellPrice = 13.79;
		Double buyQuantity = 100.00;
		Double sellQuantity = 10.00;
		
		inventoryService.create(itemName, costPrice, sellPrice);
		inventoryService.updateBuy(itemName, buyQuantity);
		inventoryService.updateSell(itemName, sellQuantity);
		
		ItemDetails itemDetails = inventoryService.findItem(itemName);
		Double profit = itemDetails.getFirstProfit();
		Double expectedProfit = (sellPrice - costPrice) * sellQuantity;
		Assert.assertEquals(expectedProfit, profit);
		Double expectedTotalProfit = expectedProfit;
		Assert.assertEquals(expectedTotalProfit, inventoryService.getTotalFirstProfit());
		
		String itemName2 = "Food01";
		Double costPrice2 = 1.47;
		Double sellPrice2 = 3.98;
		Double buyQuantity2 = 50.00;
		Double sellQuantity2 = 5.00;
		
		inventoryService.create(itemName2, costPrice2, sellPrice2);
		inventoryService.updateBuy(itemName2, buyQuantity2);
		inventoryService.updateSell(itemName2, sellQuantity2);
		
		ItemDetails itemDetails2 = inventoryService.findItem(itemName2);
		Double profit2 = itemDetails2.getFirstProfit();
		Double expectedProfit2 = (sellPrice2 - costPrice2) * sellQuantity2;
		Assert.assertEquals(expectedProfit2, profit2);
		
		expectedTotalProfit = expectedProfit +  expectedProfit2;
		
		Assert.assertEquals(expectedTotalProfit, inventoryService.getTotalFirstProfit());
		
		///

		String itemName3 = "Med01";
		Double costPrice3 = 30.63;
		Double sellPrice3 = 34.29;
		Double buyQuantity3 = 10.00;
		Double deleteCostPrice = costPrice3 * buyQuantity3;
		
		inventoryService.create(itemName3, costPrice3, sellPrice3);
		inventoryService.updateBuy(itemName3, buyQuantity3);
		inventoryService.delete(itemName3);
		
		expectedTotalProfit = expectedTotalProfit - deleteCostPrice;
		Assert.assertEquals(expectedTotalProfit, inventoryService.getTotalFirstProfit());
		
		
	}
	
	

}
