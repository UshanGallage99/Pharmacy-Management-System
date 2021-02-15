package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.QueryDAO;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO dao= DAOFactory.getInstance().getDao(DAOFactory.DAOType.CUSTOMER);
    private QueryDAO qDao= DAOFactory.getInstance().getDao(DAOFactory.DAOType.QUERY);
    @Override
    public boolean saveCustomer(CustomerDTO dto) throws Exception {
        return dao.save(new Customer(dto.getNIC(),
                dto.getName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public CustomerDTO getCustomer(String id) throws Exception {
        Customer c = dao.get(id);
        return new CustomerDTO(c.getNIC(),c.getName(), c.getAddress(),c.getContact());
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        return dao.update(new Customer(dto.getNIC(),dto.getName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws Exception {
        List<Customer> all = dao.getAll();
        ArrayList<CustomerDTO> dtoList = new ArrayList<>();
        for(Customer c : all){
            dtoList.add(new CustomerDTO(
                    c.getNIC(),
                    c.getName(),
                    c.getAddress(),
                    c.getContact()

            ));
        }
        return dtoList;
    }
}
