<?xml version="1.0" encoding="UTF-8"?>

<%--
 - OMIS - Offender Management Information System
 - Copyright (C) 2011 - 2017 State of Montana
 -
 - This program is free software: you can redistribute it and/or modify
 - it under the terms of the GNU General Public License as published by
 - the Free Software Foundation, either version 3 of the License, or
 - (at your option) any later version.
 -
 - This program is distributed in the hope that it will be useful,
 - but WITHOUT ANY WARRANTY; without even the implied warranty of
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 - GNU General Public License for more details.
 -
 - You should have received a copy of the GNU General Public License
 - along with this program.  If not, see <http://www.gnu.org/licenses/>.
 --%>

<%--
 - Screen to edit supervisory organization terms.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.supervision.msgs.supervisoryOrganizationTerm">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty supervisoryOrganizationTerm}">
				<fmt:message key="editSupervisoryOrganizationTermTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createSupervisoryOrganizationTermTitle"/>
			</c:otherwise>
		</c:choose>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery.ptTimeSelect.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/supervision/style/links.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery.ptTimeSelect.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/supervision/scripts/supervisoryOrganizationTerm.js"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a href="${pageContext.request.contextPath}/supervision/supervisoryOrganizationTerm/supervisoryOrganizationTermActionMenu.html?offender=${offenderSummary.id}" class="actionMenuItem" id="actionMenuLink"></a>
		<c:choose>
			<c:when test="${not empty supervisoryOrganizationTerm}">
				<fmt:message key="editSupervisoryOrganizationTermTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createSupervisoryOrganizationTermTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>