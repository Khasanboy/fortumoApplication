package com.fortumo.service;

import java.util.List;

import com.fortumo.model.Subscription;

public interface SubscriptionService {

    public List<Subscription> getAllSubscriptions();

    public Subscription getSubscription(Long id);

    public void addSubscription(Subscription subscription);

    public void deleteSubscription(Long id);

    public void updateSubscription(Subscription subscription);

    public Subscription findByName(String name);
}
