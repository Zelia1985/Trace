package Client;

import Server.DBServer;
import Server.DBServerArray;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "entryWorkerDB", urlPatterns = {"/entryWorkerDB"})
public class EntryWorkerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //Объявление переменных 
        String exit = request.getParameter("exit");
        String newRegistration = request.getParameter("newRegistration");
        String newRole = request.getParameter("newRole");
        String newPassword = request.getParameter("newPassword");
        int newTabNumber;
        String msgMistakeEmptyField;
        String msgMistakeRoleWorker;
        String msgMistakeAddWorker;
        String addWorkerMsg;

        //Создание экземпляров классов для работы с БД
        DBServer dbServer = new DBServer();
        DBServerArray dbServerArray = new DBServerArray();

        if (newRegistration != null) {
            try {
                //Получение последнего значения табельного номера и создание нового табельного номера на основе последнего
                newTabNumber = dbServerArray.getLastTabNumber() + 1;
                //Проверка на условие пустого значения поля пароля
                if (newPassword.isEmpty()) {
                    msgMistakeEmptyField = "Ошибка - поле пароля не может быть пустым";
                    request.setAttribute("msgMistakeEmptyField", msgMistakeEmptyField);
                    request.getRequestDispatcher("EntryWorker.jsp").forward(request, response);
                }
                //Проверка на условие значения поля роли сотрудника, далее внесение сотрудника в БД при выполнении условия и вывод сообщения
                if (newRole.equals("admin") || newRole.equals("notadmin")) {
                    boolean addWorker = dbServer.addWorker(newTabNumber, newPassword, newRole);
                    if (addWorker) {
                        addWorkerMsg = ("табельный номер - " + newTabNumber + "/пароль - " + newPassword + "/роль - " + newRole);
                        request.setAttribute("addWorkerMsg", addWorkerMsg);
                    } else {
                        msgMistakeAddWorker = "Ошибка - сотрудника в БД не добавлен";
                        request.setAttribute("msgMistakeAddWorker", msgMistakeAddWorker);
                    }
                } else {
                    msgMistakeRoleWorker = "Ошибка - роль сотрудника может иметь только два значения - admin/notadmin";
                    request.setAttribute("msgMistakeRoleWorker", msgMistakeRoleWorker);
                }
                request.getRequestDispatcher("EntryWorker.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(EntryWorkerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Процедура для выхода на страницу авторизации
        if (exit != null) {
            request.getRequestDispatcher("MainAdmin.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
