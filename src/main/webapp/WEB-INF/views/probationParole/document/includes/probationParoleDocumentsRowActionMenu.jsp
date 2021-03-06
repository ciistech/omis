<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.msgs.common">
	<ul>
		<sec:authorize access="hasRole('PROBATION_PAROLE_DOCUMENT_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/probationParole/document/edit.html?probationParoleDocumentAssociation=${probationParoleDocumentAssociation.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditLink"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('ASSESSMENT_DOCUMENT_REMOVE') or hasRole('ADMIN')">
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/probationParole/document/remove.html?probationParoleDocumentAssociation=${probationParoleDocumentAssociation.id}"><span class="visibleLinkLabel"><fmt:message key="removeLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>