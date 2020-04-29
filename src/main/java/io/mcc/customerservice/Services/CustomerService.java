package io.mcc.customerservice.Services;


import io.mcc.customerservice.domain.Customer;
import io.mcc.customerservice.exceptions.CustomerIdException;
import io.mcc.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public Iterable<Customer> getAllCustomer(){
        return repo.findAll();
    }

    public void deleteCustomerByName(String name) {
        Customer cs = repo.findCustomerByName(name.toUpperCase());
        if(cs == null) {
            throw new CustomerIdException("Customer Name: '"+name+"' Doest not exist in the list");
        }

        repo.delete(cs);


    }



    public Customer addOrUpdate(Customer customer) {
        try {

            customer.setName(customer.getName().toUpperCase());
            return repo.save(customer);


        } catch (Exception e) {
            throw new CustomerIdException("Customer Name: '" + customer.getName().toUpperCase() + "' ALREADY EXISTS");
        }
    }


    public Customer updateCustomer(Customer cs, String name, String email){

        if(cs.getName().equals(name) && cs.getEmail().equals(email)){
            cs.setName(name);
            repo.save(cs);
        }
        return cs;
    }






    public Customer getCustomerById(String customerId) {
        Customer cs = repo.findCustomerByName(customerId.toUpperCase());

        if(cs == null) {
            throw new CustomerIdException("Customer Name: '" +customerId+"' DOESNT EXIST");
        }
        return cs;
    }




}
