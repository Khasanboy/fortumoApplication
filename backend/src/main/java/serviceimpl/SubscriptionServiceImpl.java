package serviceimpl;

import model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import repository.SubscriptionRepository;
import service.SubscriptionService;

import java.util.List;

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
