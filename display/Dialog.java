package kappystudios.display;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import kappystudios.display.utility.Colors;

// A parent class that is used to display dialogs.
public class Dialog {
      // Sizing values.
      public static final int WINDOW_WIDTH = 350; 
      public static final int WINDOW_HEIGHT = 200;
      public static final float BUTT_SIZE = 10;
      
      // Instance variables to be inherited.
      protected ActionListener listener;
      protected JFrame window;
      protected JPanel header;
      protected JPanel body;
      protected String title;
      protected Color headColor;
      protected Color bodyColor;
      protected Color buttColor;
      
      protected Dialog () {
      }
      
      // Set colour scheme (the head and body colour are the same for the dialogs).
      protected void setColors (String layout) {
            if (layout.equals("cblind")) {
                  headColor = Colors.CBLIND_HEAD_COLOR;
                  bodyColor = Colors.CBLIND_HEAD_COLOR;
                  buttColor = Colors.CBLIND_BUTT_COLOR;
            } else {
                  headColor = Colors.DEFAULT_HEAD_COLOR;
                  bodyColor = Colors.DEFAULT_HEAD_COLOR;
                  buttColor = Colors.DEFAULT_BUTT_COLOR;
            }
      }
      
      // Setup frame/window.
      protected void setWindow() {
            window = new JFrame();
            window.setResizable(false);
            window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            window.setUndecorated(true);
            window.setLocationRelativeTo(null); 
      }
      
      // Setup static header (includes title/question).
      protected void setHeader() {
            header = new JPanel(new BorderLayout());
            header.setBackground(headColor);
            window.add(header, BorderLayout.CENTER);
            
            setTitle();
      }
      
      // Setup dialog text.
      protected void setTitle() {
            JLabel label = new JLabel(title);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            header.add(label);
      }
      
      // Setup response area with buttons.
      protected void setBody() {
            body = new JPanel();
            body.setBackground(bodyColor);
            window.add(body, BorderLayout.SOUTH);
            
            setYesButt();
            tweakBodySpacing();
            setNoButt();
      }
      
      // Displays "Yes" button.
      protected void setYesButt() {
            JButton butt = new JButton("Yes");
            butt.setFont(butt.getFont().deriveFont(BUTT_SIZE));
            butt.setBackground(buttColor);
            butt.setOpaque(true);
            butt.setBorderPainted(false);
            body.add(butt);
            butt.addActionListener(listener);
            butt.setName("yes");
      }
      
      // Displays "No" button.
      protected void setNoButt() {
            JButton butt = new JButton("No");
            butt.setFont(butt.getFont().deriveFont(BUTT_SIZE));
            butt.setBackground(buttColor);
            butt.setOpaque(true);
            butt.setBorderPainted(false);
            body.add(butt);
            butt.addActionListener(listener);
            butt.setName("no");
      }
      
      // Adjusts spacing between buttons.
      protected void tweakBodySpacing() {
            body.add(new JLabel("               "));
      }
}  

// Manages dialog events.
class DialogListener implements ActionListener {
      // Holds the dialog.
      protected Dialog dialog;
      
      protected DialogListener () {
      }
      
      // Required ActionListener method.
      public void actionPerformed (ActionEvent event) {
      }
}