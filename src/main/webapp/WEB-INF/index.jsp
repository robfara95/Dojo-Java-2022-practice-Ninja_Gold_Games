<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Date"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Ninja Gold</title>
	<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<h2><c:out value="${message}"/></h2>
	<p>Your Gold <c:out value="${totalGold}"/></p>
	<div id="main">
		<div class="building">
			<form method="POST" action="/findGold">
				<h3>Farm!</h3>
				<p>(Earns 10-20)</p>
				<input type="hidden" name="building" value="farm">
				<button>Find Gold!</button>
			</form>
		</div>
		<div class="building">
			<form method="POST" action="/findGold">
				<h3>Cave!</h3>
				<p>(Earns 5-10)</p>
				<input type="hidden" name="building" value="cave">
				<button>Find Gold!</button>
			</form>		
		</div>
		<div class="building">
			<form method="POST" action="/findGold">
				<h3>House!</h3>
				<p>(Earns 2-5)</p>
				<input type="hidden" name="building" value="house">
				<button>Find Gold!</button>
			</form>
		</div>	
		<div class="building">
			<form method="POST" action="/findGold">
				<h3>Casino!</h3>
				<p>(Earns/ takses 0-50)</p>
				<input type="hidden" name="building" value="casino">
				<button>Find Gold!</button>
			</form>				
		</div>
		 
		<div><a href="/findgold/reset">
			<button>Reset</button>
		</a>
	</div>
		
		
		<h2>Activities</h2>
		<div class="activites">
			<table>
				<c:forEach var="message" items="${messages}">					
					<tr>					
						<c:choose>
							<c:when test="${message.contains('lost')}">						
								<td class="color-red"><c:out value="${message}"/></td> 
							</c:when>
							<c:otherwise>
								<td class="color-green"><c:out value="${message}"/></td> 
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</div>
		
	</div>
</body>
</html>