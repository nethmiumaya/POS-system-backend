package lk.ijse.posbackend.dao;

import lk.ijse.posbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public   interface CustomerDao {
    String saveCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException;
}
