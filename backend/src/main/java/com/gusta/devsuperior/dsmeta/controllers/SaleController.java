package com.gusta.devsuperior.dsmeta.controllers;

import com.gusta.devsuperior.dsmeta.entities.Sale;
import com.gusta.devsuperior.dsmeta.services.SaleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleController {

    private final SaleService service;

    @GetMapping
    public ResponseEntity<List<Sale>> findSales() {
        var allSales = service.findSales();

        if (allSales.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allSales);
    }

}
