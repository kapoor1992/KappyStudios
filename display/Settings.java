package kappystudios.display;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

import kappystudios.display.utility.FileController;

// Used to display the colour settings screen.
public class Settings extends Screen {
      // Button sizing.
      public static final float LAYOUT_BUTT_SIZE = 16;
      
      // Sets up settings screen.
      public Settings() {
            listener = new SettingsListener(this);
            
            
            setColors(FileController.getLayout());
            setWindow();
            setHeader();
            setBody();
            
            window.setVisible(true);
      }
      
      // Sets up static header.
      protected void setHeader() {
            super.setHeader();
            
            GridBagConstraints gbc = new GridBagConstraints();
            
            setCatalogButt(gbc, 0, 0);
            setLibraryButt(gbc, 1, 0);
            setExitButt(gbc, 4, 0);
            setTitle(gbc, "Colour  Layout");
            
            tweakHeaderSpacing(gbc);
      }
      
      // Sets up bulk of screen (buttons mostly).
      protected void setBody() {
            super.setBody();
            
            setDefaultButt();
            tweakBodySpacing();
            setCblindButt();
      }
      
      // Displays the default scheme button.
      private void setDefaultButt() {
            JButton defaultButt = new JButton("  Default Mode  ");
            defaultButt.setFont(defaultButt.getFont().deriveFont(LAYOUT_BUTT_SIZE));
            defaultButt.setBackground(buttColor);
            defaultButt.setOpaque(true);
            defaultButt.setBorderPainted(false);
            body.add(defaultButt);
            defaultButt.addActionListener(listener);
            defaultButt.setName("default");
      }
      
      // Displays the colour blind scheme button.
      private void setCblindButt() {
            JButton cblindButt = new JButton("Colour Blind Mode");
            cblindButt.setFont(cblindButt.getFont().deriveFont(LAYOUT_BUTT_SIZE));
            cblindButt.setBackground(buttColor);
            cblindButt.setOpaque(true);
            cblindButt.setBorderPainted(false);
            body.add(cblindButt);
            cblindButt.addActionListener(listener);
            cblindButt.setName("cblind");
      }
      
      // Adjusts body spacing to improve readability.
      private void tweakBodySpacing () {
            body.add(new JLabel("                "));
      }
}     

// Responds to button pushes on the settings screen.
class SettingsListener extends ScreenListener {
      public SettingsListener (Settings settings) {
            screen = settings;
      }
      
      // Event handler.
      public void actionPerformed (ActionEvent event) { 
            super.actionPerformed(event);
            
            JButton butt = (JButton)event.getSource();
            String name = butt.getName();
            
            
            if (name.equals("catalog")) {        // Go to catalog.
                  screen.window.setVisible(false);
                  Catalog catalog = new Catalog();
            } else if (name.equals("library")) { // Go to library.
                  screen.window.setVisible(false);
                  Library library = new Library();
            } else if (name.equals("exit")) {    // Exit.
                  ExitDialog eDialog = new ExitDialog();
            } else {                             // change colour scheme.
                  FileController.setLayout(name);
                  screen.window.setVisible(false);
                  Settings settings = new Settings();
            }
      }
}