package lk.ijse.posbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO  implements Serializable {
    private String orderId;
    private String orderDate;
    private String custId;
    private List<ItemDTO> items = new ArrayList<>();
    private Double total;
    private String discount;
    private Double subTotal;
    private Double cash;
    private Double balance;

}
