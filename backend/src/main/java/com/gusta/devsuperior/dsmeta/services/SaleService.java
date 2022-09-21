package com.gusta.devsuperior.dsmeta.services;

import com.gusta.devsuperior.dsmeta.entities.Sale;
import com.gusta.devsuperior.dsmeta.repositories.SaleRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleService {

    private final SaleRepository saleRepository;

    public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable) {
        var today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        var min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
        var max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

        return saleRepository.findSalesBetween(min, max, pageable);
    }

}
