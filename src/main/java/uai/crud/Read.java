package uai.crud;

import uai.model.Customer;
import uai.repository.CustomerJdbcTemplate;

import java.util.List;

public class Read extends AbstractCrud{
    public static void main(String[] args) {
        CustomerJdbcTemplate customerJdbcTemplate = getCustomerJdbcTemplate();

        Customer customer = customerJdbcTemplate.findById(1);
        System.out.println("found: " + customer);

        List<Customer> customers = customerJdbcTemplate.listAll();
        for (Customer listedCustomer : customers) {
            System.out.println(listedCustomer);
        }
    }
}
