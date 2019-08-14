<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 分页数据统一名称： pageInfo !!!!!!! -->

<ul class="pagination">
    <li><a href="${pageContext.request.contextPath}${pageContext.request.pathInfo}?page=1"
           aria-label="Previous">首页</a></li>
    <li><a href="${pageContext.request.contextPath}${pageContext.request.pathInfo}?page=${pageInfo.prePage}">上一页</a>
    </li>
    <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum" varStatus="status">
        <c:choose>
            <c:when test="${status.index == pageInfo.pageNum}">
                <li>
                    <a style="background-color: #eeeeee"
                       href="${pageContext.request.contextPath}${pageContext.request.pathInfo}?page=${pageNum}">${pageNum}</a>
                </li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="${pageContext.request.contextPath}${pageContext.request.pathInfo}?page=${pageNum}">${pageNum}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <li>
        <a href="${pageContext.request.contextPath}${pageContext.request.pathInfo}?page=${pageInfo.pageNum+1}">下一页</a>
    </li>
    <li><a href="${pageContext.request.contextPath}${pageContext.request.pathInfo}?page=${pageInfo.pages}"
           aria-label="Next">尾页</a></li>
</ul>
