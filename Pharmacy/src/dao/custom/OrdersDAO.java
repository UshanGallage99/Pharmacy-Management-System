package dao.custom;

import dao.CrudDAO;
import entity.Customer;
import entity.Orders;

public interface OrdersDAO extends CrudDAO<Orders, Integer> {
    int getLastOrderId() throws Exception;
}
