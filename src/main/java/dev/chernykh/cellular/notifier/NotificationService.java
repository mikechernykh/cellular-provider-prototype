package dev.chernykh.cellular.notifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationService {
    @Value("${notice.subject}")
    private String noticeSubject;
    @Value("${notice.message}")
    private String noticeMessage;
    private final NotificationRepository repository;
    
    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }
    
    public List<Notification> getNotifications(String tariffName, LocalDate dateFrom, LocalDate dateTo) {
        List<Notification> list = repository.findAll(tariffName, dateFrom, dateTo);
        list.forEach(n -> {
            n.setSubject(noticeSubject);
            n.setMessage(noticeMessage);
            n.replaceSubjectAndMessageWildcards();
        });
        return list;
    }
}
