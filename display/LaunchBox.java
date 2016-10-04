package kappystudios.display;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

// Displays a launch screen for the application.
public class LaunchBox extends JFrame {
      // Displays screen.
      public LaunchBox() {
            setResizable(false);
            setUndecorated(true);
            
            ImageIcon icon = new ImageIcon("kappystudios/images/launch_image.png");
            JLabel label = new JLabel(icon);
            add(label);
            
            pack();
            setLocationRelativeTo(null); 
            setVisible(true);
      }
      
      // Makes screen go away.
      public void disappear() {
            setVisible(false);
      }
}