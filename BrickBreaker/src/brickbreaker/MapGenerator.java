package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator  
{
    public int map[][];
    public int brickwidth;
    public int brickheight;
    public MapGenerator()
    {
       
    }
    //Brick Generate
    public MapGenerator(int row,int col)
    {
        map=new int[row][col];
        for (int i = 0; i <map.length; i++) 
        {
            for (int j = 0; j <map[0].length; j++) 
            {
                map[i][j]=1;
            }    
            
            
        }
//Brick Size Decider
    brickwidth=160/col;
    brickheight=40/row;
    }
    //Brick Drawing
    public void draw(Graphics2D g)
    {
        for (int i = 0; i <map.length; i++) 
        {
            for (int j = 0; j <map[0].length; j++) 
            {
                if(map[i][j]>0)
                {
                    g.setColor(Color.BLACK);
                    g.fillRoundRect(j*brickwidth +300, i*brickheight +50 ,brickwidth, brickheight, 20, 20);
                    
                    g.setStroke(new BasicStroke(4));
                    g.setColor(Color.ORANGE);
                    g.drawRoundRect(j*brickwidth +300, i*brickheight +50 ,brickwidth, brickheight, 20, 20 );
                    
                }
            }
                
        }        
    }
    public void intersection()
    {
        
    }
    public void setbrickvale(int value, int row, int col)
    {
         map[row][col]=value;
    }
    
}