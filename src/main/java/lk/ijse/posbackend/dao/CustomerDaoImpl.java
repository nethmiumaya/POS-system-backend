package lk.ijse.posbackend.dao;

import lk.ijse.posbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public final class CustomerDaoImpl implements CustomerDao {

    public static String UPDATE_CUSTOMER = "UPDATE customer SET custName=?,custAddress=?,custSalary=? WHERE custId=?";
    public static String GET_CUSTOMER = "SELECT * FROM customer WHERE custId=?";
    public static String SAVE_CUSTOMER =  "INSERT INTO customer (custId,custName,custAddress,custSalary) VALUES(?,?,?,?)";
    public static String DELETE_CUSTOMER = "DELETE FROM customer WHERE custId=?";

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

    @Override
    public CustomerDTO getCustomer(String id, Connection connection) throws SQLException {
        try {
            CustomerDTO customerDTO = new CustomerDTO();
            var ps = connection.prepareStatement(GET_CUSTOMER);
            ps.setString(1,id);
            var rst = ps.executeQuery();
            while (rst.next()){
                customerDTO.setCustId(rst.getString("custId"));
                customerDTO.setCustName(rst.getString("custName"));
                customerDTO.setCustAddress(rst.getString("custAddress"));
                customerDTO.setCustSalary(rst.getString("custSalary"));

            }
            return customerDTO;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean updateCustomer(String id, CustomerDTO customerDTO, Connection connection) throws SQLException {
       try {
           var ps = connection.prepareStatement(UPDATE_CUSTOMER);
           ps.setString(1,customerDTO.getCustName());
           ps.setString(2,customerDTO.getCustAddress());
           ps.setString(3,customerDTO.getCustSalary());
           ps.setString(4,id);
           return ps.executeUpdate() !=0;
       }catch (SQLException e){
           throw new SQLException(e.getMessage());
       }
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) throws Exception {
         var ps = connection.prepareStatement(DELETE_CUSTOMER);
         ps.setString(1,id);
         return ps.executeUpdate() !=0;
    }
}
