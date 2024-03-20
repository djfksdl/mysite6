<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<!-- Axios 라이브러리 포함 -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
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
		                  <div class="view" id="t-${gList.no}">
		                     <img class="imgItem" src="${pageContext.request.contextPath}/upload/${gList.saveName}" data-no="${gList.no}">
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
			   			<button type="button" id="deleteBtn">삭제</button>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
   document.addEventListener("DOMContentLoaded",function(){
	  
	   //등록버튼 눌렀을때- 모달창 띄우기 --> 로그인안하면 안뜨는데 밑에꺼랑 순서를 바꾸던지(근데 이건 별로 안좋음, 계속 오류가 나오니까!), if문을 걸어서 로그인안했을때는 작동을 안하게 하면 된다. 
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
	  
	   
	   //이미지 눌렀을때 
	   let viewArea = document.querySelector("#viewArea");
	   console.log(viewArea);
	   viewArea.addEventListener("click",function(event){
		  console.log("이미지 눌렀을때");
		  //위임
		  console.log(event.target);
		  let imgTag = event.target.tagName;
		  console.log(imgTag);
		  
		  //모달창 열기
		  if(imgTag == "IMG"){
			let viewModal = document.querySelector("#viewModal");
		  	//console.log("위임완료");
			viewModal.style.display="block";
			
			//no값 받아오기
			//console.log("event.target.dataset:"+event.target.dataset);
			let no = event.target.dataset.no;
			//console.log(event.target.dataset.no);
			let noTag = document.querySelector('[name=no]');
			noTag.value= no;
			
			//이미지 받기- no로 보내기
			 axios({
				 method: 'get',           // put, post, delete                   
				url: '${pageContext.request.contextPath}/api/gallerys',
				 headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
				params: {no:no}, //get방식 파라미터로 값이 전달- 객체형태로 전달해야해서 하나만 보내더라도 이렇게 보내야함. --ajax로 치는거나 주소로 보내는거나 똑같음. 키값,값이니까 이렇게 보내야 뒤에 붙는다.
				//data: guestbookVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
				
				responseType: 'json' //수신타입
				}).then(function (response) {
				 console.log(response); //수신데이타
				 console.log(response.data);
				 
				 //이미지,내용 넣기
				 let contextPath = '${pageContext.request.contextPath}';
				 let viewModelImg= document.querySelector("#viewModelImg");
				 viewModelImg.src = contextPath + '/upload/' + response.data.saveName; 
				 
				 let viewModelContent= document.querySelector("#viewModelContent");
				 viewModelContent.textContent=response.data.content;
				 
				}).catch(function (error) {
				 console.log(error);
				 });

			
		  }//모달창 열기 끝
		  
		  //x눌렀을때 모달창 닫기
		  let closeBtn =document.querySelector("#viewModal .closeBtn");
		   closeBtn.addEventListener("click",function(){
			   viewModal.style.display = "none";
		   });

		
	 	//삭제하기
		let deleteBtn= document.querySelector("#deleteBtn");
		deleteBtn.addEventListener("click",function(){
			console.log("삭제버튼");
			let noTag = document.querySelector('[name=no]');
			//console.log(noTag.value);
			let no = noTag.value;

			//서버로 데이터 전송
			 axios({
				 method: 'delete',           // put, post, delete                   
				url: '${pageContext.request.contextPath}/api/gallerys/'+no,
				 headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
				//params: {no:no}, //get방식 파라미터로 값이 전달-> 뒤에 ?no=3으로 붙어서 간다. url에서 +no 써줬으면 params으로 굳이 보낼 필요없다. 만약 쓰면 두번 보낸거임
				//data: guestbookVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
				
				
				responseType: 'json' //수신타입
				}).then(function (response) {
				 console.log(response); //수신데이타
				 console.log(response.data);
				 
				 if(response.data == 1){
					console.log("삭제 성공");
					
					//리스트 삭제하기
					let tagId= "#t-"+ no;
					console.log(tagId);
					let removeImg = document.querySelector(tagId);
					console.log(removeImg);
					removeImg.remove();
					
					//모달창 끄기
					let viewModal = document.querySelector("#viewModal");
					viewModal.style.display="none";
					
				 }else{
					 //아무것도 안함
				 }
				}).catch(function (error) {
				 console.log(error);
				 });
						
		})//삭제하기 끝
		  
	   });//이미지 눌렀을때 끝

   });//스크립트 끝
 </script>
</html>

