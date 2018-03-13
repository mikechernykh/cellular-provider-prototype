package dev.chernykh.cellular.notifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class RequestParams {
    @NotBlank
    private String tariffName;
    @NotNull
    private LocalDate dateFrom;
    @NotNull
    private LocalDate dateTo;
    
    public String getTariffName() {
        return tariffName;
    }
    
    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }
    
    public LocalDate getDateFrom() {
        return dateFrom;
    }
    
    public void setDateFrom(String dateFrom) {
        this.dateFrom = LocalDate.parse(dateFrom);
    }
    
    public LocalDate getDateTo() {
        return dateTo;
    }
    
    public void setDateTo(String dateTo) {
        this.dateTo = LocalDate.parse(dateTo);
    }
}
