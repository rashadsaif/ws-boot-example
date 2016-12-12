package ws.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ws.example.model.Customer;

public interface CustomerRepository
		extends JpaRepository<Customer, Long>{


}
