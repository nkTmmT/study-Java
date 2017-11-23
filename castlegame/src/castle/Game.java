package castle;

import java.util.Scanner;

public class Game {
    private Room currentRoom;//游戏操作变量
    
    //Game构造函数
    public Game() 
    {
    	currentRoom = new RoomMaker().currentRoom();//获取游戏数据
    	new Handler();//新建一个用户操作
    }
    
    //将Handler写成内部类便可以不制造一个Game对象而访问Game类的私有成员以及使程序具有更好的封装性
    private class Handler {
    	
    	//Handler构造函数
    	public Handler()
    	{
		    printWelcome();
		    Scanner in = new Scanner(System.in);
		    while ( true ) 
		    {
		    	String word = in.next();//判断该执行什么命令
	    		if(word.equalsIgnoreCase("bye"))
	    		{
	    			System.out.println("欢迎下次光临!");
	    			System.out.println();
	    			break;
	    		}
	    		else if(word.equalsIgnoreCase("help"))
	    		{
	    			System.out.println("不知道怎么玩吗？你可以做的命令有：go bye help");
	    	        System.out.println("例如：\tgo east");
	    	        showPrompt();
	    		}
	    		else if(word.equalsIgnoreCase("go"))
	    		{
	    			String word1 = in.next();
	    			goRoom(word1.trim());
	    		}
	    		else
	    		{
	    			System.out.println("您可能需要帮助,输入help获得提示");
	    			System.out.println();
	    		}
	    	}
		    in.close(); 
    	}
    	
    	//go命令函数
        public void goRoom(String direction) 
        {
            Room nextRoom = currentRoom.getExit(direction);

            if (nextRoom == null) {
                System.out.println("那里没有门！");
                showPrompt();
            }
            else {
                currentRoom = nextRoom;
                showPrompt();
            }
        }
        
      //输出欢迎语句
        public void printWelcome() {
            System.out.println();
            System.out.println("欢迎来到城堡！");
            System.out.println("这是一个超级无聊的游戏。");
            System.out.println("如果需要帮助，请输入 'help' 。");
            System.out.println();
            showPrompt();
        }

        //输出提示语句
        public void showPrompt() {
        	 System.out.println("你在" + currentRoom);
             System.out.print("出口有: ");
             System.out.println(currentRoom.getExitDesc());
             System.out.println();
        }
        
    }
	
    //main函数
	public static void main(String[] args) 
	{
		new Game();
	}

}