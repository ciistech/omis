<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.booking.msgs.booking">
<form:form commandName="bookingForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="detailsLabel"/></legend>
		
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="bookingNumber"><fmt:message key="bookingNumberLabel"/></form:label>'
			<form:input path="bookingNumber"/>
			<form:errors cssClass="error" path="bookingNumber"/>
		</span>
		
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="date"><fmt:message key="dateLabel"/></form:label>
			<form:input id="date" class="date" path="date"/>
			<form:errors cssClass="error" path="date"/>
		</span>
		
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="category"><fmt:message key="categoryLabel"/></form:label>
			<form:select path="category">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<form:options items="${bookingAdmissionCategories}" itemLabel="name" itemValue="id"/>
			</form:select>
			<form:errors cssClass="error" path="category"/>
		</span>
		
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="correctionalStatus"><fmt:message key="correctionalStatusLabel"/></form:label>
			<form:select path="correctionalStatus">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<form:options items="${correctionalStatuses}" itemLabel="name" itemvalue="id"/>
			</form:select>
			<form:errors cssClass="error" path="correctionalStatus"/>
		</span>
		
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="county"><fmt:message key="countyLabel"/></form:label>
			<form:select path="county">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<form:options items="${counties}" itemLabel="name" itemValue="id"/>
			</form:select>
			<form:errors cssClass="error" path="county"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="transportDetailsLabel"/></legend>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="commitSource"><fmt:message key="commitSourceLabel"/></form:label>
			<form:select path="commitSource">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<form:options items="${commitSources}" itemLabel="name" itemValue="id"/>
			</form:select>
			<form:errors cssClass="error" path="commitSource"/>
		</span>
		<c:set var="staffName" value="${not empty bookingForm.transportOfficer ? 'bookingForm.transportOfficer.staffMember.name.lastName bookingForm.transportOfficer.staffMember.name.lastName' : ''}"/>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="transportOfficer"><fmt:message key="transportOfficerLabel"/></form:label>
			<input id="transportOfficerInput" value="${staffName}"/>
			<form:input path="transportOfficer" type="hidden"/>
			<a id="currentStaffAssignmentLink" class="currentUserAccountLink"></a>
			<a id="clearStaffAssignmentLink" class="clearLink"></a>
		</span>
		<form:errors path="transportOfficer" cssClass="error"/>
	</fieldset>
	<fieldset>
		<legend>
			<a class="createLink" 
    	  		  id="bookingNoteItemsActionMenuLink" 
				href="${pageContext.request.contextPath}/booking/addBookingNote.html?bookingNoteItemIndex=${bookingNoteItemIndex}"></a>
			<fmt:message key="bookingNoteLabel"/>
		</legend>
		<jsp:include page="bookingNoteTable.jsp"/>
	</fieldset>
	<c:if test="${not empty booking}">
		<c:set var="updatable" value="${booking}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<fmt:message var ="saveLabel" key="saveLabel" bundle="${commonBundle}"/>
		<input type="submit" value="${saveLabel}"/>
	</p>
	
</form:form>	
</fmt:bundle>