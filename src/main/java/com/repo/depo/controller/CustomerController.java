package com.repo.depo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repo.depo.model.Customer;
import com.repo.depo.repository.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping("/all")
	public List<Customer> getAll() {
		List<Customer> customers = this.customerRepository.findAll();

		return customers;
	}

	@PutMapping
	public void insert(@RequestBody Customer customer) {
		this.customerRepository.insert(customer);
	}

	@PostMapping
	public void update(@RequestBody Customer customer) {
		this.customerRepository.save(customer);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		this.customerRepository.deleteById(id);
	}

	@GetMapping("/{id}")
	public Optional<Customer> getById(@PathVariable("id") String id) {
		Optional<Customer> customer = this.customerRepository.findById(id);

		return customer;
	}
}
