<%@ page contentType="text/html; charset=EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<title>Insert title here</title>
</head>

<body>

	<form name="updatePurchase"
		action="/purchase/updatePurchase?tranNo=${tranNo }" method="post">

		������ ���� ���Ű� �Ǿ����ϴ�.

		<table border=1>
			<tr>
				<td>��ǰ��ȣ</td>
				<td>${purchase.purchaseProd.prodNo}</td>
				<td></td>
			</tr>
			<tr>
				<td>�����ھ��̵�</td>
				<td>${purchase.buyer.userId}</td>
				<td></td>
			</tr>
			<tr>
				<td>���Ź��</td>
				<td>${purchase.paymentOption}</td>
				<td></td>
			</tr>
			<tr>
				<td>�������̸�</td>
				<td>${purchase.receiverName}</td>
				<td></td>
			</tr>
			<tr>
				<td>�����ڿ���ó</td>
				<td>${purchase.receiverPhone}</td>
				<td></td>
			</tr>
			<tr>
				<td>�������ּ�</td>
				<td>${purchase.divyAddr}</td>
				<td></td>
			</tr>
			<tr>
				<td>���ſ�û����</td>
				<td>${purchase.divyRequest}</td>
				<td></td>
			</tr>
			<tr>
				<td>����������</td>
				<td>${purchase.divyDate}</td>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
			<tr>
				<td width="53%"></td>
				<td align="right">
				<table border="0" cellspacing="0" cellpadding="0">
						<tr>
				<td width="17" height="23">
				<img src="/images/ct_btnbg01.gif" width="17" height="23" />
				<td background="/images/ct_btnbg02.gif" class="ct_btn01"
					style="padding-top: 3px;"><a
					href="/product/listProduct?menu=search&orderby=${param.orderby }">Ȯ��</a>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>