package castle;

import java.util.ArrayList;

public class RoomMaker {
	private ArrayList<Room> rooms = new ArrayList<Room>();//储存游戏房间数据
	
	public RoomMaker()
    {
		makeRoom();
    }
	
	private void makeRoom() {
		//创造room
		Room outside = new Room("城堡外");
		Room lobby = new Room("大堂");
		Room pub = new Room("小酒吧");
		Room study = new Room("书房");
		Room bedroom = new Room("卧室");
		Room supermarket = new Room("超市");
		Room InternetCafe = new Room("网咖");
		
        //添加房间到顺序容器rooms
        rooms.add(outside);
        rooms.add(lobby);
        rooms.add(pub);
        rooms.add(study);
        rooms.add(bedroom);
        rooms.add(supermarket);
        rooms.add(InternetCafe);
        
        //	初始化房间的出口
        outside.setExit("east", lobby);
        outside.setExit("up", study);
        outside.setExit("west", pub);
        outside.setExit("north",InternetCafe);
        outside.setExit("south", supermarket);
        lobby.setExit("west", outside);
        lobby.setExit("up", bedroom);
        pub.setExit("east", outside);
        study.setExit("east", bedroom);
        study.setExit("down", outside);
        bedroom.setExit("west", study);
        bedroom.setExit("down", lobby);
        supermarket.setExit("north", outside);
        InternetCafe.setExit("south", outside);
	}
	
	//随机选定初始房间
	public Room currentRoom()
	{
		return rooms.get((int)(Math.random()*rooms.size()));
	}
	
}
