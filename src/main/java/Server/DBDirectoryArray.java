package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBDirectoryArray {

    //Метод для вывода всех типов изделий из БД (таблица PRODUCT)
    public ArrayList<String> getAllTypesProduct() throws Exception {
        ArrayList<String> typeProduct = new ArrayList();
        String queryGetAllTypesProduct = "SELECT * FROM PRODUCT";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryGetAllTypesProduct);
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

    //Метод для вывода типов плат из таблицы PLATA
    public ArrayList<String> getAllTypesCircuitBoard() throws Exception {
        ArrayList<String> circuitBoardType = new ArrayList();
        String queryGetAllTypesCircuitBoard = "SELECT * FROM PLATA";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryGetAllTypesCircuitBoard);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    circuitBoardType.add(rs.getString("TYPE_PLATA"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return circuitBoardType;
    }

    //Метод для вывода типов операций из таблицы OPERATION
    public ArrayList<String> getAllTypesOperation() throws Exception {
        ArrayList<String> circuitBoardType = new ArrayList();
        String queryGetAllTypesOperation = "SELECT * FROM OPERATION";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryGetAllTypesOperation);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    circuitBoardType.add(rs.getString("TYPE_OPERATION"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return circuitBoardType;
    }

}
