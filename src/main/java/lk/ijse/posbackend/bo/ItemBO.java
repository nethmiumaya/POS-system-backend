package lk.ijse.posbackend.bo;

import lk.ijse.posbackend.dto.CustomerDTO;
import lk.ijse.posbackend.dto.ItemDTO;

import java.sql.Connection;
import java.util.List;

public interface ItemBO {
    String saveItem(ItemDTO itemDTO, Connection connection) throws Exception;
    ItemDTO getItem(String itemId, Connection connection) throws Exception;
    boolean updateItem(ItemDTO customer, Connection connection) throws Exception;
    boolean deleteItem(String id,Connection connection) throws Exception;
    List<ItemDTO> getAllItems(Connection connection) throws Exception;
}
