<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Login screen.
 -
 - Author: Stephen Abson
 - Author: Ryan Johns
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <fmt:setBundle basename="omis.msgs.general" var="generalBundle" />
  <fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
  <fmt:bundle basename="omis.msgs.security">
  <head>
    <title><fmt:message key="loginHeader"/> - <fmt:message key="${hostingEnvPropertyHolder.propertyValue}HostingEnvLabel" bundle="${generalBundle}"/></title>
    <jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
    <jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
    <jsp:include page="/WEB-INF/views/common/includes/formResources.jsp"/>
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/common/images/appIcon.ico" />
	<style type="text/css">
	/* <![CDATA[ */
	body {
		margin-top: 40px;
	}
	form#loginForm table {
		margin-left: auto;
		margin-right: auto;
	}
	h2 {
		margin: 0px;
		padding: 0px;
		text-align: center;
	}
	h3 {
		margin: 0px;
		padding: 0px;
		text-align: center;
	}
	h4 {
		text-align: center;
	}
	input[type=text], input[type=password] {
		width: 100px;
	}
	input[type=submit], input[type=reset] {
		width: 80px;
	}
	td.buttons {
		text-align: center;
	}
	#disclaimer {
		width: 620px;
		margin-left: auto;
		margin-right: auto;
		border: solid black 1px;
		background-color: lightgray;
		padding: 5px;
	}
	td#badCredentials, td#accountDisabled, td.messages span.error {
		text-align: center;
		color: darkred;
		font-weight: bold;
	}
	label {
		text-align: right;
	}
	#quickReferenceGuideLink {
	text-align: center;
	}	
	/* ]]> */
	</style>
	<script type="text/javascript">
	/* <![CDATA[ */
	var url = top.location.href;
	var loginPath = "/login.html"<c:if test='${not empty badCredentials}'> + "?badCredentials=${badCredentials}&accountDisabled=${accountDisabled}"</c:if>;
	if (url.substring(url.length - loginPath.length, url.length) != loginPath) {
				top.location.href = "${pageContext.request.contextPath}" + loginPath;
	}
	window.onload = function() {
		var j_username = document.getElementById("j_username");
		j_username.onblur = function() {
			j_username.value = j_username.value.toUpperCase();
			if (typeof(Storage) !== "undefined") {
				sessionStorage.setItem("username", j_username.value);
			}
		}; 
		j_username.focus();

		document.getElementById("loginForm").onkeypress = function(e) {
			capsState(e);
		} 
		document.msCapsLockWarningOff = true;		
	};
	
	function capsDetection(e) 
	{
		var ev = e ? e : window.event;
		
		var shift_status = ev.shiftKey==1;
		var keyCode = ev.keyCode ? ev.keyCode : ev.charCode;
		
		if (((keyCode >= 65 && keyCode <= 90) && !shift_status) ||
				((keyCode >= 97 && keyCode <= 122) && shift_status)) {
				return true;
		} else {
			return false;
		}
	}
	
	function capsState(e) {
		var capsLockRow = document.getElementById("capsLockRow");
		var className = capsLockRow.className.replace(/\s?hide|\s?show/gi, "");
		if (className.length > 0) {
			className += " ";
		}
		if (capsDetection(e)) {
			
			capsLockRow.className = className + "show";	
		} else {	
			capsLockRow.className = className + "hide"; 
		}
	} 
	/* ]]> */
	</script>
  </head>
  <body>
  <form id="loginForm" method="post" action="${pageContext.request.contextPath}/j_spring_security_check">
  	<table>
  		<tbody>
  			<tr>
  				<c:choose>
  					<c:when test="${badCredentials or accountDisabled}">
  						<c:set var="rows" value="8"/>
  					</c:when>
  					<c:otherwise>
  						<c:set var="rows" value="7"/>
  					</c:otherwise>
  				</c:choose>
  				<td rowspan="${rows}"></td>
  				<td colspan="2"><h2><fmt:message key="loginHeader"/></h2></td>
  			</tr>
  			<tr>
  				<td colspan="2">
  					<h3><fmt:message key="loginSubHeader"/></h3>
  					<h4><fmt:message key="${hostingEnvPropertyHolder.propertyValue}HostingEnvLabel" bundle="${generalBundle}"/></h4>
  				</td>
  			</tr>
  			<tr>
  				<td><label for="j_username"><fmt:message key="usernameLabel"/></label></td>
  				<td><input type="text" name="j_username" id="j_username" /></td>
  			</tr>
  			<tr>
  				<td><label for="j_password"><fmt:message key="passwordLabel"/></label></td>
  				<td><input type="password" name="j_password" id="j_password"/></td>
  			</tr>
  			<tr>
  				<td colspan="2" class="messages" >
  					<span id="capsLockRow" class="error hide">
  						<fmt:message key="capsLockOnLabel"/>
  					</span>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<label><fmt:message key="screenModeLabel"/></label>
  				</td>
  				<td>
  					<select name="multiScreenMode">
  						<option value="" selected="selected"><fmt:message key="defaultScreenModelLabel"/></option>
  						<option value="false"><fmt:message key="singleScreenModeLabel"/></option>
  					</select>
  				</td>
  			</tr>
  			
  			<c:if test="${badCredentials || accountDisabled }">
  			<tr>
  				<c:choose>
  				<c:when test="${accountDisabled}">
  				<td colspan="2" id="accountDisabled">
  					<fmt:message key="accountDisabledMessage"/>
  				</td>
  				</c:when>
  				<c:otherwise>
  				<td colspan="2" id="badCredentials">
  					<fmt:message key="badCredentialsMessage"/>
  				</td>
  				</c:otherwise>
  				</c:choose>
  			</tr>
  			</c:if>
  			<tr>
  				<td colspan="2" class="buttons">
  					<input type="submit" value="<fmt:message key='okButton'/>" />
  					<input type="reset" value="<fmt:message key="clearButton"/>" />  					
  				</td>
  			</tr>
  		</tbody>
  	</table>
  	<p id="disclaimer">
  		
  	</p>	  		
  </form>  
  </body>
  </fmt:bundle>
</html>