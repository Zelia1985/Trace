package Client;

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

@WebServlet(name = "entryProduct", urlPatterns = {"/entryProduct"})
public class EntryProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //Объявление переменных
        String exit = request.getParameter("exit");
        String newProduct = request.getParameter("newProduct");
        String productType = request.getParameter("productType");
        String productNumber = request.getParameter("productNumber");
        String deleteProduct = request.getParameter("deleteProduct");
        String getInfo = request.getParameter("getInfo");
        String msgMistakeTypeProduct;
        String msgMistakeFactoryNumber;
        String msgMistakeFormat;
        String msgMistakeNotFoundDataBase;
        String msgMistakeAddProduct;
        String msgMistakeDeleteProduct;
        String msgAddProduct;
        String msgDeleteProduct;
        int productNum = 0;
        int productNumLast;
        ArrayList<String> allProducts;
        ArrayList<String> allTypeProduct;

        //Создание экземпляров классов для работы с БД
        DBProduct dbProduct = new DBProduct();
        DBProductArray dbProductArray = new DBProductArray();

        //Процедура для вывода типов изделий и изделий с ЗН занесенных в БД
        if (getInfo != null) {
            try {
                allProducts = dbProductArray.getAllProducts();
                allTypeProduct = dbProductArray.getAllTypeProduct();
                request.setAttribute("allProducts", allProducts);
                request.setAttribute("allTypeProduct", allTypeProduct);
                request.getRequestDispatcher("EntryProduct.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(EntryProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Процедура для добавления изделия в БД
        if (newProduct != null) {
            try {
                //Проверки на ввод типа изделия
                boolean checkProdType = dbProduct.checkProductType(productType);
                if (!checkProdType) {
                    msgMistakeTypeProduct = "Ошибка - введен несуществующий тип изделия";
                    request.setAttribute("msgMistakeTypeProduct", msgMistakeTypeProduct);
                } else {
                    //Получение сквозного заводского номера
                    productNumLast = dbProduct.getLastTabNumberProduct();
                    //Вызов метода для добавления нового изделия с указанием типа и заводского номера
                    boolean checkAddProduct = dbProduct.addProduct(productType, productNumLast);
                    if (checkAddProduct) {
                        msgAddProduct = ("В базу добавлено изделие " + productType + " с заводским номером " + productNumLast);
                        request.setAttribute("msgAddProduct", msgAddProduct);
                    } else {
                        msgMistakeAddProduct = ("Ошибка  - добавление изделия с заводским номером " + productNumLast + " не проведено");
                        request.setAttribute("msgMistakeAddProduct", msgMistakeAddProduct);
                    }
                }
                request.getRequestDispatcher("EntryProduct.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(EntryProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Процедура для удаления изделя из БД
        if (deleteProduct != null) {
            try {
                //Проверка на условие пустого значения полей
                if (productNumber.length() != 6) {
                    msgMistakeFactoryNumber = "Ошибка - заводской номер должен иметь 6 цифр";
                    request.setAttribute("msgMistakeFactoryNumber", msgMistakeFactoryNumber);
                    request.getRequestDispatcher("EntryProduct.jsp").forward(request, response);
                }
                //Проверки на ввод номера изделия цифр
                try {
                    productNum = Integer.valueOf(productNumber);
                } catch (NumberFormatException e) {
                    msgMistakeFormat = "Ошибка - неверный формат ввода заводского номера";
                    request.setAttribute("msgMistakeFormat", msgMistakeFormat);
                    request.getRequestDispatcher("EntryProduct.jsp").forward(request, response);
                }
                //Проверка заводского номера на наличие в БД
                boolean checkProductNum = dbProduct.checkProductNum(productNum);

                if (checkProductNum) {
                    //Вызов метода для удаления изделия с указанием заводского номера
                    boolean checkDeleteProduct = dbProduct.deleteProduct(productNum);
                    if (checkDeleteProduct) {
                        msgDeleteProduct = ("Из базы удалено изделие с заводским номером " + productNum);
                        request.setAttribute("msgDeleteProduct", msgDeleteProduct);
                    } else {
                        msgMistakeDeleteProduct = ("Ошибка - удаление изделия с заводским номером " + productNum + " не проведено");
                        request.setAttribute("msgMistakeDeleteProduct", msgMistakeDeleteProduct);
                    }
                } else {
                    msgMistakeNotFoundDataBase = "Ошибка - данный заводской номер в БД отсутствует";
                    request.setAttribute("msgMistakeNotFoundDataBase", msgMistakeNotFoundDataBase);
                }
                request.getRequestDispatcher("EntryProduct.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(EntryProductServlet.class.getName()).log(Level.SEVERE, null, ex);
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
