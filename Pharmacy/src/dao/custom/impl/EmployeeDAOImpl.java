package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.EmployeeDAO;
import entity.Customer;
import entity.Employee;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee employee) throws Exception {
            return CrudUtil.execute
                    ("INSERT INTO Employee VALUES(?,?,?,?,?)",
                            employee.getEmployeeID(),employee.getEname(),employee.getEaddress(),
                            employee.getEcontact(),employee.getEemail());

    }

    @Override
    public boolean update(Employee employee) throws Exception {
        return CrudUtil.execute
                ("UPDATE Employee SET EName=?,EAddress=?,EContact=?,EEmail=? WHERE  EmployeeID=?",
                        employee.getEname(),employee.getEaddress(),employee.getEcontact(),employee.getEemail(),employee.getEmployeeID());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM Employee WHERE EmployeeID=?",s);
    }

    @Override
    public Employee get(String s) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Employee WHERE EmployeeID= ?", s);
        if(set.next()){
            return new Employee(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5)
            );
        }else{
            return null;
        }
    }

    @Override
    public List<Employee> getAll() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Employee");
        ArrayList<Employee> employeeList = new ArrayList<>();

        while(set.next()){
            employeeList.add(new Employee(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5)
            ));
        }
        return employeeList;
    }

}
