package bo.custom;

import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDetailsDTO;
import entity.OrderDetails;

import java.util.ArrayList;

public interface ItemBO {
    public boolean saveItem(ItemDTO dto)throws Exception;
    public ItemDTO getItem(String id)throws Exception;
    public boolean updateItem(ItemDTO dto)throws Exception;
    public boolean deleteItem(String id)throws Exception;
    public ArrayList<ItemDTO> getAllItem()throws Exception;
    public boolean UpdateStockWhenOrder(ArrayList<OrderDetailsDTO> orderDetailDTOS) throws Exception;
    public boolean UpdateStockWhenOrder(OrderDetailsDTO detailDTO) throws Exception;
}
