package Client;

import Server.DBProduct;
import Server.DBProductArray;
import Server.DBServer;
import Server.DBServerArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "admin", urlPatterns = {"/admin"})
public class MainAdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //Объявление переменных
        String exit = request.getParameter("exit");
        String entryProductButton = request.getParameter("entryProductButton");
        String entryWorkerButton = request.getParameter("entryWorkerButton");
        String getAllWorkersButton = request.getParameter("getAllWorkersButton");
        String getAllProductButton = request.getParameter("getAllProductButton");
        String getProductButton = request.getParameter("getProductButton");
        String numberProduct = request.getParameter("numberProduct");
        String entryCircuitBoardButton = request.getParameter("entryCircuitBoardButton");
        String entryOperationButton = request.getParameter("entryOperationButton");
        String directory = request.getParameter("directory");
        ArrayList<String> allProducts;
        ArrayList<String> circuitBoard;
        ArrayList<String> operation;
        ArrayList<String> allWorkers;
        String msgMistakeFormat;
        String msgMistakeFactoryNumber;
        String msgMistakeNotFoundDataBase;
        int intNumberProduct = 0;

        //Создание экземпляров класса для работы с БД
        DBServer dbServer = new DBServer();
        DBServerArray dbServerArray = new DBServerArray();
        DBProduct dbProduct = new DBProduct();
        DBProductArray dbProductArray = new DBProductArray();

        //Процедура для вывода данных по изделию с указанным заводским номером
        if (getProductButton != null) {
            //Проверка на условие числового табельного номера
            try {
                intNumberProduct = Integer.valueOf(numberProduct);
            } catch (NumberFormatException e) {
                msgMistakeFormat = "Ошибка - неверный формат ввода заводского номера";
                request.setAttribute("msgMistakeFormat", msgMistakeFormat);
                request.getRequestDispatcher("MainAdmin.jsp").forward(request, response);
            }
            //Проверка на количество символов в заводском номере
            if (numberProduct.length() != 6) {
                msgMistakeFactoryNumber = "Ошибка - заводской номер должен иметь 6 цифр";
                request.setAttribute("msgMistakeFactoryNumber", msgMistakeFactoryNumber);
                request.getRequestDispatcher("MainAdmin.jsp").forward(request, response);
            } else {
                try {
                    //Вызов метода для проверки наличия ЗН в БД
                    boolean check = dbProduct.checkNumber(intNumberProduct);
                    if (check) {
                        //Вызов методов для вывода информации о платах и операциях, передача данных на JSP
                        circuitBoard = dbProductArray.productCircuitBoard(intNumberProduct);
                        operation = dbProductArray.productOperation(intNumberProduct);
                        request.setAttribute("circuitBoard", circuitBoard);
                        request.setAttribute("operation", operation);
                        
                    } else {
                        msgMistakeNotFoundDataBase = "Ошибка - заводской номер в БД отсутсвует";
                        request.setAttribute("msgMistakeNotFoundDataBase", msgMistakeNotFoundDataBase);
                    }
                    request.getRequestDispatcher("MainAdmin.jsp").forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(MainAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        //Процедура для вывода на странице всех изделий в базе
        if (getAllProductButton != null) {
            try {
                allProducts = dbProductArray.getAllProducts();
                request.setAttribute("allProducts", allProducts);
                request.getRequestDispatcher("MainAdmin.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(MainAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Процедура для перехода на страницу добавления и удаления изделия
        if (entryProductButton != null) {
            request.getRequestDispatcher("EntryProduct.jsp").forward(request, response);
        }

        //Процедура для перехода на страницу добавления и удаления плат изделия
        if (entryCircuitBoardButton != null) {
            request.getRequestDispatcher("EntryCircuitBoard.jsp").forward(request, response);
        }

        //Процедура для перехода на страницу добавления и удаления операции изделия
        if (entryOperationButton != null) {
            request.getRequestDispatcher("EntryOperation.jsp").forward(request, response);
        }

        //Процедура для вывода на странице всех сотрудников в базе
        if (getAllWorkersButton != null) {
            try {
                allWorkers = dbServerArray.getAllWorkers();
                request.setAttribute("allWorkers", allWorkers);
                request.getRequestDispatcher("MainAdmin.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(MainAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Процедура для перехода на страницу добавления сотрудника
        if (entryWorkerButton != null) {
            request.getRequestDispatcher("EntryWorker.jsp").forward(request, response);
        }

        //Процедура для перехода на страницу справочника
        if (directory != null) {
            request.getRequestDispatcher("Directory.jsp").forward(request, response);
        }

        //Процедура для выхода на страницу авторизации
        if (exit != null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
