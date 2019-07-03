package brickbreaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
public class BrickBreaker implements Runnable 
{
   @Override
   public void run() 
   {
       
       
      Gameplay gameplay=new Gameplay();
      
      // Top-level frame
       JFrame frame=new JFrame();
        frame.setBounds(10, 10, 700,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JPanel panel=new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        JButton button =new JButton("Start");
        
        panel.add(button);
        button.setBounds(300, 200, 100, 50);
        panel.setVisible(true);
       
    
      button.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
             
             frame.add(gameplay);
             frame.setVisible(true);
             gameplay.requestFocusInWindow();
           }
      
      });
      
     
      
       final JButton info = new JButton("Info");
       info.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(panel, "Use the left and right arrow keys to move the padel and launch the ball.\n\nFeature List:\n*Multiple levels\n*Paddle control\n  \n\n", "Information", JOptionPane.INFORMATION_MESSAGE);
            
         }
      });
       info.setBounds(300, 300, 100, 50);
       panel.add(info);
       
       

      frame.setVisible(true);

    
      }


    public static void main(String[] args) 
    {
      
       SwingUtilities.invokeLater(new BrickBreaker());
       
    }
    
}
