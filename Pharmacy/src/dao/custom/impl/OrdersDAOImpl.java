package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrdersDAO;
import entity.Customer;
import entity.OrderDetails;
import entity.Orders;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public int getLastOrderId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Orders ORDER BY OrderID DESC LIMIT 1");
        if (!rst.next()) {
            return 0;
        } else {
            return rst.getInt(1);
        }
    }

    @Override
    public boolean save(Orders orders) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO Orders VALUES(?,?,?)",
                        orders.getOrderid(),orders.getOrderdate(),orders.getCid());
    }

    @Override
    public boolean update(Orders orders) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Orders get(Integer integer) throws Exception {
        return null;
    }

    @Override
    public List<Orders> getAll() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Orders");
        ArrayList<Orders> orderList = new ArrayList<>();

        while(set.next()){
            orderList.add(new Orders(
                    set.getInt(1),
                    set.getString(2),
                    set.getString(3)

            ));
        }
        return orderList;
    }
}
