<% import java.text.SimpleDateFormat %>
<%
 dateVal = ""
 if (request.entity != null) {
 SimpleDateFormat shortFormat = new SimpleDateFormat("dd/MM/yyyy")
 dateVal= shortFormat.format(request.entity[request.widgetData]) 
 
 }%>
<input type="text" name="${request.widgetData}" id="input_${request.widgetData}"  class="datepicker" value="${dateVal}"  />