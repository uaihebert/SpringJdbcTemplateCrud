package uai.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import uai.mapper.CustomerMapper;
import uai.model.Customer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomerJdbcTemplate {
    private final static String SQL_INSERT = "INSERT INTO `CRUD`.`customer` (`name`,`birth`,`gender`,`social_security_number`) " +
                                             " VALUES(?,?,?,?)";
    private final static String SQL_UPDATE = "UPDATE `CRUD`.`customer` " +
                                             " SET `name` = ?, " +
                                                 " `birth` = ?, " +
                                                 " `gender` = ?, " +
                                                 " `social_security_number` = ? " +
                                                 " WHERE `id` = ?";

    private final static String SQL_DELETE = "DELETE FROM `CRUD`.`customer` WHERE id = ?";

    private final static String SQL_FIND_BY_ID = "select * from `CRUD`.`customer` WHERE id = ?";
    private final static String SQL_FIND_ALL = "select * from `CRUD`.`customer`";

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(final Customer customer) {
        // class that will hold the generated ID
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        Date birthAsSqlTime = new Date(customer.getBirth().getTime());

                        PreparedStatement ps =
                                connection.prepareStatement(SQL_INSERT, new String[] {"name","birth","gender","social_security_number"});
                        ps.setString(1, customer.getName());
                        ps.setDate(2, birthAsSqlTime);
                        ps.setString(3, customer.getGender().toString());
                        ps.setString(4, customer.getSocialSecurityNumber());
                        return ps;
                    }
                },
                keyHolder);

        customer.setId(keyHolder.getKey().intValue());
    }

    public void delete(Integer customerId) {
        jdbcTemplate.update(SQL_DELETE, customerId);
    }

    public void update(Customer customer) {
        Date birthAsSqlTime = new Date(customer.getBirth().getTime());

        jdbcTemplate.update(
                SQL_UPDATE,
                customer.getName(),
                birthAsSqlTime,
                customer.getGender().toString(),
                customer.getSocialSecurityNumber(),
                customer.getId()
        );
    }

    public Customer findById(Integer customerId) {
        Customer customer = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new
                                                        Object[]{customerId},
                                                        new CustomerMapper());

        return customer;
    }

    public List<Customer> listAll() {
        List<Customer> customerList = jdbcTemplate.query(SQL_FIND_ALL,
                                                         new CustomerMapper());

        return customerList;
    }
}
