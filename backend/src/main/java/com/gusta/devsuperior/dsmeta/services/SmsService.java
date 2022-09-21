package com.gusta.devsuperior.dsmeta.services;

import com.gusta.devsuperior.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsService {

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    private final SaleRepository saleRepository;

    public void sendSms(Long saleId) {
        var sale = saleRepository.findById(saleId)
                .orElseThrow(() ->  new RuntimeException("Sale not found"));

        var message = MessageFormat.format(
                "Vendedor {0} foi destaque em {1} com o total de {2,number,currency}",
                sale.getSellerName(),
                sale.getDate().format(DateTimeFormatter.ofPattern("MM/yyyy")),
                sale.getAmount()
        );

        Twilio.init(twilioSid, twilioKey);

        var to = new PhoneNumber(twilioPhoneTo);
        var from = new PhoneNumber(twilioPhoneFrom);

        var messageToSend = Message.creator(to, from, message).create();
    }
}
