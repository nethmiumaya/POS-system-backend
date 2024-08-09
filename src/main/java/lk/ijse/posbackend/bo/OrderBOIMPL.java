package lk.ijse.posbackend.bo;
import lk.ijse.posbackend.dao.OrderDaoImp;
import lk.ijse.posbackend.dao.OrderDetailDaoIMPL;
import lk.ijse.posbackend.dto.ItemDTO;
import lk.ijse.posbackend.dto.OrderDTO;
import lk.ijse.posbackend.dto.OrderDetailDTO;
import java.sql.Connection;
import java.util.List;

public class OrderBOIMPL implements OrderBO{

    @Override
    public boolean saveOrder(OrderDTO orderDTO, Connection connection) throws Exception {
        var orderDaoImpl = new OrderDaoImp();
        var orderDetailDaoImpl = new OrderDetailDaoIMPL();
        boolean isOrderSaved;
        boolean isOrderDetailSaved = true;
        connection.setAutoCommit(false);

        isOrderSaved = orderDaoImpl.saveOrder(orderDTO,connection);

        if(isOrderSaved){
            for(ItemDTO itm : orderDTO.getItems()){
                boolean isSaved = orderDetailDaoImpl.saveOrderDetail(
                        new OrderDetailDTO(
                                orderDTO.getOrderId(),
                                itm.getItemId(),
                                Integer.parseInt(itm.getItemQty())
                        ),connection);
                if(!isSaved){
                    connection.rollback();
                    isOrderDetailSaved = false;
                    break;
                }
            }
        }

        if (isOrderSaved && isOrderDetailSaved) {
            connection.commit();
        } else {
            connection.rollback();
        }
        connection.setAutoCommit(true);
        return isOrderSaved;
    }

    @Override
    public List<OrderDTO> getAllOrders(Connection connection) throws Exception {
        var orderDaoImpl = new OrderDaoImp();
        return  orderDaoImpl.getAllOrders(connection);
    }
}
