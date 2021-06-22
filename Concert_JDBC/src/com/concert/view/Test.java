package com.concert.view;

import java.util.ArrayList;

public class Test {
	


	public static void main(String[] args) {
		
		/** 공연 좌석  */
		String[] vipSeat = new String[]{"1","2","3","4","5","6","7","8","9","10"} ;
		String[] sSeat = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
		String[] rSeat = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
		
		String[] viplist = new String[]{"2","3","4","5","6", null, null, null, null, null, null};
		String[] rlist = new String[]{ null, null, null, null, null, null, null, null, null,"10","11",  null, null, null, null};
		String[] slist = new String[]{ null, null, null, "4","5","6", null, null, null, null, null, null, null, null, null, null, null, null, null, null};
			
		for(int i=0; i<viplist.length;i++) {
	//		System.out.println("배열에 저장되어 있던 좌석 번호 저장한 배열 vip_array : "+viplist[i]);		
			if(viplist[i] != null) {
				if(viplist[i] == vipSeat[i])
				{
					vipSeat[i] = "X";
				}
			}
		}
		
		for(int i=0; i<vipSeat.length;i++) {
			System.out.print("[ "+vipSeat[i]+ " ]");
		}
		
		
		
		
		
		
		

	}

}
