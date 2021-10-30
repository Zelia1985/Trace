package Server;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDirectory {

    //Метод проверки на наличие изделия в таблице PRODUCT
    public boolean checkProduct(String typeProduct) throws Exception {
        boolean check = false;
        String queryCheckProduct = "SELECT * FROM PRODUCT WHERE TYPE_PRODUCT = '" + typeProduct + "' ";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryCheckProduct);
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

    //Метод добавления продукта в справочник PRODUCT
    public boolean addProduct(String typeProduct) throws Exception {
        if (typeProduct != null) {
            String queryAddProduct = "INSERT INTO PRODUCT(TYPE_PRODUCT) VALUES ('" + typeProduct + "')";
            try {
                DBConnectSingleton.executeQuery(queryAddProduct);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            return true;
        }
        return false;
    }

    //Метод проверки на наличие платы в таблице PLATA
    public boolean checkCircuitBoard(String typeCircuitBoard) throws Exception {
        boolean check = false;
        String queryCheckCircuitBoard = "SELECT * FROM PLATA WHERE TYPE_PLATA = '" + typeCircuitBoard + "' ";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryCheckCircuitBoard);
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

    //Метод добавления платы в справочник PLATA
    public boolean addCircuitBoard(String typeCircuitBoard) throws Exception {
        if (typeCircuitBoard != null) {
            String queryAddCircuitBoard = "INSERT INTO PLATA(TYPE_PLATA) VALUES ('" + typeCircuitBoard + "')";
            try {
                DBConnectSingleton.executeQuery(queryAddCircuitBoard);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            return true;
        }
        return false;
    }

    //Метод проверки на наличие операции в таблице OPERATION
    public boolean checkOperation(String typeOperation) throws Exception {
        boolean check = false;
        String queryCheckOperation = "SELECT * FROM OPERATION WHERE TYPE_OPERATION = '" + typeOperation + "' ";
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

    //Метод добавления платы в справочник OPERATION
    public boolean addOperation(String typeOperation) throws Exception {
        if (typeOperation != null) {
            String queryAddOperation = "INSERT INTO OPERATION(TYPE_OPERATION) VALUES ('" + typeOperation + "')";
            try {
                DBConnectSingleton.executeQuery(queryAddOperation);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            return true;
        }
        return false;
    }

}
