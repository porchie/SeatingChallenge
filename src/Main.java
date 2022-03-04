public class Main {
    public static void main(String[] args) {
        String[][] seats = {{"Moiz", "Angie", "Taran", "Kelvin", "Kyler", "David", "WenHao", "Nicole", "Jennifer", "Michelle"},
                {"Beckett", "Raymond", "Lucy", "Apramjot", "Justin Lema", "Sam", "Tristan", "Pradeep", "Mohammad", "Haley", null, "Rely"},
                {"Cheng Han", "Qihan", "Kevin", "Ryan", "Justin Liu", "Jeffrey", "Danny", "Elliot", "Benson", "Fiona", "Neil", "Kaitlyn"}};
        SeatScrambler seat = new SeatScrambler(seats);
        //seat.test();
        printStudentTwoD(seat.newSeatsMaker());
       // seat.newSeatsMaker();
    }

    public static void printStudentTwoD(Student[][] arr)
    {
        for (int r = 0; r < arr.length; r++) {
            System.out.print("| ");
            for (int c = 0; c < arr[r].length; c++) {
                if(r==2&&c==11) break;
                System.out.print(arr[r][c].getName() + " | ");
            }
            System.out.println();
        }
    }
}
