<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>PARIKSHA</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<style>
.container{
padding-top:30px;
}
</style>
<body>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

 <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost/PARIKSHA"
         user = "root"  password = "svcdhoney1"/>

   

      <sql:query dataSource = "${snapshot}" var = "result">
      SELECT Q_no,Question,option1,option2,option3,option4 FROM Questions where Test_id = <%=(String) session.getAttribute("subject2")%> ;
      
        
      </sql:query>
     
    
<div class="container">
    <div class="well">
     <form action="answerservlet" method="post">
     <div class="form-group">
       
                 <c:forEach var = "row" items = "${result.rows}">
       
      <div class="panel panel-default">
        <div class="panel-footer">
          <div class="question"><c:out value = "${row.Q_no}"/>.<c:out value = "${row.Question}"/></div>
        </div>
        <div class="panel-body">
        
        
            <div class="radio">
              <label>
                <input type="radio"  name="${row.Q_no}" value = "option1"><c:out value = "${row.option1}"/></label>
            </div>
            <div class="radio">
              <label>
                <input type="radio"  name="${row.Q_no}" value = "option2"><c:out value = "${row.option2}"/></label>
            </div>
            <div class="radio">
              <label>
               <input type="radio"  name="${row.Q_no}" value = "option3"><c:out value = "${row.option3}"/></label>
            </div>
            <div class="radio">
              <label>
                <input type="radio"  name="${row.Q_no}" value = "option4"><c:out value = "${row.option4}"/></label>
            </div>
            
   
            
        </div>
      </div>
     
   
     </c:forEach>
       <div align = "center">
       

		<input class="button button1" name="next" type="submit" value="END TEST">
		</div>
		</div>
	</form>
	</div>
      </div>
      
</body>
</html>