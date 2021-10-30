package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBEntryProductOperationArray {

    //Метод для вывода типов операций из таблицы OPERATION
    public ArrayList<String> typeProductOperation() throws Exception {
        ArrayList<String> circuitBoardType = new ArrayList();
        String queryTypeProductOperation = "SELECT * FROM OPERATION";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryTypeProductOperation);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    circuitBoardType.add("Тип операции: " + rs.getString("TYPE_OPERATION"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return circuitBoardType;
    }

    //Метод для вывода операций входящих в изделие из таблицы PRODUCT_OPERATION
    public ArrayList<String> productOperation(int productNum) throws Exception {
        ArrayList<String> circuitBoard = new ArrayList();
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
                    circuitBoard.add("По изделие с З/Н " + productNum + " была проведена операция:" + rs.getString("PRODUCT_OPERATION_ON") + " с датой: " + rs.getString("OPERATION_DATE"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return circuitBoard;
    }

}
