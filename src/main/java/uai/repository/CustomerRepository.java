package uai.repository;

import uai.model.Customer;

import javax.sql.DataSource;
import java.util.List;

public interface CustomerRepository {
    public void setDataSource(DataSource dataSource);

    public Customer create(Customer customer);
    public void delete(Integer customerId);
    public void update(Customer customer);

    public Customer findById(Integer customerId);
    public List<Customer> listAll();
}
