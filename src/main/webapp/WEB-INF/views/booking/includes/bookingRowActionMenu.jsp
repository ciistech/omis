<!-- 
 - Author: Ryan Johns
 - Version: 0.1.0 (Apr 11, 2019)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.booking.msgs.booking">
<ul>
	<li>
		<a href="${pageContext.request.contextPath}/booking/edit.html?booking=${booking.id}">
			<fmt:message key="editBookingLabel"/>
		</a>
	</li>
	<li>
		<a href="${pageContext.request.contextPath}/booking/remove.html?booking=${booking.id}">
			<fmt:message key="removeBookingLabel"/>
		</a>
</ul>
</fmt:bundle>