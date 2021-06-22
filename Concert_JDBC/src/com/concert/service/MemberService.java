package com.concert.service;

import java.util.ArrayList;
import java.util.Iterator;
import com.concert.model.Info;
import com.concert.model.Member;
//import com.work.exception.CommonException;
import com.concert.model.Reservation;
import com.concert.model.ReservationList;
import  com.concert.view.MainMenu;
import com.concert.dao.MemberDao;

/**
 * 서비스 클래스 
 * @author KIMJURI
 */

public class MemberService {
	
	private MemberDao dao = MemberDao.getInstance();
	
	/** 예약 도메인 클래스 */
	public Reservation res = new Reservation();

	
	
	/**  회원들을 저장하기 위한 자료 저장 구조 */
	public ArrayList<Member> list = new ArrayList<Member>();
	/** 공연 정보 저장 리스트 */
	public ArrayList<Info> play = new ArrayList<Info>();
	
	/** 공연 좌석  */
	String[] vipSeat = new String[]{"1","2","3","4","5","6","7","8","9","10"} ;
	String[] sSeat = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
	String[] rSeat = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
	
	/** 예약 정보 저장 */
	public ArrayList<Reservation> reservation = new ArrayList<Reservation> ();
	
	/** 예약 리스트  */
	public ArrayList<ReservationList> reservationList = new ArrayList<ReservationList> ();
	
	/** 기본 생성자 */
	public MemberService() {	
		}
		
	
	
	/** 현재 등록한 전체 회원 조회 */
	public ArrayList<Member> getMember() {
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		return list;
	}
	
	
	/**
	 * 로그인
	 * @param memberId
	 * @param memberPw
	 * @return 성공시 true, 실패시 false
	 * @throws CommonException 
	 */
	public boolean login(String memberId, String memberPw)  {

		boolean result = dao.login(memberId,memberPw);
		
		if(result) {
			return true;
		}else {
			System.out.println("[오류] 입력한 정보와 맞는 회원이 없습니다. 다시 시도해주세요.");
		}
		
		return false;
		
	}
	

	/**
	 * 회원 존재 유무 조회 메서드
	 * @param memberId 아이디
	 * @return 존재 시 저장 위치 인덱스 번호, 미존재시 -1
	 */
	private boolean exist(String memberId) {

		boolean result = dao.exist(memberId);
	
		return result;
	}
	
	
	/**
	 *  회원 등록 메서드
	 * @param member
	 */
	public boolean addMember(String memberId, String memberPw, String name, String mobile) {
			
		boolean result = exist(memberId);
		
		if(result) {
			System.out.println("[오류] 해당 아이디는 이미 존재하는 아이디 입니다.");
			System.out.println("[오류] 회원가입이 정상적으로 처리 되지 않았습니다.");
			
			return false;
		}else {
			
			boolean addResult = dao.add(memberId, memberPw, name, mobile);
			
			if(addResult) {
				System.out.println("[안내] : "+name+"님의 회원 가입이 정상적으로 처리 됐습니다.");
				System.out.println();
			}
			
			return false;
		}
		
	}	
	/**
	 * 회원 상세조회
	 * @param memberId 회원아이디
	 * @return 존재하면 회원객체, 미존재 null
	 * @throws RecordNotFoundException 
	 * @see exist(String)
	 */
//	public Member getMember(String memberId){
//		int index = exist(memberId);
//		if (index >= 0) {
//			return (Member)list.get(index);
//		}
//		
//		return null;
//	}

	/**
	 *  공연 정보 조회
	 */
	public ArrayList<Info> getPlayPrint() {
		
		ArrayList<Info> play = dao.playList();
		
		for(int i=0; i<play.size(); i++) {
			System.out.print((i)+1+" : ");
			System.out.print(play.get(i).getInfoName() +" / ");
			System.out.print(play.get(i).getInfoDate()+" / ");
			System.out.print(play.get(i).getInfoTime()+" / ");
			System.out.print(play.get(i).getInfoFemaleActor()+" / ");
			System.out.print(play.get(i).getInfoMaleActor()+" / ");
			
			System.out.println();
		}
		
		return play;
	}
	
	/**
	 *  공연 예약 하기 
	 *  
	 */
	
	public boolean reservation(int playNum) {
		
		String editCode = "play"+playNum;
		
		boolean result = playExist(editCode);
		
		//해당 공연이 존재하면 
		if(result) {
			 seatReservation(editCode);
			 return true;
		}else {
			//사용자가 딴 값을 입력하면 
			System.out.println("[오류] 해당 번호의 공연은 존재하지 않습니다. 화면에 나와있는 공연 숫자를 적어주세요");
			
		}
	
		return false;
		
	}


	/**
	 * 공연 존재 유무 조회 메서드
	 * @param memberId 아이디
	 * @return 존재 시 저장 위치 인덱스 번호, 미존재시 -1
	 */
	public boolean playExist(String editCode) {
	
		boolean result = dao.playExist(editCode);
		
		return result;
	}
	
	/**
	 *  공연 좌석 전체 출력 메소드
	 */
	
	public void seatReservation(String editCode) {
		
		//예약 공연 조회 
		//예약 내역 가져오기
		ArrayList<ReservationList> reservationList  = dao.rerservationSeat(editCode);
		
		//예약 배열 만들기
		String[] vip_array = new String[vipSeat.length] ;
		String[] r_array = new String[rSeat.length] ;
		String[] s_array = new String[sSeat.length] ;
		
		//배열에 예약된 좌석 번호 저장
		for(int i=0; i<reservationList.size();i++) {
			if(reservationList.get(i).getSeat().equals("v")){
				vip_array[i] = Integer.toString(reservationList.get(i).getSeatNum());
			}else if(reservationList.get(i).getSeat().equals("s")){
				s_array[i] = Integer.toString(reservationList.get(i).getSeatNum());
			}else {
				r_array[i] = Integer.toString(reservationList.get(i).getSeatNum());
			}
		}

		for(int i=0; i<10; i++) {
			for(int j = 0; j < vipSeat.length ; j++) {
				if(vip_array[i] != null  && vip_array[i].equals(vipSeat[j]))
				{
					vipSeat[j] = "X";
				}
			}	
		}

		for(int i=0; i<10; i++) {
			for(int j = 0; j < sSeat.length ; j++) {
				if(s_array[i] != null  && s_array[i].equals(sSeat[j]))
				{
					sSeat[j] = "X";
				}
			}	
		}
		
		for(int i=0; i<15; i++) {
			for(int j = 0; j < rSeat.length ; j++) {
				if(r_array[i] != null  && r_array[i].equals(rSeat[j]))
				{
					rSeat[j] = "X";
				}
			}	
		}

		
//		for(int i = 0; i < s_array.length ; i++) {
//				
//				if(s_array[i] != null) {
//					if(s_array[i]== sSeat[i]) {
//						sSeat[i] = "X";
//					}
//				}
//			}
//		
//		for(int i = 0; i < r_array.length ; i++) {
//			
//			if(r_array[i] != null) {
//				if(r_array[i] == rSeat[i]) {
//					rSeat[i] = "X";
//				}
//			}
//		}
		
//		
		System.out.print("VIP석 : ");
		for(int i=0; i<vipSeat.length;i++) {
			System.out.print("[ "+vipSeat[i]+ " ]");
		}
		System.out.println();
		
		System.out.print("S석 : ");
		for(int i=0; i<sSeat.length; i++) {
            System.out.print("[ "+sSeat[i]+ " ]");
        }
		
		System.out.println();
		
		System.out.print("R석 : ");
		for(int i=0; i<rSeat.length; i++) {
            System.out.print("[ "+rSeat[i]+ " ]");  
		}
		System.out.println();

	}
	
	
	/**
	 *  좌석 예약
	 */
	
	public boolean userSeatChoice(int playCode, int type, int seatChoice, String memberId ) {
		
		String modifyCode = "play"+ playCode;
		String seat = "";
		
		//예약 공연 조회 
		//예약 내역 가져오기
		ArrayList<ReservationList> reservationList  = dao.rerservationSeat(modifyCode);
			
		if(type == 1) {
			seat = "v";
		}else if(type == 2 ) {
			seat = "s";
		}else {
			seat = "r";
		}
			
		
		//배열에 예약된 좌석 번호 저장
		for(int i = 0; i<reservationList.size(); i++) {
			
			System.out.println();
			
			//vip시트 조회 
			if(reservationList.get(i).getSeat().equals("v")) {
				if(reservationList.get(i).getSeatNum() == seatChoice) {
					System.out.println("[오류] 선택하신 좌석은 이미 예약이 완료 된 좌석입니다.");
					return false;
				}
			}
			
			if(reservationList.get(i).getSeat().equals("s")) {
				if(reservationList.get(i).getSeatNum() == seatChoice) {
					System.out.println("[오류] 선택하신 좌석은 이미 예약이 완료 된 좌석입니다.");
					return false;
				}
			}
			
			if(reservationList.get(i).getSeat().equals("r")) {
				if(reservationList.get(i).getSeatNum() == seatChoice) {
					System.out.println("[오류] 선택하신 좌석은 이미 예약이 완료 된 좌석입니다.");
					return false;
				}
			}
		}
		
		boolean result = dao.insertReservation(modifyCode,seat,seatChoice, memberId );
		

		return result;
		
	}
	
		/**
		 *  공연 정보 조회
		 */
		public ArrayList<Reservation> getReservationList(String memberId) {
			
			//예약 내역 가져오기
			ArrayList<ReservationList> list  = dao.rerservationList(memberId);
		
			if(list.isEmpty()) {
				System.out.println("[오류] 현재 예약된 내역이 없습니다.");
			}else {
				for (int i = 0; i < list.size(); i++) {
					
					System.out.print((i+1)+" . ");
					System.out.println(list.get(i).getInfoName());

					if(list.get(i).getSeat().equals("v")) {
						System.out.print(" 좌석 : VIP석  |");
					}else if(list.get(i).getSeat().equals("s")) {
						System.out.print(" 좌석 : S석  |");
					}else {
						System.out.print(" 좌석 : A석  |");
					}
					
					System.out.print(" 좌석 번호 : "+list.get(i).getSeatNum()+"번");
					System.out.println();
				}
			
			}
			
			return null;
	}


		/**
		 * 예매취소
		 * @return
		 */

		public boolean deleteReservation() {
			// TODO Auto-generated method stub
			return false;
		}
	

	
	
}
