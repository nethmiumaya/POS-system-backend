package lk.ijse.posbackend.dao;

import lk.ijse.posbackend.dto.OrderDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImp implements OrderDao{

    public static String SAVE_ORDER = "INSERT INTO orders (order_id, date,cust_id,total,discount,subTotal,cash,balance) VALUES(?,?,?,?,?,?,?,?)";
    public static String GET_ALL_ORDERS = "SELECT * FROM orders";

    @Override
    public boolean saveOrder(OrderDTO orderDTO, Connection connection) throws Exception {
        try {
            var ps = connection.prepareStatement(SAVE_ORDER);
            ps.setString(1, orderDTO.getOrderId());
            ps.setString(2,orderDTO.getOrderDate());
            ps.setString(3,orderDTO.getCustId());
            ps.setDouble(4,orderDTO.getTotal());
            ps.setString(5,orderDTO.getDiscount());
            ps.setDouble(6,orderDTO.getSubTotal());
            ps.setDouble(7,orderDTO.getCash());
            ps.setDouble(8,orderDTO.getBalance());
            if (ps.executeUpdate() !=0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public List<OrderDTO> getAllOrders(Connection connection) throws Exception {
        try {
            List<OrderDTO> orderDTOList = new ArrayList<>();
            var ps = connection.prepareStatement(GET_ALL_ORDERS);
            var rst = ps.executeQuery();
            while (rst.next()){
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setOrderId(rst.getString(1));
                orderDTO.setOrderDate(rst.getString(2));
                orderDTO.setCustId(rst.getString(3));
                orderDTO.setTotal(rst.getDouble(4));
                orderDTO.setDiscount(rst.getString(5));
                orderDTO.setSubTotal(rst.getDouble(6));
                orderDTO.setCash(rst.getDouble(7));
                orderDTO.setBalance(rst.getDouble(8));
                orderDTOList.add(orderDTO);
            }
            return orderDTOList;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }
}
