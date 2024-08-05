package lk.ijse.posbackend.bo;

import lk.ijse.posbackend.dto.CustomerDTO;
import lk.ijse.posbackend.dto.ItemDTO;

import java.sql.Connection;

public interface ItemBO {
    String saveItem(ItemDTO itemDTO, Connection connection) throws Exception;
    ItemDTO getItem(String itemId, Connection connection) throws Exception;
    boolean updateItem(String id, ItemDTO customer, Connection connection) throws Exception;
    boolean deleteItem(String id,Connection connection) throws Exception;

}