package com.fortumo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortumo.model.Billing;
import com.fortumo.repository.BillingRepository;
import com.fortumo.service.BillingService;

import java.util.List;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    BillingRepository billingRepository;

    @Override
    public List<Billing> getAllBillings() {
        return billingRepository.findAll();
    }

    @Override
    public Billing getBilling(Long id) {
        return billingRepository.findOne(id);
    }

    @Override
    public void addBilling(Billing billing) {
        billingRepository.save(billing);
    }

    @Override
    public void deleteBilling(Long id) {
        billingRepository.delete(id);
    }

    @Override
    public void updateBilling(Billing billing) {
        billingRepository.save(billing);
    }

}
