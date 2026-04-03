package com.amitesh.inventory.notification;

public class EmailNotifier implements Notifier {

	@Override
	public void sendNotification(String message) {
		System.out.println("[EMAIL] Notification sent: " + message);

	}

}
