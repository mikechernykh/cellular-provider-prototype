package dev.chernykh.cellular.notifier;

import java.time.LocalDate;
import java.util.List;

public interface NotificationOperations {
    List<Notification> findAll(String tariffName, LocalDate from, LocalDate to);
}
