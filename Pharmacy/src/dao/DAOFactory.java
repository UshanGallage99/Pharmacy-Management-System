package dao;

import bo.custom.impl.CustomerBOImpl;
import bo.custom.impl.OrderDetailsBOImpl;
import bo.custom.impl.SalaryBOImpl;
import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return (daoFactory == null) ?
                (daoFactory = new DAOFactory()  ) : (daoFactory);
    }
    public enum DAOType {
        CUSTOMER, ITEM, ORDERS, ORDER_DETAIL,APPOINTMENT,DOCTOR,SHEDULE,EMPLOYEE,SALARY,QUERY
    }
    public <T> T getDao(DAOType type){
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case ORDERS:
                return (T) new OrdersDAOImpl();
            case ORDER_DETAIL:
                return  (T) new OrderDetailsDAOImpl();
            case APPOINTMENT:
                return null;
            case DOCTOR:
                return null;
            case SHEDULE:
                return null;
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case SALARY:
                return (T) new SalaryDAOImpl();
            default:
                return null;
        }
    }
}
