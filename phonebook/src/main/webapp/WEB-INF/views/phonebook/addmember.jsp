<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연락처 추가</title>
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
	.a {
		float: left;
	}
	.pn_ok{
	color:#008000;
	display: none;
	}

	.pn_already{
	color:#6A82FB; 
	display: none;
	}
	.nm_ok{
	color:#008000;
	display: none;
	}

	.nm_already{
	color:#6A82FB; 
	display: none;
	}
	.add_ok{
	color:#008000;
	display: none;
	}

	.add_already{
	color:#6A82FB; 
	display: none;
	}
</style>
</head>
<body>
<%@include file="nav.jsp"%>
	<!-- Section: Design Block -->
	<section class="text-center">
		<!-- Background image -->
		<div class="p-5 bg-image"
			style="background-image: url('/images/10-10.jpg'); height: 300px;"></div>
		<!-- Background image -->

		<div class="card mx-4 mx-md-5 shadow-5-strong"
			style="margin-top: -100px; background: hsla(0, 0%, 100%, 0.8); backdrop-filter: blur(30px);">
			<div class="card-body py-5 px-md-5">

				<div class="row d-flex justify-content-center">
					<div class="col-lg-8">
						<h2 class="fw-bold mb-5">연락처 등록</h2>
						<form method="post" action="/phonebook/add">
						
							<div class="form-outline mb-4">
								<input type="text" id="form3Example1" name="membernm" class="form-control" oninput="namecheck(membernm.value)" minlength="2" maxlength="20" required/> 
								<label class="form-label" for="form3Example1">이름</label>
							</div>
							<p class="nm_ok">바르게 입력하셨습니다.</p> 
							<p class="nm_already">2글자 이상 입력하세요.</p>

							<div class="form-outline mb-4"><!-- 
								<input type="text" id="form3Example2" name="phonenumber" class="form-control" oninput="pncheck(phonenumber.value)" onkeypress='return checkNumber(event)' required/>  -->
								<input type="text" minlength="11" maxlength="11" id="form3Example2" name="phonenumber" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" oninput="pncheck(phonenumber.value)" onKeyup="onlyNumber(this)" required/>
								
								<label class="form-label" for="form3Example2">전화번호</label>
							</div>
							<p class="pn_ok">바르게 입력하셨습니다.</p> 
							<p class="pn_already">11자리를 입력하세요.</p>
							

							<div class="form-outline mb-4">
								<input type="text" id="form3Example3" name="address" class="form-control" oninput="addresscheck(address.value)" minlength="2" required />
								<label class="form-label" for="form3Example3">주소</label> 
							</div>
							<p class="add_ok">바르게 입력하셨습니다.</p> 
							<p class="add_already">2글자 이상 입력하세요.</p>

							<div class="form-outline mb-4">
		                     <select class="form-select" name="groupno" aria-label="Default select example">
		                        <option disabled>그룹 선택</option>
		                        <option value="10">가족</option>
		                        <option value="20">친구</option>
		                        <option selected value="30">기타</option>
		                     </select>
                  		   </div>
							<!-- Checkbox -->
							<!-- <div class="form-check d-flex justify-content-center mb-4">
								<input class="form-check-input me-2" type="checkbox" value=""
									id="form2Example33" checked /> <label class="form-check-label"
									for="form2Example33"> 가입하시겠습니까? </label>
							</div> -->

							<!-- Submit button -->
							<button onclick="btn(); return false;" type="submit"
								class="btn btn-primary btn-block mb-4">연락처 추가</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
 <!—  id 확인 —>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>	
<script src="/js/check.js"></script>

</body>
</html>