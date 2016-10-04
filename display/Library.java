package kappystudios.display;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

import kappystudios.display.utility.FileController;

// User's customized game library screen. 
public class Library extends Screen {
      // Sizing values.
      public static final float NAME_SIZE = 16;
      public static final float DESC_SIZE = 10;
      public static final float PLAY_BUTT_SIZE = 16;
      public static final float DEL_BUTT_SIZE = 8;
      
      // Library game information (no genres).
      private String[] games;
      private String[] descriptions;
      
      // Sets up user's library screen. 
      public Library () {
            listener = new LibraryListener(this);
            
            String[][] library = FileController.getLibrary();
            games = library[0];
            descriptions = library[1];
            
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
            setSettingsButt(gbc, 1, 0);
            setExitButt(gbc, 4, 0);
            setTitle(gbc, "  My Library  ");
            
            tweakHeaderSpacing(gbc);
      }
      
      // Sets up games listing with buttons.
      protected void setBody() {
            super.setBody();
            
            setScroller();
            
            GridBagConstraints gbc = new GridBagConstraints();
            
            setPriorityContent(gbc);
      }
      
      // Displays game names, descriptions, play buttons, and delete buttons.
      private void setPriorityContent (GridBagConstraints gbc) {
            for (int game = 0, x = 0, y = 0; game < games.length; game++, y = y + 4) {
                  // Name.
                  JLabel name = new JLabel(games[game]);
                  name.setFont(name.getFont().deriveFont(NAME_SIZE));
                  gbc.gridx = x;
                  gbc.gridy = y;
                  body.add(name, gbc);
                  // Description.
                  JLabel desc = new JLabel(descriptions[game]);
                  desc.setFont(desc.getFont().deriveFont(DESC_SIZE));
                  gbc.gridy = y + 1;
                  body.add(desc, gbc);
                  // Play button.
                  JButton playButt = new JButton("          Play          ");
                  playButt.setFont(playButt.getFont().deriveFont(PLAY_BUTT_SIZE));
                  playButt.setBackground(buttColor);
                  playButt.setOpaque(true);
                  playButt.setBorderPainted(false);
                  gbc.gridx = x + 3;
                  gbc.gridy = y;
                  body.add(playButt, gbc);
                  playButt.addActionListener(listener);
                  playButt.setName("p" + games[game]);
                  // Delete button.
                  JButton delButt = new JButton("Delete");
                  delButt.setFont(delButt.getFont().deriveFont(DEL_BUTT_SIZE));
                  delButt.setBackground(buttColor);
                  delButt.setOpaque(true);
                  delButt.setBorderPainted(false);
                  gbc.gridy = y + 1;
                  body.add(delButt, gbc);
                  delButt.addActionListener(listener);
                  delButt.setName("d" + games[game]);
                  // Adjusts body spacing.
                  gbc.gridx = x + 1;
                  gbc.gridy = y + 2;
                  tweakBodySpacing(gbc);
                  gbc.gridy = y + 3;
                  tweakBodySpacing(gbc);
            }
      }
}   

// Responds to events on the library screen.
class LibraryListener extends ScreenListener {
      protected LibraryListener (Library library) {
            screen = library;
      }
      
      // Event handler.
      public void actionPerformed (ActionEvent event) {
            super.actionPerformed(event);
            
            JButton butt = (JButton)event.getSource();
            String name = butt.getName();
            
            if (name.equals("catalog")) {         // Go to library.
                  screen.window.setVisible(false);
                  Catalog catalog = new Catalog();
            } else if (name.equals("settings")) { // Go to settings.
                  screen.window.setVisible(false);
                  Settings settings = new Settings();
            } else if (name.equals("exit")) {     // Exit.
                  ExitDialog eDialog = new ExitDialog();
            } else if (name.charAt(0) == 'p') {   // Play game.
                  
                  // ENTER GAME LAUNCHING CODE HERE!
                  
            } else if (name.charAt(0) == 'd') {   // Delete game.
                  screen.window.setVisible(false);
                  FileController.setDelete(name.substring(1));
                  DeleteDialog dDialog = new DeleteDialog();
            }
      }
}