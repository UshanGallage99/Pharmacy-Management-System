package bo.custom.impl;

import bo.custom.EmployeeBO;
import dao.DAOFactory;
import dao.QueryDAO;
import dao.custom.EmployeeDAO;
import dto.CustomerDTO;
import dto.EmployeeDTO;
import entity.Customer;
import entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    private EmployeeDAO dao= DAOFactory.getInstance().getDao(DAOFactory.DAOType.EMPLOYEE);
    private QueryDAO qDao= DAOFactory.getInstance().getDao(DAOFactory.DAOType.QUERY);
    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws Exception {
        return dao.save(new Employee(dto.getEmployeeID(),dto.getEname(),dto.getEaddress(),
                dto.getEcontact(),dto.getEemail()));
    }

    @Override
    public boolean deleteEmployee(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public EmployeeDTO getEmployee(String id) throws Exception {
        Employee e = dao.get(id);
        return new EmployeeDTO(e.getEmployeeID(),e.getEname(),e.getEaddress(),
                e.getEcontact(),e.getEemail());
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws Exception {
        return dao.update(new Employee(dto.getEmployeeID(),dto.getEname(),dto.getEaddress(),
                dto.getEcontact(),dto.getEemail()));
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws Exception {
        List<Employee> all = dao.getAll();
        ArrayList<EmployeeDTO> dtoList = new ArrayList<>();
        for(Employee e : all){
            dtoList.add(new EmployeeDTO(
                    e.getEmployeeID(),
                    e.getEname(),
                    e.getEaddress(),
                    e.getEcontact(),
                    e.getEemail()
            ));
        }
        return dtoList;
    }

}
