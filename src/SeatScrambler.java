import java.util.ArrayList;
import java.util.Random;

public class SeatScrambler {
    private String[][] originalSeats;
    private ArrayList<Integer> bag;
    private ArrayList<Student> students;
    private Random rand;


    private void fillBag()
    {
        for(int i = 1;i<=33;i++)
        {
            bag.add(i);
        }
    }


    public SeatScrambler(String[][] originalSeats) {

        this.originalSeats = originalSeats;
        rand = new Random();
        students = new ArrayList<>();
        bag = new ArrayList<Integer>();
        fillBag();
        int seat = 0;
        for(int r = 0;r<originalSeats.length;r++)
        {
            for(int c = 0;c<originalSeats[r].length;c++)
            {
                seat++;
                String prev = (c==0)?null:originalSeats[r][c-1];
                String next = (c==originalSeats[r].length-1)?null:originalSeats[r][c+1];
                String name = originalSeats[r][c];

                students.add(new Student(name,seat,prev,next));
            }
        }
       // System.out.println(students);
     //   studentWithAdjacencies();
    }

    public void studentWithAdjacencies()
    {
        for(Student s:students)
        {
            System.out.print(s.getCurSeat() + " ");
            System.out.print(s.getName() + ": ");
            if(s.getAdjacentLeft()!=null) System.out.print(s.getAdjacentLeft() + " ");
            if(s.getAdjacentRight() != null) System.out.print(s.getAdjacentRight());
            System.out.println();
        }
    }

    public Student[][] newSeatsMaker()
    {
        Student[][] changedSeats  = new Student[3][];
        changedSeats[0] = new Student[10];
        changedSeats[1] = new Student[12];
        changedSeats[2] = new Student[12];

        for(Student s:students)
        {
            studentNewSeating(s);
        }

      //  System.out.println(students);//testing
        for(Student s:students)
        {
            int[] rowCol = rowColfromSeat(s.getNewSeat());
           // System.out.print(s.getName() + " " + s.getNewSeat() + " " + rowCol[0] + " " + rowCol[1]);
            changedSeats[rowCol[0]][rowCol[1]] = s;
            //System.out.println();
        }
        ArrayList<Integer[]> sameNeighbors = studentsThatHaveSameNeighbor(changedSeats);

        while(sameNeighbors.size() > 0)
        {
            swapSameNeighbors(changedSeats,sameNeighbors);
            sameNeighbors = studentsThatHaveSameNeighbor(changedSeats);
        }


        return changedSeats;
    }

    private ArrayList<Integer[]> studentsThatHaveSameNeighbor(Student[][] arr)
    {
        ArrayList<Integer[]> rowColSameNeigh = new ArrayList<>();
        for (int r = 0; r < arr.length; r++) {
            for (int c = 1; c < arr[r].length-1; c++) {
                Student curStudent = arr[r][c];
                if(r!=2&&c!=10) {
                    if (arr[r][c - 1].getName().equals(curStudent.getAdjacentLeft()) || arr[r][c + 1].getName().equals(curStudent.getAdjacentRight())) {
                        Integer[] rowColArr = {r, c};
                        rowColSameNeigh.add(rowColArr);
                        if (arr[r][c + 1].getName().equals(curStudent.getAdjacentRight())) c++;
                    }
                }
            }
        }
        return rowColSameNeigh;
    }

    private void swapSameNeighbors(Student[][] arr, ArrayList<Integer[]> sameNList)
    {
        while(sameNList.size()>1)
        {
            Integer[] idx1 = sameNList.get(0);
            Integer[] idx2 = sameNList.get(1);
            Student temp = arr[idx1[0]][idx1[1]];
            arr[idx1[0]][idx1[1]] = arr[idx2[0]][idx2[1]];
            arr[idx2[0]][idx2[1]] = temp;

            sameNList.remove(0);
            sameNList.remove(0);
        }
        if(sameNList.size()==1)
        {
            int random = rand.nextInt(32)+1;
            int[] rowCol = rowColfromSeat(random);
            Integer[] idx = sameNList.get(0);
            Student temp = arr[idx[0]][idx[1]];
            arr[idx[0]][idx[1]] = arr[rowCol[0]][rowCol[1]];
            arr[rowCol[0]][rowCol[1]] = temp;
        }
    }
    private int[] rowColfromSeat(int seat)
    {
        int[] rowCol = new int[2];
        if(seat<=10)
        {
            rowCol[0] = 0;
            rowCol[1] = seat-1;
            return rowCol;
        }
        else
        {
            rowCol[0] = ((seat-11)/12)+1;
            rowCol[1] = (seat-11)%12;
            return rowCol;
        }
    }

    private int randomFromBag()
    {
       int randomChoice = rand.nextInt(bag.size());
       return randomChoice;
       // return bag.get(bag.size()-1);
    }

    private void studentNewSeating(Student s)
    {
        if(s.getName()==null)
        {
            s.setNewSeat(34);
            return;
        }
        int idx = randomFromBag();
        int newSeat = bag.get(idx);
        while(!s.setNewSeat(newSeat))
        {
            idx = randomFromBag();
            newSeat = bag.get(idx);
        }
        //System.out.println(newSeat-1);
        bag.remove(idx);
    }

    private void removeFromBagWithNum(int num)
    {
        for (int i = 0; i < bag.size(); i++) {
            if(bag.get(i) == num) bag.remove(i);
            return;
        }
    }

    public void test()
    {
        studentNewSeating(students.get(0));
        System.out.println(bag);
        System.out.println(students.get(0));
    }

    /*
    1 2 3 4 5 6
    2 4 6 1 3 5
    6 3 5 1 4 2


    String[][] seats = {{"Moiz", "Angie", "Taran", "Kelvin", "Kyler", "David", "WenHao Huang", "Nicole", "Jennifer", "Michelle"},
            {"Beckett", "Raymond", "Lucy", "Apramjot", "Justin Lema", "Sam", "Tristan", "Pradeep", "Mohammad", "Haley", null, "Rely"},
            {"Cheng Han", "Qihan", "Kevin", "Ryan", "Justin Liu", "Jeffrey", "Danny", "Elliot", "Benson", "Fiona", "Neil", "Kaitlyn"}};


            int[][] seatNum = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                           {11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22},
                           {23, 24, 25, 26, 27, 28, 29 ,30, 31, 32, 33, 34}};
     */
}
