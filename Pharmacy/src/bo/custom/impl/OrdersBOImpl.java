package bo.custom.impl;

import bo.BOFactory;
import bo.custom.ItemBO;
import bo.custom.OrderDetailsBO;
import bo.custom.OrdersBO;
import com.mongodb.DBPortPool;
import dao.DAOFactory;
import dao.QueryDAO;
import dao.custom.ItemDAO;
import dao.custom.OrderDetailsDAO;
import dao.custom.OrdersDAO;
import db.DBConnection;
import dto.ItemDTO;
import dto.OrderDetailsDTO;
import dto.OrdersDTO;
import entity.Item;
import entity.OrderDetails;
import entity.Orders;

import java.awt.print.PrinterAbortException;
import java.util.ArrayList;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {
    private OrdersDAO dao= DAOFactory.getInstance().getDao(DAOFactory.DAOType.ORDERS);
    private OrderDetailsDAO dao1= DAOFactory.getInstance().getDao(DAOFactory.DAOType.ORDER_DETAIL);
    private QueryDAO qDao= DAOFactory.getInstance().getDao(DAOFactory.DAOType.QUERY);
    private ItemDAO idao= DAOFactory.getInstance().getDao(DAOFactory.DAOType.ITEM);
    private ItemBO bo=BOFactory.getInstance().getBo(BOFactory.BOType.ITEM);
    private OrderDetailsBO bo1=BOFactory.getInstance().getBo(BOFactory.BOType.ORDER_DETAIL);

    @Override
    public int getNewOrderId() throws Exception {

        int lastOrderId = dao.getLastOrderId();

            if (lastOrderId != 0) {
                lastOrderId = (lastOrderId + 1);
                return (lastOrderId);
            } else {
               return 0;
            }
    }

    @Override
    public boolean saveOrder(OrdersDTO dto, ArrayList<OrderDetailsDTO> orderDetailsDTOS ) throws Exception {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isSaved = dao.save(new Orders(dto.getOrderid(), dto.getOrderdate(),dto.getCid()));
            boolean isDetailSaved = bo1.addOrderDetail(orderDetailsDTOS);
            /*boolean isDetailSaved = false;
            for (OrderDetailsDTO d:orderDetailsDTOS){
                isDetailSaved = dao1.save(new OrderDetails(d.getOrderid(),d.getItemcode(),d.getOrderqty()));
            }*/
           boolean isUpdated=bo.UpdateStockWhenOrder(orderDetailsDTOS);
            //System.out.println("ABCD");
            if (isSaved && isDetailSaved ) {
                DBConnection.getInstance().getConnection().commit();
                System.out.println("pass");
                return true;
            } else {
                DBConnection.getInstance().getConnection().rollback();
                System.out.print("fail");
                return false;
            }

        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
            System.out.println("1");
        }

    }

    @Override
    public boolean deleteOrder(int id) throws Exception {
        return false;
    }

    @Override
    public OrdersDTO getOrder(int id) throws Exception {
        return null;
    }

    @Override
    public boolean updateOrder(OrdersDTO dto) throws Exception {
        return false;
    }

    @Override
    public ArrayList<OrdersDTO> getAllOrders() throws Exception {
        List<Orders> all = dao.getAll();
        ArrayList<OrdersDTO> dtoList = new ArrayList<>();
        for(Orders o : all){
            dtoList.add(new OrdersDTO(
                    o.getOrderid(),
                    o.getOrderdate(),
                    o.getCid()
            ));
        }
        return dtoList;
    }
}
