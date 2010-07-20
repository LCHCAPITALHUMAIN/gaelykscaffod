// example routes, please remove them or update them as appropriate
get "/admin/@entityName/@actionName", forward: "/WEB-INF/groovy/admin.groovy?entityName=@entityName&actionName=@actionName"
get "/adminajax/@entityName/@actionName", forward: "/WEB-INF/groovy/admin.groovy?entityName=@entityName&actionName=@actionName"
post "/admin/@entityName/@actionName", forward: "/WEB-INF/groovy/admin.groovy?entityName=@entityName&actionName=@actionName"
get "/admin/@entityName" , forward: "/WEB-INF/groovy/admin.groovy?entityName=@entityName&actionName=index"
//get "/something", redirect: "/blog/2008/10/20/something"
//get "/book/isbn/@isbn", forward: "/WEB-INF/groovy/book.groovy?isbn=@isbn", validate: { isbn ==~ /\d{9}(\d|X)/ }
//get "/admin" , redirect: "/WEB-INF/groovy/admin.groovy"
