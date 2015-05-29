<html>
<head>
<title>Profectus Invoices Search</title>
</head>
<body>
	<br>
	<div style="text-align: center">
		<h2>Profectus Invoices Search</h2>
	</div>
	<div style="text-align: center">
		<form action="searchInvoiceNumber.do" method="post">
		    <h3>Search by Invoice number</h3>
			Invoice number: <input type="text" name="invoiceNumber" /> <input
				type="submit" value="Search" />
		</form>
	</div>
	<div style="text-align: center">
		<form action="searchInvoiceType.do" method="post">
		  <h3>Search by Invoice type</h3>
			<table style="text-align: center" width="100%">
				<tbody>
					<tr>
						<td style="text-align: center">Invoice Type: <select
							name="invoiceType">
								<option value="c">Cash</option>
								<option value="s">Security</option></td>
					</tr>
					<tr>
						<td style="text-align: center">
						  </select> 
						      Page Size: <select name="pageSize">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="50">50</option>
						  </select>
						</td>
					</tr>
					<tr>
					   <td style="text-align: center">
					       <input type="submit" value="Search" />
					   </td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>