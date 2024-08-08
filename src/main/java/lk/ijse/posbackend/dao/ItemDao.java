package lk.ijse.posbackend.dao;

import lk.ijse.posbackend.dto.CustomerDTO;
import lk.ijse.posbackend.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemDao {
    String saveItem(ItemDTO itemDTO, Connection connection) throws Exception;
    ItemDTO getItem(String itemId, Connection connection) throws Exception;
    boolean updateItem(ItemDTO item,Connection connection)throws Exception;
    boolean deleteItem(String id,Connection connection)throws Exception;
    List<ItemDTO> getAllItem(Connection connection) throws SQLException;

}
