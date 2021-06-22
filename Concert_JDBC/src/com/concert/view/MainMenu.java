package com.concert.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.concert.model.Reservation;
import com.concert.service.MemberService;


public class MainMenu {


	/** 회원관리 서비스 클래스 */
	public MemberService service = new MemberService();
	
	/** 예약 정보 저장 */
	public ArrayList<Reservation> reservation = new ArrayList<Reservation> ();
	
	

	/** 메인 화면 */
	public void mainMenu() {
		
		printLine();
		System.out.println(" \t5조 공연 관리 프로그램");
		System.out.println();
		
		printSpace("1. 로그인");
		printSpace("2. 회원가입");
		printSpace("3. 프로그램 종료");
	
		System.out.println();
		printLine();
		
		System.out.print("원하는 메뉴의 번호를 입력하세요 : ");
		int menuNo = inputNumber();
		
		switch(menuNo) {
		
		case 1:
			System.out.println("1. 로그인");
			loginMenu();
			break;
		case 2:
			System.out.println("2. 회원가입");
			addMemberMenu();
			break;
		case 3:
			System.out.println("3. 프로그램 종료");
			System.exit(0);;
			break;
		default:
			System.out.println("[오류] 화면에 표시된 숫자만 입력해주세요.");
			break;	
		
		}
		
	}

	/**
	 *  로그인 
	 */

	public void loginMenu() {

			
		printLine();
		System.out.println(" \t\t로그인");
		System.out.println();
				
		System.out.print("아이디 : ");
		String memberId = inputString();
				
		System.out.print("비밀번호 : ");
		String memberPw = inputString();
				
		boolean result = service.login(memberId, memberPw);
		
		
		if(result) {
			memberMenu(memberId);
		}else if(result == false){
			mainMenu(); 
		}
	}
	
	/**
	 *  회원 가입
	 */
	public void addMemberMenu() {
		
		printLine();
		System.out.println(" \t\t회원가입");
		System.out.println();
		
		System.out.println("아이디 : ");
		String memberId = inputString();
		
		System.out.println("비밀번호 : ");
		String memberPw = inputString();
		
		System.out.println("이름: ");
		String name = inputString();
		
		System.out.println("휴대폰 : ");
		String mobile = inputString();
		
		boolean result = service.addMember(memberId, memberPw, name, mobile);

		if(result) {
			mainMenu();
		}else {
			mainMenu();
		}
		
	}

	public void memberMenu(String memberId) {
		printLine();

		printSpace("5조 공연 관리 프로그램");
		System.out.println();
		
		printSpace("1. 공연 정보 조회 ");
		printSpace("2. 공연 예약");
		printSpace("0. 로그아웃");
		System.out.println();
		printLine(); 
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
		
		//공연 정보 조회
		case 1:
			infoCheck(memberId); 
			break;
		
		//공연 예약
		case 2:
			infoReservation(memberId);
			break;
	
			
		//캐시 충전	
		case 4:
			checkMoney();
			break;
			
		case 0:
			System.out.println("[안내] 이용해주셔서 감사합니다.");
			mainMenu();
			break;
		
		default:
			System.out.println("[오류] 화면에 표시된 숫자만 입력해주세요.");
			memberMenu(memberId);
		}
		
	}
	
	public void checkMoney() {
		// TODO Auto-generated method stub
		
	}

	public void infoReservation(String memberId) {
	
		printLine();
		printSpace("\t< 2. 공연 예약  >");
		
		System.out.println();
		
		printSpace("1. 공연 예약 ");
		printSpace("2. 예약 내역 조회");
		printSpace("3. 뒤로 가기");
		
		int menuNo = inputNumber();
		
		if(menuNo == 3) {
			memberMenu(memberId);
		}
		
		switch(menuNo) {
		
		
		case 1 :
			System.out.println("* 관람하고 싶은 공연의 번호를 입력해주세요.*");
			System.out.println("* 뒤로가기 : '0' 입력*");
			System.out.println();
			
			//공연 정보 가져오기 
			service.getPlayPrint();
			
		    menuNo = inputNumber();
			
			//while 문 조절
			boolean loop = true;
			
			if(menuNo != 0) {
				
				//공연 번호 체크
				boolean result2 = service.reservation(menuNo);
				
				if(result2 == false) {
					infoReservation(memberId);
				}
			
					while(loop) {
					// 공연 좌석 선택
					printLine();
					printSpace("\t< 2. 공연 좌석 선택>");
					System.out.println(" *안내* 'X' 표시 되어 있는 좌석은 예약된 좌석으로, 예매가 불가합니다");
					System.out.println();
								
					System.out.println();
					System.out.println("* 원하는 좌석 타입을 선택 해주세요.*");
					System.out.println("1 : VIP석, 2: S석, 3 : R석");

					//좌석 타입 입력 			
					int type = inputNumber();
					
					switch(type) {
					
					case 1 : 
						
						System.out.println("* 원하는 좌석 번호를 입력해주세요. *");
						int seatChoice = inputNumber();
					//VIP석 
						boolean result_vip = service.userSeatChoice(menuNo, type, seatChoice, memberId);
						
						if(result_vip) {
							System.out.println("[안내] "+memberId+"님의 예약이 완료되었습니다. 좋은 관람 되세요!");
							memberMenu(memberId);
						}else {
							continue;
						}
						
					case 2 : 
						
						System.out.println("* 원하는 좌석 번호를 입력해주세요. *");
						int seatChoice2 = inputNumber();
						
					//S석	
						boolean result_s = service.userSeatChoice(menuNo, type, seatChoice2, memberId);
						
						if(result_s) {
							System.out.println("[안내] "+memberId+"님의 예약이 완료되었습니다. 좋은 관람 되세요!");
							memberMenu(memberId);
						}else {
							continue;
						}
						
					case 3 :
						
						System.out.println("* 원하는 좌석 번호를 입력해주세요. *");
						int seatChoice3 = inputNumber();
						
						boolean result_r = service.userSeatChoice(menuNo, type, seatChoice3, memberId);
						
						if(result_r) {
							System.out.println("[안내] "+memberId+"님의 예약이 완료되었습니다. 좋은 관람 되세요!");
							memberMenu(memberId);
						}else {
							continue;
						}
						
					default : 
						//좌석 타입에서 1~3 사이의 숫자 입력 안했을 경우
		                System.out.println("[오류] 잘못 입력하였습니다. 다시 입력해주세요.");
		                continue;
						}
					}
				}
			
		case 2 : 
			printLine();
			printSpace("\t< 예약 내역 조회 >");
			System.out.println("* 취소하기 : '1' 입력*");
			System.out.println("* 뒤로가기 : '0' 입력*");
			
			System.out.println();
			
			service.getReservationList(memberId);
			
			menuNo = inputNumber();
			
			switch(menuNo) {
			
			case 0 : infoReservation(memberId);
			
			case 1 : 
				printLine();
				printSpace("\t< 예약 내역 조회 >");
				System.out.println("* 취소할 공연의 번호를 입력 해주세요. *");
				
//				service.getReservationList(memberId);
				
				int delete = inputNumber();
				
				boolean result  = service.deleteReservation();
				
			
			
			default : 
				System.out.println("[오류] 잘못된 숫자를 입력하셨습니다.");
				memberMenu(memberId);
				break;
			
			}
			
			
			
		case 0 : 
			memberMenu(memberId);

		}
		
		
		
	}

	/**
	 *  공연 정보 조회 화면 
	 * @param memberId 
	 */
	public  void infoCheck(String memberId) {
		
		printLine();
		printSpace("\t < 공연 조회 >");
		System.out.println("* 현재 진행되고 있는 공연들 입니다! *");
		System.out.println("* 뒤로가기 : '0' 입력*");
		System.out.println();

	
		service.getPlayPrint();
		
		int menuNo = inputNumber();
		
		if(menuNo == 0) {
			memberMenu(memberId);
		}else {
			System.out.println("[안내] 뒤로가기를 하려면 '0'을 입력하셔야 합니다.");
			infoCheck(memberId);
		}
		
		System.out.println();
	
	}
	public  void printLine() {
		System.out.println("***********************");
	}
	
	public  void printSpace(String sub) {
		System.out.println("\t"+sub);
	}
	
	/**
	 * 문자열 입력 반환
	 * @return 입력 문자열
	 */
	public String inputString() {
		String data = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			data = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 숫자 입력 반환
	 * @return 입력 정수형 숫자
	 */
	public int inputNumber() {
		String data = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			data = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(data);
	}

	
}
