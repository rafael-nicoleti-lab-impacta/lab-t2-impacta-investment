package br.com.lab.impacta.investment.infrastructure.messaging.impl;

import br.com.lab.impacta.investment.domain.event.InvestmentDoneEvent;
import br.com.lab.impacta.investment.infrastructure.messaging.InvestmentSender;
import com.google.gson.GsonBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class InvestmentSenderImpl implements InvestmentSender {

    private final String NAME_QUEUE_EVENT_INVESTMENT_DONE = "eventQueueInvestmentDone";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Async
    @Override
    public void sendEventInvestmentDone(InvestmentDoneEvent investmentDoneEvent) {
        String investmentDoneEventJson = new GsonBuilder().create().toJson(investmentDoneEvent);

        this.rabbitTemplate.convertAndSend(NAME_QUEUE_EVENT_INVESTMENT_DONE, investmentDoneEventJson);
    }
}
