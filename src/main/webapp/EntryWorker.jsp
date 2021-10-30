<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <body>
        <h2>Внесение сотрудника в БД</h2>
        <form action="entryWorkerDB" method="POST">

            <!-- Поле для ввода роли сотрудника, который будет добавлен в БД -->
            <p>Введите роль для сотрудника, который будет добавлен в БД (admin/notadmin):
                <input type="text" name="newRole" value="" style="width:240px;height:21px" />  
            </p> 
            
            <!-- Поле для ввода пароля для нового сотрудника -->
            <p style="text-indent:104px"> Введите пароль для сотрудника, который будет добавлен в БД: 
                <input type="text" name="newPassword" value="" style="width:240px;height:21px" /> 
            </p>

            <!-- Кнопка для добавление сотрудника -->
            <p style="text-indent:533px">
                <input type="submit" value="Добавить нового сотрудника" name="newRegistration" style="width:250px;height:25px;"/>
            </p> 

            <!-- Кнопка для перехода на основную страницу страницу -->
            <p style="text-indent:5px ">
                <input type="submit" value="Выход" name="exit" style="width:250px;height:25px;"/>
            </p> 
            
             <br>

        </form>
        
    
        
        <!-- Запрос и обработка сообщения об ощибке при пустом поле пароля -->
        <% String msgMistakeEmptyField =(String)request.getAttribute("msgMistakeEmptyField");
            if(msgMistakeEmptyField!=null){
            %>
                <h2 style="width:200px;height:25px; float:left; margin:15px 535px">
                    <%=msgMistakeEmptyField%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ощибке при пустом поле пароля -->
        <% String msgMistakeRoleWorker =(String)request.getAttribute("msgMistakeRoleWorker");
            if(msgMistakeRoleWorker!=null){
            %>
                <h2 style="width:200px;height:25px; float:left; margin:15px 535px">
                    <%=msgMistakeRoleWorker%>
                </h2>
        <%} %>
        
        <!-- Запрос и обработка сообщения об ощибке при пустом поле пароля -->
        <% String  msgMistakeAddWorker =(String)request.getAttribute(" msgMistakeAddWorker");
            if( msgMistakeAddWorker!=null){
            %>
                <h2 style="width:200px;height:25px; float:left; margin:15px 535px">
                    <%= msgMistakeAddWorker%>
                </h2>
        <%} %>

        <!-- Запрос и обработка сообщения об внесении сотрудника в БД -->
        <% String addWorkerMsg =(String)request.getAttribute("addWorkerMsg");
            if(addWorkerMsg!=null){
            %>
                <h2 style="width:200px;height:25px; float:left; margin:15px 535px">
                    В БД добавлен новый сотрудник с параметрами: <%=addWorkerMsg%>
                </h2>
        <%} %>

    
        
</html>
