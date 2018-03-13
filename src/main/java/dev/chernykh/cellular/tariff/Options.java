package dev.chernykh.cellular.tariff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.chernykh.cellular.utils.MoneySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table
public class Options {
    @Id
    @GeneratedValue
    @JsonIgnore
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    
    @Type(type = "dev.chernykh.cellular.utils.MoneyType")
    @Columns(columns = {
        @Column(name = "cur_price"),
        @Column(name = "cur_currency")
    })
    @JsonSerialize(using = MoneySerializer.class)
    private Money price;
    
    @Type(type = "dev.chernykh.cellular.utils.MoneyType")
    @Columns(columns = {
        @Column(name = "old_price"),
        @Column(name = "old_currency")
    })
    @JsonIgnore
    private Money oldPrice;
    
    @Column(name = "date_of_change")
    private LocalDate dateOfChange;
    
    public Options(long id, String name, double price, LocalDate dateOfChange) {
        this.id = id;
        this.name = name;
        this.price = Money.of(price, "RUB");
        this.dateOfChange = dateOfChange;
    }
    
    public void setPrice(double amount, String currency) {
        oldPrice = Money.of(price.getNumber(), price.getCurrency());
        price = Money.of(amount, currency);
    }
}
