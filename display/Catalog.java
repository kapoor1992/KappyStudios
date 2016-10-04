package kappystudios.display;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

import kappystudios.display.utility.FileController;

// Screen used to display the game catalog.
public class Catalog extends Screen {
      // Sizing values.
      public static final float VIEW_BAR_SIZE = 8;
      public static final float NAME_SIZE = 16;
      public static final float DESC_SIZE = 10;
      public static final float DL_BUTT_SIZE = 16;
      
      // Game information.
      private String[] genres;
      private String[] games;
      private String[] descriptions;
      
      // Sets up screen.
      public Catalog () {
            listener = new CatalogListener(this);
            
            genres = FileController.getGenres();
            String[][] catalog = FileController.getCatalog();
            games = catalog[0];
            descriptions = catalog[1];
            
            setColors(FileController.getLayout());
            setWindow();
            setHeader();
            setBody();
            
            window.setVisible(true);
      }
      
      // Sets up screen for a listing of games (used for display games in a certain genre).
      public Catalog (String[] games, String[] descriptions) {
            listener = new CatalogListener(this);
            
            genres = FileController.getGenres();
            this.games = games;
            this.descriptions = descriptions;
            
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
            
            setLibraryButt(gbc, 0, 0);
            setSettingsButt(gbc, 1, 0); // 2,0
            setExitButt(gbc, 4, 0); // 
            setTitle(gbc, " Game Catalog ");
            setViewBar(gbc);
            
            tweakHeaderSpacing(gbc);
      }
      
      // Sets up the option to categorize games by genre.
      private void setViewBar (GridBagConstraints gbc) {
            JMenuBar viewBar = new JMenuBar();
            gbc.gridx = 0;
            gbc.gridy = 3;
            header.add(viewBar, gbc);
            
            JMenu view = new JMenu("View By...");
            view.setFont(view.getFont().deriveFont(VIEW_BAR_SIZE));
            viewBar.add(view);
            
            UIManager.put("MenuItem.background", buttColor);
            
            for (int genre = 0; genre < genres.length; genre++) {
                  JMenuItem currGenre = new JMenuItem(genres[genre]);
                  currGenre.setFont(currGenre.getFont().deriveFont(VIEW_BAR_SIZE));
                  view.add(currGenre);
                  currGenre.addActionListener(listener);
                  currGenre.setName(genres[genre]);
            }
      }
      
      // Sets up the actual catalog.
      protected void setBody() {
            super.setBody();
            
            setScroller();
            
            GridBagConstraints gbc = new GridBagConstraints();
            
            setPriorityContent(gbc);
      }
      
      // Displays the name, description, and download option for each game.
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
                  // Download button.
                  JButton butt = new JButton("Download to Library");
                  butt.setFont(butt.getFont().deriveFont(DL_BUTT_SIZE));
                  butt.setBackground(buttColor);
                  butt.setOpaque(true);
                  butt.setBorderPainted(false);
                  gbc.gridx = x + 3;
                  gbc.gridy = y;
                  body.add(butt, gbc);
                  butt.addActionListener(listener);
                  butt.setName(games[game]);
                  // Spacing adjustments.
                  gbc.gridx = x + 1;
                  gbc.gridy = y + 2;
                  tweakBodySpacing(gbc);
                  gbc.gridy = y + 3;
                  tweakBodySpacing(gbc);
            }
      }
}     

// Used to respond to events in the catalog screen.
class CatalogListener extends ScreenListener {
      protected CatalogListener (Catalog catalog) {
            screen = catalog;
      }
      
      // Event handler.
      public void actionPerformed (ActionEvent event) {
            super.actionPerformed(event);
            
            String name;
            boolean isButt = true;
            
            try {
                  JButton butt = (JButton)event.getSource();
                  name = butt.getName();
            } catch (ClassCastException cce) {
                  JMenuItem item = (JMenuItem)event.getSource();
                  name = item.getName();
                  isButt = false;
            }
            
            if (name.equals("library")) {         // Go to library.
                  screen.window.setVisible(false);
                  Library library = new Library();
            } else if (name.equals("settings")) { // Go to settings.
                  screen.window.setVisible(false);
                  Settings settings = new Settings();
            } else if (name.equals("exit")) {     // Exit.
                  ExitDialog eDialog = new ExitDialog();
            } else if (isButt) {                  // Download game.
                  screen.window.setVisible(false);
                  FileController.setDownload(name);
                  DownloadDialog dDialog = new DownloadDialog();
            } else {                              // View by genre.
                  screen.window.setVisible(false);
                  String[][] genre = FileController.getGenre(name);
                  Catalog catalog = new Catalog(genre[0], genre[1]);
            }
      }
}