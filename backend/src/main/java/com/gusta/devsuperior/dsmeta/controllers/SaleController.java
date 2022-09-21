package com.gusta.devsuperior.dsmeta.controllers;

import com.gusta.devsuperior.dsmeta.entities.Sale;
import com.gusta.devsuperior.dsmeta.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleController {

    private final SaleService service;

    @GetMapping
    public ResponseEntity<Page<Sale>> findSales(@RequestParam(value = "minDate", defaultValue = "") String minDate,
                                                @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
                                                Pageable pageable) {

        var allSales = service.findSales(minDate, maxDate,pageable);

        if (allSales.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allSales);
    }

}
