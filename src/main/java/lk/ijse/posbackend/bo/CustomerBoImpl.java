package lk.ijse.posbackend.bo;

import lk.ijse.posbackend.dto.CustomerDTO;
import lk.ijse.posbackend.dao.CustomerDaoImpl;
import java.sql.Connection;
import java.util.List;

public class CustomerBoImpl implements CustomerBO{
    @Override
    public String saveCustomer(CustomerDTO customer, Connection connection) throws Exception {
        var customerDaoImp = new CustomerDaoImpl();
        return customerDaoImp.saveCustomer(customer, connection);
    }

    @Override
    public CustomerDTO getCustomer(String id, Connection connection) throws Exception {
        var customerDaoImp = new CustomerDaoImpl();
        return customerDaoImp.getCustomer(id, connection);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer, Connection connection) throws Exception {
      var customerDaoImp = new CustomerDaoImpl();
      return customerDaoImp.updateCustomer(customer, connection);
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) throws Exception {
        var customerDaoImp = new CustomerDaoImpl();
        return customerDaoImp.deleteCustomer(id, connection);
    }

    @Override
    public List<CustomerDTO> getAllCustomers(Connection connection) throws Exception {
        var customerDaoImp = new CustomerDaoImpl();
        return  customerDaoImp.getAllCustomers(connection);
    }
}
