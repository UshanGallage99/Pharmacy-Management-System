package bo.custom.impl;

import bo.custom.OrderDetailsBO;
import dao.DAOFactory;
import dao.custom.OrderDetailsDAO;
import dto.OrderDetailsDTO;
import entity.OrderDetails;

import java.util.ArrayList;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    private OrderDetailsDAO dao1= DAOFactory.getInstance().getDao(DAOFactory.DAOType.ORDER_DETAIL);
    @Override
    public boolean addOrderDetail(ArrayList<OrderDetailsDTO> orderDetailDTOS) throws Exception {
        for (OrderDetailsDTO orderDetailDTO : orderDetailDTOS) {
            boolean b = addOrderDetail(orderDetailDTO);
            if(!b){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addOrderDetail(OrderDetailsDTO detailDTO) throws Exception {
        return dao1.save(new OrderDetails(detailDTO.getOrderid(),detailDTO.getItemcode(),detailDTO.getOrderqty()));
    }

}
