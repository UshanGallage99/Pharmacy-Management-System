package controller;
import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.OrderDetailsBO;
import bo.custom.OrdersBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.sun.media.jfxmediaimpl.HostUtils;
import db.DBConnection;
import dto.*;
import entity.Item;
import entity.Orders;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.CustomerTM;
import view.tm.ItemTM;
import view.tm.OrderDetailsTM;
import view.tm.OrdersTM;

import javax.management.Notification;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static javax.xml.bind.JAXBIntrospector.getValue;


public class CashierMainFormController {
    public JFXTabPane root;
    public JFXButton btnAddCustomer;
    public JFXButton btnUpdateCustomer;
    public JFXButton btnSearchCustomer;
    public JFXTextField txtNIC;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtContact;
    public JFXButton btnDeleteCustomer;
    public JFXTextField txtItemCode;
    public JFXTextField txtDiscription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXButton btnAddItem;
    public JFXButton btnUpdateItem;
    public JFXButton btnSearchItem;
    public JFXButton btnDeleteItem;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtLocation;
    public JFXTextField txtOrderID;
    public JFXTextField txtOrderDate;
    public JFXTextField txtCustomerID;
    public TableView bt2;
    public JFXButton btnSearchItem1;
    public TableView bt11;
    public TableView bt1;
    public JFXButton btnAddToList;
    public JFXButton btnPlaceOrder;
    public JFXButton btnClearAll;
    public JFXButton btnRemoveItem;
    public TableView tblCustomer;
    public TableColumn colNIC;
    public TableColumn colCustomerName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableView bt;
    public TableColumn colItemCode;
    public TableColumn colDiscription;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colLocation;
    public JFXComboBox cmbCustomerId;
    public JFXComboBox cmbItemCode;
    public Label lblDate;
    public Label lblOrderID;
    public Label lblCustomerName;
    public Label lblQtyOnHand;
    public JFXTextField txtlocation;
    public JFXTextField txtunitprice;
    public JFXTextField txtqty;
    public JFXTextField txtdiscription;
    public JFXTextField txtpacksize;
    public TableView<OrderDetailsTM> tblPlaceOrder;
    public TableColumn colitemcode;
    public TableColumn coldiscriptin;
    public TableColumn collocation;
    public TableColumn colunitprice;
    public TableColumn colqty;
    public TableColumn colamount;
    public Label lblTotal;
    public TableView tblOrdrs;
    public TableColumn colOrdrsID;
    public TableColumn colOrderDate;
    public TableColumn colCustomerID;
    public JFXButton btnRemoveRow;
    public JFXButton btnClearTable;
    public Label lblcustomer;
    public Label lblitem;
    public Label lblorders;
    public ImageView img1;
    private CustomerBO bo;
    private ItemBO bo1;
    private OrdersBO bo2;
    private OrderDetailsBO bo3;
    public ObservableList observableList;


    public void initialize() {
        bo = BOFactory.getInstance().getBo(BOFactory.BOType.CUSTOMER);
        bo1 = BOFactory.getInstance().getBo(BOFactory.BOType.ITEM);
        bo2 = BOFactory.getInstance().getBo(BOFactory.BOType.ORDERS);
        generateOrderId();
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        loadAllCustomer();
        loadCustomer();
        bt.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData((CustomerTM) newValue);

        });

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDiscription.setCellValueFactory(new PropertyValueFactory<>("discription"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packsize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyonhand"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        loadAllItem();
        loadItem();
        LocalDate today = LocalDate.now();
        lblDate.setText(today.toString());
        bt1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData2((ItemTM) newValue);

        });

        colOrdrsID.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("cid"));
        loadAllOrders();
        tblOrdrs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData3((OrdersTM) newValue);

        });
        //calculateQtyOnHand();
        setLable();
        setLable1();
        setLable2();

    }
    private void setLable() {
        List<CustomerDTO> allcustomer = null;
        try {
            allcustomer = bo.getAllCustomer();
        } catch (Exception e) {
            System.out.println("lable");
        }
        lblcustomer.setText(String.valueOf(allcustomer.size()));

    }
    private void setLable1()  {
        List<ItemDTO> allitem = null;
        try {
            allitem = bo1.getAllItem();
        } catch (Exception e) {
            System.out.println("lable");
        }
        lblitem.setText("Number of Student :"+String.valueOf(allitem.size()));

    }
    private void setLable2() {
        List<OrdersDTO> allitem = null;
        try {
            allitem = bo2.getAllOrders();
        } catch (Exception e) {
            System.out.println("lable");
        }
        lblorders.setText(String.valueOf(allitem.size()));

    }

    public void setCustomer(ActionEvent actionEvent)  {
            bo = BOFactory.getInstance()
                    .getBo(BOFactory.BOType.CUSTOMER);
        CustomerDTO dto = null;
        try {
            dto = bo.getCustomer(cmbCustomerId.getValue().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        lblCustomerName.setText(dto.getName());
        }
    public void setItem(ActionEvent actionEvent){
        bo1 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.ITEM);
        ItemDTO dto = null;
        try {
            dto = bo1.getItem(cmbItemCode.getValue().toString());
        } catch (Exception e) {
            System.out.println("2");
        }
        txtdiscription.setText(dto.getDiscription());
        txtpacksize.setText(dto.getPacksize());
        txtunitprice.setText(dto.getUnitprice()+"");
        txtlocation.setText(dto.getLocation());
        lblQtyOnHand.setText(dto.getQtyonhand()+"");
        txtqty.requestFocus();

    }



    private void setData(CustomerTM tm) {
        try {
            txtNIC.setText(tm.getNIC());
            txtCustomerName.setText(tm.getName());
            txtCustomerAddress.setText(tm.getAddress());
            txtContact.setText(tm.getContact());
        }catch(Exception e){
                System.out.println("1");
            }



    }
    private void setData2(ItemTM tm) {
        try {
        txtItemCode.setText(tm.getCode());
        txtDiscription.setText(tm.getDiscription());
        txtPackSize.setText(tm.getPacksize());
        txtUnitPrice.setText(tm.getUnitprice()+"");
        txtQtyOnHand.setText(tm.getQtyonhand()+"");
        txtLocation.setText(tm.getLocation());
        }catch(Exception e){
            System.out.println("1");
        }

    }
    private void setData3(OrdersTM tm) {
        try {
        txtOrderID.setText(tm.getOrderid()+"");
        txtOrderDate.setText(tm.getOrderdate());
        txtCustomerID.setText(tm.getCid());
        }catch(Exception e){
            System.out.println("1");
        }

    }
    public void loadAllCustomer() {
        ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();

        ArrayList<CustomerDTO> allCustomer = null;
        try {
            allCustomer = bo.getAllCustomer();
        } catch (Exception e) {
            System.out.println("2");
        }
        for(CustomerDTO dto : allCustomer){
            tmList.add(new CustomerTM(
                    dto.getNIC(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getContact()));

        }
        bt.setItems(tmList);
        try {
            setLable();
        } catch (Exception e) {
            System.out.println("label");
        }
    }
    public void loadAllItem() {
        ObservableList<ItemTM> tmList = FXCollections.observableArrayList();

        ArrayList<ItemDTO> allitem = null;
        try {
            allitem = bo1.getAllItem();
        } catch (Exception e) {
            System.out.println("2");
        }
        for(ItemDTO dto : allitem){
            tmList.add(new ItemTM(
                    dto.getCode(),
                    dto.getDiscription(),
                    dto.getPacksize(),
                    dto.getUnitprice(),
                    dto.getQtyonhand(),
                    dto.getLocation()));

        }
        bt1.setItems(tmList);
        try {
            setLable1();
        } catch (Exception e) {
            System.out.println("lable");
        }
    }
    public void loadAllOrders() {
        bo2 = BOFactory.getInstance().getBo(BOFactory.BOType.ORDERS);
        ObservableList<OrdersTM> tmList = FXCollections.observableArrayList();

        ArrayList<OrdersDTO> allOrders = null;
        try {
            allOrders = bo2.getAllOrders();
        } catch (Exception e) {
            System.out.println("2");
        }
        for(OrdersDTO dto : allOrders){
            tmList.add(new OrdersTM(
                    dto.getOrderid(),
                    dto.getOrderdate(),
                    dto.getCid()));
        }
        tblOrdrs.setItems(tmList);
        try {
            setLable2();
        } catch (Exception e) {
            System.out.println("lable");
        }
    }
    public void btnCustomerOnAction(ActionEvent actionEvent) {
        bo = BOFactory.getInstance()
                .getBo(BOFactory.BOType.CUSTOMER);

        if (Pattern.compile("^[0-9]{9}(V)|(20)[0-9]{10}$").matcher(txtNIC.getText()).matches()) {
            if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtCustomerName.getText()).matches()){
                if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtCustomerAddress.getText()).matches()){
                    if (Pattern.compile("^[0-9]{1,12}$").matcher(txtContact.getText()).matches()) {

                        boolean isSaved = false;
                        try {
                            isSaved = bo.saveCustomer(
                                    new CustomerDTO(
                                            txtNIC.getText(),
                                            txtCustomerName.getText(),
                                            txtCustomerAddress.getText(),
                                            txtContact.getText()

                                    )
                            );
                        } catch (Exception e) {
                            System.out.println("1");
                        }
                        if (isSaved) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Customer Successfully Added", ButtonType.OK).show();
                            txtNIC.clear();
                            txtCustomerName.clear();
                            txtCustomerAddress.clear();
                            txtContact.clear();
                        } else {
                            new Alert(Alert.AlertType.WARNING,
                                    "Try Again", ButtonType.OK).show();
                        }
                        loadAllCustomer();
                        try {
                            loadCustomer();
                        } catch (Exception e) {
                            System.out.println("1");
                        }

                    }else{
                        txtContact.setUnFocusColor(Paint.valueOf("red"));
                        txtContact.requestFocus();
                    }

                }else{
                    txtCustomerAddress.setUnFocusColor(Paint.valueOf("red"));
                    txtCustomerAddress.requestFocus();
                }
            } else {
            txtCustomerName.setUnFocusColor(Paint.valueOf("red"));
            txtCustomerName.requestFocus();
        }
        }else{
            txtNIC.setUnFocusColor(Paint.valueOf("red"));
            txtNIC.requestFocus();
            }




    }

    public void btnAddItemOnAction(ActionEvent actionEvent) {
        bo1 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.ITEM);
        if (Pattern.compile("^(P)[0-9]{1,50}$").matcher(txtItemCode.getText()).matches()) {
            if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtDiscription.getText()).matches()){
                if (Pattern.compile("^[0-9]{1,10}(g)$").matcher(txtPackSize.getText()).matches()){
                    if (Pattern.compile("^[0-9]{1,50}$").matcher(txtUnitPrice.getText()).matches()) {
                       if (Pattern.compile("^[0-9]{1,50}$").matcher(txtQtyOnHand.getText()).matches()) {
                            if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtLocation.getText()).matches()) {
                                boolean isSaved = false;
                                try {
                                    isSaved = bo1.saveItem(
                                            new ItemDTO(
                                                    txtItemCode.getText(),
                                                    txtDiscription.getText(),
                                                    txtPackSize.getText(),
                                                    Double.parseDouble(txtUnitPrice.getText()),
                                                    Integer.parseInt(txtQtyOnHand.getText()),
                                                    txtLocation.getText()

                                            )
                                    );
                                } catch (Exception e) {
                                    System.out.println("1");
                                }
                                if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Item Successfully Added", ButtonType.OK).show();
            txtItemCode.clear();
            txtDiscription.clear();
            txtPackSize.clear();
            txtUnitPrice.clear();
            txtQtyOnHand.clear();
            txtLocation.clear();
        }else{
            new Alert(Alert.AlertType.WARNING,
                    "Try Again", ButtonType.OK).show();
        }
        loadAllItem();
                                try {
                                    loadItem();
                                } catch (Exception e) {
                                    System.out.println("1");
                                }

                            }else{
                                txtLocation.setUnFocusColor(Paint.valueOf("red"));
                                txtLocation.requestFocus();
                            }

                       }else{
                           txtQtyOnHand.setUnFocusColor(Paint.valueOf("red"));
                           txtQtyOnHand.requestFocus();
                       }
                    } else {
                        txtUnitPrice.setUnFocusColor(Paint.valueOf("red"));
                        txtUnitPrice.requestFocus();
                    }
                }else{
                    txtPackSize.setUnFocusColor(Paint.valueOf("red"));
                    txtPackSize.requestFocus();
                }
            }else{
                txtDiscription.setUnFocusColor(Paint.valueOf("red"));
                txtDiscription.requestFocus();
            }
        }else{
            txtItemCode.setUnFocusColor(Paint.valueOf("red"));
            txtItemCode.requestFocus();
        }
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent)  {
        bo = BOFactory.getInstance()
                .getBo(BOFactory.BOType.CUSTOMER);
        if (Pattern.compile("^[0-9]{9}(V)|(20)[0-9]{10}$").matcher(txtNIC.getText()).matches()) {
            CustomerDTO dto = null;
            try {
                dto = bo.getCustomer(txtNIC.getText());
            } catch (Exception e) {
                System.out.println("1");
            }
            if(null != dto){
            txtCustomerName.setText(dto.getName());
            txtCustomerAddress.setText(dto.getAddress());
            txtContact.setText(dto.getContact());
        }
        }else{
            txtNIC.setUnFocusColor(Paint.valueOf("red"));
            txtNIC.requestFocus();
        }


    }

    public void btnDeleteCustomerOnAction(ActionEvent actionEvent)  {
        bo = BOFactory.getInstance()
                .getBo(BOFactory.BOType.CUSTOMER);
        if (Pattern.compile("^[0-9]{9}(V)|(20)[0-9]{10}$").matcher(txtNIC.getText()).matches()) {
            boolean isDeleted= false;
            try {
                isDeleted = bo.deleteCustomer(txtNIC.getText());
            } catch (Exception e) {
                System.out.println("1");
            }
            if (isDeleted){

           new Alert(Alert.AlertType.CONFIRMATION,
                    "Customer Successfully Deleted",ButtonType.OK).show();



            txtNIC.clear();
            txtCustomerName.clear();
            txtCustomerAddress.clear();
            txtContact.clear();
        }else{
            new Alert(Alert.AlertType.WARNING,
                    "Try Again", ButtonType.OK).show();
        }
        loadAllCustomer();
            try {
                loadCustomer();
            } catch (Exception e) {
                System.out.println("1");
            }
        }else{
            txtNIC.setUnFocusColor(Paint.valueOf("red"));
            txtNIC.requestFocus();
        }

    }

    public void btnUpdateCustomerOnAction(ActionEvent actionEvent) {
        bo = BOFactory.getInstance()
                .getBo(BOFactory.BOType.CUSTOMER);
        if (Pattern.compile("^[0-9]{9}(V)|(20)[0-9]{10}$").matcher(txtNIC.getText()).matches()) {
            if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtCustomerName.getText()).matches()){
                if (Pattern.compile("^[A-z]{1,50}$").matcher(txtCustomerAddress.getText()).matches()){
                    if (Pattern.compile("^[0-9]{1,12}$").matcher(txtContact.getText()).matches()) {
                        boolean isUpdate= false;
                        try {
                            isUpdate = bo.updateCustomer(
                                    new CustomerDTO(txtNIC.getText(),
                                            txtCustomerName.getText(),
                                            txtCustomerAddress.getText(),
                                            txtContact.getText())
                            );
                        } catch (Exception e) {
                            System.out.println("1");
                        }
                        if (isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Customer Successfully Updated", ButtonType.OK).show();
            txtNIC.clear();
            txtCustomerName.clear();
            txtCustomerAddress.clear();
            txtContact.clear();
        }else{
            new Alert(Alert.AlertType.WARNING,
                    "Try Again", ButtonType.OK).show();
        }
        loadAllCustomer();
                        try {
                            loadCustomer();
                        } catch (Exception e) {
                            System.out.println("1");
                        }
                    }else{
                        txtContact.setUnFocusColor(Paint.valueOf("red"));
                        txtContact.requestFocus();
                    }

                }else{
                    txtCustomerAddress.setUnFocusColor(Paint.valueOf("red"));
                    txtCustomerAddress.requestFocus();
                }
            } else {
                txtCustomerName.setUnFocusColor(Paint.valueOf("red"));
                txtCustomerName.requestFocus();
            }
        }else{
            txtNIC.setUnFocusColor(Paint.valueOf("red"));
            txtNIC.requestFocus();
        }
    }

    public void btnUpdateItemOnAction(ActionEvent actionEvent) {
        bo1 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.ITEM);
        if (Pattern.compile("^(P)[0-9]{1,50}$").matcher(txtItemCode.getText()).matches()) {
            if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtDiscription.getText()).matches()){
                if (Pattern.compile("^[0-9]{1,10}(g)$").matcher(txtPackSize.getText()).matches()){
                    if (Pattern.compile("^[0-9]{1,50}$").matcher(txtUnitPrice.getText()).matches()) {
                        if (Pattern.compile("^[0-9]{1,50}$").matcher(txtQtyOnHand.getText()).matches()) {
                            if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtLocation.getText()).matches()) {
                                boolean isUpdate= false;
                                try {
                                    isUpdate = bo1.updateItem(
                                            new ItemDTO(txtItemCode.getText(),
                                                    txtDiscription.getText(),
                                                    txtPackSize.getText(),
                                                    Double.parseDouble(txtUnitPrice.getText()),
                                                    Integer.parseInt(txtQtyOnHand.getText()),
                                                    txtLocation.getText())
                                    );
                                } catch (Exception e) {
                                    System.out.println("1");
                                }
                                if (isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Item Successfully Updated", ButtonType.OK).show();
            txtItemCode.clear();
            txtDiscription.clear();
            txtPackSize.clear();
            txtUnitPrice.clear();
            txtQtyOnHand.clear();
            txtLocation.clear();
        }else{
            new Alert(Alert.AlertType.WARNING,
                    "Try Again", ButtonType.OK).show();
        }
        loadAllItem();
                                try {
                                    loadItem();
                                } catch (Exception e) {
                                    System.out.println("1");
                                }
                            }else{
                                txtLocation.setUnFocusColor(Paint.valueOf("red"));
                                txtLocation.requestFocus();
                            }

                        }else{
                            txtQtyOnHand.setUnFocusColor(Paint.valueOf("red"));
                            txtQtyOnHand.requestFocus();
                        }
                    } else {
                        txtUnitPrice.setUnFocusColor(Paint.valueOf("red"));
                        txtUnitPrice.requestFocus();
                    }
                }else{
                    txtPackSize.setUnFocusColor(Paint.valueOf("red"));
                    txtPackSize.requestFocus();
                }
            }else{
                txtDiscription.setUnFocusColor(Paint.valueOf("red"));
                txtDiscription.requestFocus();
            }
        }else{
            txtItemCode.setUnFocusColor(Paint.valueOf("red"));
            txtItemCode.requestFocus();
        }
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) {
        bo1 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.ITEM);
        if (Pattern.compile("^(P)[0-9]{1,50}$").matcher(txtItemCode.getText()).matches()) {
            ItemDTO dto = null;
            try {
                dto = bo1.getItem(txtItemCode.getText());
            } catch (Exception e) {
                System.out.println("1");
            }
            if(null != dto){
            txtDiscription.setText(dto.getDiscription());
            txtPackSize.setText(dto.getPacksize());
            txtUnitPrice.setText(dto.getUnitprice()+"");
            txtQtyOnHand.setText(dto.getQtyonhand()+"");
            txtLocation.setText(dto.getLocation());
        }else{
            new Alert(Alert.AlertType.WARNING  ,
                    "Try Again", ButtonType.OK).show();
        }
        }else{
            txtItemCode.setUnFocusColor(Paint.valueOf("red"));
            txtItemCode.requestFocus();
        }
    }

    public void btnDeleteItemOnAction(ActionEvent actionEvent) {
        bo1 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.ITEM);
        if (Pattern.compile("^(P)[0-9]{1,50}$").matcher(txtItemCode.getText()).matches()) {
            boolean isDeleted= false;
            try {
                isDeleted = bo1.deleteItem(txtItemCode.getText());
            } catch (Exception e) {
                System.out.println("1");
            }
            if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Item Successfully Deleted", ButtonType.OK).show();
            txtItemCode.clear();
            txtDiscription.clear();
            txtPackSize.clear();
            txtUnitPrice.clear();
            txtQtyOnHand.clear();
            txtLocation.clear();
        }else{
            new Alert(Alert.AlertType.WARNING,
                    "Try Again", ButtonType.OK).show();
        }
        loadAllItem();
            try {
                loadItem();
            } catch (Exception e) {
                System.out.println("1");
            }
        }else{
            txtItemCode.setUnFocusColor(Paint.valueOf("red"));
            txtItemCode.requestFocus();
        }
    }
    private void loadCustomer()  {
        bo = BOFactory.getInstance()
                .getBo(BOFactory.BOType.CUSTOMER);
        List<CustomerDTO> list= null;
        try {
            list = bo.getAllCustomer();
        } catch (Exception e) {
            System.out.println("1");
        }
        ObservableList<String> list1=FXCollections.observableArrayList();
        for (CustomerDTO c:list){
            list1.add(c.getNIC());
        }
        cmbCustomerId.setItems(list1);
    }
    private void loadItem() {
        bo1 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.ITEM);
        List<ItemDTO> list= null;
        try {
            list = bo1.getAllItem();
        } catch (Exception e) {
            System.out.println("1");
        }
        ObservableList<String> list1=FXCollections.observableArrayList();
        for (ItemDTO i:list){
            list1.add(i.getCode());
        }
        cmbItemCode.setItems(list1);
    }
    public void generateOrderId(){
        bo2 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.ORDERS);
        try {
            lblOrderID.setText(bo2.getNewOrderId()+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ObservableList<OrderDetailsTM> tmList = FXCollections.observableArrayList();
    public void btnAddToListOnAction(ActionEvent actionEvent) {
        bo1 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.ITEM);
        bo3 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.ORDER_DETAIL);
        if (txtqty.getText().trim().isEmpty()) {
           new Alert(Alert.AlertType.ERROR, "Input the Qty", ButtonType.OK).show();
            return;
        }
        int qty = Integer.parseInt(txtqty.getText());
        if (qty < 1 || qty > Integer.parseInt(lblQtyOnHand.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid Qty.", ButtonType.OK).show();
            return;

        }

        colitemcode.setCellValueFactory(new PropertyValueFactory("code"));
        coldiscriptin.setCellValueFactory(new PropertyValueFactory("discription"));
        collocation.setCellValueFactory(new PropertyValueFactory("location"));
        colunitprice.setCellValueFactory(new PropertyValueFactory("unitprice"));
        colqty.setCellValueFactory(new PropertyValueFactory("qty"));
        colamount.setCellValueFactory(new PropertyValueFactory("total"));
        String code = String.valueOf(cmbItemCode.getValue());
        String desc = txtdiscription.getText();
        String location=txtlocation.getText();
        double unitPrice = Double.parseDouble(txtunitprice.getText());
        int qty1=Integer.parseInt(txtqty.getText());
        if (!tmList.isEmpty()) { // check observableList is empty
            for (int i = 0; i < tblPlaceOrder.getItems().size(); i++) { // check all rows in table
                if (colItemCode.getCellData(i).equals(code)) { // check  itemcode in table == itemcode we enter
                    double temp = (double) colqty.getCellData(i); // get qty in table for temp
                    temp += qty1; // add new qty to old qty
                    double tot = (temp * unitPrice); // get new total
                    tmList.get(i).setQty((int) temp); // set new qty to observableList
                    tmList.get(i).setTotal(tot); // set new total to observableList
                    tblPlaceOrder.refresh(); // refresh table
                    return;
                }

            }
        }

        tmList.add(new OrderDetailsTM(code,desc,location,unitPrice,qty1, (qty1 * unitPrice)));
        tblPlaceOrder.setItems(tmList);
        calculateTotal();
        //calculateQtyOnHand();
        txtdiscription.clear();
        txtpacksize.clear();
        txtunitprice.clear();
        txtlocation.clear();
        txtqty.clear();
        lblQtyOnHand.setText("");

    }
    public void calculateTotal() {
        double tot = 0.0;
        for (OrderDetailsTM orderTM : tmList) {
            tot += orderTM.getTotal();
        }
        lblTotal.setText(String.valueOf(tot));
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        bo2 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.ORDERS);
        if (cmbCustomerId.getSelectionModel().getSelectedIndex() == -1) {
            new Alert(Alert.AlertType.ERROR, "Please Enter a Name !", ButtonType.OK).show();
            cmbCustomerId.requestFocus();
            return;
        }
        if (tblPlaceOrder.getItems().size() == 0) {
            new Alert(Alert.AlertType.ERROR, "Table Is Empty !", ButtonType.OK).show();
            cmbItemCode.requestFocus();
            return;
        }
        try {
            boolean isSaved = bo2.saveOrder(getOrder(),getOrderDetail());
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Order Saved",ButtonType.OK).show();
                generateOrderId();
                tblPlaceOrder.getItems().clear();
                lblTotal.setText("");
                lblCustomerName.setText("");
                loadAllOrders();


            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Order Not Saved !",ButtonType.OK).show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK).show();
        }

    }
    private ArrayList<OrderDetailsDTO> getOrderDetail() {
        int orderId = Integer.parseInt(lblOrderID.getText().trim());
        ArrayList<OrderDetailsDTO> orderDetailDTOS = new ArrayList<>();

        for (int i = 0; i < tblPlaceOrder.getItems().size(); i++) {
            OrderDetailsTM orderTM = tmList.get(i);
            orderDetailDTOS.add(new OrderDetailsDTO(orderId,
                    orderTM.getCode(),orderTM.getQty()));
        }
        return orderDetailDTOS;
    }

   private OrdersDTO getOrder() {
       int orderId = Integer.parseInt(lblOrderID.getText().trim());
       String orderDate = lblDate.getText().trim();
       String customerId = String.valueOf(cmbCustomerId.getValue());
       return new OrdersDTO(orderId,orderDate,customerId);
   }

    public void btnCustomerListOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/ABCD.jasper");
            //JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint jp = JasperFillManager.fillReport(is,null, DBConnection.getInstance().getConnection());
            //JasperPrintManager.printReport(jp, true);
            JasperViewer.viewReport(jp,false);
        }catch(JRException e){
            e.printStackTrace();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void btnStoreListOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/Store.jasper");
            //JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint jp = JasperFillManager.fillReport(is,null, DBConnection.getInstance().getConnection());
            //JasperPrintManager.printReport(jp, true);
            JasperViewer.viewReport(jp,false);
        }catch(JRException e){
            e.printStackTrace();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void btnOrdersListOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/Orders.jasper");
            //JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint jp = JasperFillManager.fillReport(is,null, DBConnection.getInstance().getConnection());
            //JasperPrintManager.printReport(jp, true);
            JasperViewer.viewReport(jp,false);
        }catch(JRException e){
            e.printStackTrace();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    /*private void calculateQtyOnHand() throws Exception {
        bo1 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.ITEM);
        bo3 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.ORDER_DETAIL);
        int qtyonhand=Integer.parseInt(lblQtyOnHand.getText());
        ObservableList<OrderDetailsTM> orderDetails = tblPlaceOrder.getItems();
        for (OrderDetailsTM orderDetail : orderDetails) {
            if (orderDetail.getCode().equals(bo1.getItem(txtQtyOnHand.getText()))) {
                int displayQty = qtyonhand - orderDetail.getQty();
                lblQtyOnHand.setText(String.valueOf(displayQty));
                break;
            }
        }
    }*/

    public void btnClearRowOnAction(ActionEvent actionEvent) {
        OrderDetailsTM selectedItem = tblPlaceOrder.getSelectionModel().getSelectedItem();
        if(selectedItem!=null) {
            tmList.remove(selectedItem);
            tblPlaceOrder.getItems().remove(selectedItem);
        }else{
            System.out.println("select Row");
        }



        calculateTotal();
    }

    public void btnClearTableOnAction(ActionEvent actionEvent) {
        for ( int i = 0; i<tblPlaceOrder.getItems().size(); i++) {
            tblPlaceOrder.getItems().clear();
        }
    }
}
