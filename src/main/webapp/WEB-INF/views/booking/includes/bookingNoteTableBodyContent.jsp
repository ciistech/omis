<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.booking.msgs.booking">
	<c:forEach var="bookingNoteItem" items="${bookingNoteItems}" varStatus="status">
		<c:set var="bookingNoteItem" value="${bookingNoteItem}" scope="request"/>
		<c:set var="bookingNoteItemIndex" value="${status.index}" scope="request"/>
		<jsp:include page="bookingNoteItemTableRow.jsp"/>
	</c:forEach>
</fmt:bundle>