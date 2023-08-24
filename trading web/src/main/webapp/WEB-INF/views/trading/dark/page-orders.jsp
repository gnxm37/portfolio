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
              <h2 class="h3 mb-3 page-title">News</h2>
              <div class="row mb-4 items-align-center">
                <div class="col-md">
                  <ul class="nav nav-pills justify-content-start">
                    <li class="nav-item">
                      <a class="nav-link active bg-transparent pr-2 pl-0 text-primary" href="#">All <span class="badge badge-pill bg-primary text-white ml-2">${list.size()}</span></a>
                    </li>
                  </ul>
                </div>
                <div class="col-md-auto ml-auto text-right">
                  <button type="button" class="btn" onClick="window.location.reload()"><span class="fe fe-refresh-ccw fe-16 text-muted"></span></button>
                </div>
              </div>
              <table class="table border table-hover bg-white">
                <thead>
                  <tr role="row">
                    <th>#</th>
                    <th>Date</th>
                    <th>Title</th>
                  </tr>
                </thead>
		 	    <tbody>
		         <c:forEach items="${items}" var="item">
            		<tr>
             	       <td>${item.number}</td>
                   	   <td>${item.newsdates}</td>
                   	   <td><a href="/trading/newscontent?number=${item.number}" style="text-decoration:none" onmouseover="this.style.fontWeight='bold'" onmouseout="this.style.fontWeight=''"><font color=#000000>${item.newstitles}</font></a></td>
                 	</tr>
      			  </c:forEach>
                </tbody>
    			<%-- 페이지 처리를 위한 변수들 --%>
    			<c:set var="currentPage" value="${param.page != null ? param.page : 1}" />
    			<c:set var="itemsPerPage" value="1000" />
              </table>
	          <%-- 페이징 처리를 위한 페이지 링크 생성 --%>
			   <nav aria-label="Table Paging" class="my-3">
			        <ul class="pagination justify-content-end mb-0">
			            <li class="page-item">
			                <%-- 이전 페이지로 이동하는 링크 --%>
			                <c:if test="${currentPage >= 1}">
			                    <a class="page-link" href="news?page=${currentPage - 1}">Previous</a>
			                </c:if>
			            </li>
			            <%-- 페이지 번호를 표시하는 링크 --%>
			            <c:forEach begin="0" end="${totalPages - 1}" varStatus="status">
			                <li class="page-item ${status.index + 1 == currentPage ? 'active' : ''}">
			                    <a class="page-link" href="news?page=${status.index + 1}">${status.index + 1}</a>
			                </li>
			            </c:forEach>
			            <li class="page-item">
			                <%-- 다음 페이지로 이동하는 링크 --%>
			                <c:if test="${currentPage <= totalPages}">
			                    <a class="page-link" href="news?page=${currentPage + 1}">Next</a>
			                </c:if>
			            </li>
			        </ul>
			   </nav>
            </div>
          </div> <!-- .row -->
        </div> <!-- .container-fluid -->
      </main> <!-- main -->
    </div> <!-- .wrapper -->
<!--     <script src="/js/jquery.min.js"></script>
    <script src="/js/popper.min.js"></script>
    <script src="/js/moment.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/simplebar.min.js"></script>
    <script src='/js/daterangepicker.js'></script>
    <script src='/js/jquery.stickOnScroll.js'></script>
    <script src="/js/tinycolor-min.js"></script>
    <script src="/js/config.js"></script>
    <script src='/js/jquery.mask.min.js'></script>
    <script src='/js/select2.min.js'></script>
    <script src='/js/jquery.steps.min.js'></script>
    <script src='/js/jquery.validate.min.js'></script>
    <script src='/js/jquery.timepicker.js'></script>
    <script src='/js/dropzone.min.js'></script>
    <script src='/js/uppy.min.js'></script>
    <script src='/js/quill.min.js'></script> -->
    <script>
      $('.select2').select2(
      {
        theme: 'bootstrap4',
      });
      $('.select2-multi').select2(
      {
        multiple: true,
        theme: 'bootstrap4',
      });
      $('.drgpicker').daterangepicker(
      {
        singleDatePicker: true,
        timePicker: false,
        showDropdowns: true,
        locale:
        {
          format: 'MM/DD/YYYY'
        }
      });
      $('.time-input').timepicker(
      {
        'scrollDefault': 'now',
        'zindex': '9999' /* fix modal open */
      });
      /** date range picker */
      if ($('.datetimes').length)
      {
        $('.datetimes').daterangepicker(
        {
          timePicker: true,
          startDate: moment().startOf('hour'),
          endDate: moment().startOf('hour').add(32, 'hour'),
          locale:
          {
            format: 'M/DD hh:mm A'
          }
        });
      }
      var start = moment().subtract(29, 'days');
      var end = moment();

      function cb(start, end)
      {
        $('#reportrange span').jsp(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
      }
      $('#reportrange').daterangepicker(
      {
        startDate: start,
        endDate: end,
        ranges:
        {
          'Today': [moment(), moment()],
          'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
          'Last 7 Days': [moment().subtract(6, 'days'), moment()],
          'Last 30 Days': [moment().subtract(29, 'days'), moment()],
          'This Month': [moment().startOf('month'), moment().endOf('month')],
          'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
      }, cb);
      cb(start, end);
      $('.input-placeholder').mask("00/00/0000",
      {
        placeholder: "__/__/____"
      });
      $('.input-zip').mask('00000-000',
      {
        placeholder: "____-___"
      });
      $('.input-money').mask("#.##0,00",
      {
        reverse: true
      });
      $('.input-phoneus').mask('(000) 000-0000');
      $('.input-mixed').mask('AAA 000-S0S');
      $('.input-ip').mask('0ZZ.0ZZ.0ZZ.0ZZ',
      {
        translation:
        {
          'Z':
          {
            pattern: /[0-9]/,
            optional: true
          }
        },
        placeholder: "___.___.___.___"
      });
      // editor
      var editor = document.getElementById('editor');
      if (editor)
      {
        var toolbarOptions = [
          [
          {
            'font': []
          }],
          [
          {
            'header': [1, 2, 3, 4, 5, 6, false]
          }],
          ['bold', 'italic', 'underline', 'strike'],
          ['blockquote', 'code-block'],
          [
          {
            'header': 1
          },
          {
            'header': 2
          }],
          [
          {
            'list': 'ordered'
          },
          {
            'list': 'bullet'
          }],
          [
          {
            'script': 'sub'
          },
          {
            'script': 'super'
          }],
          [
          {
            'indent': '-1'
          },
          {
            'indent': '+1'
          }], // outdent/indent
          [
          {
            'direction': 'rtl'
          }], // text direction
          [
          {
            'color': []
          },
          {
            'background': []
          }], // dropdown with defaults from theme
          [
          {
            'align': []
          }],
          ['clean'] // remove formatting button
        ];
        var quill = new Quill(editor,
        {
          modules:
          {
            toolbar: toolbarOptions
          },
          theme: 'snow'
        });
      }
      // Example starter JavaScript for disabling form submissions if there are invalid fields
      (function()
      {
        'use strict';
        window.addEventListener('load', function()
        {
          // Fetch all the forms we want to apply custom Bootstrap validation styles to
          var forms = document.getElementsByClassName('needs-validation');
          // Loop over them and prevent submission
          var validation = Array.prototype.filter.call(forms, function(form)
          {
            form.addEventListener('submit', function(event)
            {
              if (form.checkValidity() === false)
              {
                event.preventDefault();
                event.stopPropagation();
              }
              form.classList.add('was-validated');
            }, false);
          });
        }, false);
      })();
    </script>
    <script>
      var uptarg = document.getElementById('drag-drop-area');
      if (uptarg)
      {
        var uppy = Uppy.Core().use(Uppy.Dashboard,
        {
          inline: true,
          target: uptarg,
          proudlyDisplayPoweredByUppy: false,
          theme: 'dark',
          width: 770,
          height: 210,
          plugins: ['Webcam']
        }).use(Uppy.Tus,
        {
          endpoint: 'https://master.tus.io/files/'
        });
        uppy.on('complete', (result) =>
        {
          console.log('Upload complete! We’ve uploaded these files:', result.successful)
        });
      }
    </script>
    <script src="/js/apps.js"></script>
  </body>
</html>