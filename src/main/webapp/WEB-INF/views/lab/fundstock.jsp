<%@ page isErrorPage="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spform"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet"
		href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
	<meta charset="UTF-8">
	<title>Fundstock Form</title>
	<style type="text/css">
	.error {
		color: #FF0000
	}
	</style>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['symbol', 'share'],
          <c:forEach var="map" items="${ groupMap }">
			['${ map.key }', ${ map.value }],
		  </c:forEach>
        ]);

        var options = {
          title: 'stock info'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
	</script>
</head>
<body style="padding: 15px">
	<table>
		<tr>
			<!-- Fundstock Form -->
			<td valign="top">
				<spform:form class="pure-form" method="post"
					modelAttribute="fundstock"
					action="${ pageContext.request.contextPath }/mvc/lab/fundstock/">
					<fieldset>
						<legend>Fundstock Form</legend>
						<input type="hidden" id="_method" name="_method"
							value="${ _method }"> 序號：
						<spform:input path="sid" />
						<spform:errors path="sid" cssClass="error" />
						<p />
						代號：
						<spform:input path="symbol" />
						<spform:errors path="symbol" cssClass="error" />
						<p />
						數量：
						<spform:input path="share" />
						<spform:errors path="share" cssClass="error" />
						<p />
						基金：
						<p />
						<button type="submit" class="pure-button pure-button-primary"
							${ _method=='POST'?'':'disabled' }>新增</button>
						<button type="submit" class="pure-button pure-button-primary"
							${ _method=='PUT'?'':'disabled' }>修改</button>
						<p />
						<spform:errors path="*" cssClass="error" />
					</fieldset>
				</spform:form>
			</td>
			<!-- Fundstock List -->
			<td valign="top">
				<form class="pure-form">
					<fieldset>
						<legend>
							Fundstock List&nbsp;|&nbsp;
							<a href="${ pageContext.request.contextPath }/mvc/lab/fundstock/page/0">全部</a>
							&nbsp;|&nbsp;
							<c:forEach var="num" begin="1" end="${ pageTotalCount+1 }">
								<a href="${ pageContext.request.contextPath }/mvc/lab/fundstock/page/${ num }">${ num }</a>
							</c:forEach>
						</legend>
						<table class="pure-table pure-table-bordered">
							<thead>
								<tr>
									<th>序號</th>
									<th>代號</th>
									<th>數量</th>
									<th>基金</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="fundstock" items="${ fundstocks }">
									<tr>
										<td><a
											href="${ pageContext.request.contextPath }/mvc/lab/fundstock/${ fundstock.sid }">${ fundstock.sid }</a></td>
										<td>${ fundstock.symbol }</td>
										<td>${ fundstock.share }</td>
										<td>${ fundstock.fund.fname }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</fieldset>
				</form>
			</td>
			<!-- Fundstock chart -->
			<td valign="top">
				<form class="pure-form">
					<fieldset>
						<legend>Fundstock Chart</legend>
						<div id="piechart" style="width: 500px; height: 300px;"></div>
						
						
					</fieldset>
				</form>
			</td>
		</tr>
	</table>




</body>
</html>