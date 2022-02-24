package br.com.lab.impacta.investment.infrastructure.messaging;

import br.com.lab.impacta.investment.domain.event.InvestmentDoneEvent;
import org.springframework.scheduling.annotation.Async;

public interface InvestmentSender {

    @Async
    void sendEventInvestmentDone(InvestmentDoneEvent investmentDoneEvent);
}
