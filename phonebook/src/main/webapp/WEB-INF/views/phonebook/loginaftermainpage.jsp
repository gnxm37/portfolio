<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사이트 메인</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"
	rel="stylesheet" />
<style type="text/css">

.a{
	position:relative;
	top:-100px;
}
.search {
	position: relative;
	text-align: center;
	width: 400px;
	margin: 0 auto;
}

input {
	width: 100%;
	border-radius: 20px;
	border: 1px solid #bbb;
	margin: 10px 0;
	padding: 10px 12px;
}

.fa-search {
	position: absolute;
	left: 370px;
	top: 25px;
	margin: 0;
}

.fa-keyboard {
	position: absolute;
	right: 35px;
	top: 20px;
}

.fa-microphone {
	position: absolute;
	right: 15px;
	top: 20px;
	color: blue;
}
</style>
</head>
<!-- Navbar -->
<%@include file="nav.jsp"%>
<!-- Navbar -->
<body>
	<form method="post" action="/phonebook/search">
	<div class="p-5 text-center bg-image"
		style="background-image: url('/images/10-8.jpg'); height: 750px;">
		<div class="mask" style="background-color: rgba(0, 0, 0, 0.6);">
			<div class="d-flex justify-content-center align-items-center h-100">
				<div class="text-white">
				<div class = a>
					<h1 class="mb-3">PHONE BOOK</h1>
					<div class="container">
						<div class="d-flex justify-content-center">
							<div class="search">
								<input class="search_input" type="text" name="membernm" placeholder="&nbsp;&nbsp;회원이름 검색">
									<button type="submit" class="btn btn-link"><i class="fas fa-search"></i></button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
${msg}
</body>
<%@include file="footer.jsp"%>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>
</html>