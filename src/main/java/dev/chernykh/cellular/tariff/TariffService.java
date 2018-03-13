package dev.chernykh.cellular.tariff;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class TariffService {
    private final TariffRepository repository;
    
    public TariffService(TariffRepository repository) {
        this.repository = repository;
    }
    
    public List<Tariff> getTariffs() {
        return repository.findAll();
    }
    
    public void updateTariffs() {
        Random rand = new Random();
        List<Tariff> tariffs = repository.findAll();
        LocalDate date = LocalDate.now();
        
        tariffs.stream()
            .peek(t -> t.setDateOfChange(date))
            .map(Tariff::getOptions)
            .flatMap(List::stream)
            .forEach(option -> {
                int v = 10 + rand.nextInt(1990);
                double newPrice = v / 100 + (v % 100) / 100.0;
                option.setPrice(newPrice, "RUB");
                option.setDateOfChange(date);
            });
        
        repository.saveAll(tariffs);
    }
}
