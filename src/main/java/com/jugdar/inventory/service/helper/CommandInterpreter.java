package com.jugdar.inventory.service.helper;

import com.jugdar.inventory.service.InventoryService;

public class CommandInterpreter {

	
	InventoryService inventoryService = new InventoryService();
	
	public void run(String command) {
		//create Book01 10.50 13.79
				//updateBuy Tab01 100
				//updateSell Food01 1
				//delete Book01
		String[] commandParts = command.split(" ");
		if (commandParts.length > 0) {
			String commandName = commandParts[0];
			String itemName = "";
			if (commandParts.length >= 2) {
				itemName = commandParts[1];
			}
			Double costPrice = 0.0;
			Double sellPrice = 0.0;
			Double quantity = 0.0;
			
			switch(commandName) {
				case "create":
					costPrice =  Double.valueOf(commandParts[2]);
					sellPrice =  Double.valueOf(commandParts[3]);
					inventoryService.create(itemName, costPrice, sellPrice);
				break;
				case "updateBuy":
					quantity =  Double.valueOf(commandParts[2]);
					inventoryService.updateBuy(itemName, quantity);
				break;
				case "updateSell":
					quantity =  Double.valueOf(commandParts[2]);
					inventoryService.updateSell(itemName, quantity);
				break;
				case "delete":
					inventoryService.delete(itemName);
				break;
				case "report":
					inventoryService.report();
				break;
			
				default:
				break;
			}
		}
		
		
	}

	public InventoryService getInventoryService() {
		return inventoryService;
	}


}
