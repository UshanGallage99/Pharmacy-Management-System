package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Customer;
import entity.Item;
import entity.OrderDetails;
import org.apache.poi.hssf.record.formula.functions.T;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item item) throws Exception {
        return CrudUtil.execute
                ( "INSERT INTO Item VALUES(?,?,?,?,?,?)",
                         item.getCode(),item.getDiscription(),item.getPacksize(),
                        item.getUnitprice(),item.getQtyonhand(),item.getLocation());
    }

    @Override
    public boolean update(Item item) throws Exception {
        return CrudUtil.execute
                ("UPDATE Item SET Discription =?,PackSize=?,UnitPrice=?,QtyOnHand=?,Location=? WHERE ItemCode=?",
                        item.getDiscription(),item.getPacksize(),item.getUnitprice(),item.getQtyonhand(),item.getLocation(),item.getCode());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM Item WHERE ItemCode=?",s);
    }

    @Override
    public Item get(String s) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM item WHERE ItemCode= ?", s);
        if(set.next()){
            return new Item(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getDouble(4),
                    set.getInt(5),
                    set.getString(6)
            );
        }else{
            return null;
        }
    }

    @Override
    public List<Item> getAll() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<Item> itemlist = new ArrayList<>();

        while(set.next()){
            itemlist.add(new Item(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getDouble(4),
                    set.getInt(5),
                    set.getString(6)
            ));
        }
        return itemlist;
    }

    @Override
    public boolean updateWhenOrder(Item item) throws Exception {
        return CrudUtil.execute("UPDATE Item SET QtyOnHand =( QtyOnHand - ?)  WHERE ItemCode = ?",
                item.getQtyonhand(),item.getCode());
    }
}
