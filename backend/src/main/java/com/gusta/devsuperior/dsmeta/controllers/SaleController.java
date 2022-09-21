package com.gusta.devsuperior.dsmeta.controllers;

import com.gusta.devsuperior.dsmeta.entities.Sale;
import com.gusta.devsuperior.dsmeta.services.SaleService;
import com.gusta.devsuperior.dsmeta.services.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleController {

    private final SaleService saleService;
    private final SmsService smsService;

    @GetMapping
    public ResponseEntity<Page<Sale>> findSales(@RequestParam(value = "minDate", defaultValue = "") String minDate,
                                                @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
                                                Pageable pageable) {

        var allSales = saleService.findSales(minDate, maxDate,pageable);

        if (allSales.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allSales);
    }

    @GetMapping("/{id}/notification")
    public ResponseEntity<Void> notifySms(@PathVariable("id") Long saleId) {
        smsService.sendSms(saleId);
        return ResponseEntity.noContent().build();
    }

}
