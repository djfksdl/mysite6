package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.TboardDao;
import com.javaex.vo.TboardVo;

@Service
public class TboardService {

	@Autowired
	private TboardDao tboardDao;
	
	//리스트(검색X,페이징 O)
	public List<TboardVo> exeList2(int crtPage){
		System.out.println("TboardService.exeList2()");
		
		//한페이지당 출력 글갯수
		int listCnt =10;
		
		//crtPage: 음수나 0이 오면 디폴트값이 오도록 설정
		crtPage = (crtPage>0)? crtPage: (crtPage = 1);
		
		/*if(crtPage>0) {
			crtPage = crtPage;
		}else {
			crtPage = 1;
		}*/
		
		//startRowNo 구하기
		// 1->1~10, 2->11~20, 3-> 21~30
		// 1->0 10, 2->10 10 3->20,10
		// (1-1)*10 -> 0
		// (2-1)*10 -> 10
		// (3-1)*10 -> 20
		// (crtPage-1)*listCnt 
		
		int startRowNo = (crtPage-1)*listCnt;
		
		//startRowNo,listCnt Map으로 묶는다.
		//Map안에는 항상 클래스인애를 적어야함. 원래 Object썼는데 둘다 int라서 Integer 써줌.
		Map<String, Integer> limitMap = new HashMap<String , Integer>();
		limitMap.put("startRowNo", startRowNo);//키, 값
		limitMap.put("listCnt", listCnt);
		
		//dao에 전달해서 현제페이지의 리스트 10개를 받는다.
		List<TboardVo> boardList = tboardDao.boardSelectList2(limitMap);
		
		
		////////////////////////////////////////
		//페이징 계산
		////////////////////////////////////////
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//전체 글 갯수
		int totalCnt=tboardDao.selectTotalCnt();
		//System.out.println(totalCnt);
		
		//마지막 버튼 번호
		//1~5->(1,5)
		//6~10->(6,10)
		//11~15->(11,15)
		//21~25 ->(21,25)
		
		/*현재페이지 버튼갯수
		 * 1   5 =>올림(1/5) *5 --> 0.2(1)*5 => 5
		 * 2   5 =>올림(2/5) *5 --> 0.4(1)*5 => 5
		 * 3   5 =>올림(3/5) *5 --> 0.6(1)*5 => 5
		 * 4   5 =>올림(4/5) *5 --> 0.8(1)*5 => 5
		 * 5   5 =>올림(5/5) *5 -->   1(1)*5 => 5
		 * 6   5 =>올림(6/5) *5 --> 1.2(2)*5 => 10
		 * 7   5 =>올림(7/5) *5 --> 1.4(2)*5 => 10
		 * 11  5 =>올림(11/5) *5 --> 2.4(2)*5 => 15 
		*/
		
		//마지막버튼 번호
		//정수나누기 정수는 자바에선 정수로 나옴
		int endPageBtnNo= (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount ;
		
		//시작버튼 번호
		int startPageBtnNo = (endPageBtnNo-pageBtnCount)+1;
		
		//다음 화살표 유무
		boolean next = false;
		if(listCnt *endPageBtnNo < totalCnt) {// 현재 페이지당 글갯수(10) *마지막버튼 번호(5) <전체 글 갯수 102개
			next = true;
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo !=1) {
			prev = true;
		}
		
		//5개를 map으로 묶어서 controller한테 보낸다. (리턴) 값들이 boolean도 있고, int도 있기때문에 오브젝트 
		//startPageBtnNo endPageBtnNo prev next를 보내야함. ->리스트에서만 쓸거같다 = 맵으로 보내기
		//이미 return boardList가 있다. 
		Map<String , Object> pMap = new HashMap<String,Object>();
		pMap.put("boardList", boardList);//암호, 실제값. 리턴으로 보내주고있기때문에 이것도 넣어버린다.//1:06
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("prev", prev);
		pMap.put("next", next);
		
		System.out.println(pMap);
		
		return boardList;
	}
	
	//리스트(검색X,페이징 X)
	public List<TboardVo> exeList(){
		System.out.println("TboardService.exeList()");
		
		List<TboardVo> boardList = tboardDao.boardSelectList();
		
		return boardList;
		
	}
	
	
}
