package bo;

import bo.custom.impl.*;
import dao.custom.impl.OrderDetailsDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {
    }
    public static BOFactory getInstance() {
        return (boFactory == null) ?
                (boFactory = new BOFactory()) : (boFactory);
    }
    public enum BOType {
        CUSTOMER, ITEM, ORDERS, ORDER_DETAIL,APPOINTMENT,DOCTOR,SHEDULE,EMPLOYEE,SALARY
    }
    public <T> T getBo(BOType type) {
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDERS:
                return (T) new OrdersBOImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailsBOImpl();
            case APPOINTMENT:
                return null;
            case DOCTOR:
                return null;
            case SHEDULE:
                return null;
            case EMPLOYEE:
                return (T) new EmployeeBOImpl();
            case SALARY:
                return (T) new SalaryBOImpl();
            default:
                return null;
        }
    }
}
