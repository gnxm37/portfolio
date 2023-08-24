<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <title>Dashboard</title>
    <!-- 제이쿼리 -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js@3.3.2"></script>
	<script src="https://cdn.jsdelivr.net/npm/luxon@1.27.0"></script>
	<script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-luxon@1.0.0"></script>
	<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-streaming@2.0.0"></script>
	<!-- Simple bar CSS -->
    <link rel="stylesheet" href="/css/simplebar.css">
    <!-- Fonts CSS -->
    <link href="https://fonts.googleapis.com/css2?family=Overpass:ital,wght@0,100;0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <!-- Icons CSS -->
    <link rel="stylesheet" href="/css/feather.css">
    <!-- Date Range Picker CSS -->
    <link rel="stylesheet" href="/css/daterangepicker.css">
    <!-- App CSS -->
    <link rel="stylesheet" href="/css/app-light.css" id="lightTheme">
    <link rel="stylesheet" href="/css/app-dark.css" id="darkTheme" disabled>
  </head>
  <body class="vertical  dark">
    <div class="wrapper">
      <nav class="topnav navbar navbar-light">
        <button type="button" class="navbar-toggler text-muted mt-2 p-0 mr-3 collapseSidebar">
          <i class="fe fe-menu navbar-toggler-icon"></i>
        </button>
        <form class="form-inline mr-auto searchform text-muted">
          <input class="form-control mr-sm-2 bg-transparent border-0 pl-4 text-muted" type="search" placeholder="Type something..." aria-label="Search">
        </form>
      </nav>
      <aside class="sidebar-left border-right bg-white shadow" id="leftSidebar" data-simplebar>
        <a href="#" class="btn collapseSidebar toggle-btn d-lg-none text-muted ml-2 mt-3" data-toggle="toggle">
          <i class="fe fe-x"><span class="sr-only"></span></i>
        </a>
        <nav class="vertnav navbar navbar-light">
          <!-- nav bar -->
          <div class="w-100 mb-4 d-flex">
            <a class="navbar-brand mx-auto mt-2 flex-fill text-center">
              <svg version="1.1" id="logo" class="navbar-brand-img brand-sm" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 120 120" xml:space="preserve">
                <g>
                  <polygon class="st0" points="78,105 15,105 24,87 87,87 	" />
                  <polygon class="st0" points="96,69 33,69 42,51 105,51 	" />
                  <polygon class="st0" points="78,33 15,33 24,15 87,15 	" />
                </g>
              </svg>
            </a>
          </div>
          <ul class="navbar-nav flex-fill w-100 mb-2">
            <li class="nav-item dropdown">
              <a href="#" onclick="location.href='/trading/main'" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                <i class="fe fe-home fe-16"></i>
                <span class="ml-3 item-text">Dashboard</span><span class="sr-only">(current)</span>
              </a>
            </li>
          </ul>
          <p class="text-muted nav-heading mt-4 mb-1">
            <span>Components</span>
          </p>
          <ul class="navbar-nav flex-fill w-100 mb-2">
          	<li class="nav-item dropdown">
              <a href="#charts" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                <i class="fe fe-pie-chart fe-16"></i>
                <span class="ml-3 item-text">Charts</span>
              </a>
              <ul class="collapse list-unstyled pl-4 w-100" id="charts">
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/exchange"><span class="ml-1 item-text">환율(Exchange)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/012450"><span class="ml-1 item-text">한화에어로스페이스(012450)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/009150"><span class="ml-1 item-text">삼성전기(009150)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/042700"><span class="ml-1 item-text">한미반도체(042700)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/068270"><span class="ml-1 item-text">셀트리온(068270)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/033780"><span class="ml-1 item-text">KT&G(033780)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/064350"><span class="ml-1 item-text">현대로템(064350)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/017670"><span class="ml-1 item-text">SK텔레콤(017670)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/005490"><span class="ml-1 item-text">POSCO홀딩스(005490)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/014680"><span class="ml-1 item-text">한솔케미칼(014680)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/051900"><span class="ml-1 item-text">LG생활건강(051900)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/066570"><span class="ml-1 item-text">LG전자(066570)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/108320"><span class="ml-1 item-text">LX세미콘(108320)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/047810"><span class="ml-1 item-text">한국항공우주(047810)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/000660"><span class="ml-1 item-text">SK하이닉스(000660)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/010140"><span class="ml-1 item-text">삼성중공업(010140)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/035420"><span class="ml-1 item-text">NAVER(035420)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/005380"><span class="ml-1 item-text">현대차(005380)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/009830"><span class="ml-1 item-text">한화솔루션(009830)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/052690"><span class="ml-1 item-text">한전기술(052690)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/034020"><span class="ml-1 item-text">두산에너빌리티(034020)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/005070"><span class="ml-1 item-text">코스모신소재(005070)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/005420"><span class="ml-1 item-text">코스모화학(005420)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/028050"><span class="ml-1 item-text">삼성엔지니어링(028050)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/137310"><span class="ml-1 item-text">에스디바이오센서(137310)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/005930"><span class="ml-1 item-text">삼성전자(005930)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/017960"><span class="ml-1 item-text">한국카본(017960)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/075580"><span class="ml-1 item-text">세진중공업(075580)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link pl-3" href="/trading/003490"><span class="ml-1 item-text">대한항공(003490)</span></a>
                </li>
              </ul>
            </li>
            <li class="nav-item dropdown">
              <a href="#" onclick="location.href='/trading/news'" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                <i class="fe fe-credit-card fe-16"></i>
                <span class="ml-3 item-text">News</span>
              </a>
            </li>
          </ul>
          <p class="text-muted nav-heading mt-4 mb-1">
            <span>Documentation</span>
          </p>
          <ul class="navbar-nav flex-fill w-100 mb-2">
            <li class="nav-item w-100">
              <a class="nav-link" href="../docs/index.html">
                <i class="fe fe-help-circle fe-16"></i>
                <span class="ml-3 item-text">Getting Start</span>
              </a>
            </li>
          </ul>
        </nav>
      </aside>
      <main role="main" class="main-content">
        <div class="container-fluid">
          <div class="row justify-content-center">
            <div class="col-12">
              <div class="row align-items-center mb-2">
                <div class="col">
                  <h2 class="h5 page-title">Welcome!</h2>
                </div>
                <div class="col-auto">
                  <form class="form-inline">
                    <div class="form-group">
                      <button type="button" class="btn btn-sm" onClick="window.location.reload()"><span class="fe fe-refresh-ccw fe-16 text-muted"></span></button>
                    </div>
                  </form>
                </div>
              </div>
              <!-- linechart -->
              <div class="my-4">
				<!-- Card Body -->
				<div class="card-body">
					<div class="chart-area">
						<canvas id="myChart" width="722" height="400"></canvas>
					</div>
				</div>
			  </div>
              <div class="row">
                <div class="col-md-6">
                  <div class="card shadow mb-4">
                    <div class="card-header">
                      <strong>Goal</strong>
                    </div>
                    <div class="card-body px-4">
                      <div class="row border-bottom">
	                    <c:forEach var="deal" items="${dealo}" varStatus="status">
	                        <div class="col-4 text-center mb-3">
	                          <p class="mb-1 small text-muted">매도/매수</p>
	                          <span class="h3">${deal.bs_type }</span><br />
	                        </div>
	                        <div class="col-4 text-center mb-3">
	                          <p class="mb-1 small text-muted">체결가</p>
	                          <span class="h3">${deal.hbuy }</span><br />
	                        </div>
	                        <div class="col-4 text-center mb-3">
	                          <p class="mb-1 small text-muted">수익률</p>
	                          <span class="h3">${deal.ror }</span><br />
	                        </div>
                        	</c:forEach>
                      </div>
                      <table class="table table-borderless mt-3 mb-1 mx-n1 table-sm">
                        <thead>
                          <tr>
                            <th class="text-center">매도/매수</th>
                            <th class="text-center">체결가</th>
                            <th class="text-center">체결량</th>
                            <th class="text-center">수익률</th>
                          </tr>
                        </thead>
                        <tbody>
                        	 <c:forEach var="deal" items="${deal}" varStatus="status">
	                          <tr>
	                            <td class="text-center">${deal.bs_type }</td>
	                            <td class="text-center">${deal.hbuy }</td>
	                            <td class="text-center">${deal.mvolume }</td>
	                            <td class="text-center">${deal.ror }</td>
	                          </tr>
                         </c:forEach>
                        </tbody>
                      </table>
                    </div> <!-- .card-body -->
                  </div> <!-- .card -->
                </div> <!-- .col -->
                <div class="col-md-6">
                  <div class="card shadow mb-4">
                    <div class="card-header">
                      <strong class="card-title">Top Selling</strong>
                    </div>
                    <div class="card-body">
                      <div class="list-group list-group-flush my-n3">
                       <c:forEach var="jusik" items="${jusikh}" varStatus="status">
                        <div class="list-group-item">
                          <div class="row align-items-center">
                            <div class="col">
                              <strong>한화에어로스페이스</strong>
                              <div class="my-0 text-muted small">012450</div>
                            </div>
                            <div class="col-auto">
                              <div class="my-0 text-muted small">현재가</div>
                              <strong>₩${jusik.price}</strong>
                            </div>
                            <div class="col-auto">
                              <div class="my-0 text-muted small">거래량</div>
                              <strong>${jusik.volume}</strong>
                            </div>
                            <div class="col-auto">
                              <div class="my-0 text-muted small">체결수량</div>
                              <strong>${jusik.cvolume}</strong>
                            </div>
                          </div>
                        </div>
                        	</c:forEach>
                        	<c:forEach var="jusik" items="${jusiks}" varStatus="status">
                        <div class="list-group-item">
                          <div class="row align-items-center">
                            <div class="col">
                              <strong>삼성전기</strong>
                              <div class="my-0 text-muted small">009150</div>
                            </div>
                            <div class="col-auto">
                              <div class="my-0 text-muted small">현재가</div>
                              <strong>₩${jusik.price}</strong>
                            </div>
                            <div class="col-auto">
                              <div class="my-0 text-muted small">거래량</div>
                              <strong>${jusik.volume}</strong>
                            </div>
                            <div class="col-auto">
                              <div class="my-0 text-muted small">체결수량</div>
                              <strong>${jusik.cvolume}</strong>
                            </div>
                          </div>
                        </div>
                        	</c:forEach>
                        	<c:forEach var="jusik" items="${jusikm}" varStatus="status">
                        <div class="list-group-item">
                          <div class="row align-items-center">
                            <div class="col">
                              <strong>한미반도체</strong>
                              <div class="my-0 text-muted small">042700</div>
                            </div>
                            <div class="col-auto">
                              <div class="my-0 text-muted small">현재가</div>
                              <strong>₩${jusik.price}</strong>
                            </div>
                            <div class="col-auto">
                              <div class="my-0 text-muted small">거래량</div>
                              <strong>${jusik.volume}</strong>
                            </div>
                            <div class="col-auto">
                              <div class="my-0 text-muted small">체결수량</div>
                              <strong>${jusik.cvolume}</strong>
                            </div>
                          </div>
                        </div>
                        </c:forEach>
                        <c:forEach var="jusik" items="${jusikt}" varStatus="status">
                        <div class="list-group-item">
                          <div class="row align-items-center">
                            <div class="col">
                              <strong>셀트리온</strong>
                              <div class="my-0 text-muted small">068270</div>
                            </div>
                            <div class="col-auto">
                              <div class="my-0 text-muted small">현재가</div>
                              <strong>₩${jusik.price}</strong>
                            </div>
                            <div class="col-auto">
                              <div class="my-0 text-muted small">거래량</div>
                              <strong>${jusik.volume}</strong>
                            </div>
                            <div class="col-auto">
                              <div class="my-0 text-muted small">체결수량</div>
                              <strong>${jusik.cvolume}</strong>
                            </div>
                          </div>
                        </div>
                        </c:forEach>
                      </div> <!-- / .list-group -->
                    </div> <!-- / .card-body -->
                  </div> <!-- .card -->
                </div> <!-- .col -->
              </div> <!-- .row -->
             </div>
            </div>
           </div>
      </main> <!-- main -->
    </div> <!-- .wrapper -->
    <script>
	    // myChart를 fetchData 함수 내에서 선언
	   (function() {
		const config = {
			type: 'line',
			data: {
				labels: [],
				datasets: [
					{
						label: 'kospi',
						data: [],
						backgroundColor: 'rgb(27, 104, 255)',
						borderColor: 'rgb(27, 104, 255)',
						borderWidth: 1,
						radius: 0,
						pointStyle: false
					}
				]
			},
			options: {
				scales: {
					x: {
						type: 'time',
						/* time: {
							unit: 'day',
							displayFormats: {
								day: 'DD'
							}
						} */
						time: {
							unit: 'second',
							displayFormats: {
								second : 'h:mm:ss a'
							}
						}
					},
					y: {
						position: 'left'
					}
				}
			}
		};
	
		const myChart = new Chart(
			document.getElementById('myChart'),
			config
		);
	
		async function fetchData() {
			const response = await fetch('/trading/kospi');
			const dataFromDB = await response.json();
	
			const labels = dataFromDB.map((data) => data.ymd_time);
			const dataset2 = dataFromDB.map((data) => data.kospi);
			
			myChart.data.labels = labels;
			myChart.data.datasets[0].data = dataset2;
			myChart.update();
		}
	
		fetchData(); // Initial data fetch
		// Update data at desired time interval
		
		setInterval(fetchData, 5000); // For example, update data every 5 seconds
		
	   })();
	</script>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/popper.min.js"></script>
	<script src="/js/moment.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/simplebar.min.js"></script>
	<script src='/js/daterangepicker.js'></script>
	<script src='/js/jquery.stickOnScroll.js'></script>
	<script src="/js/tinycolor-min.js"></script>
	<script src="/js/d3.min.js"></script>
	<script src="/js/topojson.min.js"></script>
    <script src="/js/datamaps.custom.js"></script> -->
	<script src="/js/gauge.min.js"></script>
	<script src="/js/jquery.sparkline.min.js"></script>
  </body>
</html>