<!-- 
 - Author: Ryan Johns
 - Version: 0.1.0 (Apr 10, 2019)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.booking.msgs.booking">
<ul>
	<li>
		<a class="createLink" href="${pageContext.request.contextPath}/booking/create.html?offender=${offender.id}">
			<fmt:message key="createBookingLabel"/>
		</a>
	</li>
</ul>
</fmt:bundle>