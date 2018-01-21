package com.jugdar.inventory.service.helper;


import org.junit.Assert;
import org.junit.Test;

import com.jugdar.inventory.model.ItemDetails;

public class CommandInterpreterTest {
	
	CommandInterpreter commandInterpreter = new CommandInterpreter();
	
	@Test
	public void run_create_command() {
		
		commandInterpreter.run("create Book01 10.50 13.79");
		ItemDetails itemDetails = commandInterpreter.getInventoryService().findItem("Book01");
		Double costPrice = 10.50;
		Double sellPrice =  13.79;
		
		Assert.assertNotNull(itemDetails);
		Assert.assertEquals(costPrice, itemDetails.getItem().getCostPrice());
		Assert.assertEquals(sellPrice, itemDetails.getItem().getSellPrice());
		
		
	}
	
	@Test
	public void run_report_command() {
		
		commandInterpreter.run("create Book01 10.50 13.79");
		commandInterpreter.run("create Food01 1.47 3.98");
		commandInterpreter.run("create Med01 30.63 34.29");
		commandInterpreter.run("create Tab01 57.00 84.98");
		commandInterpreter.run("updateBuy Tab01 100");
		commandInterpreter.run("updateSell Tab01 2");
		commandInterpreter.run("updateBuy Food01 500");
		commandInterpreter.run("updateBuy Book01 100");
		commandInterpreter.run("updateBuy Med01 100");
		commandInterpreter.run("updateSell Food01 1");
		commandInterpreter.run("updateSell Food01 1");
		commandInterpreter.run("updateSell Tab01 2");
		commandInterpreter.run("report");
	}
	
	@Test
	public void run_report_twice_command() {
		
//      	INVENTORY REPORT
//Item Name 	Bought At    	Sold At  	AvailableQty    	Value
//--------- 	---------    	-------  	-----------     	-------
//Food01          	1.47      	3.98       	493           	724.71
//Med01          	30.63     	34.29        	90          	2756.70
//Mobile01       	10.51     	44.56       	246          	2585.46
//Tab01          	57.00     	84.98        	91          	5187.00
//---------------------------------------------------------------------------
//Total value                                                   	11253.87
//Profit since previous report                                   	-724.75
		
		commandInterpreter.run("create Book01 10.50 13.79");
		commandInterpreter.run("create Food01 1.47 3.98");
		commandInterpreter.run("create Med01 30.63 34.29");
		commandInterpreter.run("create Tab01 57.00 84.98");
		commandInterpreter.run("updateBuy Tab01 100");
		commandInterpreter.run("updateSell Tab01 2");
		commandInterpreter.run("updateBuy Food01 500");
		commandInterpreter.run("updateBuy Book01 100");
		commandInterpreter.run("updateBuy Med01 100");
		commandInterpreter.run("updateSell Food01 1");
		commandInterpreter.run("updateSell Food01 1");
		commandInterpreter.run("updateSell Tab01 2");
		commandInterpreter.run("report");
		commandInterpreter.run("delete Book01");
		commandInterpreter.run("updateSell Tab01 5");
		commandInterpreter.run("create Mobile01 10.51 44.56");
		commandInterpreter.run("updateBuy Mobile01 250");
		commandInterpreter.run("updateSell Food01 5");
		commandInterpreter.run("updateSell Mobile01 4");
	    commandInterpreter.run("updateSell Med01 10");
		commandInterpreter.run("report");
		
	}
	
	@Test
	public void update_sellprice_run_report_command() {
		
		Double profit = (13.79 - 10.50) * 10;
		Double profit2 = (11.79 - 10.50) * 10;
		Double totalProfit =  116.94 + profit + profit2;
		
		System.out.println("totalProfit should be " +  totalProfit);
		
		commandInterpreter.run("create Book01 10.50 13.79");
		commandInterpreter.run("create Food01 1.47 3.98");
		commandInterpreter.run("create Med01 30.63 34.29");
		commandInterpreter.run("create Tab01 57.00 84.98");
		commandInterpreter.run("updateBuy Tab01 100");
		commandInterpreter.run("updateSell Tab01 2");
		commandInterpreter.run("updateBuy Food01 500");
		commandInterpreter.run("updateBuy Book01 100");
		commandInterpreter.run("updateBuy Med01 100");
		commandInterpreter.run("updateSell Food01 1");
		commandInterpreter.run("updateSell Food01 1");
		commandInterpreter.run("updateSell Tab01 2");
		commandInterpreter.run("updateSell Book01 10");
		commandInterpreter.run("updateSellPrice Book01 11.79");
		commandInterpreter.run("updateSell Book01 10");
		commandInterpreter.run("report");
		
		
	}
}