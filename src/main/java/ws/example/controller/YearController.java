package ws.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ws.example.model.Customer;
import ws.example.repo.CustomerRepository;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired) )
public class YearController {
	private final CustomerRepository repository;
	
	@RequestMapping(value = "/year/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void>  delete(@PathVariable("id") Long id) {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/year/", method = RequestMethod.POST)
	public ResponseEntity<Void> addYear(@RequestBody @Valid Customer cust) {
		repository.save(cust);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/year", method = RequestMethod.GET)
	public List<Customer> listAllCust(){
		return repository.findAll();
	}
	

}
