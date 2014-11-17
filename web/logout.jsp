<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
HttpSession sesion = request.getSession(true);
sesion.invalidate();
response.sendRedirect("home");
%>