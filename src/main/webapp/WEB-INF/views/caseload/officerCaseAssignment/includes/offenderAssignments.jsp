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
 - Author: Josh Divine
 - Author: Annie Wahl
 - Version: 0.1.1 (Jan 8, 2019)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<tr id="subAssignmentsRow${officerCaseAssignment.id}" class="offenderAssignmentRow">
	<c:set var="officerCaseAssignmentId" value="${officerCaseAssignment.id}" scope="request"/>
 	<c:set var="officerCaseAssignmentSummaries" value="${subOfficerCaseAssignments}" scope="request"/>
 	<td></td>
 	<td colspan="5"><jsp:include page="listTable.jsp"/></td>
</tr>