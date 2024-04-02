package com.itwillbs.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.MemberVO;

//@RestController : 해당 클래스를 REST 컨트롤러의 역할 수행
//		-> 모든 메서드의 동작을 @ResponseBody 생략해 놓은 상태




@RestController
public class SampleRestController1 {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleRestController1.class);
	
	// http://localhost:8088/test1
	@GetMapping(value = "/test1")
	//@ResponseBody : : 데이터를 뷰페이지가 아니라 데이터(리소스)로 처리
	public /* @ResponseBody */ String test() {
		logger.debug(" test() 실행! ");
		
		
		return "ITWILL";
	}
	
	// http://localhost:8088/test2
	@GetMapping(value = "/test2")
	public void test2() {
		logger.debug(" test2() 실행! ");
		
	}
	
	// 정수형 데이터타입 (X) -> jackson-databind 라이브러리 설치 -> (O)
	// *jackson-databind 라이브러리 : 객체 -> JSON변환/JSON -> 객체 변환
	
	// http://localhost:8088/test3
	@GetMapping(value = "/test3")
	public Timestamp test3() {
		logger.debug(" test3() 실행! ");
		
		// return 100; //int
		//return 11.111; //double
		//return true; //boolean
		//return new Date(); //Date
		return new Timestamp(System.currentTimeMillis());
	}
	
	
	// http://localhost:8088/test4
	@GetMapping(value = "/test4")
	public MemberVO test4() {
		logger.debug(" test4() 실행! ");
		
		MemberVO vo = new MemberVO();
		vo.setUserid("admin");
		vo.setUserpw("12345");
		vo.setUsername("관리자");
		vo.setUseremail("admin@naver.com");
		vo.setRegdate(new Timestamp(System.currentTimeMillis()));
		vo.setUpdatedate(new Timestamp(System.currentTimeMillis()));
		
		return vo;
	}
	
	// JSON 배열(List)
	// http://localhost:8088/test5
	@GetMapping(value = "/test5")
	public List<MemberVO> test5() {
		logger.debug(" test5() 실행! ");
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		for(int i = 0;i<10;i++) {
			MemberVO vo = new MemberVO();
			vo.setUserid("admin");
			vo.setUserpw("12345");
			vo.setUsername("관리자");
			vo.setUseremail("admin@naver.com");
			vo.setRegdate(new Timestamp(System.currentTimeMillis()));
			vo.setUpdatedate(new Timestamp(System.currentTimeMillis()));
			
			list.add(vo);
		}
		
		
		return list;
	}	

	// http://localhost:8088/test6
	@GetMapping(value = "/test6")
	public Map<Integer, MemberVO> test6() {
		logger.debug(" test6() 실행! ");
		
		Map<Integer, MemberVO> memberMap = new HashMap<Integer, MemberVO>();
		
		for(int i = 0;i<10;i++) {
			MemberVO vo = new MemberVO();
			vo.setUserid("admin");
			vo.setUserpw("12345");
			vo.setUsername("관리자");
			vo.setUseremail("admin@naver.com");
			vo.setRegdate(new Timestamp(System.currentTimeMillis()));
			vo.setUpdatedate(new Timestamp(System.currentTimeMillis()));
			
			memberMap.put(i, vo);
		}
		
		
		return memberMap;
	}	
	
	// http://localhost:8088/test7
	// http://localhost:8088/test7?bno=1000
	// http://localhost:8088/test7/1000
	// http://localhost:8088/test7/5555
	// http://localhost:8088/test7/itwill
	@GetMapping(value = "/test7/{bno}")
	public String test7(@PathVariable("bno") String bno/* @ModelAttribute int bno *//* @RequestParam int bno */) throws Exception{
		logger.debug(" test7 실행 ");
		
		
		return bno;
	}
	
	@PostMapping(value = "/test/add")
	public void testADD(@RequestBody MemberVO vo)throws Exception{
		//@RequestBody : JSON타입의 문자열 데이터를 객체의 형태로 형변환
		logger.debug(" testADD() 실행 ");
		logger.debug("vo : "+vo);
		// 서비스 객체주입 -> 메서드 호출 -> 결과리턴 -> 뷰페이지 전달
		
	}
	
	// ResposeEntity 클래스 
	// : @RestController 에서 별도의 view페이지가 없는 데이터를 처리할때
	//   발생하는  예외상황을 처리하기 위한 클래스
	
	// * HTTP 상태코드(status)
	// https://developer.mozilla.org/ko/docs/Web/HTTP/Status
	
	// 100번대 : 정보응답/ 현재 데이터의 처리 중인 상태
	// - 100 : 데이터의 일부를 서버가 받은 상태
	
	// 200번대 : 성공응답/ 정상적인 응답상태
	// - 200 : 에러 없이 정상처리
	// - 201 : 요청의 처리가 성공적, 결과로 새로운 리소스가 생성됨
	// - 204 : 정상처리 되었으나, 서버에서 보내줄 데이터가 없음
	
	// 300번대 : 리다이렉션 메세지/ 다른 URL 처리상태
	// - 301 : 요청된 페이지가 새 URL으로 변경
	// - 304 : 이미 기존의 데이터정보와 변경이 없음
	
	// 400번대 : 클라이언트 오류응답 / 서버에서 인식이 불가능
	// - 400 : 전송한 데이터에 문제가 있어서 서버가 인지 불가(ajax)
	// - 401 : 인증되지 않음
	// - 403 : 서버에서 허락X(컨텐츠 접근 권한이 없음)
	// - 404 : URL에 해당하는 리소스를 찾을 수 없음
	// - 405/406 : 전송 방식에 따른 접근 허용 여부(REST)
	
	// 500번대 : 서버 오류 응답 / 서버 내부의 문제
	// - 500 : 서버 내부에서 문제가 발생(컴파일 에러/실행시 에러)
	// - 502 : 게이트웨이/프록시 상태 문제
	// - 503 : 서버가 요청을 처리할 상태X(일시적 과부하/서비스 중단)
	
	// => ResponseEntity객체가 처리 결과의 데이터 + HTTP 상태코드 전달
	
//	// http://localhost:8088/test8
//	@GetMapping(value = "/test8")
//	public String test8()throws Exception{
//		logger.debug(" test8 호출");
//		
//		return "ITWILL"; // 처리되는 데이터만 확인, 상태확인 불가능
//		
//	}
	
	// http://localhost:8088/test8
	@GetMapping(value = "/test8")
	public ResponseEntity<Void> test8()throws Exception{
		logger.debug(" test8 호출");
		
		//return "ITWILL"; // 처리되는 데이터만 확인, 상태확인 불가능
		//return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	// http://localhost:8088/test9
	@GetMapping(value = "/test9")
	public ResponseEntity<List<MemberVO>> test9()throws Exception{
		logger.debug(" test9 호출");
		
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		MemberVO vo = null;
		for(int i = 0 ;i<10 ;i++ ) {
			vo = new MemberVO();
			vo.setUserid("itwill0"+i);
			vo.setUserpw("1234"+i);
			vo.setUsername("아이티윌0"+i);
			vo.setUseremail("itwill"+i+"@itwill.com");
			
			memberList.add(vo);
		}
		
		
		return new ResponseEntity<List<MemberVO>>(memberList,HttpStatus.OK);
	}
	
	// http://localhost:8088/test10
	@GetMapping(value = "/test10")
	public ResponseEntity test10()throws Exception{
		logger.debug(" test10 호출");
		
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String result = "<script>";
		result += " alert('테스트 메세지'); ";
		result += " location.href='http://www.naver.com';";
		result += "</script>";
		
		
		return new ResponseEntity(result,respHeaders,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}// controller
