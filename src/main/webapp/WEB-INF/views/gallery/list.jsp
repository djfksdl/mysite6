<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">

</head>

	
<body>
	<div id="wrap">
	
	<!-- header -->
	<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
	<!--// header -->
	
	<div id="container" class="clearfix">
		<div id="aside">
			<h2>갤러리</h2>
			<ul>
			   <li><a href="">일반갤러리</a></li>
			   <li><a href="">파일첨부연습</a></li>
			</ul>
		</div>
	   <!-- //aside -->
	   <div id="content">
			<div id="content-head">
			   <h3>갤러리</h3>
			   <div id="location">
			      <ul>
			         <li>홈</li>
			         <li>갤러리</li>
			         <li class="last">갤러리</li>
			      </ul>
			   </div>
			   <div class="clear"></div>
			</div>
	      <!-- //content-head -->
	
	
			<div id="gallery">
	         <div id="list">  	
	         	<c:if test="${authUser.no != null }">
	            	<button id="btnImgUpload">이미지올리기</button>
	            </c:if>
	            <c:if test="${authUser.no == null }"></c:if>
	            
	            <div class="clear"></div>
	
	            <ul id="viewArea">
	
	               <!-- 이미지반복영역 -->
	               <c:forEach items="${galleryList}" var="gList">
		               <li>
		                  <div class="view">
		                     <img class="imgItem" src="${pageContext.request.contextPath}/upload/${saveName}">
		                     <div class="imgWriter">
		                        작성자: <strong>${gList.name}</strong>
		                     </div>
		                  </div>
		               </li>
	               </c:forEach>
	               <!-- 이미지반복영역 -->
	
	            </ul>
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
	
	<!-- 이미지등록 팝업(모달)창 -->
	<div id="addModal" class="modal">
		<div class="modal-content">
			<form action="${pageContext.request.contextPath}/gallery/upload" method="post" enctype="multipart/form-data">
				<div class="addModal-top">
					<div class="closeBtn">×</div>
					<div class="m-header">이미지 등록</div>
				</div>
				<div class="m-body">
					<div>
					   <label class="form-text">글작성</label> <input id="addModalContent" type="text" name="content" value="">
					</div>
					<div class="form-group">
					   <label class="form-text">이미지선택</label> <input id="file" type="file" name="file" value="">
					</div>
				</div>
				<div class="m-footer">
					<button type="submit">등록</button>
				</div>
			</form>
		</div>
	</div>
	
	
	<!-- 이미지보기 팝업(모달)창 -->
	<div id="viewModal" class="modal">
		<div class="modal-content">
			<div class="closeBtn">×</div>
			<div class="m-header">간단한 타이틀</div>
			<div class="m-body">
				<div>
				   <img id="viewModelImg" src="">
				   <!-- ajax로 처리 : 이미지출력 위치-->
				</div>
				<div>
				   <p id="viewModelContent"></p>
				</div>
			</div>
			<div class="m-footer">
			   <button>삭제</button>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
   document.addEventListener("DOMContentLoaded",function(){
	   //등록버튼 눌렀을때- 모달창 띄우기
	   let btnImgUpload = document.querySelector("#btnImgUpload");
	   btnImgUpload.addEventListener("click",function(){
			//console.log("등록버튼 작동");
		   
		   //모달창 보이기
		   let addModal = document.querySelector("#addModal");
		   addModal.style.display = "block";
		   
		   //모달창 끄기
		   let closeBtn =document.querySelector(".closeBtn");
		   closeBtn.addEventListener("click",function(){
			   addModal.style.display = "none";
		   });
					   
		   
	   });//등록버튼 눌렀을때 끝
	   
	   //사진 등록하기
	   
	   
   });//스크립트 끝
 </script>
</html>

