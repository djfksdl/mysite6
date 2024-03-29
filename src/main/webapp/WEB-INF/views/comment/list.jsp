<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글게시판</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!--// header -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>게시판</h2>
				<ul>
					<li><a href="">일반게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/comment">댓글게시판</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>댓글게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">댓글게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="board">
					<div id="list">
						<form action="" method="">
							<div class="form-group text-right">
								<input type="text">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>댓글쓰기</th>
									<th>no</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${cList}" var="commentVo">
									<tr>
										<c:if test="${commentVo.depth != 0 }">
											<td>${commentVo.no}</td>
											<td class="text-left">
												<c:forEach var="i" begin="1" end="${commentVo.depth}">
										            &nbsp;&nbsp;
										        </c:forEach>
										        &#8627;	<a href=""> ${commentVo.title}</a>
											</td>
											<td>${commentVo.name}</td>
											<td>${commentVo.hit}</td>
											<td>${commentVo.reg_date}</td>
											<c:if test="${authUser != null }">
												<td><a href="${pageContext.request.contextPath}/comment/wform?group_no=${commentVo.group_no}&order_no=${commentVo.order_no}&depth=${commentVo.depth}&user_no=${authUser.no}">[댓글쓰기]</a></td>
											</c:if>
											<c:if test="${authUser == null }">
												<td></td>
											</c:if>
											<td>g: ${commentVo.group_no}, o: ${commentVo.order_no}, d: ${commentVo.depth}</td>
										</c:if>
										<c:if test="${commentVo.depth == 0 }">
											<td>${commentVo.no}</td>
											<td class="text-left"><a href="">${commentVo.title}</a></td>
											<td>${commentVo.name}</td>
											<td>${commentVo.hit}</td>
											<td>${commentVo.reg_date}</td>
											<c:if test="${authUser != null }">
												<td><a href="${pageContext.request.contextPath}/comment/wform?group_no=${commentVo.group_no}&order_no=${commentVo.order_no}&depth=${commentVo.depth}&user_no=${authUser.no}">[댓글쓰기]</a></td>
											</c:if>
											<c:if test="${authUser == null }">
												<td></td>
											</c:if>
											<td>g: ${commentVo.group_no}, o: ${commentVo.order_no}, d: ${commentVo.depth}</td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
								
								
								<div id="paging">
												<ul>
													<li><a href="">◀</a></li>
								<li><a href="">1</a></li>
								<li><a href="">2</a></li>
								<li><a href="">3</a></li>
								<li><a href="">4</a></li>
								<li class="active"><a href="">5</a></li>
								<li><a href="">6</a></li>
								<li><a href="">7</a></li>
								<li><a href="">8</a></li>
								<li><a href="">9</a></li>
								<li><a href="">10</a></li>
								<li><a href="">▶</a></li>
							</ul>
							
							
							<div class="clear"></div>
						</div>
					
					
										
										</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->
		

		<!-- footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!--// footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
