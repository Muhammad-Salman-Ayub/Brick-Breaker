package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import javafx.scene.shape.Circle;

public class Gameplay extends JPanel implements KeyListener,ActionListener
{   
    private boolean play=false;
    private int totalbricks;
    private int score=0;
    private int Level=1;
    private Timer timer;
    private int delay=5;
    private Graphics g;
    //SLIDER POSITION
   private int playerX1=310;
//   protected int playerX2=343;
//   protected int playerX3=376;
   //BALL POSITION
    private int ballposX=310;
    private int ballposY=310;
    //BALL MOVEMENT DIRECTION
    private int balldirX=-2;
    private int balldirY=-2;
    
    private MapGenerator map;//for bricks generation of level 1
    private Map2 map2;///for bricks generation of level 2
   
    public Gameplay()
    {
       if(Level==1)
       {
       totalbricks=3;    
       map=new MapGenerator(3,3);
       }
       if(Level==2)
       {
          totalbricks=21;
          map2=new Map2(4,7);
       }
       
       addKeyListener(this);
       setFocusable(true);
       setFocusTraversalKeysEnabled(false);
       
       timer =new Timer(delay,this);
       timer.start();
       
    }
     
    @Override
    public void paint(Graphics g)
    { //background
       g.setColor(Color.ORANGE);
       g.fillRect(0, 0, 682,560);
       
       //drawing bricks
       if(Level==1)
       {
           map.draw((Graphics2D)g);
       }
       if(Level==2)
       {
           map2.draw((Graphics2D)g);
           //level.draw((Graphics2D)g);
       }
       //SCORES
       g.setColor(Color.WHITE);
       g.setFont(new Font("aerial" , Font.BOLD , 20 ));
       g.drawString("SCORE "+score, 575, 20);
       
        
     //borders
       g.setColor(Color.yellow);
       //left
       g.fillRect(0, 0, 3,560);
       //upper
       g.fillRect(0, 0, 682,3);
       //right
       g.fillRect(682, 0, 3,560);
      
       //sliderdesign set
       g.setColor(Color.BLACK);
       g.fillRoundRect(playerX1, 540, 100, 14,20,20);
//the balL
       g.setColor(Color.green);
       g.fillOval(ballposX, ballposY, 20,20);
       //display level
       g.setColor(Color.WHITE);
       g.setFont(new Font("aerial" , Font.BOLD , 20 ));
       g.drawString("LEVEL ="+Level, 10, 20);

       if(totalbricks==0 && play==true && Level==1)
       { 
                play=false;
                playerX1=310;
                ballposX=310;
                ballposY=310;
                balldirX=-2;
                balldirY=-2;
                Level++;
                //score = 0;
                totalbricks = 28;
                map2=new Map2(4,7);
                 repaint();
                 
             
           
       }      
       if (totalbricks ==0 && play==true && Level==1)
        {   balldirX = 0;
           balldirY = 0;
           g.setColor(Color.red);
           g.setFont(new Font("aerial" , Font.BOLD , 30 ));
           
           g.drawString("YOU WON", 150, 250);
           g.drawString("SCORE ="+score, 150, 300);
           g.setFont(new Font("aerial" , Font.BOLD , 20 ));
           g.drawString("PRESS ENTER TO RESTART ",150, 350);
       }
       if(ballposY > 560)
       {
           play=false;
           balldirX = 0;
           balldirY = 0;
           g.setColor(Color.red);
           g.setFont(new Font("aerial" , Font.BOLD , 30 ));
           g.drawString("GAME OVER", 150, 250);
           g.drawString("SCORE ="+score, 150, 300);
           g.setFont(new Font("aerial" , Font.BOLD , 20 ));
           g.drawString("PRESS ENTER TO RESTART ",150, 350);
           
           
       }
       
      
       
       
       g.dispose();
    }
     @Override
    public void actionPerformed(ActionEvent e) 
    {
        timer.start();
        
        if(play)
        {
           if (new Circle(ballposX, ballposY, 20).intersects(playerX1, 540, 90,1))
           
            {
                balldirY=-2;
            }
           
         intersection();//Intersection with bricks of level 1
         
         
         //Intersection with bricks of level 
        if(Level==2)
         {  
   A:   for (int i = 0; i < map2.bricks.length; i++) 
            {
                for (int j = 0; j < map2.bricks[0].length; j++)
                {
                   if(map2.bricks[i][j] > 0)
                   {
                       int brickX =j*map2.brickwidth +80;
                       int brickY =i*map2.brickheight +50;
                       int brickwidth = map2.brickwidth;
                       int brickheight = map2.brickheight;
                       
             Rectangle rect =new Rectangle(brickX,brickY,brickwidth,brickheight);
             Rectangle ballrect =new Rectangle(ballposX,ballposY,20,20);
             Rectangle brickrect =rect;
            
            if(ballrect.intersects(brickrect))
            {
                map2.setbrickvale(0, i, j);
                totalbricks--;
                score+=5;
                
                if(ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + brickrect.width)
                {
                    balldirX=-balldirX;
                }
                else
                {
                    balldirY=-balldirY;
                }
                break A;
            }
                   }
                }
                
        }
        
           }
            ballposX+=balldirX;
            ballposY+=balldirY;
            //left
            if(ballposX<0)
            {
                balldirX=-balldirX;
            }
            //top
             if(ballposY<0)
            {
                balldirY=-balldirY;
            }
             //right
              if(ballposX>665)
            {
                balldirX=-balldirX;
            }
        }
        repaint();
        
    }
            
    
    @Override
    public void keyTyped(KeyEvent e)
    {}
    @Override
    public void keyReleased(KeyEvent e)
    {}
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        { 
            if( playerX1 >=570)
            { 
               playerX1=570;
            }
            else
            { 
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
             if(playerX1 <= 5 )
             {
               playerX1=5;
               
             } 
             else
             {
                 moveLeft();
             }    
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(!play && totalbricks==0 && Level==2)
            { 
                
                play=false;
                playerX1=310;
                ballposX=310;
                ballposY=310;
                balldirX=-2;
                balldirY=-2;
                Level=1;
                score = 0;
                totalbricks = 3;
                map=new MapGenerator(1,3);
                repaint();
               
            }
            if(!play)
            { 
                
                play=false;
                playerX1=310;
                ballposX=310;
                ballposY=310;
                balldirX=-2;
                balldirY=-2;
                Level=1;
                score = 0;
                totalbricks = 3;
                map=new MapGenerator(1,3);
                repaint();
               
            }
        }
    }
    
    public void moveRight()
    {
        play=true;
        playerX1+=20;
    }
    public void moveLeft()
    {
        play=true;
        playerX1-=20;
    }
    public void intersection()
    {
         A:   for (int i = 0; i < map.map.length; i++) 
            {
                for (int j = 0; j < map.map[0].length; j++)
                {
                   if(map.map[i][j] > 0)
                   {
                       int brickX =j*map.brickwidth +300;
                       int brickY =i*map.brickheight +50;
                       int brickwidth = map.brickwidth;
                       int brickheight = map.brickheight;
                       
             Rectangle rect =new Rectangle(brickX,brickY,brickwidth,brickheight);
             Rectangle ballrect =new Rectangle(ballposX,ballposY,20,20);
             Rectangle brickrect =rect;
            
            if(ballrect.intersects(brickrect))
            {   
                map.setbrickvale(0, i, j);
                
                totalbricks--;
                score+=5;
                
                if(ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + brickrect.width)
                {
                    balldirX=-balldirX;
                    
                }
                else
                {
                    balldirY=-balldirY;
                }
                break A;
            }
                   }
                }
                
        }
    }
    
}