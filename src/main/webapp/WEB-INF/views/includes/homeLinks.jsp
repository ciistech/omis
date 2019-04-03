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
 - Author: Stephen Abson
 - Author: Ryan Johns
 - Author: Sheronda Vaughn
 - Author: Josh Divine
 - Author: Annie Wahl
 - Version: 0.1.3 (Jan 16, 2019)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.offender.msgs.offender" var="offenderBundle"/>
<fmt:bundle basename="omis.msgs.homeLinks">
	<div id="homeLinksHeaderContainer">
		<h1 class="homeLinksHeader">
			<%-- <a class="actionMenuItem" id="searchActionMenu" href="${pageContext.request.contextPath}/searchActionMenu.html"></a> --%>
			<span class="contentHeading"><fmt:message key="searchHeader"/></span>
		</h1>
		<h1 class="homeLinksHeader">
			<%-- <a class="actionMenuItem" id="caseLoadActionMenu" href="${pageContext.request.contextPath}/caseLoadActionMenu.html"></a> --%>
			<span class="contentHeading"><fmt:message key="caseLoadHeader"/></span>
		</h1>
		<h1 class="homeLinksHeader">
			<%-- <a class="actionMenuItem" id="workCentersActionMenu" href="${pageContext.request.contextPath}/workCentersActionMenu.html"></a> --%>
			<span class="contentHeading"><fmt:message key="workCentersHeader"/></span>
		</h1>
	</div>
	<div id="homeLinksPanelContainer">
		<ul class="content homeLinksListContainer">
			<li>
				<a href="${pageContext.request.contextPath}/offender/advancedOffenderSearch.html">
					<img src="${pageContext.request.contextPath}/resources/common/images/offenderSearch.png" />
					<span class="label"><fmt:message key="offenderSearchLabel"/></span>
				</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/staffSearch/staffSearch.html">
					<img src="${pageContext.request.contextPath}/resources/common/images/staffSearch.png" />
					<span class="label"><fmt:message key="staffSearchLabel"/></span>
				</a>
			</li>
			<sec:authorize access="hasRole('ADMIN') or hasRole('OFFENDER_RELATIONSHIP_LIST')">
			<li>
				<a href="${pageContext.request.contextPath}/offenderRelationship/search.html">
					<img style="display: none" src="${pageContext.request.contextPath}/resources/common/images/offenderRelationsSearch.png" />
					<span class="label"><fmt:message key="offenderRelationsSearchLabel"/></span>
				</a>				
			</li>
			</sec:authorize>
			<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
			<li>
				<a href="${pageContext.request.contextPath}/employer/employerSearch.html">
					<img src="${pageContext.request.contextPath}/resources/common/images/employerSearch.png" />
					<span class="label"><fmt:message key="employerSearchLabel"/></span>
				</a>
			</li>
			</sec:authorize>
			<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
			<li>
				<a href="${pageContext.request.contextPath}/health/provider/providerSearch.html">
					<img src="${pageContext.request.contextPath}/resources/common/images/serviceProviderSearch.png" />
					<span class="label"><fmt:message key="serviceProviderSearchLabel"/></span>
				</a>
			</li>
			</sec:authorize>
			<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
			<li>
				<a href="${pageContext.request.contextPath}/residence/residenceSearch.html">
					<img src="${pageContext.request.contextPath}/resources/common/images/addressSearch.png" />
					<span class="label"><fmt:message key="addressPhoneSearchLabel"/></span>
				</a>
			</li>
			</sec:authorize>
		</ul>
		<ul class="content homeLinksListContainer">
			<sec:authorize access="hasRole('ADMIN') or hasRole('OFFICER_CASE_ASSIGNMENT_LIST')">
			<li>
				<a href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/list.html">
					<img src="${pageContext.request.contextPath}/resources/common/images/myCaseloads.png" />
					<span class="label"><fmt:message key="myCaseloadsLabel"/></span>
				</a>
			</li>			
			</sec:authorize>
			<%-- <sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
			<li>
				<a href="#">
					<img src="${pageContext.request.contextPath}/resources/common/images/caseloadAnalytics.png" />
					<span class="label"><fmt:message key="caseloadAnalyticsHealth"/></span>
				</a>
			</li>
			</sec:authorize> --%>
			<%-- <sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
			<li>
				<a href="#">
					<img src="${pageContext.request.contextPath}/resources/common/images/caseloadAdministration.png" />
					<span class="label"><fmt:message key="caseloadAdministration"/></span>
				</a>
			</li>
			</sec:authorize> --%>
			<%-- <sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
					<img src="${pageContext.request.contextPath}/resources/common/images/administrativeCaseloads.png" />
					<span class="label"><fmt:message key="administrativeCaseloads"/></span>
			</sec:authorize> --%>
			<%-- <sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
			<li>
				<a href="${pageContext.request.contextPath}/caseload/list.html">	
			
				<a href="${pageContext.request.contextPath}/caseLoad/administrative/create.html">
				<a href="${pageContext.request.contextPath}/caseLoad/supervisory/create.html">
					<img src="${pageContext.request.contextPath}/resources/common/images/supervisoryCaseloads.png" />
					<span class="label">Supervisory Caseloads</span>
				</a>
			</li>
			</sec:authorize> --%>
		</ul>
		<ul class="content homeLinksListContainer">
			<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
			<li>
				<a href="${pageContext.request.contextPath}/health/referral/referralCenter.html">
					<img src="${pageContext.request.contextPath}/resources/common/images/medicalCenter.png" />
					<span class="label"><fmt:message key="facilityHealthReferralCenter"/></span>
				</a>
			</li>
			</sec:authorize>
			<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
			<li>
				<a href="${pageContext.request.contextPath}/bopp/home.html">
					<!-- <img src="${pageContext.request.contextPath}/resources/common/images/??.png" /> -->
					<span class="label"><fmt:message key="boardOfPardonsAndParole"/></span>
				</a>
			</li>
			</sec:authorize>
			<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
			<li>
				<a href="${pageContext.request.contextPath}/presentenceInvestigation/request/list.html">
					<!-- <img src="${pageContext.request.contextPath}/resources/common/images/??.png" />-->
					<span class="label"><fmt:message key="presentenceInvestigations"/></span>
				</a>
			</li>
			</sec:authorize>
			<sec:authorize access="(hasRole('ADMIN') or hasRole('VISIT_LIST'))">
			<li>
				<a href="${pageContext.request.contextPath}/visitation/facility/list.html">
					<img src="${pageContext.request.contextPath}/resources/common/images/visitationCenter.png" />
					<span class="label"><fmt:message key="facilityVisitationCenter"/></span>
				</a>
			</li>
			</sec:authorize>
			<sec:authorize access="(hasRole('INCIDENT_REPORT_LIST') or hasRole('ADMIN'))">
			<li>
				<a href="${pageContext.request.contextPath}/incident/statement/list.html">
					<img src="${pageContext.request.contextPath}/resources/common/images/incidentManagementSystem.png" />
					<span class="label"><fmt:message key="correctionsIMS"/></span>
				</a>
			</li>
			</sec:authorize>	
			<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">	
			<li>
				<a href="#">
					<img src="${pageContext.request.contextPath}/resources/common/images/legalRecordsCenter.png" />
					<span class="label"><fmt:message key="offenderLegalCenter"/></span>
				</a>
			</li>
			</sec:authorize>
			<sec:authorize access="(hasRole('ADMIN') and hasRole('APP_DEV'))">
			<li>
				<a href="${pageContext.request.contextPath}/placement/home.html">
					<img src="${pageContext.request.contextPath}/resources/common/images/offenderPlacementCenter.png" />
					<span class="label"><fmt:message key="offenderPlacementCenter"/></span>
				</a>
			</li>
			</sec:authorize>
		</ul>
	</div>
</fmt:bundle>