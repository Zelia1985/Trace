package Client;

import Server.DBServer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "getAuthorization", urlPatterns = {"/getAuthorization"})
public class AuthorizationClient extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //Объявление переменных
        String registration = request.getParameter("registration");
        String msgMistakeEmptyField;
        String msgMistakeIncorrectFormat;
        String msgMistakeNotFoundDataBase;
        Integer intTabNumber = null;
        String tabNumber = request.getParameter("tabNumber");
        String password = request.getParameter("password");

        //Создание экземпляра класса для работы с БД
        DBServer dbServer = new DBServer();

        if (registration != null) {

            //Проверка на условие пустого значения полей
            if (tabNumber.isEmpty() || password.isEmpty()) {
                msgMistakeEmptyField = "Ошибка - поле табельного номера и пароля не может быть пустым";
                request.setAttribute("msgMistakeEmptyField", msgMistakeEmptyField);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            //Проверка на условие числового табельного номера
            try {
                intTabNumber = Integer.valueOf(tabNumber);
            } catch (NumberFormatException e) {
                msgMistakeIncorrectFormat = "Ошибка - неверный формат ввода табельного номера";
                request.setAttribute("msgMistakeIncorrectFormat", msgMistakeIncorrectFormat);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            //Вызов метода проверки табельного номера и пароля
            boolean check = false;
            try {
                check = dbServer.checkWorker(intTabNumber, password);
            } catch (Exception ex) {
                Logger.getLogger(AuthorizationClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Вызов метода проверки роли сотрудника
            boolean checkRole = false;
            try {
                checkRole = dbServer.checkWorkerRole(intTabNumber, password);
            } catch (Exception ex) {
                Logger.getLogger(AuthorizationClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Проверка на корректность данных
            if (!check) {
                msgMistakeNotFoundDataBase = "Ошибка - сотрудник с данным табельным номером и паролем в БД не найден";
                request.setAttribute("msgMistakeNotFoundDataBase", msgMistakeNotFoundDataBase);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            //Перевод на основные рабочие страницы
            if (checkRole) {
                request.getRequestDispatcher("MainAdmin.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("MainNotAdmin.jsp").forward(request, response);
            }
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
