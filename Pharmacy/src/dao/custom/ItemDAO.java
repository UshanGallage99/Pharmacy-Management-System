package dao.custom;

import dao.CrudDAO;
import entity.Item;
import entity.OrderDetails;
import org.apache.poi.hssf.record.formula.functions.T;

import javax.persistence.Id;

public interface ItemDAO extends CrudDAO<Item,String> {
    boolean updateWhenOrder(Item item) throws Exception;
}
