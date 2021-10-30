package Client;

import Server.DBDirectory;
import Server.DBDirectoryArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "directory", urlPatterns = {"/directory"})
public class DirectoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //Объявление переменных
        String exit = request.getParameter("exit");
        String getProductTypes = request.getParameter("getProductTypes");
        String getCircuitBoardTypes = request.getParameter("getCircuitBoardTypes");
        String getOperationTypes = request.getParameter("getOperationTypes");
        String someNumber = request.getParameter("someNumber");
        String addProductType = request.getParameter("addProductType");
        String addCircuitBoardType = request.getParameter("addCircuitBoardTypesType");
        String addOperationType = request.getParameter("addOperationType");
        ArrayList<String> productTypes;
        ArrayList<String> circuitBoardTypes;
        ArrayList<String> operationTypes;
        String msgMistakeIsInDataBase;
        String msgMistakeEmptyField;
        String msgMistakeAddDataBase;
        String msgAddProductDirectory;
        String msgAddCircuitBoardDirectory;
        String msgAddOperationDirectory;

        //Создание экземпляра класса для работы с БД
        DBDirectory dbDirectory = new DBDirectory();
        DBDirectoryArray dbDirectoryArray = new DBDirectoryArray();

        //Метод для вывода всех типов изделий
        if (getProductTypes != null) {
            try {
                productTypes = dbDirectoryArray.getAllTypesProduct();
                request.setAttribute("product", productTypes);
                request.getRequestDispatcher("Directory.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(DirectoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Метод для вывода всех типов плат
        if (getCircuitBoardTypes != null) {
            try {
                circuitBoardTypes = dbDirectoryArray.getAllTypesCircuitBoard();
                request.setAttribute("circuitBoardTypes", circuitBoardTypes);
                request.getRequestDispatcher("Directory.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(DirectoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Метод для вывода всех типов операций
        if (getOperationTypes != null) {
            try {
                operationTypes = dbDirectoryArray.getAllTypesOperation();
                request.setAttribute("operation", operationTypes);
                request.getRequestDispatcher("Directory.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(DirectoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Метод для добавления типа изделия
        if (addProductType != null) {
            if (!someNumber.isEmpty()) {
                try {
                    boolean checkProduct = dbDirectory.checkProduct(someNumber);
                    if (!checkProduct) {
                        boolean addProduct = dbDirectory.addProduct(someNumber);
                        if (addProduct) {
                            msgAddProductDirectory = ("Изделие " + someNumber + " добавлено в справочник");
                            request.setAttribute("msgAddProductDirectory", msgAddProductDirectory);
                        } else {
                            msgMistakeAddDataBase = "Ошибка -  изделие в справочник не внесено";
                            request.setAttribute("msgMistakeAddDataBase", msgMistakeAddDataBase);
                        }
                    } else {
                        msgMistakeIsInDataBase = "Ошибка - данное изделие уже есть в базе";
                        request.setAttribute("msgMistakeIsInDataBase", msgMistakeIsInDataBase);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DirectoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                msgMistakeEmptyField = "Ошибка - поле ввода пустое";
                request.setAttribute("msgMistakeEmptyField", msgMistakeEmptyField);
            }
            request.getRequestDispatcher("Directory.jsp").forward(request, response);
        }

        //Метод для добавления типа платы
        if (addCircuitBoardType != null) {
            if (!someNumber.isEmpty()) {
                try {
                    boolean checkCircuitBoard = dbDirectory.checkCircuitBoard(someNumber);
                    if (!checkCircuitBoard) {
                        boolean addCircuitBoard = dbDirectory.addCircuitBoard(someNumber);
                        if (addCircuitBoard) {
                            msgAddCircuitBoardDirectory = ("Плата " + someNumber + " добавлена в справочник");
                            request.setAttribute("msgAddCircuitBoardDirectory", msgAddCircuitBoardDirectory);
                        } else {
                            msgMistakeAddDataBase = "Ошибка -  плата в справочник не внесена";
                            request.setAttribute("msgMistakeAddDataBase", msgMistakeAddDataBase);
                        }
                    } else {
                        msgMistakeIsInDataBase = "Ошибка - данная плата уже есть в базе";
                        request.setAttribute("msgMistakeIsInDataBase", msgMistakeIsInDataBase);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DirectoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                msgMistakeEmptyField = "Ошибка - поле ввода пустое";
                request.setAttribute("msgMistakeEmptyField", msgMistakeEmptyField);
            }
            request.getRequestDispatcher("Directory.jsp").forward(request, response);
        }

        //Метод для добавления типа операции
        if (addOperationType != null) {
            if (!someNumber.isEmpty()) {
                try {
                    boolean checkOperation = dbDirectory.checkOperation(someNumber);
                    if (!checkOperation) {
                        boolean addOperation = dbDirectory.addOperation(someNumber);
                        if (addOperation) {
                            msgAddOperationDirectory = ("Операция " + someNumber + " добавлена в справочник");
                            request.setAttribute("msgAddOperationDirectory", msgAddOperationDirectory);
                        } else {
                            msgMistakeAddDataBase = "Ошибка - тип операции в справочник не внесен";
                            request.setAttribute("msgMistakeAddDataBase", msgMistakeAddDataBase);
                        }
                    } else {
                        msgMistakeIsInDataBase = "Ошибка - данная операция уже есть в базе";
                        request.setAttribute("msgMistakeIsInDataBase", msgMistakeIsInDataBase);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DirectoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                msgMistakeEmptyField = "Ошибка - поле ввода пустое";
                request.setAttribute("msgMistakeEmptyField", msgMistakeEmptyField);
            }
            request.getRequestDispatcher("Directory.jsp").forward(request, response);
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
