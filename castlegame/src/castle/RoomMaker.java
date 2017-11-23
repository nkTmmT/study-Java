package castle;

import java.util.ArrayList;

public class RoomMaker {
	private ArrayList<Room> rooms = new ArrayList<Room>();//������Ϸ��������
	
	public RoomMaker()
    {
		makeRoom();
    }
	
	private void makeRoom() {
		//����room
		Room outside = new Room("�Ǳ���");
		Room lobby = new Room("����");
		Room pub = new Room("С�ư�");
		Room study = new Room("�鷿");
		Room bedroom = new Room("����");
		Room supermarket = new Room("����");
		Room InternetCafe = new Room("����");
		
        //��ӷ��䵽˳������rooms
        rooms.add(outside);
        rooms.add(lobby);
        rooms.add(pub);
        rooms.add(study);
        rooms.add(bedroom);
        rooms.add(supermarket);
        rooms.add(InternetCafe);
        
        //	��ʼ������ĳ���
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
	
	//���ѡ����ʼ����
	public Room currentRoom()
	{
		return rooms.get((int)(Math.random()*rooms.size()));
	}
	
}
