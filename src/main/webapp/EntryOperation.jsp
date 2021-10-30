<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Добавление и удаление операций в изделиях</h2>
        <form action="entryOperation" method="POST">

            <!-- Поле для ввода заводского номера изделия   -->
            <p>Введите заводской номер изделия, в которое необходимо добавить или удалить операцию: <br /><br />
                <input type="text" name="productNumber" value="" style="width:250px;height:18px"/>  
            </p> 
  
            <!-- Кнопка для вывода информации по типам операций и текущим операциям по изделию  -->
            <p>
                <input type="submit" value="Типы операций и операции по изделию" name="getInfoOperation" style="width:250px;height:25px;"/>
            </p> 
            
            <!-- Поле для ввода операции для удаления   -->
            <p>Введите операцию , которую необходимо удалить из изделия: <br /><br />
                <input type="text" name="operationProduct" value="" style="width:250px;height:18px"/>  
            </p>           
            
            <!-- Кнопка для удаления операции по изделию -->
            <p>
                <input type="submit" value="Удалить операцию по изделию" name="deleteOperation" style="width:250px;height:25px;"/>
            </p> 
            
            <!-- Поле для ввода операции для добавления в изделие  -->
            <p>Введите операцию, которую необходимо добавить в изделие: <br /><br />
                <input type="text" name="typeOperation" value="" style="width:250px;height:18px"/>  
            </p>  
            
            <!-- Поле для ввода даты операции для добавления в изделие  -->
            <p>Введите дату операции (хх.хх.хххх), которую необходимо добавить к операции: <br /><br />
                <input type="text" name="dateOperation" value="" style="width:250px;height:18px"/>  
            </p>    
            
            <!-- Кнопка для добавление операции по изделлию -->
            <p>
                <input type="submit" value="Добавить операцию по изделию" name="addOperation" style="width:250px;height:25px;"/>
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
        
        <!-- Запрос и обработка сообщения об ошибке при отсутствии ЗН в БД -->
        <% String msgMistakeNotFoundDataBase =(String)request.getAttribute("msgMistakeNotFoundDataBase");
            if(msgMistakeNotFoundDataBase!=null){
            %>
                <h2>
                    <%=msgMistakeNotFoundDataBase%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при отсутствии ЗН в БД -->
        <% String msgMistakeDeleteOperation =(String)request.getAttribute("msgMistakeDeleteOperation");
            if(msgMistakeDeleteOperation!=null){
            %>
                <h2>
                    <%=msgMistakeDeleteOperation%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при отсутствии ЗН в БД -->
        <% String msgMistakeOperationToFactoryNumber =(String)request.getAttribute("msgMistakeOperationToFactoryNumber");
            if(msgMistakeOperationToFactoryNumber!=null){
            %>
                <h2>
                    <%=msgMistakeOperationToFactoryNumber%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при добавлении операции в БД -->
        <% String msgMistakeAddOperation =(String)request.getAttribute("msgMistakeAddOperation");
            if(msgMistakeAddOperation!=null){
            %>
                <h2>
                    <%=msgMistakeAddOperation%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ошибке при пустом значении поля дата -->
        <% String msgMistakeEmptyDate =(String)request.getAttribute("msgMistakeEmptyDate");
            if(msgMistakeEmptyDate!=null){
            %>
                <h2>
                    <%=msgMistakeEmptyDate%>
                </h2>
        <%} %>

        <!-- Запрос и обработка сообщения об успешном удалении операции в изделии -->
        <% String msgDeleteOperation =(String)request.getAttribute("msgDeleteOperation");
            if(msgDeleteOperation!=null){
            %>
                <h2>
                    <%=msgDeleteOperation%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об успешном добавление операции в изделии -->
        <% String msgAddOperation =(String)request.getAttribute("msgAddOperation");
            if(msgAddOperation!=null){
            %>
                <h2>
                    <%=msgAddOperation%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка списка всех операций в изделии -->
        <% ArrayList <String> operationOnProduct =(ArrayList <String>) request.getAttribute("operationOnProduct"); %>
        <%     if (operationOnProduct!=null) { %> 
                   <h3 style="text-indent:20px;line-height:0.2"> 
                    Список всех операций по изделию:       
                   </h3>
        <%         for(int i = 0 ; i < operationOnProduct.size();i++) { %> 
                       <p style="text-indent:20px;line-height:0.2">   
                             <%=operationOnProduct.get(i)%>
                             <%  } %>
                       </p>
        <% } %>
        
        <!-- Запрос и обработка списка всех типов операций -->
        <% ArrayList <String> operationType =(ArrayList <String>) request.getAttribute("operationType"); %>
        <%     if (operationType!=null) { %> 
                   <h3 style="text-indent:20px;line-height:0.2"> 
                    Список всех типов операций:       
                   </h3>
        <%         for(int i = 0 ; i < operationType.size();i++) { %>
                       <p style="text-indent:20px;line-height:0.2">  
                             <%=operationType.get(i)%>
                             <%  } %>
                       </p>
        <% } %>
    
    </body>
</html>
