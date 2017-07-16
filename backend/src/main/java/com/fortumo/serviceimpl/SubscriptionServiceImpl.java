package com.fortumo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortumo.model.Subscription;
import com.fortumo.repository.SubscriptionRepository;
import com.fortumo.service.SubscriptionService;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription getSubscription(Long id) {
        return subscriptionRepository.findOne(id);
    }

    @Override
    public void addSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepository.delete(id);
    }

    @Override
    public void updateSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription findByName(String name) {
       return subscriptionRepository.findOneByName(name);
    }
}
