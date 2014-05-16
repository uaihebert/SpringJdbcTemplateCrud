package uai.crud;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uai.repository.CustomerJdbcTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractCrud {

    protected static ApplicationContext loadContext(){
        return new ClassPathXmlApplicationContext("spring.xml");
    }

    protected static CustomerJdbcTemplate getCustomerJdbcTemplate() {
        ApplicationContext context = loadContext();
        return (CustomerJdbcTemplate) context.getBean("customerJdbcTemplate");
    }

    protected static Date parseDate(String dateToBeParsed){
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return format.parse(dateToBeParsed);
        } catch (ParseException e) {
            return null;
        }
    }
}
