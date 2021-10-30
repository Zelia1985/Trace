package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBServerArray {

    //Метод для вывода всех сотрудников в таблице Workers
    public ArrayList<String> getAllWorkers() throws Exception {
        ArrayList<String> workers = new ArrayList();
        String queryGetAllWorkers = "SELECT * FROM Workers";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryGetAllWorkers);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    workers.add("Сотрудник с табельным номером: " + Integer.toString(rs.getInt("TABNUMBER")) + ", роль в системе - " + rs.getString("ADMIN") + ";");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        for (int i = 0; i < workers.size(); i++) {
            System.out.println(workers.get(i));
        }
        return workers;
    }

    //Метод для получения значения последнего табельного номера
    public int getLastTabNumber() throws Exception {
        ArrayList<Integer> workers = new ArrayList();
        int lastTabnumber = 0;
        String queryGetLastTabNumber = "SELECT * FROM Workers";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryGetLastTabNumber);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    workers.add(rs.getInt("TABNUMBER"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        lastTabnumber = workers.get(workers.size() - 1);
        System.out.println(lastTabnumber);
        return lastTabnumber;
    }

}
