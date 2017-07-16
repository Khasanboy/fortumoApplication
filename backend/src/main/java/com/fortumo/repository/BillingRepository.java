package com.fortumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fortumo.model.Billing;


public interface BillingRepository extends JpaRepository<Billing, Long> {

}
