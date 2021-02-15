package bo.custom;

import dto.CustomerDTO;
import dto.OrderDetailsDTO;
import dto.OrdersDTO;
import view.tm.OrderDetailsTM;
import view.tm.OrdersTM;

import java.util.ArrayList;
import java.util.List;

public interface OrdersBO {
    public int getNewOrderId() throws Exception;
    public boolean saveOrder(OrdersDTO dto,ArrayList<OrderDetailsDTO>orderDetailsDTOS)throws Exception;
    public boolean deleteOrder(int id)throws Exception;
    public OrdersDTO getOrder(int id)throws Exception;
    public boolean updateOrder(OrdersDTO dto)throws Exception;
    public ArrayList<OrdersDTO> getAllOrders()throws Exception;
}
