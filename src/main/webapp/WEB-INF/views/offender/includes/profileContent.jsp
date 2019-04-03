<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<fmt:bundle basename="omis.offender.msgs.profile">
<div class="offenderProfile">
	
	<%-- Basic Information --%>
	<div class="profileItems">
		<h2>
			<a class="actionMenuItem profileActionMenuItem" id="profileBasicInformationActionMenuLink" href="${pageContext.request.contextPath}/offender/profileBasicInformationActionMenu.html?offender=${offender.id}"></a>
			<fmt:message key="basicInformationLabel"/>
		</h2>
		<div class="profileItemsWrapper">	
			<div class="profileItemsInnerWrapper">
				<c:forEach var="profileItem" items="${basicInformationProfileItemRegistry.items}">
					<c:if test="${profileItem.enabled}">
						<c:set var="offenderSummary" value="${offender}" scope="request" />
						<c:set var="authorized" value="${false}"/>
						<c:forEach var="requiredAuthorization" items="${profileItem.requiredAuthorizations}">
							<sec:authorize access="hasRole('${requiredAuthorization}')">
								<c:set var="authorized" value="${true}"/>
							</sec:authorize>
						</c:forEach>
						<c:if test="${authorized}">
							<div class="profileItemContainer">
								<jsp:include page="${profileItem.includePage}"/>
							</div>
						</c:if>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<%-- Placement --%>
	<div class="profileItems">
		<h2>
			<a class="actionMenuItem profileActionMenuItem" id="profilePlacementActionMenuLink" href="${pageContext.request.contextPath}/offender/profilePlacementActionMenu.html?offender=${offender.id}"></a>
			<fmt:message key="placementLabel"/>
		</h2>
		<div class="profileItemsWrapper">
			<div class="profileItemsInnerWrapper">
				<c:forEach var="profileItem" items="${placementProfileItemRegistry.items}">
					<c:if test="${profileItem.enabled}">
						<c:set var="offenderSummary" value="${offender}" scope="request" />
						<c:set var="authorized" value="${false}"/>
						<c:forEach var="requiredAuthorization" items="${profileItem.requiredAuthorizations}">
							<sec:authorize access="hasRole('${requiredAuthorization}')">
								<c:set var="authorized" value="${true}"/>
							</sec:authorize>
						</c:forEach>
						<c:if test="${authorized}">
							<div class="profileItemContainer">
								<jsp:include page="${profileItem.includePage}"/>
							</div>
						</c:if>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<%-- Legal --%>
	<div class="profileItems">
		<h2>
			<a class="actionMenuItem profileActionMenuItem" id="profileLegalActionMenuLink" href="${pageContext.request.contextPath}/offender/profileLegalActionMenu.html?offender=${offender.id}"></a>
			<fmt:message key="legalLabel"/>
		</h2>
		<div class="profileItemsWrapper">
			<div class="profileItemsInnerWrapper">
				<c:forEach var="profileItem" items="${legalProfileItemRegistry.items}">
					<c:if test="${profileItem.enabled}">
						<c:set var="offenderSummary" value="${offender}" scope="request" />
						<c:set var="authorized" value="${false}"/>
						<c:forEach var="requiredAuthorization" items="${profileItem.requiredAuthorizations}">
							<sec:authorize access="hasRole('${requiredAuthorization}')">
								<c:set var="authorized" value="${true}"/>
							</sec:authorize>
						</c:forEach>
						<c:if test="${authorized}">
							<div class="profileItemContainer">
								<jsp:include page="${profileItem.includePage}"/>
							</div>
						</c:if>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	
	
	
	<%-- Case Management --%>
	<div class="profileItems">
		<h2>
			<a class="actionMenuItem profileActionMenuItem" id="profileCaseManagementActionMenuLink" href="${pageContext.request.contextPath}/offender/profileCaseManagementActionMenu.html?offender=${offender.id}"></a>
			<fmt:message key="caseManagementLabel"/>
		</h2>
		<div class="profileItemsWrapper">
			<div class="profileItemsInnerWrapper">
				<c:forEach var="profileItem" items="${caseManagementProfileItemRegistry.items}">
					<c:if test="${profileItem.enabled}">
						<c:set var="offenderSummary" value="${offender}" scope="request" />
						<c:set var="authorized" value="${false}"/>
						<c:forEach var="requiredAuthorization" items="${profileItem.requiredAuthorizations}">
							<sec:authorize access="hasRole('${requiredAuthorization}')">
								<c:set var="authorized" value="${true}"/>
							</sec:authorize>
						</c:forEach>
						<c:if test="${authorized}">
							<div class="profileItemContainer">
								<jsp:include page="${profileItem.includePage}"/>
							</div>
						</c:if>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	
	
	
	<%-- Safety --%>
	<div class="profileItems">
		<h2>
			<a class="actionMenuItem profileActionMenuItem" id="profileSafetyActionMenuLink" href="${pageContext.request.contextPath}/offender/profileSafetyActionMenu.html?offender=${offender.id}"></a>
			<fmt:message key="safetyLabel"/>
		</h2>
		<div class="profileItemsWrapper">
			<div class="profileItemsInnerWrapper">
				<c:forEach var="profileItem" items="${safetyProfileItemRegistry.items}">
					<c:if test="${profileItem.enabled}">
						<c:set var="offenderSummary" value="${offender}" scope="request" />
						<c:set var="authorized" value="${false}"/>
						<c:forEach var="requiredAuthorization" items="${profileItem.requiredAuthorizations}">
							<sec:authorize access="hasRole('${requiredAuthorization}')">
								<c:set var="authorized" value="${true}"/>
							</sec:authorize>
						</c:forEach>
						<c:if test="${authorized}">
							<div class="profileItemContainer">
								<jsp:include page="${profileItem.includePage}"/>
							</div>
						</c:if>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	
	
	
	<%-- Compliance --%>
	<div class="profileItems">
		<h2>
			<a class="actionMenuItem profileActionMenuItem" id="profileComplianceActionMenuLink" href="${pageContext.request.contextPath}/offender/profileComplianceActionMenu.html?offender=${offender.id}"></a>
			<fmt:message key="complianceLabel"/>
		</h2>
		<div class="profileItemsWrapper">
			<div class="profileItemsInnerWrapper">
				<c:forEach var="profileItem" items="${complianceProfileItemRegistry.items}">
					<c:if test="${profileItem.enabled}">
						<c:set var="offenderSummary" value="${offender}" scope="request" />
						<c:set var="authorized" value="${false}"/>
						<c:forEach var="requiredAuthorization" items="${profileItem.requiredAuthorizations}">
							<sec:authorize access="hasRole('${requiredAuthorization}')">
								<c:set var="authorized" value="${true}"/>
							</sec:authorize>
						</c:forEach>
						<c:if test="${authorized}">
							<div class="profileItemContainer">
								<jsp:include page="${profileItem.includePage}"/>
							</div>
						</c:if>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	

	<%-- Relationships --%>
	<div class="profileItems">
		<h2>
			<a class="actionMenuItem profileActionMenuItem" id="profileRelationshipsActionMenuLink" href="${pageContext.request.contextPath}/offender/profileRelationshipsActionMenu.html?offender=${offender.id}"></a>
			<fmt:message key="relationshipsLabel"/>
		</h2>
		<div class="profileItemsWrapper">
			<div class="profileItemsInnerWrapper">
				<c:forEach var="profileItem" items="${relationshipsProfileItemRegistry.items}">
					<c:if test="${profileItem.enabled}">
						<c:set var="offenderSummary" value="${offender}" scope="request" />
						<c:set var="authorized" value="${false}"/>
						<c:forEach var="requiredAuthorization" items="${profileItem.requiredAuthorizations}">
							<sec:authorize access="hasRole('${requiredAuthorization}')">
								<c:set var="authorized" value="${true}"/>
							</sec:authorize>
						</c:forEach>
						<c:if test="${authorized}">
							<div class="profileItemContainer">
								<jsp:include page="${profileItem.includePage}"/>
							</div>
						</c:if>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
</fmt:bundle>