package uai.mapper;

import org.springframework.jdbc.core.RowMapper;
import uai.model.Customer;
import uai.model.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int currentRow) throws SQLException {
        Customer customer = new Customer();

        customer.setId(resultSet.getInt("id"));
        customer.setName(resultSet.getString("name"));
        customer.setBirth(resultSet.getDate("birth"));
        customer.setGender(Gender.valueOf(resultSet.getString("gender")));
        customer.setSocialSecurityNumber(resultSet.getString("social_security_number"));

        return customer;
    }
}
