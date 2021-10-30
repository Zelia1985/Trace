<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Добавление и удаление плат в изделиях</h2>
        <form action="entryCircuitBoard" method="POST">

            <!-- Поле для ввода заводского номера изделия   -->
            <p>Введите заводской номер изделия, в которое необходимо добавить или удалить плату: <br /><br />
                <input type="text" name="productNumber" value="" style="width:250px;height:18px"/>  
            </p> 
  
            <!-- Кнопка для вывода информации по типам плат и текущим платам в изделии  -->
            <p>
                <input type="submit" value="Получить типы плат и платы в изделии" name="getInfoCircuitBoard" style="width:250px;height:25px;"/>
            </p> 
            
            <!-- Поле для ввода заводского номера платы для удаления   -->
            <p>Введите заводской номер платы, который необходимо удалить из изделия: <br /><br />
                <input type="text" name="circuitBoardNumber" value="" style="width:250px;height:18px"/>  
            </p>           
            
            <!-- Кнопка для удаления платы из изделия -->
            <p>
                <input type="submit" value="Удалить плату из изделия" name="deleteCircuitBoard" style="width:250px;height:25px;"/>
            </p> 
            
            <!-- Поле для ввода типа платы для добавления в изделие  -->
            <p>Введите тип платы, который необходимо добавить в изделие: <br /><br />
                <input type="text" name="typeCircuitBoard" value="" style="width:250px;height:18px"/>  
            </p>           
            
            <!-- Кнопка для добавление платы в изделие -->
            <p>
                <input type="submit" value="Добавить плату из изделия" name="addCircuitBoard" style="width:250px;height:25px;"/>
            </p>

            <br>  

            <!-- Кнопка для перехода на основную страницу страницу -->
            <p>
                <input type="submit" value="Выход" name="exit" style="width:250px;height:25px;"/>
            </p> 
            
            <br>
             
        </form>
        
        <!-- Запрос и обработка сообщения об ошибке при неверном количестве символов в З/Н -->
        <% String msgMistakeFactoryNumber =(String)request.getAttribute("msgMistakeFactoryNumber");
            if(msgMistakeFactoryNumber!=null){
            %>
                <h2>
                    <%=msgMistakeFactoryNumber%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при неверном формате символов -->
        <% String msgMistakeFormat =(String)request.getAttribute("msgMistakeFormat");
            if(msgMistakeFormat!=null){
            %>
                <h2>
                    <%=msgMistakeFormat%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при отсутствии параметра в БД -->
        <% String msgMistakeNotFoundDataBase =(String)request.getAttribute("msgMistakeNotFoundDataBase");
            if(msgMistakeNotFoundDataBase!=null){
            %>
                <h2>
                    <%=msgMistakeNotFoundDataBase%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при несоответствии платы и З/Н изделия -->
        <% String msgMistakeCircuitBoardToFactoryNumber =(String)request.getAttribute("msgMistakeCircuitBoardToFactoryNumber");
            if(msgMistakeCircuitBoardToFactoryNumber!=null){
            %>
                <h2>
                    <%=msgMistakeCircuitBoardToFactoryNumber%>
                </h2>
        <%} %>

        <!-- Запрос и обработка сообщения об успешном удалении платы из изделия -->
        <% String msgDeleteCircuitBoard =(String)request.getAttribute("msgDeleteCircuitBoard");
            if(msgDeleteCircuitBoard!=null){
            %>
                <h2>
                    <%=msgDeleteCircuitBoard%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об успешном добавлении платы в изделие -->
        <% String msgAddCircuitBoard =(String)request.getAttribute("msgAddCircuitBoard");
            if(msgAddCircuitBoard!=null){
            %>
                <h2>
                    <%=msgAddCircuitBoard%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка списка всех плат в изделии -->
        <% ArrayList <String> circuitBoardOnProduct =(ArrayList <String>) request.getAttribute("circuitBoardOnProduct"); %>
        <%     if (circuitBoardOnProduct!=null) { %> 
                   <h3 style="text-indent:20px;line-height:0.2"> 
                    Список всех плат в изделии:       
                   </h3>
        <%         for(int i = 0 ; i < circuitBoardOnProduct.size();i++) { %>
                       <p style="text-indent:20px;line-height:0.2"> 
                             <%=circuitBoardOnProduct.get(i)%>
                             <%  } %>
                       </p>
        <% } %>
        
        <!-- Запрос и обработка списка всех типов плат -->
        <% ArrayList <String> circuitBoardType =(ArrayList <String>) request.getAttribute("circuitBoardType"); %>
        <%     if (circuitBoardType!=null) { %> 
                   <h3 style="text-indent:20px;line-height:0.2"> 
                    Список всех типов плат:       
                   </h3>
        <%         for(int i = 0 ; i < circuitBoardType.size();i++) { %>
                       <p style="text-indent:20px;line-height:0.2"> 
                             <%=circuitBoardType.get(i)%>
                             <%  } %>
                       </p>
        <% } %>

    </body>
</html>
