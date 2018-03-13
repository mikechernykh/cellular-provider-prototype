package dev.chernykh.cellular.tariff;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tariffs")
public class TariffController {
    private final TariffService service;
    
    public TariffController(TariffService service) {
        this.service = service;
    }
    
    @GetMapping
    public List<Tariff> tariffs() {
        return service.getTariffs();
    }
    
    @GetMapping("/update")
    public ResponseEntity updateTariffs() {
        service.updateTariffs();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
