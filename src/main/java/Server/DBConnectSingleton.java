package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectSingleton {

    private static final String URL = "jdbc:derby://localhost:1527/DBTrace";
    private static Connection con = null;
    private static Statement st = null;
    
    //Использование паттерна при ситуации когда необходима многопоточность и 
    //есть гарантия, что объект синглтон класса будет создан без проблем.
    private DBConnectSingleton() {
   }

   private static class SingletonHolder {
       public static final DBConnectSingleton HOLDER_INSTANCE = new DBConnectSingleton();
   }

   public static DBConnectSingleton getInstance() {
       return SingletonHolder.HOLDER_INSTANCE;
   }

    // Метод создания подключения к базе данных
    private static void setConnection() throws Exception {
        try {
            con = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Метод создания условия для базы данных
    private static void setStatement() throws Exception {
        st = con.createStatement();
    }

    //Метод для обработки запроса БД
    public static ResultSet executeQuery(String query) throws Exception {
        // Если первое обращение к БД, необходимо подкючиться
        if (con == null) {
            setConnection();
            setStatement();
        }
        // Выполняем запрос в виде строки
        return st.executeQuery(query);

    }
}
