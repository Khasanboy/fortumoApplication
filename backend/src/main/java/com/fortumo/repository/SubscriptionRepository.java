package com.fortumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fortumo.model.Subscription;


public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    public Subscription findOneByName(String name);
}
