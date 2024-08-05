package lk.ijse.posbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements Serializable {

    private String custId;
    private String custName;
    private String custAddress;
    private String custSalary;

}
