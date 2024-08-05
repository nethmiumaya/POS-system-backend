package lk.ijse.posbackend.dao;

import lk.ijse.posbackend.dto.CustomerDTO;
import lk.ijse.posbackend.dto.ItemDTO;

import java.sql.Connection;

public interface ItemDao {
    String saveItem(ItemDTO itemDTO, Connection connection) throws Exception;
    ItemDTO getItem(String itemId, Connection connection) throws Exception;
    boolean updateItem(String id,ItemDTO item,Connection connection)throws Exception;
    boolean deleteItem(String id,Connection connection)throws Exception;


}
