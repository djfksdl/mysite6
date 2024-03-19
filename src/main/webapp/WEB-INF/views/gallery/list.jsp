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
			         <li class="last">일반 갤러리</li>
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
		                     <img class="imgItem" src="${pageContext.request.contextPath}/upload/${gList.saveName}">
		                     <!-- 반복되는 gList안에서 savaName을 뽑아 와야 리스트에 반영이 된다. attribute에 넣는건 이 곳과는 상관없음 -->
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
				<div class="Modal-top">
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
			<div class="Modal-top">
				<div class="closeBtn">×</div>
				<div class="m-header">이미지 보기</div>
			</div>
			<div class="m-body">
				<div>
				   <img id="viewModelImg" src="">
				   <!--http://localhost:8880/mysite6/upload/17109315...jpg-->
				   <!-- ajax로 처리 : 이미지출력 위치-->
				</div>
				<div>
				   <p id="viewModelContent">내용입니다.</p>
				</div>
			</div>
			<div class="m-footer">
				<input type="text" name="no" value="">
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
		   let closeBtn =document.querySelector("#addModal .closeBtn");
		   closeBtn.addEventListener("click",function(){
			   addModal.style.display = "none";
		   });
					   
		   
	   });//등록버튼 눌렀을때 끝
	   
	   //이미지 눌렀을때 모달창 띄우기
	   let viewArea = document.querySelector("#viewArea");
	   viewArea.addEventListener("click",function(event){
		  //console.log("이미지 눌렀을때");
		  //위임
		  //console.log(event.target);
		  let imgTag = event.target.tagName;
		  console.log(imgTag);
		  
		  //이미지 클릭할때 모달창 나오기
		  if(imgTag == "IMG"){
			let viewModal = document.querySelector("#viewModal");
		  	//console.log("위임완료");
			viewModal.style.display="block";
			
			//이미지 받기- no값 받아와야함+주소 채우기
			console.log("event.target.dataset"+event.target.dataset);
			console.log("event.target.dataset.no"+event.target.dataset.no);
			let noTag = document.querySelector('[name=no]');
			noTag.value=event.target.dataset.no;
			
			//1. 이벤트 잡기
			//2. 데이터 수집
			//3. 요청
			//(서버쪽 요청처리: 스프링)
			//4. 응답 데이터 수신
			//5. 화면반영 -> 수작업
			
			
			
		  }//이미지모달창안에서 할 수 있는 일 끝
		  
		  
		  
		  
		  
		  //x눌렀을때 모달창 닫기
		  let closeBtn =document.querySelector("#viewModal .closeBtn");
		   closeBtn.addEventListener("click",function(){
			   viewModal.style.display = "none";
		   });
		  
	   });//이미지 눌렀을때 끝
	   

	   
	   
   });//스크립트 끝
 </script>
</html>

