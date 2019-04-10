<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.booking.msgs.booking">
	<tr id="bookingNoteItemRow${bookingNoteItemIndex}">
		<td>
			<a class="removeLink" id="removeLink${bookingNoteItemIndex}" href="${pageContext.request.contextPath}/booking/removeBookingNote.html">
				<span class="linkLabel">
					<fmt:message key="removeLink" bundle="${commonBundle}"/>
				</span>
			</a>
			<input type="hidden" id="bookingNoteId${bookingNoteItemIndex}" name="bookingNoteItems[${bookingNoteItemIndex}].bookingNote" value="${bookingNoteItem.bookingNote.id}"/>
			<form:errors path="serviceTermNoteItems[${bookingNoteItemIndex}].bookingNote" cssClass="error"/>
			<input type="hidden" id="bookingNoteOperation${bookingNoteItemIndex}" name="bookingNoteItems[${bookingNoteItemIndex}].itemOperation" value="${bookingNoteItem.itemOperation}"/>
		</td>
		<td>
			<fmt:formatDate var="bookingNoteDate" value="${bookingNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input id="bookingNoteItemDate${bookingNoteItemIndex}" type="text" class="date" name="bookingNoteItems[${bookingNoteItem.date}]" value="${bookingNoteDate}"/>
			<form:errors path="bookingNoteItems[${bookingNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea id="bookingNoteItemNote${bookingNoteItemIndex}" rows="3" name="bookingNoteItems[${bookingNoteItemIndex}].description">
				<c:out value="${bookingNoteItem.description}"/>
			</textarea>
			<form:errors path="bookingNoteItems[${bookingNoteItemIndex}].description"/>
		</td>
		<td>
			<c:if test="${not empty bookingNoteItem.bookingNote.updateSignature}">
				<c:set var="updateName" value="${bookingNoteItem.bookingNote.updateSignature.user.name}"/>
				<fmt:formatDate var="updateDate" value="${bookingNoteItem.bookingNote.updateSignature.date}" pattern="MM/dd/yyyy"/>
				<fmt:message key="lastUpdatedLabel">
					<fmt:param value="${updateName.lastName}"/>
					<fmt:param value="${updateName.firstName}"/>
					<fmt:param value="${updateDate}"/>
				</fmt:message>
			</c:if>
	</tr>
</fmt:bundle>