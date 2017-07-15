package service;

import model.Billing;
import model.Billing;

import java.util.List;

public interface BillingService {
    public List<Billing> getAllBillings();

    public Billing getBilling(Long id);

    public void addBilling(Billing billing);

    public void deleteBilling(Long id);

    public void updateBilling(Billing billing);
}
