package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map2 extends MapGenerator
{
    public int bricks[][];
    public int brickwidth;
    public int brickheight;
    public Map2()
    {
       
    }
    
    public Map2(int row,int col)
    {
        bricks=new int[row][col];
        for (int i = 0; i <bricks.length; i++) 
        {
            for (int j = 0; j <bricks[0].length; j++) 
            {
                bricks[i][j]=1;
            }    
            
            
        }
    brickwidth=540/col;
    brickheight=100/row;
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        for (int i = 0; i <bricks.length; i++) 
        {
            for (int j = 0; j <bricks[0].length; j++) 
            {
                if(bricks[i][j]>0)
                {
                    g.setColor(Color.WHITE);
                    g.fillRoundRect(j*brickwidth +80, i*brickheight +50 ,brickwidth, brickheight, 20, 20);
                    
                    g.setStroke(new BasicStroke(4));
                    g.setColor(Color.ORANGE);
                    g.drawRoundRect(j*brickwidth +80, i*brickheight +50 ,brickwidth, brickheight, 20, 20 );
                    
                }
            }
                
        }        
    }
    
    public void setbrickvale(int value, int row, int col)
    {
         bricks[row][col]=value;
    }
    
}