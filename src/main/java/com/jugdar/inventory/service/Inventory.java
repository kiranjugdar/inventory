package com.jugdar.inventory.service;



public interface Inventory {

	void create (String name, Double costPrice, Double sellPrice);
	void delete (String name);
	void updateBuy(String name, Double quantity);
	void updateSell(String name, Double quantity);
	void report();
}
