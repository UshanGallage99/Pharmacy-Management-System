package bo.custom.impl;

import bo.custom.EmployeeBO;
import bo.custom.SalaryBO;
import dao.CrudUtil;
import dao.DAOFactory;
import dao.QueryDAO;
import dao.custom.EmployeeDAO;
import dao.custom.SalaryDAO;
import dto.EmployeeDTO;
import dto.SalaryDTO;
import entity.Customer;
import entity.Employee;
import entity.Item;
import entity.Salary;

import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {
    private SalaryDAO dao= DAOFactory.getInstance().getDao(DAOFactory.DAOType.SALARY);
    private QueryDAO qDao= DAOFactory.getInstance().getDao(DAOFactory.DAOType.QUERY);
    @Override
    public boolean saveSalary(SalaryDTO dto) throws Exception {
        return dao.save(new Salary(dto.getEid(),dto.getBasicsalary(),dto.getEpf(),
                dto.getEtf(),dto.getTotalsalary()));
    }

    @Override
    public boolean deleteSalary(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public SalaryDTO getSlary(String id) throws Exception {
        Salary s = dao.get(id);
        return new SalaryDTO(s.getEid(),s.getBasicsalary(),s.getEpf(),s.getEtf(),
                s.getTotalsalary());
    }

    @Override
    public boolean updateSalary(SalaryDTO dto) throws Exception {
        return dao.update(new Salary(dto.getEid(),dto.getBasicsalary(),dto.getEpf(),dto.getEtf(),dto.getTotalsalary()));
    }

    @Override
    public ArrayList<SalaryDTO> getAllSalry() throws Exception {
        List<Salary> all = dao.getAll();
        ArrayList<SalaryDTO> dtoList = new ArrayList<>();
        for(Salary s : all){
            dtoList.add(new SalaryDTO(
                    s.getEid(),
                    s.getBasicsalary(),
                    s.getTotalsalary()
            ));
        }
        return dtoList;
    }

}
