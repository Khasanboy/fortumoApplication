package serviceimpl;

import model.Billing;
import org.springframework.beans.factory.annotation.Autowired;
import repository.BillingRepository;
import service.BillingService;

import java.util.List;

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
