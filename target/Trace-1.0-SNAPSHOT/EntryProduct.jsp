<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Добавление и удаление изделия в БД</h2>
        <form action="entryProduct" method="POST">

            <!-- Кнопка для вывода информации по типам изделий и текущим изделиям в БД -->
            <p>
                <input type="submit" value="Получить список типов и изделий в БД" name="getInfo" style="width:250px;height:25px;"/>
            </p> 
            
             <br>  
            
            <!-- Поле для ввода типа изделия  -->
            <p>Введите тип изделия, который будет добавлен в БД: <br /><br />
                <input type="text" name="productType" value="" style="width:250px;height:18px"/>  
            </p> 

            <!-- Кнопка для добавление нового изделия в БД -->
            <p>
                <input type="submit" value="Добавить изделие в БД" name="newProduct" style="width:250px;height:25px;"/>
            </p> 
            
             <br>  
            
            <!-- Поле для ввода заводского номера изделия для удаления из БД  -->
            <p>Введите заводской номер изделия, который будет удален из БД: <br /><br />
                <input type="text" name="productNumber" value="" style="width:250px;height:18px"/>  
            </p> 

            <!-- Кнопка для добавление нового изделия в БД -->
            <p>
                <input type="submit" value="Удалить изделие из БД" name="deleteProduct" style="width:250px;height:25px;"/>
            </p> 

            <br>  

            <!-- Кнопка для перехода на основную страницу страницу -->
            <p>
                <input type="submit" value="Выход" name="exit" style="width:250px;height:25px;"/>
            </p> 
            
             <br>

        </form>
        
        <!-- Запрос и обработка сообщения об ошибке при неверном формате ввода З/Н изделия -->
        <% String msgMistakeTypeProduct =(String)request.getAttribute("msgMistakeTypeProduct");
            if(msgMistakeTypeProduct!=null){
            %>
                <h2>
                    <%=msgMistakeTypeProduct%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при неверном количестве цифр в З/Н -->
        <% String msgMistakeFactoryNumber =(String)request.getAttribute("msgMistakeFactoryNumber");
            if(msgMistakeFactoryNumber!=null){
            %>
                <h2>
                    <%=msgMistakeFactoryNumber%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при неверном формате ввода З/Н -->
        <% String msgMistakeFormat =(String)request.getAttribute("msgMistakeFormat");
            if(msgMistakeFormat!=null){
            %>
                <h2>
                    <%=msgMistakeFormat%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при отсутствии З/Н в БД -->
        <% String msgMistakeNotFoundDataBase =(String)request.getAttribute("msgMistakeNotFoundDataBase");
            if(msgMistakeNotFoundDataBase!=null){
            %>
                <h2>
                    <%=msgMistakeNotFoundDataBase%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при добавлении изделия в БД -->
        <% String msgMistakeAddProduct =(String)request.getAttribute("msgMistakeAddProduct");
            if(msgMistakeAddProduct!=null){
            %>
                <h2>
                    <%=msgMistakeAddProduct%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при удалении изделия в БД -->
        <% String msgMistakeDeleteProduct =(String)request.getAttribute("msgMistakeDeleteProduct");
            if(msgMistakeDeleteProduct!=null){
            %>
                <h2>
                    <%=msgMistakeDeleteProduct%>
                </h2>
        <%} %>

        <!-- Запрос и обработка сообщения о внесении изделия в БД с типом и ЗН -->
        <% String msgAddProduct =(String)request.getAttribute("msgAddProduct");
            if(msgAddProduct!=null){
            %>
                <h2>
                    <%=msgAddProduct%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения о удалении изделия из БД с указанием ЗН -->
        <% String msgDeleteProduct =(String)request.getAttribute("msgDeleteProduct");
            if(msgDeleteProduct!=null){
            %>
                <h2>
                    <%=msgDeleteProduct%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка списка всех изделий в БД -->
        <% ArrayList <String> allTypeProduct =(ArrayList <String>) request.getAttribute("allTypeProduct"); %>
        <%     if (allTypeProduct!=null) { %> 
                   <h3 style="text-indent:20px;line-height:0.2"> 
                    Список всех типов в базе:       
                   </h3>
        <%         for(int i = 0 ; i < allTypeProduct.size();i++) { %>
                       <p style="text-indent:20px;line-height:0.2"> 
                             <%=allTypeProduct.get(i)%>
                             <%  } %>
                       </p>
        <% } %>

        <!-- Запрос и обработка списка всех изделий в БД -->
        <% ArrayList <String> allProducts =(ArrayList <String>) request.getAttribute("allProducts"); %>
        <%     if (allProducts!=null) { %> 
                   <h3 style="text-indent:20px;line-height:0.2"> 
                    Список всех изделий в базе:       
                   </h3>
        <%         for(int i = 0 ; i < allProducts.size();i++) { %>
                       <p style="text-indent:20px;line-height:0.2"> 
                             <%=allProducts.get(i)%>
                             <%  } %>
                       </p>
        <% } %>

    </body>
</html>
