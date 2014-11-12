<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
HttpSession sesion = request.getSession(true);
sesion.removeAttribute("usuario");
response.sendRedirect("home");
%>