package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.QueryDAO;
import dao.custom.ItemDAO;
import dao.custom.OrdersDAO;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDetailsDTO;
import entity.Customer;
import entity.Item;
import entity.OrderDetails;
import entity.Orders;

import java.util.ArrayList;
import java.util.List;


public class ItemBOImpl implements ItemBO {
    private ItemDAO dao= DAOFactory.getInstance().getDao(DAOFactory.DAOType.ITEM);
    private QueryDAO qDao= DAOFactory.getInstance().getDao(DAOFactory.DAOType.QUERY);
    private OrdersDAO odao=DAOFactory.getInstance().getDao(DAOFactory.DAOType.ORDERS);
    @Override
    public boolean saveItem(ItemDTO dto) throws Exception {
        return dao.save(new Item(dto.getCode(),
                dto.getDiscription(),dto.getPacksize(),dto.getUnitprice(),
                dto.getQtyonhand(),dto.getLocation()));
    }

    @Override
    public ItemDTO getItem(String id) throws Exception {
        Item i = dao.get(id);
        return new ItemDTO(i.getCode(),i.getDiscription(),i.getPacksize(),i.getUnitprice(),i.getQtyonhand(),i.getLocation());
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws Exception {
        return dao.update(new Item(dto.getCode(),dto.getDiscription(),dto.getPacksize(),dto.getUnitprice(),dto.getQtyonhand(),dto.getLocation()));
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws Exception {
        List<Item> all = dao.getAll();
        ArrayList<ItemDTO> dtoList = new ArrayList<>();
        for(Item i : all){
            dtoList.add(new ItemDTO(
                    i.getCode(),
                    i.getDiscription(),
                    i.getPacksize(),
                    i.getUnitprice(),
                    i.getQtyonhand(),
                    i.getLocation()));
        }
        return dtoList;
    }



    @Override
    public boolean UpdateStockWhenOrder(ArrayList<OrderDetailsDTO> orderDetailDTOS) throws Exception {
        for (OrderDetailsDTO orderDetailDTO : orderDetailDTOS) {
            boolean b = UpdateStockWhenOrder(orderDetailDTO);
            if(!b){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean UpdateStockWhenOrder(OrderDetailsDTO detailDTO) throws Exception {
        return dao.updateWhenOrder(new Item(detailDTO.getItemcode(),detailDTO.getOrderqty()));
    }
}
