<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.booking.msgs.booking">
<table id="bookingNoteTable" class="editTable">
	<thead class="notes">
		<tr>
			<th class="operations"></th>
			<th class="date">
				<fmt:message key="dateLabel"/>
			</th>
			<th>
				<fmt:message key="descriptionLabel"/>
			</th>
			<th>
				<fmt:message key="updatedLabel"/>
			</th>
		</tr>
	</thead>
	<tbody id="bookingNoteTableBody">
		<jsp:include page="bookingNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>