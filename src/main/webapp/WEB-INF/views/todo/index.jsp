<%@ page contentType="text/html;charset=UTF-8" language="java"       %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"        %>

<html>
  <head>
    <jsp:include page="../templates/head.jsp" />
  </head>
  <body>
    <jsp:include page="../templates/menu.jsp" />

    <div class="container main">
      <jsp:include page="../templates/message.jsp" />
      <div class="row">
        <a href="<c:url value="/todo/create" />" >New Todo</a>
      </div>
      <table class="table">
        <thead>
          <th>Id</th>
          <th>Title</th>
          <th>Description</th>
          <th></th>
          <th></th>
          <th></th>
        </thead>
        <tbody>
          <c:forEach var="todo" items="${todolists.getContent()}" >
            <tr>
              <td>${todo.id}</td>
              <td>${todo.title}</td>
              <td>${todo.description}</td>
              <td><a href="<c:url value="todo/${todo.id}/edit"/>" />Edit</td>
              <td><a href="<c:url value="todo/${todo.id}/delete"/>" />Delete</td>
              <td></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

      <c:url var="firstUrl" value="/todo?p=1" />
      <c:url var="lastUrl"  value="/todo?p=${totalPages}" />
      <c:url var="prevUrl"  value="/todo?p=${currentIndex - 1}" />
      <c:url var="nextUrl"  value="/todo?p=${currentIndex + 1}" />

      <div class="row">
          <ul class = "pagination">
              <c:choose>
                  <c:when test="${currentIndex == 1}">
                      <li class="disabled"><a href="#">&lt;&lt;</a></li>
                      <li class="disabled"><a href="#">&lt;</a></li>
                  </c:when>
                  <c:otherwise>
                      <li><a href="${firstUrl}">&lt;&lt;</a></li>
                      <li><a href="${prevUrl}">&lt;</a></li>
                  </c:otherwise>
              </c:choose>
              <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                  <c:url var="pageUrl" value="/todo?p=${i}" />
                  <c:choose>
                      <c:when test="${i == currentIndex}">
                          <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                      </c:when>
                      <c:otherwise>
                          <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                      </c:otherwise>
                  </c:choose>
              </c:forEach>
              <c:choose>
                  <c:when test="${currentIndex == totalPages}">
                      <li class="disabled"><a href="#">&gt;</a></li>
                      <li class="disabled"><a href="#">&gt;&gt;</a></li>
                  </c:when>
                  <c:otherwise>
                      <li><a href="${nextUrl}">&gt;</a></li>
                      <li><a href="${lastUrl}">&gt;&gt;</a></li>
                  </c:otherwise>
              </c:choose>
          </ul>
      </div>
    </div>
  </body>
</html>