package com.example.demo.scheduler;

import com.example.demo.dto.UnsubscriptionRequestDto;
import com.example.demo.model.AccountId;
import com.example.demo.model.BankDetails;
import com.example.demo.model.Subscription;
import com.example.demo.model.SubscriptionType;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.services.BankDetailsService;
import com.example.demo.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PaymentChecker {
    private final SubscriptionRepository subscriptionRepository;
    private final BankDetailsService bankDetailsService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public PaymentChecker(SubscriptionRepository subscriptionRepository,
                          BankDetailsService bankDetailsService,
                          SubscriptionService subscriptionService) {
        this.subscriptionRepository = subscriptionRepository;
        this.bankDetailsService = bankDetailsService;
        this.subscriptionService = subscriptionService;
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void checkPayment() {
        final List<Subscription> subscriptions = subscriptionRepository.findAllByNextPaymentDate(LocalDate.now());

        subscriptions.forEach(subscription -> {
            final AccountId accountId = subscription.getAccountId();
            final SubscriptionType subscriptionType = subscription.getType();
            if (accountId.isFrozen()){
                subscriptionService.interrupt(
                        new UnsubscriptionRequestDto(subscriptionType.getName()),
                        accountId
                );
                return;
            }
            final BankDetails bankDetails = subscription.getBankDetails();
            final int amount = subscriptionType.getPrice();
            try {
                bankDetailsService.makePayment(accountId, bankDetails, amount);
            } catch (Exception e) {
                subscriptionService.unsubscribe(
                        new UnsubscriptionRequestDto(subscriptionType.getName()),
                        accountId
                );
            } finally {
                final LocalDate nextPaymentDate = subscription.getNextPaymentDate().plusMonths(1);
                if (!nextPaymentDate.isAfter(subscription.getEndDate())){
                    subscription.setNextPaymentDate(nextPaymentDate);
                    subscriptionRepository.save(subscription);
                }
            }
        });
    }
}
