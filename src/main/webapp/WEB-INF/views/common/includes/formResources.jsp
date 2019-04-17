<%--Include form resources if not loaded.
 - Author: Ryan Johns
 - Version: 0.1.0 (Jun 29, 2015)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<jsp:include page="/WEB-INF/views/common/includes/formCSS.jsp"/>
	
	<%--REMOVE ME: When support for IE9 can be dropped.--%>
	<!--[if lte IE 9 ]>
	<style>
		form.editForm select, form select {
			background-image:none;
			padding-right:0px;
		}
	</style>
	<![endif]-->
	<%--REMOVE ME: When support for IE9 can be dropped.--%>
	
	<jsp:include page="/WEB-INF/views/common/includes/auditCSS.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<c:if test="${empty formResources}">
		<c:set var="formResources" value="true" scope="request"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/form.js?VERSION=3"></script>
	</c:if>