package ex.model;

 abstract class Theater {
	
	String []temp = {"A", "B", "C"};
    int[][] seat = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
    };
    abstract void printfWelcome();
 
    void reserveSeat(char row, int col) {
        if(row == 'A') {
            seat[0][col-1] = 1;
        }
        else if(row == 'B') {
            seat[1][col-1] = 1;
        }
        else if(row == 'C') {
            seat[2][col-1] = 1;
        }
    }
 
    void checkSeat() {
        System.out.println("     1  2  3  4  5");
        for(int i = 0 ; i < seat.length; i++) {
            System.out.print(temp[i] + "   ");
            for(int j = 0; j < seat[0].length; j++) {
                System.out.print(seat[i][j] + "  ");
            }
            System.out.println();
        }
    }

}
