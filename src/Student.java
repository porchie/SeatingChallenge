public class Student {
    private String name;
    private int curSeat;
    private String adjacentLeft;
    private String adjacentRight;
    private int newSeat;


    public Student(String name, int curSeat, String adjacentLeft, String adjacentRight) {
        this.name = name;
        this.curSeat = curSeat;
        this.adjacentLeft = adjacentLeft;
        this.adjacentRight = adjacentRight;
    }

    public boolean setNewSeat(int seat)
    {
        if(curSeat!=seat)
        {
            newSeat = seat;
            return true;
        }
        else
        return false;
    }

    public String getAdjacentLeft() {
        return adjacentLeft;
    }

    public String getAdjacentRight() {
        return adjacentRight;
    }

    public String getName() {
        return name;
    }

    public int getCurSeat() {
        return curSeat;
    }

    public int getNewSeat() {
        return newSeat;
    }

    public String toString()
    {
        return name + newSeat;
    }



}
