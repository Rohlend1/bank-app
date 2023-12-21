package org.bank.globalutils.notificationservice.utils.errors;

public class NotificationNotFoundException extends RuntimeException{

    public NotificationNotFoundException(Long id) {
        super(String.format("Notification with id {%d} wasn't found", id));
    }

    public NotificationNotFoundException(String message) {
        super(message);
    }
}
