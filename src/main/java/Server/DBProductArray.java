package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBProductArray {

    //Метод для вывода всех изделий из БД (таблица PRODUCT_NUMBER)
    public ArrayList<String> getAllProducts() throws Exception {
        ArrayList<String> products = new ArrayList();
        String queryGetAllProducts = "SELECT * FROM PRODUCT_NUMBER";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryGetAllProducts);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    products.add("Изделие:" + rs.getString("TYPE_PRODUCT") + "/ ЗН:" + Integer.toString(rs.getInt("PRODUCT_NUM")));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return products;
    }

    //Метод для вывода плат входящих в изделие из таблицы PRODUCT_PLATA
    public ArrayList<String> productCircuitBoard(int productNum) throws Exception {
        ArrayList<String> circuitBoard = new ArrayList();
        String queryProductCircuitBoard = "SELECT * FROM PRODUCT_PLATA WHERE PRODUCT_NUM = " + productNum + "";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryProductCircuitBoard);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    circuitBoard.add("В изделие с З/Н " + productNum + " входит:" + rs.getString("PRODUCT_PLATA_ON") + " З/Н:" + Integer.toString(rs.getInt("PLATA_NUMBER")));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return circuitBoard;
    }

    //Метод для вывода операций по заводскому номеру изделия
    public ArrayList<String> productOperation(int productNum) throws Exception {
        ArrayList<String> operation = new ArrayList();
        String queryProductOperation = "SELECT * FROM PRODUCT_OPERATION WHERE PRODUCT_NUM = " + productNum + "";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryProductOperation);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    operation.add("С изделие с З/Н " + productNum + " проводилась операция:" + rs.getString("PRODUCT_OPERATION_ON") + " дата - " + rs.getString("OPERATION_DATE"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return operation;
    }

    //Метод для вывода всех типов изделий из БД (таблица PRODUCT)
    public ArrayList<String> getAllTypeProduct() throws Exception {
        ArrayList<String> typeProduct = new ArrayList();
        String queryGetAllTypeProduct = "SELECT * FROM PRODUCT";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryGetAllTypeProduct);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    typeProduct.add(rs.getString("TYPE_PRODUCT"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return typeProduct;
    }

}
