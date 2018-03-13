package dev.chernykh.cellular.notifier;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService service;
    
    public NotificationController(NotificationService service) {
        this.service = service;
    }
    
    @GetMapping(consumes = APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity getNotifications(@Valid RequestParams requestParams, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        List<Notification> notifications = service.getNotifications(
            requestParams.getTariffName(),
            requestParams.getDateFrom(),
            requestParams.getDateTo()
        );
        
        return ResponseEntity.ok(notifications);
    }
}
