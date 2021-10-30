package Server;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBEntryProductCircuitBoard {

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

    //Метод проверки номера платы в таблице PRODUCT_PLATA
    public boolean checkCircuitBoard(int circuitBoardNumber) throws Exception {
        boolean check = false;
        String queryCheckCircuitBoard = "SELECT * FROM PRODUCT_PLATA WHERE PLATA_NUMBER = " + circuitBoardNumber + " ";
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

    //Метод проверки соответствия номера изделия и номера платы в таблице PRODUCT_PLATA
    public boolean checkCircuitBoardOnProduct(int productNum, int circuitBoardNumber) throws Exception {
        boolean check = false;
        String queryCheckCircuitBoardOnProduct = "SELECT * FROM PRODUCT_PLATA WHERE PRODUCT_NUM = " + productNum + " AND PLATA_NUMBER = " + circuitBoardNumber + " ";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryCheckCircuitBoardOnProduct);
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

    //Метод для удаления платы из БД(таблица PRODUCT_PLATA)
    public boolean deleteCircuitBoardOnProduct(int productNum, int circuitBoardNumber) throws Exception {
        if (productNum != 0 && circuitBoardNumber != 0) {
            String queryDeleteCircuitBoardOnProduct = "DELETE FROM PRODUCT_PLATA WHERE PRODUCT_NUM = " + productNum + " AND PLATA_NUMBER = " + circuitBoardNumber + "";
            try {
                DBConnectSingleton.executeQuery(queryDeleteCircuitBoardOnProduct);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            return true;
        }
        return false;
    }

    //Метод проверки типа платы в таблице PLATA
    public boolean checkTypeCircuitBoard(String circuitBoardType) throws Exception {
        boolean check = false;
        String queryCheckTypeCircuitBoard = "SELECT * FROM PLATA WHERE TYPE_PLATA = '" + circuitBoardType + "' ";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryCheckTypeCircuitBoard);
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

    //Метод для добавления платы в изделие
    public boolean addCircuitBoard(int productNum, String circuitBoardPlata, int circuitBoardNumber) throws Exception {
        if (circuitBoardPlata != null && productNum != 0) {
            String queryAddCircuitBoard = "INSERT INTO PRODUCT_PLATA(PRODUCT_NUM,PRODUCT_PLATA_ON,PLATA_NUMBER) VALUES (" + productNum + ",'" + circuitBoardPlata + "'," + circuitBoardNumber + ")";
            try {
                DBConnectSingleton.executeQuery(queryAddCircuitBoard);
                System.out.println("Плата внесена в БД");
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println(true);
            return true;
        }
        return false;
    }
}
