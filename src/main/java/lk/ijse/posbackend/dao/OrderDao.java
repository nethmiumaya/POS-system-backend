package lk.ijse.posbackend.dao;

import lk.ijse.posbackend.dto.OrderDTO;

import java.sql.Connection;
import java.util.List;

public interface OrderDao{
    boolean saveOrder(OrderDTO orderDTO, Connection connection) throws Exception;
    List<OrderDTO> getAllOrders(Connection connection) throws Exception;
}
