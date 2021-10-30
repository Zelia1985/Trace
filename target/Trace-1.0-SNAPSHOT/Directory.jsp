<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Справочник</h2>
        <form action="directory" method="POST">

            <!-- Кнопка для получения данных по типам изделий -->
            <p>
                <input type="submit" value="Получить перечень по типам изделий" name="getProductTypes" style="width:250px;height:25px"/>
            </p> 

            <!-- Кнопка для получения данных по типам плат -->
            <p>
                <input type="submit" value="Получить перечень по типам плат" name="getCircuitBoardTypes" style="width:250px;height:25px"/>
            </p> 

            <!-- Кнопка для получения данных по типам операций -->
            <p>
                <input type="submit" value="Получить перечень по типам операций" name="getOperationTypes" style="width:250px;height:25px"/>
            </p> 

            <!-- Основное поле ввода справочников   -->
            <p>Введите тип платы, операции или изделия, по которым необходимы изменения: <br /><br />
                <input type="text" name="someNumber" value="" style="width:250px;height:18px"/>  
            </p> 

            <!-- Кнопка для добавления типа изделия -->
            <p>
                <input type="submit" value="Добавить тип изделия в справочник" name="addProductType" style="width:250px;height:25px"/>
            </p>            

            <!-- Кнопка для добавления типа платы -->
            <p>
                <input type="submit" value="Добавить тип платы в справочник" name="addCircuitBoardTypesType" style="width:250px;height:25px"/>
            </p> 

            <!-- Кнопка для добавления типа операции -->
            <p>
                <input type="submit" value="Добавить тип операции в справочник" name="addOperationType" style="width:250px;height:25px"/>
            </p> 

            <!-- Кнопка для перехода на страницу авторизации -->
            <p>
                <input type="submit" value="Выход" name="exit" style="width:250px;height:25px"/>
            </p> 

            <br>

        </form>

        <!-- Запрос и обработка сообщения об ошибке при типе изделия, который уже есть в БД -->
        <% String msgMistakeIsInDataBase = (String) request.getAttribute("msgMistakeIsInDataBase");
            if (msgMistakeIsInDataBase != null) {
        %>
        <h2>
            <%=msgMistakeIsInDataBase%>
        </h2>
        <%} %>

        <!-- Запрос и обработка сообщения об ошибке при типе изделия, которого нет в БД -->
        <% String msgMistakeEmptyField = (String) request.getAttribute("msgMistakeEmptyField");
            if (msgMistakeEmptyField != null) {
        %>
        <h2>
            <%=msgMistakeEmptyField%>
        </h2>
        <%} %>       

        <!-- Запрос и обработка сообщения об ошибке при занесении нового изделия в справочник -->
        <% String msgMistakeAddDataBase = (String) request.getAttribute("msgMistakeAddDataBase");
            if (msgMistakeAddDataBase != null) {
        %>
        <h2>
            <%=msgMistakeAddDataBase%>
        </h2>
        <%} %>

        <!-- Запрос и обработка сообщения об успешном добавление изделия в справочник -->
        <% String msgAddProductDirectory = (String) request.getAttribute("msgAddProductDirectory");
            if (msgAddProductDirectory != null) {
        %>
        <h2>
            <%=msgAddProductDirectory%>
        </h2>
        <%} %>

        <!-- Запрос и обработка сообщения об успешном добавление платы в справочник -->
        <% String msgAddCircuitBoardDirectory = (String) request.getAttribute("msgAddCircuitBoardDirectory");
            if (msgAddCircuitBoardDirectory != null) {
        %>
        <h2>
            <%=msgAddCircuitBoardDirectory%>
        </h2>
        <%} %>

        <!-- Запрос и обработка сообщения об успешном добавление платы в справочник -->
        <% String msgAddOperationDirectory = (String) request.getAttribute("msgAddOperationDirectory");
            if (msgAddOperationDirectory != null) {
        %>
        <h2>
            <%=msgAddOperationDirectory%>
        </h2>
        <%} %>

        <!-- Запрос и обработка списка всех операций в изделии -->
        <% ArrayList<String> product = (ArrayList<String>) request.getAttribute("product"); %>
        <%     if (product != null) { %> 
        <h3 style="text-indent:20px;line-height:0.2"> 
            Список всех типов изделий:       
        </h3>
        <%         for (int i = 0; i < product.size(); i++) {%>    
        <p style="text-indent:20px;line-height:0.2"> 
            <%=product.get(i)%>
            <%  } %>
        </p>
        <% } %>

        <!-- Запрос и обработка списка всех операций в изделии -->
        <% ArrayList<String> circuitBoardTypes = (ArrayList<String>) request.getAttribute("circuitBoardTypes"); %>
        <%     if (circuitBoardTypes != null) { %> 
        <h3 style="text-indent:20px;line-height:0.2"> 
            Список всех типов плат:       
        </h3>
        <%         for (int i = 0; i < circuitBoardTypes.size(); i++) {%>      
        <p style="text-indent:20px;line-height:0.2">  
            <%=circuitBoardTypes.get(i)%>
            <%  } %>
        </p>
        <% } %>

        <!-- Запрос и обработка списка всех операций в изделии -->
        <% ArrayList<String> operation = (ArrayList<String>) request.getAttribute("operation"); %>
        <%     if (operation != null) { %> 
        <h3 style="text-indent:20px;line-height:0.2"> 
            Список всех типов операций:       
        </h3>
        <%         for (int i = 0; i < operation.size(); i++) {%>
        <p style="text-indent:20px;line-height:0.2">    
            <%=operation.get(i)%>
            <%  } %>
        </p>
        <% }%>

    </body>
</html>
