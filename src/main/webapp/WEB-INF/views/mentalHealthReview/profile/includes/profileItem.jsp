<%-- Author: Ryan Johns
 - Version: 0.1.0 (Apr 13, 2018)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.mentalhealthreview.msgs.mentalHealthReview">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/mentalHealthReview/list.html?offender=${offenderSummary.id}">
			<span>
				<fmt:message key="mentalHealthReviewsTitle"/>
			</span>
		</a>
	</div>
</fmt:bundle>