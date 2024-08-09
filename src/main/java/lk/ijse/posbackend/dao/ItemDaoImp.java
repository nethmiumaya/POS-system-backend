package lk.ijse.posbackend.dao;
import lk.ijse.posbackend.dto.ItemDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImp implements ItemDao {

    public static String GET_ALL_ITEMS = "SELECT * FROM item";
    public static String UPDATE_ITEM ="UPDATE item SET itemName=?,itemQty=?,itemPrice=? WHERE itemId=?";
    public static String SAVE_ITEM= "INSERT INTO item (itemId,itemName,itemQty,itemPrice) VALUES(?,?,?,?)";
    public static String GET_ITEM = "SELECT * FROM item WHERE itemId=?";
    public static String DELETE_ITEM = "DELETE FROM item WHERE itemId=?";

    @Override
    public String saveItem(ItemDTO itemDTO, Connection connection) throws Exception {
        try {
            var ps = connection.prepareStatement(SAVE_ITEM);
            ps.setString(1, itemDTO.getItemId());
            ps.setString(2, itemDTO.getItemName());
            ps.setString(3, itemDTO.getItemQty());
            ps.setString(4, itemDTO.getItemPrice());

            if (ps.executeUpdate() == 0) {
                return "Item added successfully";
            } else {
                return "  ";
            }
        } catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public ItemDTO getItem(String itemId, Connection connection) throws Exception {
       try {
           ItemDTO itemDTO = new ItemDTO();
           var ps = connection.prepareStatement(GET_ITEM);
           ps.setString(1, itemId);
           var rst = ps.executeQuery();
           while (rst.next()){
               itemDTO.setItemId(rst.getString("itemId"));
               itemDTO.setItemName(rst.getString("itemName"));
               itemDTO.setItemQty(rst.getString("itemQty"));
               itemDTO.setItemPrice(rst.getString("itemPrice"));
           }
           return itemDTO;
       } catch (Exception e){
           throw new SQLException(e.getMessage());
       }
    }

    @Override
    public boolean updateItem( ItemDTO item, Connection connection) throws Exception {
       try {
           var ps = connection.prepareStatement(UPDATE_ITEM);
           ps.setString(1,item.getItemName());
           ps.setString(2,item.getItemQty());
           ps.setString(3,item.getItemPrice());
           ps.setString(4,item.getItemId());
           return ps.executeUpdate() !=0;
       } catch (SQLException e){
           throw new SQLException(e.getMessage());
       }
    }

    @Override
    public boolean deleteItem(String id, Connection connection) throws Exception {
        var ps = connection.prepareStatement(DELETE_ITEM);
        ps.setString(1,id);
        return ps.executeUpdate() !=0;
    }

    @Override
    public List<ItemDTO> getAllItem(Connection connection) throws SQLException {
        try {
            List<ItemDTO> itemDTOList = new ArrayList<>();
            var ps = connection.prepareStatement(GET_ALL_ITEMS);
            var rst = ps.executeQuery();
            while (rst.next()){
              ItemDTO itemDTO = new ItemDTO();

              itemDTO.setItemId(rst.getString("itemId"));
              itemDTO.setItemName(rst.getString("itemName"));
              itemDTO.setItemQty(rst.getString("itemQty"));
              itemDTO.setItemPrice(rst.getString("itemPrice"));
              itemDTOList.add(itemDTO);
            }
            return itemDTOList;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }
}
