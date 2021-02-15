package bo.custom;

import dto.EmployeeDTO;
import dto.SalaryDTO;

import java.util.ArrayList;

public interface SalaryBO {
    public boolean saveSalary(SalaryDTO dto)throws Exception;
    public boolean deleteSalary(String id)throws Exception;
    public SalaryDTO getSlary(String id)throws Exception;
    public boolean updateSalary(SalaryDTO dto)throws Exception;
    public ArrayList<SalaryDTO> getAllSalry()throws Exception;
}
