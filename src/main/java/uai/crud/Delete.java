package uai.crud;

import uai.repository.CustomerJdbcTemplate;

public class Delete extends AbstractCrud{
    public static void main(String[] args) {
        CustomerJdbcTemplate customerJdbcTemplate = getCustomerJdbcTemplate();
        customerJdbcTemplate.delete(1);
        System.out.println("Customer with ID 1 deleted");
    }
}
