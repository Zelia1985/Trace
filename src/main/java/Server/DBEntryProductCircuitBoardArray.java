package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBEntryProductCircuitBoardArray {

    //Метод для вывода типов плат из таблицы PLATA
    public ArrayList<String> typeProductCircuitBoard() throws Exception {
        ArrayList<String> circuitBoardType = new ArrayList();
        String queryTypeProductCircuitBoard = "SELECT * FROM PLATA";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryTypeProductCircuitBoard);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    circuitBoardType.add("Тип платы: " + rs.getString("TYPE_PLATA"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return circuitBoardType;
    }

    //Метод для получения значения последнего номера платы (таблица PRODUCT_PLATA)
    public int getLastTabNumberCircuitBoard() throws Exception {
        ArrayList<Integer> productNum = new ArrayList();
        int lastTabNumberProduct = 0;
        String queryGetLastTabNumberCircuitBoard = "SELECT * FROM PRODUCT_PLATA";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryGetLastTabNumberCircuitBoard);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    productNum.add(rs.getInt("PLATA_NUMBER"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        lastTabNumberProduct = productNum.get(productNum.size() - 1);
        return (lastTabNumberProduct + 1);
    }

}
