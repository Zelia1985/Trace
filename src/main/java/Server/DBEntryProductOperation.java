package Server;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBEntryProductOperation {

    //Метод проверки номера изделия в таблице PRODUCT_NUMBER
    public boolean checkNumber(int tabnumber) throws Exception {
        boolean check = false;
        String queryCheckNumber = "SELECT * FROM PRODUCT_NUMBER WHERE PRODUCT_NUM = " + tabnumber + " ";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryCheckNumber);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    check = true;
                    System.out.println(check);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return check;
    }

    //Метод проверки номера изделия в таблице PRODUCT_NUMBER
    public boolean checkOperation(String typeOperation) throws Exception {
        boolean check = false;
        String queryCheckOperation = "SELECT * FROM OPERATION WHERE TYPE_OPERATION = '" + typeOperation + "'";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryCheckOperation);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    check = true;
                    System.out.println(check);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return check;
    }

    //Метод проверки соответствия номера изделия и операции в таблице PRODUCT_OPERATION
    public boolean checkOperationOnProduct(int productNum, String productOperationOn) throws Exception {
        boolean check = false;
        String queryCheckOperationOnProduct = "SELECT * FROM PRODUCT_OPERATION WHERE PRODUCT_NUM = " + productNum + " AND PRODUCT_OPERATION_ON = '" + productOperationOn + "' ";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryCheckOperationOnProduct);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    check = true;
                    System.out.println(check);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return check;
    }

    //Метод для удаления операции по изделию из БД(таблица PRODUCT_OPERATION)
    public boolean deleteOperationOnProduct(int productNum, String productOperationOn) throws Exception {
        if (productNum != 0 && productOperationOn != null) {
            String queryDeleteOperationOnProduct = "DELETE FROM PRODUCT_OPERATION WHERE PRODUCT_NUM = " + productNum + " AND PRODUCT_OPERATION_ON = '" + productOperationOn + "'";
            try {
                DBConnectSingleton.executeQuery(queryDeleteOperationOnProduct);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            return true;
        }
        return false;
    }

    //Метод для добавления платы в изделие
    public boolean addOperation(int productNum, String productOperationOn, String operationDate) throws Exception {

        if (operationDate.isEmpty()) {
            return false;
        }

        if (productOperationOn != null && productNum != 0 && operationDate != null) {
            String queryAddOperation = "INSERT INTO PRODUCT_OPERATION(PRODUCT_NUM,PRODUCT_OPERATION_ON,OPERATION_DATE) VALUES (" + productNum + ",'" + productOperationOn + "','" + operationDate + "')";
            try {
                DBConnectSingleton.executeQuery(queryAddOperation);
                System.out.println("Операция внесена в БД");
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println(true);
            return true;
        }
        return false;
    }

}
