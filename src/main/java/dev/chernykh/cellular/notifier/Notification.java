package dev.chernykh.cellular.notifier;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.chernykh.cellular.utils.MoneySerializer;
import lombok.Data;
import org.javamoney.moneta.Money;

import java.time.LocalDate;

@Data
public class Notification {
    private String tariffName;
    private String optionName;
    @JsonSerialize(using = MoneySerializer.class)
    private Money oldPrice;
    @JsonSerialize(using = MoneySerializer.class)
    private Money newPrice;
    private LocalDate dateOfChange;
    private String subject;
    private String message;
    
    public void setNewPrice(double price, String currency) {
        newPrice = Money.of(price, currency);
    }
    
    public void setOldPrice(double price, String currency) {
        oldPrice = Money.of(price, currency);
    }
    
    public void replaceSubjectAndMessageWildcards() {
        subject = subject.replace("{{tariffName}}", tariffName);
        
        message = message.replace("{{optionName}}", optionName);
        message = message.replace("{{tariffName}}", tariffName);
        message = message.replace("{{oldPrice}}", oldPrice.toString());
        message = message.replace("{{newPrice}}", newPrice.toString());
    }
}
