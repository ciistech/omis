<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
	<ul>
		<sec:authorize access="hasRole('VIOLATION_EVENT_CREATE') or hasRole('VIOLATION_EVENT_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createViolationEventNoteItemLink" class="createLink" href="${pageContext.request.contextPath}/violationEvent/createViolationEventNoteItem.html?violationEventNoteItemIndex=${violationEventNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addViolationEventNoteLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>