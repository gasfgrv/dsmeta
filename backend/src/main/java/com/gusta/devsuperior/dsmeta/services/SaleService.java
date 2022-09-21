package com.gusta.devsuperior.dsmeta.services;

import com.gusta.devsuperior.dsmeta.entities.Sale;
import com.gusta.devsuperior.dsmeta.repositories.SaleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleService {

    private final SaleRepository saleRepository;

    public List<Sale> findSales() {
        return saleRepository.findAll();
    }

}
