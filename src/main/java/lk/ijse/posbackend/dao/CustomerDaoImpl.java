package lk.ijse.posbackend.dao;

import lk.ijse.posbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public final class CustomerDaoImpl implements CustomerDao {


    public static String SAVE_CUSTOMER =  "INSERT INTO customer (custId,custName,custAddress,custSalary) VALUES(?,?,?,?)";

    @Override
    public String saveCustomer(CustomerDTO customer, Connection connection) throws SQLException {
        try {
            var ps = connection.prepareStatement(SAVE_CUSTOMER);
            ps.setString(1,customer.getCustId());
            ps.setString(2,customer.getCustName());
            ps.setString(3,customer.getCustAddress());
            ps.setString(4,customer.getCustSalary());

            if (ps.executeUpdate() !=0){
                return "Customer added successfully";
            }else {
                return "Customer not added";
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
