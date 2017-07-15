package repository;

import model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BillingRepository extends JpaRepository<Billing, Long> {

}
