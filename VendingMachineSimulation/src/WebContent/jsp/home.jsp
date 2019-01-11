<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<c:set value="${pageContext.request.contextPath}" var="contextPath"></c:set>
<title>Vending Machine</title>
<style type="text/css">
.isDisabled {
	pointer-events: none;
	cursor: not-allowed;
	opacity: 0.5;
	text-decoration: none;
	color: currentColor;
}
</style>
<!-- Bootstrap core CSS -->
<link href="${contextPath}/css/bootstrap.css"
	rel="stylesheet">
</head>
<body>
	<c:set var="vendingService" value="${vendingObj}" />
	<div class="container">
		<h1 class="text-center" style="margin-top: 30px">VM</h1>
		<hr style="margin-bottom: 30px">
		<!-- ITEMS -->
		<div id="productsColumn" class="col-sm-9">
			<c:forEach var="item" items="${vendingService.products}">
				<div class="col-sm-4">
					<div class="panel panel-default">
						<c:if test="${item.quantity<=0}">
							<c:set var="soldOut" value="isDisabled"></c:set>
						</c:if>
						<a class="${soldOut}" style="text-decoration: none"
							href="${pageContext.request.contextPath}/makeSelection/${item.id}">
							<div class="panel-body">
								<p class="text-left">${item.id}</p>
								<p class="text-center">${item.name}</p>
								<p class="text-center">$${item.price}</p>
								<br>
								<p class="text-center">Quantity Left: ${item.quantity}</p>
							</div>
						</a>
						<c:set var="soldOut" value=""></c:set>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- FORMS -->
		<div class="col-sm-3">
			<div class="row">
				<h3 class="text-center">Total ￥ In</h3>
				<form>
					<div class="form-group">
						<input class="form-control" id="balance" type="text"
							value="${vendingService.balance}" disabled>
					</div>
				</form>
				<div class="text-center">
					<a href="${pageContext.request.contextPath}/addMoney/500"
						class="btn btn-default">500￥</a> <a
						href="${pageContext.request.contextPath}/addMoney/100"
						class="btn btn-default">100￥</a>
				</div>
				<div class="text-center">
					<a href="${pageContext.request.contextPath}/addMoney/50"
						class="btn btn-default">50￥</a> <a
						href="${pageContext.request.contextPath}/addMoney/10"
						class="btn btn-default">10￥</a> <a
						href="${pageContext.request.contextPath}/addMoney/5"
						class="btn btn-default">5￥</a>
				</div>
				<hr>
			</div>
			<div class="row">
				<h3 class="text-center">Messages</h3>
				<form>
					<div class="form-group">
						<input value="${vendingService.message}" class="form-control"
							disabled></input>
					</div>
					<div class="form-group">
						<h3 style="display: inline">Item:</h3>
						<c:forEach var="selectedItem" items="${vendingService.selection}">
							<input id="itemNum" class="form-control" type="text"
								value="<c:if test="${selectedItem != null}">${selectedItem.name}&nbsp;&nbsp;${selectedItem.price} </c:if>"
								style="width: 75%; display: inline" disabled>
						</c:forEach>
					</div>
				</form>
				<a href="makePurchase" class="btn btn-default btn-block">Make
					Purchase</a>
				<hr>
			</div>
			<div class="row" style="margin-bottom: 30px">
				<h3 class="text-center">change</h3>
				<form>
					<div class="form-group">
						<textarea id="changeMsg" class="form-control" row="2" disabled><c:if
								test="${vendingService.myChange != null}">
								<c:if test="${vendingService.myChange.yen500 ne 0}">￥500 ${vendingService.myChange.yen500}</c:if>
								<c:if test="${vendingService.myChange.yen100 ne 0}">￥100 ${vendingService.myChange.yen100}</c:if>
								<c:if test="${vendingService.myChange.yen50 ne 0}">￥50 ${vendingService.myChange.yen50}</c:if>
								<c:if test="${vendingService.myChange.yen10 ne 0}">￥10 ${vendingService.myChange.yen10}</c:if>
								<c:if test="${vendingService.myChange.yen5 ne 0}">￥5 ${vendingService.myChange.yen5}</c:if>
								
  </c:if></textarea>
					</div>
				</form>
				<a class="btn btn-default btn-block" href="changeReturn">Change
					Return</a>
			</div>
		</div>
	</div>

	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${contextPath}/js/js/jquery-3.1.1.min.js"></script>
	<script src="${contextPath}/js/bootstrap.js"></script>
	<script src="${contextPath}/js/home.js">
		
	</script>

</body>

</html>

