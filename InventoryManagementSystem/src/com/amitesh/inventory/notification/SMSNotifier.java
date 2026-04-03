package com.amitesh.inventory.notification;

public class SMSNotifier implements Notifier {

	@Override
	public void sendNotification(String message) {
		System.out.println("[SMS] Notification sent: " + message);
	}

}
