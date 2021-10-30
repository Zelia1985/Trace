package Client;

import Server.DBEntryProductOperation;
import Server.DBEntryProductOperationArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "entryOperation", urlPatterns = {"/entryOperation"})
public class EntryOperationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //Объявление переменных
        String exit = request.getParameter("exit");
        String productNumber = request.getParameter("productNumber");
        String getInfoOperation = request.getParameter("getInfoOperation");
        String operationProduct = request.getParameter("operationProduct");
        String deleteOperation = request.getParameter("deleteOperation");
        String typeOperation = request.getParameter("typeOperation");
        String addOperation = request.getParameter("addOperation");
        String dateOperation = request.getParameter("dateOperation");
        ArrayList<String> operationOnProduct;
        ArrayList<String> operationType;
        String msgMistakeFactoryNumber;
        String msgMistakeFormat;
        String msgMistakeNotFoundDataBase;
        String msgMistakeOperationToFactoryNumber;
        String msgMistakeDeleteOperation;
        String msgMistakeAddOperation;
        String msgMistakeEmptyDate;
        String msgDeleteOperation;
        String msgAddOperation;
        int productNum = 0;

        //Создание экземпляра классов для работы с БД
        DBEntryProductOperation dbProductOperation = new DBEntryProductOperation();
        DBEntryProductOperationArray dbProductOperationArray = new DBEntryProductOperationArray();

        //Метод для вывода типа операций и операций по ЗН изделия
        if (getInfoOperation != null) {
            try {
                //Проверка на условие пустого значения полей
                if (productNumber.length() != 6) {
                    msgMistakeFactoryNumber = "Ошибка - заводской номер должен иметь 6 цифр";
                    request.setAttribute("msgMistakeFactoryNumber", msgMistakeFactoryNumber);
                    request.getRequestDispatcher("EntryOperation.jsp").forward(request, response);
                }
                //Проверки на ввод номера изделия цифр
                try {
                    productNum = Integer.valueOf(productNumber);
                } catch (NumberFormatException e) {
                    msgMistakeFormat = "Ошибка - неверный формат ввода заводского номера";
                    request.setAttribute("msgMistakeFormat", msgMistakeFormat);
                    request.getRequestDispatcher("EntryOperation.jsp").forward(request, response);
                }
                //Проверка заводского номера на наличие в БД
                boolean checkProductNum = dbProductOperation.checkNumber(productNum);
                if (checkProductNum) {
                    //Вызов метода для получения списка всех операций в изделии и всех типов операций
                    operationOnProduct = dbProductOperationArray.productOperation(productNum);
                    operationType = dbProductOperationArray.typeProductOperation();
                    request.setAttribute("operationOnProduct", operationOnProduct);
                    request.setAttribute("operationType", operationType);
                } else {
                    msgMistakeNotFoundDataBase = "Ошибка - данный заводской номер в БД отсутствует";
                    request.setAttribute("msgMistakeNotFoundDataBase", msgMistakeNotFoundDataBase);
                }
                request.getRequestDispatcher("EntryOperation.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(EntryOperationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Метод для удаления операции по изделия
        if (deleteOperation != null) {
            try {
                //Проверка на условие пустого значения полей
                if (productNumber.length() != 6) {
                    msgMistakeFactoryNumber = "Ошибка - заводской номер должен иметь 6 цифр";
                    request.setAttribute("msgMistakeFactoryNumber", msgMistakeFactoryNumber);
                    request.getRequestDispatcher("EntryOperation.jsp").forward(request, response);
                }
                //Проверки на правильный ввод формата номера изделия
                try {
                    productNum = Integer.valueOf(productNumber);
                } catch (NumberFormatException e) {
                    msgMistakeFormat = "Ошибка - неверный формат ввода заводского номера";
                    request.setAttribute("msgMistakeFormat", msgMistakeFormat);
                    request.getRequestDispatcher("EntryOperation.jsp").forward(request, response);
                }
                //Проверка на соответствие изделия и операции
                boolean checkOperationOnProduct = dbProductOperation.checkOperationOnProduct(productNum, operationProduct);
                if (checkOperationOnProduct) {
                    //Вызов метода удаления операции для изделия
                    boolean checkDeleteOperationOnProduct = dbProductOperation.deleteOperationOnProduct(productNum, operationProduct);
                    if (checkDeleteOperationOnProduct) {
                        msgDeleteOperation = ("Операция " + operationProduct + " удалена из изделия с ЗН" + productNum);
                        request.setAttribute("msgDeleteOperation", msgDeleteOperation);
                    } else {
                        msgMistakeDeleteOperation = "Ошибка - удалении операции не проведено";
                        request.setAttribute("msgMistakeDeleteOperation", msgMistakeDeleteOperation);
                    }
                    request.getRequestDispatcher("EntryOperation.jsp").forward(request, response);
                } else {
                    msgMistakeOperationToFactoryNumber = "Ошибка - данная операция не соответствует ЗН изделия";
                    request.setAttribute("msgMistakeOperationToFactoryNumber", msgMistakeOperationToFactoryNumber);
                }
                request.getRequestDispatcher("EntryOperation.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(EntryOperationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Метод для добавления операции по изделию  
        if (addOperation != null) {
            try {
                //Проверка на условие пустого значения полей
                if (productNumber.length() != 6) {
                    msgMistakeFactoryNumber = "Ошибка - заводской номер должен иметь 6 цифр";
                    request.setAttribute("msgMistakeFactoryNumber", msgMistakeFactoryNumber);
                    request.getRequestDispatcher("EntryOperation.jsp").forward(request, response);
                }
                //Проверка на правильный ввод формата номера изделия
                try {
                    productNum = Integer.valueOf(productNumber);
                } catch (NumberFormatException e) {
                    msgMistakeFormat = "Ошибка - неверный формат ввода заводского номера";
                    request.setAttribute("msgMistakeFormat", msgMistakeFormat);
                    request.getRequestDispatcher("EntryOperation.jsp").forward(request, response);
                }

                //Проверки на правильный ввод формата даты
                if (dateOperation.isEmpty()) {
                    msgMistakeEmptyDate = "Введите поле даты в формате хх.хх.хххх";
                    request.setAttribute("msgMistakeEmptyDate", msgMistakeEmptyDate);
                    request.getRequestDispatcher("EntryOperation.jsp").forward(request, response);
                }

                //Проверка заводского номера на наличие в БД
                boolean checkProductNum = dbProductOperation.checkNumber(productNum);

                if (checkProductNum) {
                    //Метод для проверки операции в БД
                    boolean checkOperation = dbProductOperation.checkOperation(typeOperation);
                    if (checkOperation) {
                        boolean addCircuitBoardOnProduct = dbProductOperation.addOperation(productNum, typeOperation, dateOperation);
                        if (addCircuitBoardOnProduct) {
                            msgAddOperation = ("Операция " + typeOperation + " с датой " + dateOperation + " добавлена к изделию с ЗН " + productNum);
                            request.setAttribute("msgAddOperation", msgAddOperation);

                        } else {
                            msgMistakeAddOperation = "Ошибка - добавление операции не проведено";
                            request.setAttribute("msgMistakeAddOperation", msgMistakeAddOperation);
                        }
                    } else {
                        msgMistakeNotFoundDataBase = "Ошибка - данный тип операции в БД отсутствует";
                        request.setAttribute("msgMistakeNotFoundDataBase", msgMistakeNotFoundDataBase);
                    }
                } else {
                    msgMistakeNotFoundDataBase = "Ошибка - данный заводской номер изделия в БД отсутствует";
                    request.setAttribute("msgMistakeNotFoundDataBase", msgMistakeNotFoundDataBase);
                }
                request.getRequestDispatcher("EntryOperation.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(EntryOperationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
