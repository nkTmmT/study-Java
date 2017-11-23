package castle;

import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Room> exits= new HashMap<String, Room>();//存储当前房间的各个方向的房间信息
    
  //定义当前房间的名字
    public Room(String description)
    {
        this.description = description;
    }
    
    //设置当前房间某一方向的房间的信息
    public void setExit(String dir, Room room) {
    	exits.put(dir, room);
    }
    
  //重写toString()以便Room正确输出
    @Override
    public String toString()
    {
        return description;
    }
    
    //获取当前房间的所有出口方向并转换为字符串以便输出
    public String getExitDesc() {
    	StringBuffer sb = new StringBuffer();
    	for(String dir : exits.keySet())
    	{
    		sb.append(dir);
    		sb.append(' ');
    	}
    	return sb.toString();
    }
    
    //获取当前房间的某一出口的房间的信息
    public Room getExit(String direction) {
    	return exits.get(direction);
    }
    
}
