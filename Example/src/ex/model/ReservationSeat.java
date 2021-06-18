package ex.model;

import java.util.*;

public class ReservationSeat {

	 CGV cgv;
	    Megabox mb;
	    Yawoori yw;
	 
	    Scanner in;
	    int menu, movie, col;
	    char row;
	    boolean flag;
	 
	    public void initialize() {
	        cgv = new CGV();
	        mb = new Megabox();
	        yw = new Yawoori();
	        in = new Scanner(System.in);
	        menu = 0; movie = 0; col=0;
	        flag = true;
	    }
	 
	    public void menu() {
	        initialize();
	 
	        System.out.println("좌석예약 프로그램");
	        while (flag) {
	 
	            System.out.println("1.영화관 선택");
	            System.out.println("2.좌석 예약");
	            System.out.println("3.좌석 확인");
	            System.out.println("4.종료");
	            System.out.print("메뉴를 선택해 주세요 : ");
	            int menu = in.nextInt();
	 
	            switch(menu) {
	                case 1:
	                    System.out.println("1.야우리시네마, 2.메가박스, 3.CGV");
	                    System.out.print("영화관을 선택해주세요 : ");
	                    movie = in.nextInt();
	                    break;
	                case 2:
	                    if(movie == 1) {
	                        yw.printfWelcome();
	                        System.out.print("좌석을 선택해 주세요. (A-C 1-5) : "); //띄어쓰기로 구분
	                        row = in.next().charAt(0);
	                        col = in.nextInt();
	                        yw.reserveSeat(row, col);
	                        System.out.println(row + "열 " + col + "번으로 예약 되었습니다.");
	                    }
	                    else if(movie == 2) {
	                        mb.printfWelcome();
	                        System.out.print("좌석을 선택해주세요. (A-C 1-5) : "); //띄어쓰기로 구분
	                        row = in.next().charAt(0);
	                        col = in.nextInt();
	                        mb.reserveSeat(row, col);
	                        System.out.println(row + "열 " + col + "번으로 예약 되었습니다.");
	                    }
	                    else if(movie == 3) {
	                        cgv.printfWelcome();
	                        System.out.print("좌석을 선택해주세요. (A-C 1-5) : "); //띄어쓰기로 구분
	                        row = in.next().charAt(0);
	                        col = in.nextInt();
	                        cgv.reserveSeat(row, col);
	                        System.out.println(row + "열 " + col + "번으로 예약 되었습니다.");
	                    }
	                    break;
	                case 3 :
	                    if(movie == 1) {
	                        yw.checkSeat();
	                    }
	                    else if(movie == 2) {
	                        mb.checkSeat();
	                    }
	                    else if(movie == 3) {
	                        cgv.checkSeat();
	                    }
	                    break;
	                case 4:
	                    System.out.println("프로그램을 종료합니다.");
	                    flag = false;
	                    break;
	                default :
	            }
	        }
	    }
}
