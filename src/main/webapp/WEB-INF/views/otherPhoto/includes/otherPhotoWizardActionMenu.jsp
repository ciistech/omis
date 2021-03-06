<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (Aug 13, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.physicalfeature.msgs.otherphoto">
	<ul>
		<sec:authorize access="hasRole('PHYSICAL_FEATURE_MODULE') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/physicalFeature/list.html?offender=${offender.id}">
					<span class="visibleLinkLabel">
						<fmt:message key="listOffenderPhysicalFeatureLink"/>
					</span>
				</a>
			</li>
		</sec:authorize>
		<li></li>
		<li></li>
	</ul>
</fmt:bundle>