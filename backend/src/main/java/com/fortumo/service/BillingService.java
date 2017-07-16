package com.fortumo.service;

import java.util.List;

import com.fortumo.model.Billing;

public interface BillingService {
    public List<Billing> getAllBillings();

    public Billing getBilling(Long id);

    public void addBilling(Billing billing);

    public void deleteBilling(Long id);

    public void updateBilling(Billing billing);
    
}
