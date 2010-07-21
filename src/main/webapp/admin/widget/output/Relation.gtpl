<% import com.googlecode.objectify.* %>
<%if (request.entity != null) {
	def ofy = ObjectifyService.begin()
	def outObj = ofy.get(request.entity[request.widgetData]);
%>
	${outObj}
	
	<%}%>