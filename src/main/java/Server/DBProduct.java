package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBProduct {

    //Метод проверки номера изделия в таблице PRODUCT_NUMBER
    public boolean checkNumber(Integer tabnumber) throws Exception {
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

    //Метод для получения значения последнего номера изделия (таблица PRODUCT_NUMBER)
    public int getLastTabNumberProduct() throws Exception {
        ArrayList<Integer> productNum = new ArrayList();
        int lastTabNumberProduct = 0;
        String queryGetLastTabNumberProduct = "SELECT * FROM PRODUCT_NUMBER";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryGetLastTabNumberProduct);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    productNum.add(rs.getInt("PRODUCT_NUM"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        lastTabNumberProduct = productNum.get(productNum.size() - 1);
        return (lastTabNumberProduct + 1);
    }

    //Метод проверки типа изделия (таблица PRODUCT)
    public boolean checkProductType(String productType) throws Exception {
        boolean checkProdType = false;
        String queryCheckProductType = "SELECT * FROM PRODUCT WHERE TYPE_PRODUCT = '" + productType + "'";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryCheckProductType);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    checkProdType = true;
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return checkProdType;
    }

    //Метод для добавления изделия в БД
    public boolean addProduct(String productType, int productNum) throws Exception {
        if (productType != null && productNum != 0) {
            String queryAddProduct = "INSERT INTO PRODUCT_NUMBER(TYPE_PRODUCT,PRODUCT_NUM) VALUES ('" + productType + "'," + productNum + ")";
            try {
                DBConnectSingleton.executeQuery(queryAddProduct);
                System.out.println("Изделие внесено в БД");
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println(true);
            return true;
        }
        return false;
    }

    //Метод проверки по номеру изделия (таблица PRODUCT_NUMBER)
    public boolean checkProductNum(int productNum) throws Exception {
        boolean CheckProductNum = false;
        String queryCheckProductNum = "SELECT * FROM PRODUCT_NUMBER WHERE PRODUCT_NUM = " + productNum + "";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryCheckProductNum);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    CheckProductNum = true;
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return CheckProductNum;
    }

    //Метод для удаления изделия из БД(таблицы PRODUCT_OPERATION,PRODUCT_PLATA,PRODUCT_NUMBER)
    public boolean deleteProduct(int productNum) throws Exception {
        if (productNum != 0) {
            String queryDeleteProductInProduct_Operation = "DELETE FROM PRODUCT_OPERATION WHERE PRODUCT_NUM = " + productNum + "";
            String queryDeleteProductInProduct_Plata = "DELETE FROM PRODUCT_PLATA WHERE PRODUCT_NUM = " + productNum + "";
            String queryDeleteProductInProduct_Number = "DELETE FROM PRODUCT_NUMBER WHERE PRODUCT_NUM = " + productNum + "";
            try {
                DBConnectSingleton.executeQuery(queryDeleteProductInProduct_Operation);
                DBConnectSingleton.executeQuery(queryDeleteProductInProduct_Plata);
                DBConnectSingleton.executeQuery(queryDeleteProductInProduct_Number);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            return true;
        }
        return false;
    }

}
