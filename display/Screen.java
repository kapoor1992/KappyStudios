package kappystudios.display;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import kappystudios.display.utility.Colors;

// Parent class to be used for the main interface display.
public class Screen {
      // Sizing values.
      public static final int WINDOW_WIDTH = 875; 
      public static final int WINDOW_HEIGHT = 600;
      public static final int TITLE_SIZE = 32;
      public static final float MENU_BUTT_SIZE = 10;
      
      // Instance variables to be inherited.
      protected ActionListener listener;
      protected JFrame window;
      protected JPanel header;
      protected JPanel body;
      protected Color headColor;
      protected Color bodyColor;
      protected Color buttColor;
      
      protected Screen () {
      }
 
      // Set colour scheme.
      public void setColors (String layout) {
            if (layout.equals("cblind")) {
                  headColor = Colors.CBLIND_HEAD_COLOR;
                  bodyColor = Colors.CBLIND_BODY_COLOR;
                  buttColor = Colors.CBLIND_BUTT_COLOR;
            } else {
                  headColor = Colors.DEFAULT_HEAD_COLOR;
                  bodyColor = Colors.DEFAULT_BODY_COLOR;
                  buttColor = Colors.DEFAULT_BUTT_COLOR;
            }
      }
      
      // Set up frame/window.
      protected void setWindow() {
            window = new JFrame();
            window.setResizable(false);
            window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            window.setUndecorated(true);
            window.setLocationRelativeTo(null); 
      }
      
      // Setup static header.
      protected void setHeader() {
            header = new JPanel(new GridBagLayout());
            header.setBackground(headColor);
            window.add(header, BorderLayout.NORTH);
      }
      
      // Places the catalog header button.
      protected void setCatalogButt (GridBagConstraints gbc, int x, int y) {
            JButton butt = new JButton("    View All Games    ");
            butt.setFont(butt.getFont().deriveFont(MENU_BUTT_SIZE));
            butt.setBackground(buttColor);
            butt.setOpaque(true);
            butt.setBorderPainted(false);
            gbc.gridx = x;
            gbc.gridy = y;
            header.add(butt, gbc);
            butt.addActionListener(listener);
            butt.setName("catalog");
      }
      
      // Places the library header button.
      protected void setLibraryButt (GridBagConstraints gbc, int x, int y) {
            JButton butt = new JButton("  Go To Game Library  ");
            butt.setFont(butt.getFont().deriveFont(MENU_BUTT_SIZE));
            butt.setBackground(buttColor);
            butt.setOpaque(true);
            butt.setBorderPainted(false);
            gbc.gridx = x;
            gbc.gridy = y;
            header.add(butt, gbc);
            butt.addActionListener(listener);
            butt.setName("library");
      }
      
      // Places the colour settings header button.
      protected void setSettingsButt (GridBagConstraints gbc, int x, int y) {
            JButton butt = new JButton("Change Colour Settings");
            butt.setFont(butt.getFont().deriveFont(MENU_BUTT_SIZE));
            butt.setBackground(buttColor);
            butt.setOpaque(true);
            butt.setBorderPainted(false);
            gbc.gridx = x;
            gbc.gridy = y;
            header.add(butt, gbc);
            butt.addActionListener(listener);
            butt.setName("settings");
      }
      
      // Places the exit header button.
      protected void setExitButt (GridBagConstraints gbc, int x, int y) {
            JButton butt = new JButton("X");
            butt.setFont(butt.getFont().deriveFont(MENU_BUTT_SIZE));
            butt.setBackground(buttColor);
            butt.setOpaque(true);
            butt.setBorderPainted(false);
            gbc.gridx = x;
            gbc.gridy = y;
            header.add(butt, gbc);
            butt.addActionListener(listener);
            butt.setName("exit");
            header.add(butt, gbc);
      }
      
      // Places the header title (the title of the screen).
      protected void setTitle (GridBagConstraints gbc, String title) {
            JLabel label = new JLabel(title);
            label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, TITLE_SIZE));
            gbc.gridx = 2; 
            gbc.gridy = 2; 
            header.add(label, gbc);
      }
      
      // Adds spacing to the header for easier reading.
      protected void tweakHeaderSpacing (GridBagConstraints gbc) {
            gbc.gridx = 3;
            gbc.gridy = 0;
            header.add(new JLabel("                                                       "), gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            header.add(new JLabel(" "), gbc);
            gbc.gridx = 1;
            gbc.gridy = 3;
            header.add(new JLabel(" "), gbc);
      }
      
      // Sets up the bulk of the screen.
      protected void setBody() {
            body = new JPanel(new GridBagLayout());
            body.setBackground(bodyColor);
            window.add(body, BorderLayout.CENTER);
      }
      
      // Makes the body scrollable.
      protected void setScroller() {
            JScrollPane scrollPane = new JScrollPane(body);
            window.add(scrollPane);
      }
      
      // Adds spacing to the body for easier reading.
      protected void tweakBodySpacing (GridBagConstraints gbc) {
            body.add(new JLabel("                                                                "), gbc);
      }
}     

// Parent class to be used to take action when an event on the screen takes place.
class ScreenListener implements ActionListener {
      // Holds the screen.
      protected Screen screen;
      
      protected ScreenListener() {
      }
      
      // Required method of ActionListener. All events make a short beep noise.
      public void actionPerformed (ActionEvent event) {
            Toolkit.getDefaultToolkit().beep();
      }
}