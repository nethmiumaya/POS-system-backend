package lk.ijse.posbackend.dao;

import lk.ijse.posbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public   interface CustomerDao {
    String saveCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException;
    CustomerDTO getCustomer(String id, Connection connection) throws SQLException;
    boolean updateCustomer(CustomerDTO customer,Connection connection)throws Exception;
    boolean deleteCustomer(String id,Connection connection)throws Exception;
    List<CustomerDTO> getAllCustomers(Connection connection) throws SQLException;
}
