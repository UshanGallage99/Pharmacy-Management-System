package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.EmployeeDAO;
import dao.custom.SalaryDAO;
import entity.Employee;
import entity.Salary;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public boolean save(Salary salary) throws Exception {
        return CrudUtil.execute
                ( "INSERT INTO Salary VALUES(?,?,?,?,?)",
                        salary.getEid(),salary.getBasicsalary(),salary.getEpf(),
                        salary.getEtf(),salary.getTotalsalary());
    }

    @Override
    public boolean update(Salary salary) throws Exception {
        return CrudUtil.execute
                ("UPDATE Salary SET BasicSalary =?,Epf=?,Etf=?,Totalsalary=? WHERE EID=?",
                        salary.getBasicsalary(),salary.getEpf(),salary.getEtf(),salary.getTotalsalary(),salary.getEid());

    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM Salary WHERE EID=?",s);
    }

    @Override
    public Salary get(String s) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Salary WHERE EID= ?", s);
        if(set.next()){
            return new Salary(
                    set.getString(1),
                    set.getInt(2),
                    set.getInt(3),
                    set.getInt(4),
                    set.getInt(5)
            );
        }else{
            return null;
        }
    }

    @Override
    public List<Salary> getAll() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Salary");
        ArrayList<Salary> salaryList = new ArrayList<>();

        while(set.next()){
            salaryList.add(new Salary(
                    set.getString(1),
                    set.getDouble(2),
                    set.getDouble(5)
            ));
        }
        return salaryList;
    }

}
