<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 入力フォーム -->
<form:form modelAttribute="articleForm" action="${pageContext.request.contextPath}/article/insertArticle" >
<form:errors path="name" cssStyle="color:red" element="div"/>
投稿者名:<form:input path="name" /><br>
<form:errors path="content" cssStyle="color:red" element="div"></form:errors>
投稿内容:<form:textarea path="content"/><br>
<input type="submit" value="記事投稿">
</form:form>

<!-- 記事一覧 -->
<c:forEach var="article" items="${articleList}">
<hr>
投稿者ID:<c:out value="${article.id }"></c:out>
投稿者名:<c:out value="${article.name}"></c:out><br>
投稿内容:<c:out value="${article.content}"></c:out><br>
<form:form modelAttribute="articleForm" action="${pageContext.request.contextPath}/article/delete"> 
<input type="hidden" name="articleId" value="${article.id}">
<input type="submit" value="記事削除">
</form:form>
<!-- コメント一覧 -->
<c:forEach var="comment" items="${article.commentList}">
コメントID:<c:out value="${comment.id }"></c:out><br>
コメント者名:<c:out value="${comment.name }"></c:out><br>
コメント内容:<c:out value="${comment.content }"></c:out><br>
</c:forEach>
<br>
<!-- コメントフォーム -->
<form:form modelAttribute="commentForm" action="${pageContext.request.contextPath}/article/insertComment">
<form:errors path="name" cssStyle="color:red" element="div" ></form:errors>
名前:<form:input path="name"/><br>
<form:errors path="content" cssStyle="color:red" element="div"></form:errors>
コメント:<form:textarea path="content"/><br>
<input type="hidden" name="articleId" value="${article.id}">
<input type="submit" value="コメント投稿">
</form:form>
</c:forEach>

<!-- actionの飛ばし先 -->

</body>
</html>