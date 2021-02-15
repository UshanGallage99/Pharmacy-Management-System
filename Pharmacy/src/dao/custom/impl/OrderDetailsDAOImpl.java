package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailsDAO;
import entity.OrderDetails;

import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean save(OrderDetails orderDetails) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO OrderDetail VALUES(?,?,?)",
                        orderDetails.getOrderid(),orderDetails.getItemcode(),orderDetails.getOrderqty());
    }

    @Override
    public boolean update(OrderDetails orderDetails) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public OrderDetails get(String s) throws Exception {
        return null;
    }

    @Override
    public List<OrderDetails> getAll() throws Exception {
        return null;
    }
}
