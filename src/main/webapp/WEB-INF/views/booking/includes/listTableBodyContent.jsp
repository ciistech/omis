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
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:forEach var="booking" items="${bookings}">
	<tr>
		<td>
			<a class="actionMenuItem" 
				href="${pageContext.request.contextPath}/booking/bookingsActionMenu?booking=${booking.id}"></a>
		<td>
			<fmt:formatDate var="bookingDate" value="${booking.date}" pattern="MM/dd/yyyy"/>
			<c:out value="${bookingDate}"/>
		</td>
		<td><c:out value="${booking.bookingNumber}"/></td>
		<td><c:out value="${booking.countyName}"/></td>
	</tr>
</c:forEach>