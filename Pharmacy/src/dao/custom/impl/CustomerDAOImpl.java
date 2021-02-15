package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer customer) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO Customer VALUES(?,?,?,?)",
                        customer.getNIC(),customer.getName(),customer.getAddress(),
                        customer.getContact());
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtil.execute
                ("UPDATE Customer SET Name =?,Address=?,Contact=? WHERE NIC=?",
                        customer.getName(),customer.getAddress(),customer.getContact(),customer.getNIC());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM Customer WHERE NIC=?",s);
    }

    @Override
    public Customer get(String s) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Customer WHERE NIC= ?", s);
        if(set.next()){
            return new Customer(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4)
                    );
        }else{
            return null;
        }
    }

    @Override
    public List<Customer> getAll() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customerList = new ArrayList<>();

        while(set.next()){
            customerList.add(new Customer(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4)
            ));
        }
        return customerList;
    }
}
