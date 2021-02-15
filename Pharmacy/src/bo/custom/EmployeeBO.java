package bo.custom;

import dto.EmployeeDTO;

import java.util.ArrayList;

public interface EmployeeBO {
    public boolean saveEmployee(EmployeeDTO dto)throws Exception;
    public boolean deleteEmployee(String id)throws Exception;
    public EmployeeDTO getEmployee(String id)throws Exception;
    public boolean updateEmployee(EmployeeDTO dto)throws Exception;
    public ArrayList<EmployeeDTO> getAllEmployee()throws Exception;

}
