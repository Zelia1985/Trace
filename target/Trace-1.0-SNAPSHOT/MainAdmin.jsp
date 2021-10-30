<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        <h2>Ваша роль в системе - администратор</h2>
        <form action="admin" method="POST">

            <!-- Поле для ввода заводского номера изделия -->
            <p> Введите заводской номер изделия: 
                <input type="text" name="numberProduct" value="" style="width:240px;height:21px"/> 
            </p>

            <!-- Кнопка для получения данных по изделию -->
            <p style="text-indent:237px">
                <input type="submit" value="Получить данные по изделию" name="getProductButton" style="width:250px;height:25px"/>
            </p> 
            
            <br>
            
            <!-- Кнопка для получения всех изделий в БД -->
            <p style="text-indent:5px">
                <input type="submit" value="Получить список всех изделий" name="getAllProductButton" style="width:250px;height:25px"/>
            </p>   
            
            <!-- Кнопка для перехода на страницу, где возможно внесение и удаление изделия в БД -->
            <p style="text-indent:5px">
                <input type="submit" value="Внесение и удаление изделия в БД" name="entryProductButton" style="width:250px;height:25px"/>
            </p> 
            
            <!-- Кнопка для перехода на страницу, где возможно внесение и удаление платы для изделия в БД -->
            <p style="text-indent:5px">
                <input type="submit" value="Внесение и удаление платы в БД" name="entryCircuitBoardButton" style="width:250px;height:25px"/>
            </p> 
            
            <!-- Кнопка для перехода на страницу, где возможно внесение и удаление операции для изделия в БД -->
            <p style="text-indent:5px">
                <input type="submit" value="Внесение и удаление операции в БД" name="entryOperationButton" style="width:250px;height:25px"/>
            </p> 
            
            <!-- Кнопка для получения всех сотрудников в БД -->
            <p style="text-indent:5px">
                <input type="submit" value="Получить список всех сотр. в БД" name="getAllWorkersButton" style="width:250px;height:25px"/>
            </p> 
            
            <!-- Кнопка для перехода на страницу, где возможно внесение сотрудника в БД -->
            <p style="text-indent:5px">
                <input type="submit" value="Внесение сотрудника в БД" name="entryWorkerButton" style="width:250px;height:25px"/>
            </p>
            
            <!-- Кнопка для перехода на страницу справочников -->
            <p style="text-indent:5px">
                <input type="submit" value="Справочники" name="directory" style="width:250px;height:25px"/>
            </p> 

            <!-- Кнопка для перехода на страницу авторизации -->
            <p style="text-indent:5px ">
                <input type="submit" value="Выход" name="exit" style="width:250px;height:25px"/>
            </p> 

            <br>
            
        </form>
      
        <!-- Запрос и обработка сообщения об ошибке при неверном формате ввода З/Н изделия -->
        <% String msgMistakeFormat =(String)request.getAttribute("msgMistakeFormat");
            if(msgMistakeFormat!=null){
            %>
                <h2>
                     <%=msgMistakeFormat%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при неверном количестве символов в З/Н -->
        <% String msgMistakeFactoryNumber =(String)request.getAttribute("msgMistakeFactoryNumber");
            if(msgMistakeFactoryNumber!=null){
            %>
                <h2>
                    <%=msgMistakeFactoryNumber%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при З/Н, который отсутсвует в БД -->
        <% String msgMistakeNotFoundDataBase =(String)request.getAttribute("msgMistakeNotFoundDataBase");
            if(msgMistakeNotFoundDataBase!=null){
            %>
                <h2>
                    <%=msgMistakeNotFoundDataBase%>
                </h2>
        <%} %>
        
       <!-- Запрос и обработка списка всех плат в изделии по З/Н -->
        <% ArrayList <String> circuitBoard =(ArrayList <String>) request.getAttribute("circuitBoard"); %>
        <%     if (circuitBoard!=null) { %> 
                   <h3 style="text-indent:20px;line-height:0.2"> 
                    Список всех плат для изделия:       
                   </h3>
        <%         for(int i = 0 ; i < circuitBoard.size();i++) { %>
                       <p style="text-indent:20px;line-height:0.2"> 
                             <%=circuitBoard.get(i)%>
                             <%  } %>
                       </p>
        <% } %>
        
        <!-- Запрос и обработка списка всех операций в изделии по З/Н -->
        <% ArrayList <String> operation =(ArrayList <String>) request.getAttribute("operation"); %>
        <%     if (operation!=null) { %> 
                   <h3 style="text-indent:20px;line-height:0.2"> 
                    Список всех операций для изделия:       
                   </h3>
        <%         for(int i = 0 ; i < operation.size();i++) { %> 
                       <p style="text-indent:20px;line-height:0.2">   
                             <%=operation.get(i)%>
                             <%  } %>
                       </p>
        <% } %>
        
        <!-- Запрос и обработка списка всех сотрудников в БД с выводом табельного номера и роли в системе -->
        <% ArrayList <String> allWorkers =(ArrayList <String>) request.getAttribute("allWorkers"); %>
        <%     if (allWorkers!=null) { %> 
                   <h3 style="text-indent:20px;line-height:0.2"> 
                    Список всех сотрудников в базе:       
                   </h3>
        <%         for(int i = 0 ; i < allWorkers.size();i++) { %>
                       <p style="text-indent:20px;line-height:0.2"> 
                             <%=allWorkers.get(i)%>
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
