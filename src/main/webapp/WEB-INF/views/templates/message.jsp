<%@ page contentType="text/html;charset=UTF-8" language="java"       %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"        %>

<div class="row">
  <c:if test="${feedbackMessage != null}">
    <div class="alert alert-success" >
      <c:out value="${feedbackMessage}" />
    </div>
  </c:if>
  <c:if test="${errorMessage != null }">
    <div class="alert alert-danger" >
      <c:out value="${errorMessage}" />
    </div>
  </c:if>
</div>