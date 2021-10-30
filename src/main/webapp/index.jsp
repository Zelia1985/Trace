<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        <h2>Авторизация сотрудника</h2>
        <form action="getAuthorization" method="POST">

            <!-- Поле для ввода табельного номера -->
            <p> Табельный номер: 
                <input type="text" name="tabNumber" value="" /> 
            </p>

            <!-- Поле для ввода пароль -->
            <p style="text-indent:70px">Пароль:
                <input type="password" name="password" value="" /> 
            </p> 

            <!-- Кнопка для продолжения авторизации -->
            <p style="text-indent:127px">
                <input type="submit" value="Регистрация" name="registration" style="width:150px;height:21px"/>
            </p> 

        </form>
        
        <!-- Запрос и обработка сообщения об ощибке при пустом поле табельного номера или пароля -->
        <% String msgMistakeEmptyField =(String)request.getAttribute("msgMistakeEmptyField");
            if(msgMistakeEmptyField!=null){
            %>
                <h2>
                     <%=msgMistakeEmptyField%>
                </h2>
        <%} %>
        
         <!-- Запрос и обработка сообщения об ошибке при неверном формате ввода табельного номера -->
        <% String msgMistakeIncorrectFormat =(String)request.getAttribute("msgMistakeIncorrectFormat");
            if(msgMistakeIncorrectFormat!=null){
            %>
                <h2>
                    <%=msgMistakeIncorrectFormat%>
                </h2>
        <%} %>
        
         <!-- Запрос и обработка сообщения об ощибке при получении данных, которых нет в БД -->
        <% String msgMistakeNotFoundDataBase =(String)request.getAttribute("msgMistakeNotFoundDataBase");
            if(msgMistakeNotFoundDataBase!=null){
            %>
                <h2>
                    <%=msgMistakeNotFoundDataBase%>
                </h2>
        <%} %>
        
    </body>
</html>
