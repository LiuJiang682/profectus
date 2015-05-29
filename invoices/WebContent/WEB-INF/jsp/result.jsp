<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profectus Invoices Search Result</title>
</head>
<body>
<h3>Profectus Invoices Search Result</h3>
	<table border="1">
		<tbody>
		    <tr>
		      <td>Invoice Number</td>
		      <td>Invoice Date</td>
		      <td>Total Amount</td>
		      <td>Net Amount</td>
		      <td>Cash Method</td>
		      <td>Security Fee</td>
		    </tr>
			<c:forEach var="invoice" items="${invoices}">
			   <tr>
			     <td>${invoice.invoiceNumber}</td>
			     <td>${invoice.invoiceDate}</td>
			     <td>${invoice.totalAmount}</td>
			     <td>${invoice.netAmount}</td>
			     <td>${invoice.cashMethod}</td>
			     <td>${invoice.securityFee}</td>
			   </tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>