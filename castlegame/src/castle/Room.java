package castle;

import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Room> exits= new HashMap<String, Room>();//�洢��ǰ����ĸ�������ķ�����Ϣ
    
  //���嵱ǰ���������
    public Room(String description)
    {
        this.description = description;
    }
    
    //���õ�ǰ����ĳһ����ķ������Ϣ
    public void setExit(String dir, Room room) {
    	exits.put(dir, room);
    }
    
  //��дtoString()�Ա�Room��ȷ���
    @Override
    public String toString()
    {
        return description;
    }
    
    //��ȡ��ǰ��������г��ڷ���ת��Ϊ�ַ����Ա����
    public String getExitDesc() {
    	StringBuffer sb = new StringBuffer();
    	for(String dir : exits.keySet())
    	{
    		sb.append(dir);
    		sb.append(' ');
    	}
    	return sb.toString();
    }
    
    //��ȡ��ǰ�����ĳһ���ڵķ������Ϣ
    public Room getExit(String direction) {
    	return exits.get(direction);
    }
    
}
