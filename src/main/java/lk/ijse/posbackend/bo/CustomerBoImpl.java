package lk.ijse.posbackend.bo;

import lk.ijse.posbackend.dto.CustomerDTO;
import lk.ijse.posbackend.dao.CustomerDaoImpl;

import java.sql.Connection;

public class CustomerBoImpl implements CustomerBO{
    @Override
    public String saveCustomer(CustomerDTO customer, Connection connection) throws Exception {
        var customerDaoImp = new CustomerDaoImpl();
        return customerDaoImp.saveCustomer(customer, connection);
    }
}
