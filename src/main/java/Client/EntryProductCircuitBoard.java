package Client;

import Server.DBEntryProductCircuitBoard;
import Server.DBEntryProductCircuitBoardArray;
import Server.DBProduct;
import Server.DBProductArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "entryCircuitBoard", urlPatterns = {"/entryCircuitBoard"})
public class EntryProductCircuitBoard extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //Объявление переменных
        String exit = request.getParameter("exit");
        String productNumber = request.getParameter("productNumber");
        String getInfoCircuitBoard = request.getParameter("getInfoCircuitBoard");
        String circuitBoardNumber = request.getParameter("circuitBoardNumber");
        String deleteCircuitBoard = request.getParameter("deleteCircuitBoard");
        String typeCircuitBoard = request.getParameter("typeCircuitBoard");
        String addCircuitBoard = request.getParameter("addCircuitBoard");
        ArrayList<String> circuitBoardOnProduct;
        ArrayList<String> circuitBoardType;
        String msgMistakeFactoryNumber;
        String msgMistakeFormat;
        String msgMistakeNotFoundDataBase;
        String msgMistakeCircuitBoardToFactoryNumber;
        String msgMistakeAddCircuitBoard;
        String msgDeleteCircuitBoard;
        String msgAddCircuitBoard;
        int productNum = 0;
        int circuitBoardNum = 0;
        int lastTabNumberCircuitBoard = 0;

        //Создание экземпляров классов для работы с БД
        DBEntryProductCircuitBoard dbProductCircuitBoard = new DBEntryProductCircuitBoard();
        DBEntryProductCircuitBoardArray dbProductCircuitBoardArray = new DBEntryProductCircuitBoardArray();
        DBProduct dbProduct = new DBProduct();
        DBProductArray dbProductArray = new DBProductArray();

        //Метод для вывода типа плат и плат в изделии по ЗН изделия
        if (getInfoCircuitBoard != null) {
            try {
                //Проверка на условие пустого значения полей
                if (productNumber.length() != 6) {
                    msgMistakeFactoryNumber = "Ошибка - заводской номер должен иметь 6 цифр";
                    request.setAttribute("msgMistakeFactoryNumber", msgMistakeFactoryNumber);
                    request.getRequestDispatcher("EntryCircuitBoard.jsp").forward(request, response);
                }
                //Проверки на ввод номера изделия цифр
                try {
                    productNum = Integer.valueOf(productNumber);
                } catch (NumberFormatException e) {
                    msgMistakeFormat = "Ошибка - неверный формат ввода заводского номера";
                    request.setAttribute("msgMistakeFormat", msgMistakeFormat);
                    request.getRequestDispatcher("EntryCircuitBoard.jsp").forward(request, response);
                }
                //Проверка заводского номера на наличие в БД
                boolean checkProductNum = dbProductCircuitBoard.checkNumber(productNum);
                if (checkProductNum) {
                    //Вызов метода для получения списка всех плат в изделии и всех типов плат
                    circuitBoardOnProduct = dbProductArray.productCircuitBoard(productNum);
                    circuitBoardType = dbProductCircuitBoardArray.typeProductCircuitBoard();
                    request.setAttribute("circuitBoardOnProduct", circuitBoardOnProduct);
                    request.setAttribute("circuitBoardType", circuitBoardType);
                } else {
                    msgMistakeNotFoundDataBase = "Ошибка - данный заводской номер в БД отсутствует";
                    request.setAttribute("msgMistakeNotFoundDataBase", msgMistakeNotFoundDataBase);
                }
                request.getRequestDispatcher("EntryCircuitBoard.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(EntryProductCircuitBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Метод для удаления платы из изделия
        if (deleteCircuitBoard != null) {
            try {
                //Проверка на условие пустого значения полей
                if (productNumber.length() != 6) {
                    msgMistakeFactoryNumber = "Ошибка - заводской номер должен иметь 6 цифр";
                    request.setAttribute("msgMistakeFactoryNumber", msgMistakeFactoryNumber);
                    request.getRequestDispatcher("EntryCircuitBoard.jsp").forward(request, response);
                }
                //Проверки на правильный ввод формата номера изделия
                try {
                    productNum = Integer.valueOf(productNumber);
                } catch (NumberFormatException e) {
                    msgMistakeFormat = "Ошибка - неверный формат ввода заводского номера";
                    request.setAttribute("msgMistakeFormat", msgMistakeFormat);
                    request.getRequestDispatcher("EntryCircuitBoard.jsp").forward(request, response);
                }
                //Проверка заводского номера на наличие в БД
                boolean checkProductNum = dbProductCircuitBoard.checkNumber(productNum);
                if (checkProductNum) {
                    //Проверки на правильный ввод формата номера платы
                    try {
                        circuitBoardNum = Integer.valueOf(circuitBoardNumber);
                    } catch (NumberFormatException e) {
                        msgMistakeFormat = "Ошибка - неверный формат ввода заводского номера платы";
                        request.setAttribute("msgMistakeFormat", msgMistakeFormat);
                        request.getRequestDispatcher("EntryCircuitBoard.jsp").forward(request, response);
                    }
                    //Проверка на наличие платы в БД
                    boolean checkCircuitBoard = dbProductCircuitBoard.checkCircuitBoard(circuitBoardNum);
                    if (checkCircuitBoard) {
                        //Проверка на соответствие изделия и платы
                        boolean checkCircuitBoardOnProduct = dbProductCircuitBoard.checkCircuitBoardOnProduct(productNum, circuitBoardNum);
                        if (checkCircuitBoardOnProduct) {
                            //Вызов метода удаления платы для изделия
                            boolean checkDeleteCircuitBoardOnProduct = dbProductCircuitBoard.deleteCircuitBoardOnProduct(productNum, circuitBoardNum);
                            if (checkDeleteCircuitBoardOnProduct) {
                                msgDeleteCircuitBoard = ("Плата с ЗН " + circuitBoardNum + " удалена из изделия с ЗН" + productNum);
                                request.setAttribute("msgDeleteCircuitBoard", msgDeleteCircuitBoard);
                            } else {
                                msgMistakeAddCircuitBoard = "Ошибка - удалении платы не проведено";
                                request.setAttribute("msgMistakeAddCircuitBoard", msgMistakeAddCircuitBoard);
                            }
                        } else {
                            msgMistakeCircuitBoardToFactoryNumber = "Ошибка - данный ЗН платы не соответствует ЗН изделия";
                            request.setAttribute("msgMistakeCircuitBoardToFactoryNumber", msgMistakeCircuitBoardToFactoryNumber);
                        }
                    } else {
                        msgMistakeNotFoundDataBase = "Ошибка - данный заводской номер платы в БД отсутствует";
                        request.setAttribute("msgMistakeNotFoundDataBase", msgMistakeNotFoundDataBase);
                    }
                } else {
                    msgMistakeNotFoundDataBase = "Ошибка - данный заводской номер изделия в БД отсутствует";
                    request.setAttribute("msgMistakeNotFoundDataBase", msgMistakeNotFoundDataBase);
                }
                request.getRequestDispatcher("EntryCircuitBoard.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(EntryProductCircuitBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Метод для добавления платы в изделия  
        if (addCircuitBoard != null) {
            try {
                //Проверка на условие пустого значения полей
                if (productNumber.length() != 6) {
                    msgMistakeFactoryNumber = "Ошибка - заводской номер должен иметь 6 цифр";
                    request.setAttribute("msgMistakeFactoryNumber", msgMistakeFactoryNumber);
                    request.getRequestDispatcher("EntryCircuitBoard.jsp").forward(request, response);
                }
                //Проверки на правильный ввод формата номера изделия
                try {
                    productNum = Integer.valueOf(productNumber);
                } catch (NumberFormatException e) {
                    msgMistakeFormat = "Ошибка - неверный формат ввода заводского номера";
                    request.setAttribute("msgMistakeFormat", msgMistakeFormat);
                    request.getRequestDispatcher("EntryCircuitBoard.jsp").forward(request, response);
                }
                //Проверка заводского номера на наличие в БД
                boolean checkProductNum = dbProductCircuitBoard.checkNumber(productNum);
                if (checkProductNum) {
                    boolean checkCircuitBoardType = dbProductCircuitBoard.checkTypeCircuitBoard(typeCircuitBoard);
                    if (checkCircuitBoardType) {
                        lastTabNumberCircuitBoard = dbProductCircuitBoardArray.getLastTabNumberCircuitBoard();
                        boolean addCircuitBoardOnProduct = dbProductCircuitBoard.addCircuitBoard(productNum, typeCircuitBoard, lastTabNumberCircuitBoard);
                        if (addCircuitBoardOnProduct) {
                            msgAddCircuitBoard = ("Плата типа " + typeCircuitBoard + " с ЗН " + lastTabNumberCircuitBoard + " добавлена к изделию с ЗН " + productNum);
                            request.setAttribute("msgAddCircuitBoard", msgAddCircuitBoard);
                        } else {
                            msgMistakeAddCircuitBoard = "Ошибка - добавлении платы не проведено";
                            request.setAttribute("msgMistakeAddCircuitBoard", msgMistakeAddCircuitBoard);
                        }
                    } else {
                        msgMistakeNotFoundDataBase = "Ошибка - данный тип платы в БД отсутствует";
                        request.setAttribute("msgMistakeNotFoundDataBase", msgMistakeNotFoundDataBase);
                    }
                } else {
                    msgMistakeNotFoundDataBase = "Ошибка - данный заводской номер изделия в БД отсутствует";
                    request.setAttribute("msgMistakeNotFoundDataBase", msgMistakeNotFoundDataBase);
                }
                request.getRequestDispatcher("EntryCircuitBoard.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(EntryProductCircuitBoard.class.getName()).log(Level.SEVERE, null, ex);
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
