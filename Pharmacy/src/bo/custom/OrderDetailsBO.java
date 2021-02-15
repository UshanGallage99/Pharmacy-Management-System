package bo.custom;

import dto.OrderDetailsDTO;

import java.util.ArrayList;

public interface OrderDetailsBO {
    public boolean addOrderDetail(ArrayList<OrderDetailsDTO> orderDetailDTOS) throws Exception;
    public boolean addOrderDetail(OrderDetailsDTO detailDTO) throws Exception;
}
