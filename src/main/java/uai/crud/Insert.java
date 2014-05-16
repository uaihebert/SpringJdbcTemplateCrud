package uai.crud;

import uai.model.Customer;
import uai.model.Gender;
import uai.repository.CustomerJdbcTemplate;

public class Insert extends AbstractCrud {
    public static void main(String[] args) {
        CustomerJdbcTemplate customerJdbcTemplate = getCustomerJdbcTemplate();

        Customer customer01 = new Customer(parseDate("01/01/2001"), "John", Gender.MALE, "111111111");
        Customer customer02 = new Customer(parseDate("02/02/2002"), "Mary", Gender.FEMALE, "222222222");
        Customer customer03 = new Customer(parseDate("03/03/2003"), "Peter", Gender.MALE, "333333333");


        customerJdbcTemplate.create(customer01);
        customerJdbcTemplate.create(customer02);
        customerJdbcTemplate.create(customer03);

        System.out.println("persisted customer 01 with ID: " + customer01.getId());
        System.out.println("persisted customer 02 with ID: " + customer02.getId());
        System.out.println("persisted customer 03 with ID: " + customer03.getId());
    }
}