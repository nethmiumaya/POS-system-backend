package lk.ijse.posbackend.bo;

import lk.ijse.posbackend.dao.ItemDaoImp;
import lk.ijse.posbackend.dto.ItemDTO;

import java.sql.Connection;
import java.util.List;

public class ItemBaoImp implements ItemBO{
    @Override
    public String saveItem(ItemDTO itemDTO, Connection connection) throws Exception {
        var itemDaoImp = new ItemDaoImp();
        return itemDaoImp.saveItem(itemDTO, connection);
    }

    @Override
    public ItemDTO getItem(String itemId, Connection connection) throws Exception {
       var itemDaoImp = new ItemDaoImp();
       return itemDaoImp.getItem(itemId, connection);
    }

    @Override
    public boolean updateItem( ItemDTO customer, Connection connection) throws Exception {
        var itemDaoImp = new ItemDaoImp();
        return itemDaoImp.updateItem(customer, connection);
    }

    @Override
    public boolean deleteItem(String id, Connection connection) throws Exception {
      var itemDaoImp = new ItemDaoImp();
      return itemDaoImp.deleteItem(id, connection);
    }

    @Override
    public List<ItemDTO> getAllItems(Connection connection) throws Exception {
        var itemDaoImp = new ItemDaoImp();
        return itemDaoImp.getAllItem(connection);
    }
}
