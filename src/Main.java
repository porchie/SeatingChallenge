public class Main {
    public static void main(String[] args) {
        /**
        boolean isWorking = true;
        while(isWorking) {
            String[][] seats = {{"Moiz", "Angie", "Taran", "Kelvin", "Kyler", "David", "WenHao", "Nicole", "Jennifer", "Michelle"},
                    {"Beckett", "Raymond", "Lucy", "Apramjot", "Justin Lema", "Sam", "Tristan", "Pradeep", "Mohammad", "Haley", null, "Rely"},
                    {"Cheng Han", "Qihan", "Kevin", "Ryan", "Justin Liu", "Jeffrey", "Danny", "Elliot", "Benson", "Fiona", "Neil", "Kaitlyn"}};
            SeatScrambler seat = new SeatScrambler(seats);
            Student[][] newSeats = seat.newSeatsMaker();
            printStudentTwoD(newSeats);
            isWorking = seat.isActuallyWorking(newSeats);
            //System.out.println(isWorking);
        }
        System.out.println(">:( doesnt work aasjdfiafisdisifjviv");
**/
        String[][] seats = {{"Moiz", "Angie", "Taran", "Kelvin", "Kyler", "David", "WenHao", "Nicole", "Jennifer", "Michelle"},
                {"Beckett", "Raymond", "Lucy", "Apramjot", "Justin Lema", "Sam", "Tristan", "Pradeep", "Mohammad", "Haley", null, "Rely"},
                {"Cheng Han", "Qihan", "Kevin", "Ryan", "Justin Liu", "Jeffrey", "Danny", "Elliot", "Benson", "Fiona", "Neil", "Kaitlyn"}};
        SeatScrambler seat = new SeatScrambler(seats);
        Student[][] newSeats = seat.newSeatsMaker();
        printStudentTwoD(newSeats);

    }

    public static void printStudentTwoD(Student[][] arr)
    {
        int computerNum = 1;
        for (int r = 0; r < arr.length; r++) {
            System.out.print("|");
            for (int c = 0; c < arr[r].length; c++) {
                if(r==2&&c==11) break;
                System.out.print(computerNum + " " + arr[r][c].getName() + " | ");
                computerNum++;
            }
            System.out.println();
        }
    }
}
