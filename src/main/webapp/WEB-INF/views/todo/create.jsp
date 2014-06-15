<%@ page contentType="text/html;charset=UTF-8" language="java"       %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"      %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"        %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
    <jsp:include page="../templates/head.jsp" />
  </head>
  <body>
    <jsp:include page="../templates/menu.jsp" />

    <div class="container main">
      <jsp:include page="../templates/message.jsp" />
      <c:url var="action" value="/todo/create" />
      <form:form action="${action}" commandName="todo" method="POST">
        <div class="row">
          <div class="col-md-2" >
            <form:label path="title"><spring:message code="todo.label.title"/>:</form:label>
          </div>
          <div class="col-md-5" >
            <form:input path="title" size="20"/>
          </div>
          <div class="col-md-4" >
            <form:errors path="title" cssClass="error" element="div"/>
          </div>
        </div>
        <div class="row">
          <div class="col-md-2" >
            <form:label path="description"><spring:message code="todo.label.description"/>:</form:label>
          </div>
          <div class="col-md-5" >
            <form:input path="description" size="60"/>
          </div>
          <div class="col-md-4" >
            <form:errors path="description" cssClass="error" element="div"/>
          </div>
        </div>
        <div class="row">
          <div class="col-md-8" >
            <input type="submit" value="<spring:message code="todo.create.page.submit.label"/>"/>
          </div>
        </div>
      </form:form>
    </div>
  </body>
</html>