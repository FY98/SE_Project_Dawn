import java.util.Vector;

public class Room {
    public String RoomID;
    public Vector<String> memberList;
    public Vector<String> prepareList;
    public int numberOfMember;
    public Room(String pureIP, String RoomID, int numberOfMember){
        this.RoomID = RoomID;
        this.numberOfMember = numberOfMember;
        this.memberList = new Vector<>(numberOfMember);
        addPlayer(pureIP);
        prepareList = new Vector<>();
    }
    public Boolean addPlayer(String pureIP) {
        if(!memberList.contains(pureIP)){
            memberList.add(pureIP);
            return true;
        }else{
            return false;
        }
    }
    public void displayAllMember(){
        for(String ID : memberList){
            System.out.println("--ID: " + ID);
        }
    }
    public boolean prepareOne(String pureIP){
        if(!prepareList.contains(pureIP)){
            prepareList.add(pureIP);
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        Room tmp = new Room("xxx", "111", 2);
        tmp.addPlayer("lyt");
        tmp.addPlayer("lyt");
        tmp.displayAllMember();

    }

    public void removePlayer(String pureIP) {
        if(memberList.contains(pureIP)){
            memberList.remove(pureIP);
            System.out.println("Remove from room.");
        }
        if(prepareList.contains(pureIP)){
            prepareList.remove(pureIP);
            System.out.println("Remove from prepare list");
        }
    }
}
