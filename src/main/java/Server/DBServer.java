package Server;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBServer {

    //Метод проверки табельного номера и пароля
    public boolean checkWorker(Integer tabnumber, String password) throws Exception {
        boolean check = false;
        String queryCheckWorker = "SELECT * FROM Workers WHERE TABNUMBER = " + tabnumber + " AND PASSWORD = '" + password + "'";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryCheckWorker);
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
        System.out.println(check);
        return check;
    }

    //Метод проверки роли сотрудника
    public boolean checkWorkerRole(Integer tabnumber, String password) throws Exception {
        boolean check = false;
        String queryCheckWorkerRole = "SELECT * FROM Workers WHERE TABNUMBER = " + tabnumber + " AND PASSWORD = '" + password + "' AND ADMIN = 'admin'";
        ResultSet rs = null;
        try {
            rs = DBConnectSingleton.executeQuery(queryCheckWorkerRole);
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
        System.out.println(check);
        return check;
    }

    //Метод для внесения нового сотрудника в БД
    public boolean addWorker(int tabnumber, String password, String role) throws Exception {
        String queryAddWorker = "INSERT INTO WORKERS (TABNUMBER,PASSWORD,ADMIN) VALUES (" + tabnumber + ",'" + password + "','"
                + role + "')";
        try {
            DBConnectSingleton.executeQuery(queryAddWorker);
            System.out.println("Внесен новый сотрудник/" + tabnumber + "/" + password + "/" + role);
        } catch (SQLException ex) {
            System.out.println("Error #7: " + ex.getMessage());
            return false;
        }
        return true;
    }

}
