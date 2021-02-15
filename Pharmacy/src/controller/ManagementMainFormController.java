package controller;

import bo.BOFactory;
import bo.custom.AppointmentBO;
import bo.custom.EmployeeBO;
import bo.custom.SalaryBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.CustomerDTO;
import dto.EmployeeDTO;
import dto.ItemDTO;
import dto.SalaryDTO;
import entity.Employee;
import entity.Salary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.CustomerTM;
import view.tm.EmployeeTM;
import view.tm.SalaryTM;

import javax.sound.midi.Soundbank;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ManagementMainFormController {
    public JFXTabPane root;
    public JFXTextField txtEmployeeID;
    public JFXTextField txtEmployeeName;
    public JFXTextField txtEmployeeAddress;
    public JFXTextField txtEmployeeContact;
    public JFXButton btnAddEmployee;
    public JFXButton btnUpdateEmployee;
    public JFXButton btnSearchEmployee;
    public JFXButton btnDeleteEmployee;
    public TableView tblEmployee;
    public TableColumn colEmployeeID;
    public TableColumn colEmployeeName;
    public TableColumn colEmployeeAddress;
    public TableColumn colEmployeeEmail;
    public JFXTextField txtEmployeeEmail;
    public TableColumn colEmployeeContact;
    public JFXTextField txtBasicSalary;
    public JFXTextField txtEpf;
    public JFXTextField txtEtf;
    public JFXComboBox cmbEmployeeID;
    public Label lblEmployeeName;
    public Label lblTotalSalary;
    public TableView tblSalary;
    public TableColumn colEid;
    public TableColumn colBasicSalary;
    public TableColumn colTotalSalary;
    public JFXButton btnAddSalary;
    public JFXButton btnUpdateSalary;
    public JFXButton btnSearchSalary;
    public JFXButton btnDeleteSalary;
    public JFXTextField txteid;
    public JFXButton btnSalaryList;
    public JFXButton btnEmployeeSalaryList;
    public JFXButton btnEmployeeList;
    public Label lblemployee;
    public Label lblemployee1;
    private EmployeeBO bo;
    private SalaryBO bo1;

    public void initialize()  {
        bo = BOFactory.getInstance().getBo(BOFactory.BOType.EMPLOYEE);
        bo1 = BOFactory.getInstance().getBo(BOFactory.BOType.SALARY);
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("ename"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("eaddress"));
        colEmployeeContact.setCellValueFactory(new PropertyValueFactory<>("econtact"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("eemail"));

            loadAllEmployee();


            loadEmployee();

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData((EmployeeTM) newValue);

        });
        colEid.setCellValueFactory(new PropertyValueFactory<>("eid"));
        colBasicSalary.setCellValueFactory(new PropertyValueFactory<>("basicsalary"));
        colTotalSalary.setCellValueFactory(new PropertyValueFactory<>("totalsalary"));

            loadAllSalary();

        tblSalary.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData2((SalaryTM) newValue);

        });
        setLable();


    }
    private void setLable() {
        List<EmployeeDTO> allEmployee = null;
        try {
            allEmployee = bo.getAllEmployee();
        } catch (Exception e) {
            System.out.println("lable");
        }
        lblemployee.setText(String.valueOf(allEmployee.size()));
        lblemployee1.setText(String.valueOf(allEmployee.size()));
    }
    private void setData(EmployeeTM tm) {
        try {
            txtEmployeeID.setText(tm.getEmployeeID());
            txtEmployeeName.setText(tm.getEname());
            txtEmployeeAddress.setText(tm.getEaddress());
            txtEmployeeContact.setText(tm.getEcontact());
            txtEmployeeEmail.setText(tm.getEemail());
        }catch (NullPointerException e){
            System.out.printf("1");
        }

    }
    private void setData2(SalaryTM tm) {
        try {
            txteid.setText(tm.getEid());
            txtBasicSalary.setText(tm.getBasicsalary() + "");
            lblTotalSalary.setText(tm.getTotalsalary() + "");
        }catch (NullPointerException e){
            System.out.printf("2");
        }

    }
    private void loadEmployee() {
        bo = BOFactory.getInstance()
                .getBo(BOFactory.BOType.EMPLOYEE);
        List<EmployeeDTO> list= null;
        try {
            list = bo.getAllEmployee();
        } catch (Exception e) {
            System.out.println("1");
        }
        ObservableList<String> list1=FXCollections.observableArrayList();
        for (EmployeeDTO e:list){
            list1.add(e.getEmployeeID());
        }
        cmbEmployeeID.setItems(list1);


    }
    public void loadAllEmployee()  {
        ObservableList<EmployeeTM> tmList = FXCollections.observableArrayList();

        ArrayList<EmployeeDTO> allEmployee = null;
        try {
            allEmployee = bo.getAllEmployee();
        } catch (Exception e) {
            System.out.println("1");
        }
        for(EmployeeDTO dto : allEmployee){
            tmList.add(new EmployeeTM(
                    dto.getEmployeeID(),dto.getEname(),dto.getEaddress(),
                    dto.getEcontact(),dto.getEemail()));

        }
        tblEmployee.setItems(tmList);
        setLable();
    }
    public void loadAllSalary() {
        ObservableList<SalaryTM> tmList = FXCollections.observableArrayList();
        ArrayList<SalaryDTO> allSalary = null;
        try {
            allSalary = bo1.getAllSalry();
        } catch (Exception e) {
            System.out.println("1");
        }
        for(SalaryDTO dto : allSalary){
            tmList.add(new SalaryTM(
                    dto.getEid(),dto.getBasicsalary(),dto.getTotalsalary()));

        }
        tblSalary.setItems(tmList);
    }
    public void btnAddEmployeeOnAction(ActionEvent actionEvent)  {
        bo = BOFactory.getInstance().getBo(BOFactory.BOType.EMPLOYEE);
        if (Pattern.compile("^(E)[0-9]{1,50}$").matcher(txtEmployeeID.getText()).matches()) {
            if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtEmployeeName.getText()).matches()){
                if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtEmployeeAddress.getText()).matches()){
                    if (Pattern.compile("^[0-9]{1,12}$").matcher(txtEmployeeContact.getText()).matches()) {
                        if (Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$").matcher(txtEmployeeEmail.getText()).matches()) {
                            boolean isSaved = false;
                            try {
                                isSaved = bo.saveEmployee(
                                        new EmployeeDTO(
                                                txtEmployeeID.getText(),
                                                txtEmployeeName .getText(),
                                                txtEmployeeAddress.getText(),
                                                txtEmployeeContact.getText(),
                                                txtEmployeeEmail.getText()

                                        )
                                );
                            } catch (Exception e) {
                                new Alert(Alert.AlertType.ERROR,
                                        "Something Went Wrong !", ButtonType.OK).show();
                            }
                            if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Employee Successfully Added", ButtonType.OK).show();
            txtEmployeeID.clear();
            txtEmployeeName.clear();
            txtEmployeeAddress.clear();
            txtEmployeeContact.clear();
            txtEmployeeEmail.clear();

        }else{
            new Alert(Alert.AlertType.WARNING,
                    "Try Again", ButtonType.OK).show();
        }
                            try {
                                loadAllEmployee();
                            } catch (Exception e) {
                                System.out.println(0);
                            }
                            try {
                                loadEmployee();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else{
                            txtEmployeeEmail.setUnFocusColor(Paint.valueOf("red"));
                            txtEmployeeEmail.requestFocus();
                        }

                    }else{
                        txtEmployeeContact.setUnFocusColor(Paint.valueOf("red"));
                        txtEmployeeContact.requestFocus();
                    }
                } else {
                    txtEmployeeAddress.setUnFocusColor(Paint.valueOf("red"));
                    txtEmployeeAddress.requestFocus();
                }
            }else{
                txtEmployeeName.setUnFocusColor(Paint.valueOf("red"));
                txtEmployeeName.requestFocus();
            }
        }else{
            txtEmployeeID.setUnFocusColor(Paint.valueOf("red"));
            txtEmployeeID.requestFocus();
        }
    }

    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent){
        bo = BOFactory.getInstance()
                .getBo(BOFactory.BOType.EMPLOYEE);
        if (Pattern.compile("^(E)[0-9]{1,50}$").matcher(txtEmployeeID.getText()).matches()) {
            if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtEmployeeName.getText()).matches()){
                if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtEmployeeAddress.getText()).matches()){
                    if (Pattern.compile("^[0-9]{1,12}$").matcher(txtEmployeeContact.getText()).matches()) {
                        if (Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$").matcher(txtEmployeeEmail.getText()).matches()) {
                            boolean isUpdate= false;
                            try {
                                isUpdate = bo.updateEmployee(
                                        new EmployeeDTO(txtEmployeeID.getText(),
                                                txtEmployeeName.getText(),
                                                txtEmployeeAddress.getText(),
                                                txtEmployeeContact.getText(),
                                                txtEmployeeEmail.getText())
                                );
                            } catch (Exception e) {
                                new Alert(Alert.AlertType.ERROR,
                                        "Something Went Wrong !", ButtonType.OK).show();
                            }
                            if (isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Employee Successfully Updated", ButtonType.OK).show();
            txtEmployeeID.clear();
            txtEmployeeName.clear();
            txtEmployeeAddress.clear();
            txtEmployeeContact.clear();
            txtEmployeeEmail.clear();
        }else{
            new Alert(Alert.AlertType.WARNING,
                    "Try Again", ButtonType.OK).show();
        }
                            try {
                                loadAllEmployee();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                loadEmployee();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else{
                            txtEmployeeEmail.setUnFocusColor(Paint.valueOf("red"));
                            txtEmployeeEmail.requestFocus();
                        }

                    }else{
                        txtEmployeeContact.setUnFocusColor(Paint.valueOf("red"));
                        txtEmployeeContact.requestFocus();
                    }
                } else {
                    txtEmployeeAddress.setUnFocusColor(Paint.valueOf("red"));
                    txtEmployeeAddress.requestFocus();
                }
            }else{
                txtEmployeeName.setUnFocusColor(Paint.valueOf("red"));
                txtEmployeeName.requestFocus();
            }
        }else{
            txtEmployeeID.setUnFocusColor(Paint.valueOf("red"));
            txtEmployeeID.requestFocus();
        }
    }

    public void btnSearchEmployeeOnAction(ActionEvent actionEvent) {
        bo = BOFactory.getInstance()
                .getBo(BOFactory.BOType.EMPLOYEE);
        if (Pattern.compile("^(E)[0-9]{1,50}$").matcher(txtEmployeeID.getText()).matches()) {
            EmployeeDTO dto = null;
            try {
                dto = bo.getEmployee(txtEmployeeID.getText());
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,
                        "Something Went Wrong !", ButtonType.OK).show();
            }
            if(null != dto){
            txtEmployeeName.setText(dto.getEname());
            txtEmployeeAddress.setText(dto.getEaddress());
            txtEmployeeContact.setText(dto.getEcontact());
            txtEmployeeEmail.setText(dto.getEemail());
        }
        }else{
            txtEmployeeID.setUnFocusColor(Paint.valueOf("red"));
            txtEmployeeID.requestFocus();
        }
    }

    public void btnDeleteEmployeeOnAction(ActionEvent actionEvent) {
        bo = BOFactory.getInstance()
                .getBo(BOFactory.BOType.EMPLOYEE);
        if (Pattern.compile("^(E)[0-9]{1,50}$").matcher(txtEmployeeID.getText()).matches()) {
            boolean isDeleted= false;
            try {
                isDeleted = bo.deleteEmployee(txtEmployeeID.getText());
            } catch (Exception e) {
                System.out.println("1");
            }
            if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Employee Successfully Deleted", ButtonType.OK).show();
            txtEmployeeID.clear();
            txtEmployeeName.clear();
            txtEmployeeAddress.clear();
            txtEmployeeContact.clear();
            txtEmployeeEmail.clear();
        }else{
            new Alert(Alert.AlertType.WARNING,
                    "Try Again", ButtonType.OK).show();
        }
            try {
                loadAllEmployee();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                loadEmployee();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            txtEmployeeID.setUnFocusColor(Paint.valueOf("red"));
            txtEmployeeID.requestFocus();
        }
    }

    public void setEmployee(ActionEvent actionEvent)  {
        bo = BOFactory.getInstance()
                .getBo(BOFactory.BOType.EMPLOYEE);
        bo1 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.SALARY);
        EmployeeDTO dto;
        try {
            dto = bo.getEmployee(cmbEmployeeID.getValue().toString());
            lblEmployeeName.setText(dto.getEname());


        try {
        SalaryDTO dto1;
            dto1 = bo1.getSlary(cmbEmployeeID.getValue().toString());

        if (dto1==null) {

        }else{
            txteid.setText(dto1.getEid());
            txtBasicSalary.setText(dto1.getBasicsalary() + "");
            txtEpf.setText(dto1.getEpf()+ "");
            txtEtf.setText(dto1.getEtf() + "");
            lblTotalSalary.setText(dto1.getTotalsalary() + "");
        }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING,
                    "Empty Salary Set !", ButtonType.OK).show();
            txteid.clear();
            txtBasicSalary.clear();
            txtEpf.clear();
            txtEtf.clear();
            lblTotalSalary.setText("");
        }
        }catch (Exception e) {
            System.out.printf("");
        }
    }

    public void AddSalaryOnAction(ActionEvent actionEvent)  {
        bo1 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.SALARY);
        if (Pattern.compile("^(E)[0-9]{1,50}$").matcher(txteid.getText()).matches()) {
            if (Pattern.compile("^[0-9]{1,10}$").matcher(txtBasicSalary.getText()).matches()){
                if (Pattern.compile("^[0-9]{1,10}$").matcher(txtEpf.getText()).matches()){
                    if (Pattern.compile("^[0-9]{1,10}$").matcher(txtEpf.getText()).matches()) {
        double tot=Double.parseDouble(txtBasicSalary.getText())+Double.parseDouble(txtEpf.getText())+Double.parseDouble(txtEtf.getText());
        lblTotalSalary.setText(Double.toString(tot));
                        boolean isSaved = false;
                        try {
                            isSaved = bo1.saveSalary(
                                    new SalaryDTO(
                                            txteid.getText(),
                                            Double.parseDouble(txtBasicSalary.getText()),
                                            Double.parseDouble(txtEpf.getText()),
                                            Double.parseDouble(txtEtf.getText()),
                                            Double.parseDouble(lblTotalSalary.getText())

                            )
                            );
                        } catch (Exception e) {
                            new Alert(Alert.AlertType.ERROR,
                                    "Something Went Wrong !", ButtonType.OK).show();
                        }
                        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Salary Successfully Added", ButtonType.OK).show();
           txteid.clear();
            txtBasicSalary.clear();
            txtEpf.clear();
            txtEtf.clear();
            lblTotalSalary.setText("");
            lblEmployeeName.setText("");

        }else{
            new Alert(Alert.AlertType.WARNING,
                    "Try Again", ButtonType.OK).show();
        }
                        try {
                            loadAllSalary();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            loadEmployee();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        txtEtf.setUnFocusColor(Paint.valueOf("red"));
                        txtEtf.requestFocus();
                    }
                }else{
                    txtEpf.setUnFocusColor(Paint.valueOf("red"));
                    txtEpf.requestFocus();
                }
            }else{
                txtBasicSalary.setUnFocusColor(Paint.valueOf("red"));
                txtBasicSalary.requestFocus();
            }
        }else{
            txteid.setUnFocusColor(Paint.valueOf("red"));
            txteid.requestFocus();
        }
    }

    public void UpdateSalaryOnAction(ActionEvent actionEvent)  {
        bo1 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.SALARY);
        if (Pattern.compile("^(E)[0-9]{1,50}$").matcher(txteid.getText()).matches()) {
            if (Pattern.compile("^[0-9]{1,10}$").matcher(txtBasicSalary.getText()).matches()){
                if (Pattern.compile("^[0-9]{1,10}$").matcher(txtEpf.getText()).matches()){
                    if (Pattern.compile("^[0-9]{1,10}$").matcher(txtEpf.getText()).matches()) {
        double tot=Double.parseDouble(txtBasicSalary.getText())+Double.parseDouble(txtEpf.getText())+Double.parseDouble(txtEtf.getText());
        lblTotalSalary.setText(Double.toString(tot));

                        boolean isUpdate= false;
                        try {
                            isUpdate = bo1.updateSalary(
                                    new SalaryDTO(txteid.getText(),
                                            Double.parseDouble(txtBasicSalary.getText()),
                                            Double.parseDouble(txtEpf.getText()),
                                            Double.parseDouble(txtEtf.getText()),
                                            Double.parseDouble(lblTotalSalary.getText())
                                    )
                            );
                        } catch (Exception e) {
                            new Alert(Alert.AlertType.ERROR,
                                    "Something Went Wrong !", ButtonType.OK).show();
                        }
                        if (isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Salary Successfully Updated", ButtonType.OK).show();
            txteid.clear();
            txtBasicSalary.clear();
            txtEpf.clear();
            txtEtf.clear();
            lblTotalSalary.setText("");
            lblEmployeeName.setText("");
        }else{
            new Alert(Alert.AlertType.WARNING,
                    "Try Again", ButtonType.OK).show();
        }
        //loadEmployee();
                        try {
                            loadAllSalary();
                        } catch (Exception e) {
                            System.out.printf("");
                        }
                    }else{
                        txtEtf.setUnFocusColor(Paint.valueOf("red"));
                        txtEtf.requestFocus();
                    }
                }else{
                    txtEpf.setUnFocusColor(Paint.valueOf("red"));
                    txtEpf.requestFocus();
                }
            }else{
                txtBasicSalary.setUnFocusColor(Paint.valueOf("red"));
                txtBasicSalary.requestFocus();
            }
        }else{
            txteid.setUnFocusColor(Paint.valueOf("red"));
            txteid.requestFocus();
        }

    }

   /*public void SearchSalaryOnAction(ActionEvent actionEvent) throws Exception {
        bo1 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.SALARY);
        SalaryDTO dto = bo1.getSlary(txteid.getId());
        if(null != dto){
            txtBasicSalary.setText(dto.getBasicsalary()+"");
            txtEpf.setText(dto.getEpf()+"");
            txtEtf.setText(dto.getEtf()+"");
            lblTotalSalary.setText(dto.getTotalsalary()+"");
        }
    }*/

    public void DeleteSalaryOnAction(ActionEvent actionEvent){
        bo1 = BOFactory.getInstance()
                .getBo(BOFactory.BOType.SALARY);
        if (Pattern.compile("^(E)[0-9]{1,50}$").matcher(txteid.getText()).matches()) {
            boolean isDeleted= false;
            try {
                isDeleted = bo1.deleteSalary(txteid.getText());
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,
                        "Something Went Wrong !", ButtonType.OK).show();
            }
            if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Salary Successfully Deleted", ButtonType.OK).show();
            txteid.clear();
            txtBasicSalary.clear();
            txtEpf.clear();
            txtEtf.clear();
            lblTotalSalary.setText("");
            lblEmployeeName.setText("");
        }else{
            new Alert(Alert.AlertType.WARNING,
                    "Try Again", ButtonType.OK).show();
        }
            try {
                loadAllSalary();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                loadEmployee();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            txteid.setUnFocusColor(Paint.valueOf("red"));
            txteid.requestFocus();
        }
    }

    public void btnEmployeeListOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/Employee.jasper");
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

    public void btnEmployeeSalaryListOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/EmployeeSalary1.jasper");
            //JasperReport jr = JasperCompileManager.compileReport(is);
            HashMap hm=new HashMap();
            hm.put("Employee ID",txteid.getText());
            hm.put("Basic Salary",txtBasicSalary.getText());
            hm.put("Epf",txtEpf.getText());
            hm.put("Etf",txtEtf.getText());
            hm.put("Total Salary",lblTotalSalary.getText());
            JasperPrint jp = JasperFillManager.fillReport(is,hm, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,false);
        }catch(JRException e){
            e.printStackTrace();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void btnSalaryListOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/SalaryList.jasper");
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
}
