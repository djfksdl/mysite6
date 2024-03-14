<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<!-- Axios 라이브러리 포함: 원래 위에 있으면 다 받을때까지 멈추지만 일단 당분간 여기 쓰기 -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<style>
	.modal{
		display:none;
	}
	.modal .modal-content{
		width: 818px;
		border: 1px solid #000000;
	}
</style>
</head>

<body>
	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!--// header -->
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>ajax방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">ajax방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form id="guestForm" action="${pageContext.request.contextPath}/guest/write" method="">
					
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass"type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea id="" name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id=btnAdd type="submit">등록</button></td>
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">
						
					</form>	
					<!-- 모달 창 컨텐츠 -->
	               <div id="myModal" class="modal">
	                  <div id="guestbook" class="modal-content">
	                     <div class="closeBtn">×</div>
	                     <div class="m-header">
	                        패스워드를 입력하세요
	                     </div>
	                     <div class="m-body">
	                        <input class="m-password" type="password" name="password" value=""><br>
	                        <input class="m-no" type="text" name="no" value="">
	                     </div>
	                     <div class="m-footer">
	                        <button class="btnDelete" type="button">삭제</button>
	                     </div>
	                  </div>
	               </div>
					<div id="guestbookListArea">
						<!-- 방명록 글 리스트 올자리 -->
					</div>
				<!-- //guestbook -->
			
			</div>
			<!-- //content  -->
			</div>
		<!-- //container  -->

		</div>
		<!-- footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!--// footer -->
	</div>
	<!-- //wrap -->

</body>

<script>
//DOM tree가 생성되었을때
document.addEventListener("DOMContentLoaded",function(){
	//돔 트리가 생성완료 되었을때 리스트 요청 : 데이터만 받을 거야//////////////////////////
	axios({
		method: 'get', // put, post, delete 
		url: '${pageContext.request.contextPath}/api/guestbooks',
		headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
		//params: guestbookVo, //get방식 파라미터로 값이 전달
		//data: guestbookVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달 -왜지움?:옵션들 많이 쓰는거 가져옴. 안쓰는것도 있음. 
		//위까지는 요청할때
		
		//응답받을때
		responseType: 'json' //수신타입
	}).then(function (response) {//컨트롤러에서 json으로 바꿔서 바디에 붙인다고하면 여기에 들어옴
		//console.log(response); //수신데이타
		//console.log(response.data);
		
		//리스트 자리에 글을 추가한다
		for(let i = 0; i<response.data.length; i++){//컨트롤러에선 리스트쓰는데 여기선 배열씀. 기능이 비슷. 그리고 제한이 js가 더 널널함.
			let guestVo = response.data[i];
			render(guestVo,"down");//1개의 글을 render()에게 전달 -> render()는 리스트위치에 그린다. 
		}
		
	}).catch(function (error) {
		console.log(error);
	}); 
	
	// 등록버튼 클릭했을때(데이터만 받을거야 => form을 안쓰는 이유)/////////////////////////
	let btnAdd = document.querySelector("#guestForm");
	console.log("guestForm");
	
	btnAdd.addEventListener("submit",function(event){
		//어제는 잠깐 끼어서 원래기능을 유지하려고 쓴거고, 지금은 클릭또는 서브밋으로 쓸거다. 
		//근데 폼은 항상 서브밋으로 써주면 된다. 
		//console.log("글쓰기 버튼 클릭")
		//그냥 눌러버리면 이전에 있던 주소로 날라감. 그래서 막아줘야함. 
		event.preventDefault();
		
		//폼의 데이터 수집
		let name =document.querySelector('[name="name"]').value;
		//console.log(name);
		let password =document.querySelector('[name="password"]').value;
		let content =document.querySelector('[name="content"]').value;
		
		let guestVo = {
				name: name,
				password: password,
				content: content //앞에 content는 필드명과 같아야 getter로 작용할 수 있다.
		};
		console.log(guestVo);
		//서버로 데이터 전송
		axios({
			method: 'post', // put, post, delete 
			url: '${pageContext.request.contextPath}/api/guestbooks',        //?name='+name+'+'&password,이렇게 써도됨. 11:05
			headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
			params: guestVo, //get방식 파라미터로 값이 전달 -- 또는 {}로 가져와도됨
			//data: guestbookVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달. 여긴 지금은 필요없음 -왜??
			responseType: 'json' //수신타입
			})
			.then(function (response) {
			console.log(response); //수신데이타
			console.log(response.data); //수신데이타- 여기에 우리가 넣은 값들을 볼 수 있다.
			let guestbookVo = response.data;
			
			render(guestbookVo,"up");//
			})
			.catch(function (error) {
			console.log(error);
			}); 
		
	});
	
	//모달창 호출 버튼을 클릭했을때
	let guestbookListArea = document.querySelector("#guestbookListArea");
	//새로생긴거라 원래 있던 테이블을 클릭할수있게해서 버튼에 '위임'해주기
	guestbookListArea.addEventListener("click",function(event){
		//console.log(event.target);//클릭한걸 찍어줌. 버튼클릭하면 버튼, td를 찍으면 td
		//console.log(this);//리스너를 테이블에 걸어놓았기때문에 테이블이 찍혀야함.
		//console.log(event.target.tagName);
		
		if(event.target.tagName == "BUTTON"){//버튼만 눌렸을때 ~로 조건 설정
			//console.log("모달창 보이기");	
			let modal = document.querySelector(".modal");
			modal.style.display = "block";
			
			//hidden의 value -> no값 입력
			console.log(event.target.dataset.no);
			let noTag = document.querySelector('[name="no"]');
			noTag.value=event.target.dataset.no;
		}
		
	});
	
	//모달창에 삭제 버튼을 클릭했을때 (진짜 삭제)
	let btnDelete = document.querySelector(".btnDelete");
	btnDelete.addEventListener("click",function(){
		console.log("클릭");
		
		let no = document.querySelector('.m-no').value;
		let password = document.querySelector('.m-password').value; //이전처럼 '[name="password"]'라고하면 이전에도 같은 password가 있어서 맨위의 값을 가져온다. 겹치지않는 다른값으로 불러와주기
		
		//데이터 모으고
		let guestbookVo = {
				no: no ,
				password: password
		}
		//console.log(guestbookVo);
		
		//서버요청
		axios({
			method: 'post', // put, post, delete 
			url: '${pageContext.request.contextPath}/api/guestbooks/delete',
			headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
			params: guestbookVo, //get방식 파라미터로 값이 전달
			//data: guestbookVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
			responseType: 'json' //수신타입
			})
			.then(function (response) {
			console.log("수신데이터:"+response); //수신데이타
			console.log("수신데이터값:"+response.data); //수신데이타- 여기에 우리가 넣은 값들을 볼 수 있다.
			let no = response.data;
			//console.log(typeof no);
			rmTd(no);//
			})
			.catch(function (error) {
			console.log(error);
			});
		
	});
	
	
});//document.addEventListener

//////////////함수들////////////////
//방명록 글 그리기 //옵션으로 위에 붙일지 아래로 붙일지 주면 됨. (dir은 방향이다)
function render(guestbookVo, dir){//이 안에 재료를 준다고했음. 위에서 주소를 줌. 이름은 달라도됨.
	console.log("render()");
	//console.log(guestbookVo);
	
	let guestbookListArea = document.querySelector("#guestbookListArea");
	//console.log(guestbookListArea);
	
	let str = '';
	str += '<table class="guestRead">';
	str += '	<colgroup>';
	str += '		<col style="width: 10%;">';		
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 10%;">';
	str += '	</colgroup>';
	str += '	<tr>';
	str += '		<td>'+guestbookVo.no+'</td>';
	str += '		<td>'+guestbookVo.name+'</td>';
	str += '		<td>'+guestbookVo.reg_date+'</td>';
	str += '		<td><button class=btnModal type="button" data-no="'+ guestbookVo.no +'">삭제</button></td>';
	str += '	</tr>';
	str += '	<tr>';
	str += '		<td colspan=4 class="text-left">'+guestbookVo.content+'</td>';	
	str += '	</tr>';	
	str += '</table>';	
	
	if(dir == "down"){
		guestbookListArea.insertAdjacentHTML("beforeend", str);		
	}else if(dir == "up"){
		guestbookListArea.insertAdjacentHTML("afterbegin", str);
	}
				
}
//삭제
function rmTd(no){
	let trTag = document.querySelector(".guestRead tr");
	//console.log(trTag);
	let tdTag = document.querySelector(".guestRead tr td");
	console.log(tdTag.textContent);
	if(no == tdTag.textContent){
		//console.log(trTag.parentElement.parentElement);
		trTag.parentElement.parentElement.remove();
	}
}
</script>

</html>