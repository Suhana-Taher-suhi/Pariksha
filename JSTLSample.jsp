<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<html>
   <head>
      <title>JSTL sql:update Tag</title>
   </head>

   <body>
    
   
      <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost/onlineexamination"
         user = "root"  password = "suchi"/>
         
      <sql:query dataSource = "${snapshot}" var = "result">
         SELECT * from QuestionInfo where sid = '1' and tid = 1;
      </sql:query>
 
      <table border = "1" width = "100%">
         <tr>
         	<th> Sub Id</th>
         	<th> Test Id</th>
            <th>Qstn ID</th>
            <th>Question name</th>
            <th>Option1</th>
            <th>Option2</th>
            <th>Option3</th>
            <th>Option4</th>
            <th>Solution</th>
         </tr>
         
         <c:forEach var = "row" items = "${result.rows}">
            <tr>
               <td> <c:out value = "${row.sid}"/></td>
               <td> <c:out value = "${row.tid}"/></td>
               <td> <c:out value = "${row.qid}"/></td>
               <td> <c:out value = "${row.question}"/></td>
               <td> <c:out value = "${row.option1}"/></td>
               <td> <c:out value = "${row.option2}"/></td>
               <td> <c:out value = "${row.option3}"/></td>
               <td> <c:out value = "${row.option4}"/></td>
               <td> <c:out value = "${row.solution}"/></td>
            </tr>
         </c:forEach>
      </table>
	  <form action = "InsertQuestion.jsp">
           	<input class = "button" align = "right"  type = "submit" value = "Insert">
      </form>
   </body>
</html>
</body>
</html>