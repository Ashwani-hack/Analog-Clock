package clk;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class Clock extends JFrame {
    
    public Clock()
    {
        setTitle("Analog Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        ClockPanel clock = new ClockPanel();
        add(clock);
        Timer timer = new Timer(1000, e->{
        
            clock.setCurrentTime();
            clock.repaint();
            
        });
        timer.start();
        clock.setCurrentTime();
        
    }
    
    private class ClockPanel extends JPanel
    {
        private int centerX;
        private int centerY;
        private int clockRadius;
        
        public ClockPanel()
        {
            setBackground(Color.white);
        }
        
        public void setCurrentTime()
        {
            repaint();
        }
        
        
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            clockRadius = Math.min(getWidth(), getHeight()) / 2 - 20; 
            centerX = getWidth() / 2; 
            centerY = getHeight() / 2; 
            
            
            g2d.setColor(new Color(50,50,50)); 
             
            g2d.fillOval(centerX - clockRadius, centerY - clockRadius, 2 * clockRadius, 2 * clockRadius);
            
           
            g2d.setFont(new Font("Arial", Font.BOLD, 14)); 
            g2d.setColor(Color.white); 
            
            for(int hour = 1; hour <= 12; hour++)
            {
                double angle = Math.toRadians(90-(360/12) * hour); 
                int x = (int)(centerX + clockRadius * 0.8 * Math.cos(angle)); 
                int y = (int)(centerY - clockRadius * 0.8 * Math.sin(angle)); 
                g2d.drawString(Integer.toString(hour), x - 7, y + 5); 
            }
            
            
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
            String currentTime = sdf.format(new Date()); 
            
          
            int hours = Integer.parseInt(currentTime.substring(0,2)); 
            int minutes = Integer.parseInt(currentTime.substring(3,5)); 
            int seconds = Integer.parseInt(currentTime.substring(6,8)); 
            
          
            double hoursAngle = Math.toRadians(90 - (360 / 12) * hours); 
            double minutesAngle = Math.toRadians(90 - (360 / 60) * minutes); 
            double secondsAngle = Math.toRadians(90 - (360 / 60) * seconds); 
            
           
            drawClockHand(g2d, centerX, centerY, clockRadius * 0.5, hoursAngle, 6, Color.yellow);
            
          
            drawClockHand(g2d, centerX, centerY, clockRadius * 0.7, minutesAngle, 4, Color.yellow);
            
            
            //g2d.setColor(new Color(255, 90, 90));
            drawClockHand(g2d, centerX, centerY, clockRadius * 0.8, secondsAngle, 2, new Color(255, 90, 90));

        }
        
        
        
        private void drawClockHand(Graphics2D g2d, int x, int y, double length, 
                                   double angle, int thickness, Color color)
        {
            
            g2d.setStroke(new BasicStroke(thickness));
            g2d.setColor(color);
            int x2 = (int)(x + length * Math.cos(angle));
            int y2 = (int)(y - length * Math.sin(angle));
            
            g2d.drawLine(x, y, x2, y2);
        }
        
        
    }


    
    
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(()->{
        
            Clock app = new Clock();
            app.setVisible(true);
            
        });
        
    }

}

