package com.amitesh.inventory;

import java.util.List;
import java.util.Scanner;

import com.amitesh.inventory.notification.EmailNotifier;
import com.amitesh.inventory.notification.Notifier;
import com.amitesh.inventory.notification.SMSNotifier;
import com.amitesh.inventory.service.InventoryMenu;
import com.amitesh.inventory.service.InventoryService;
import com.amitesh.inventory.service.ProductRepository;
import com.amitesh.inventory.service.ProductRepositoryImpl;
import com.amitesh.inventory.service.ReorderService;

public class InventoryManagementSystem {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Notifier> notifiers = List.of(new EmailNotifier(), new SMSNotifier());
		ProductRepository productRepository = new ProductRepositoryImpl();
		ReorderService reorderService = new ReorderService();

		InventoryService service = new InventoryService(productRepository, reorderService, notifiers);

		InventoryMenu.run(scanner, service);
	}

}
