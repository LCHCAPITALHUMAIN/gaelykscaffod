<% import java.text.SimpleDateFormat %>
<%
 dateVal = ""
 if (request.entity != null) {
 SimpleDateFormat shortFormat = new SimpleDateFormat("dd/MM/yyyy")
 dateVal= shortFormat.format(request.entity[request.widgetData]) 
 
 }%>${dateVal}