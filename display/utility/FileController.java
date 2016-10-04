package kappystudios.display.utility;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

// Responsible for all text file operations needed in the interface.
public final class FileController {
      // Limits certain values.
      public static final int MAX_GENRES = 10;
      public static final int MAX_CATALOG_GAMES = 25;
      public static final int MAX_LIBRARY_GAMES = 25;
      
      private FileController() {
      }
      
      // Gets all game genres.
      public static String[] getGenres() {
            String line;
            String[] maxGenres = new String[MAX_GENRES];
            int count = 0;
            
            try {
                  BufferedReader file = new BufferedReader(new FileReader("kappystudios/data/catalog_data/genres.txt"));
                  
                  line = file.readLine();
                        
                  while (line != null) {
                        maxGenres[count] = line;
                        count++;
                        
                        line = file.readLine();
                  }
                  
                  file.close();
            } catch (IOException ioe) {
            }
            
            String[] genres = new String[count];
            
            for (int i = 0; i < count; i++) 
                  genres[i] = maxGenres[i];
            
            return genres;
      }
      
      // Gets entire catalog of games by the name and description.
      public static String[][] getCatalog() {
            String line;
            String[] names = new String[MAX_CATALOG_GAMES];
            String[] descs = new String[MAX_CATALOG_GAMES];
            String[][] catalog;
            int gameCount = 0;
            int mode = 0;
            
            try {
                  BufferedReader file = new BufferedReader(new FileReader("kappystudios/data/catalog_data/catalog.txt"));
                  
                  line = file.readLine();
                        
                  while (line != null) {
                        if (mode == 0) {
                              mode++;
                        } else if (mode == 1) {
                              names[gameCount] = line;
                              mode++;
                        } else {
                              descs[gameCount] = line;
                              gameCount++;
                              mode = 0;
                        }
                        
                        line = file.readLine();
                  }
                  
                  file.close();
            } catch (IOException ioe) {
            }
            
            catalog = new String[2][gameCount];
            
            for (int i = 0; i < gameCount; i++) {
                  catalog[0][i] = names[i];
                  catalog[1][i] = descs[i];
            }
            
            return catalog;
      }
      
      // Gets all catalog games by name and description that fit under a given genre.
      public static String[][] getGenre (String genre) {
            if (genre.equals("ALL"))
                  return getCatalog();
            
            String line;
            String[] names = new String[MAX_CATALOG_GAMES];
            String[] descs = new String[MAX_CATALOG_GAMES];
            String[][] genreGames;
            int gameCount = 0;
            int mode = 0;
            boolean isGenre = false;
            
            try {
                  BufferedReader file = new BufferedReader(new FileReader("kappystudios/data/catalog_data/catalog.txt"));
                  
                  line = file.readLine();
                        
                  while (line != null) {
                        if (mode == 0) {
                              if (line.equals(genre))
                                    isGenre = true;
                              else 
                                    isGenre = false;
                              mode++;
                        } else if (mode == 1) {
                              if (isGenre)
                                    names[gameCount] = line;
                              mode++;
                        } else {
                              if (isGenre) {
                                    descs[gameCount] = line;
                                    gameCount++;
                              }
                              mode = 0;
                        }
                        
                        line = file.readLine();
                  }
                  
                  file.close();
            } catch (IOException ioe) {
            }
            
            genreGames = new String[2][gameCount];
            
            for (int i = 0; i < gameCount; i++) {
                  genreGames[0][i] = names[i];
                  genreGames[1][i] = descs[i];
            }
            
            return genreGames;
      }
      
      // Adds a given game to the user's personal library.
      public static void addToLibrary (String name) {
            if (!isInLibrary(name)) {
                  try {
                        BufferedReader reader = new BufferedReader(new FileReader("kappystudios/data/user_data/library.txt"));
                        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("kappystudios/data/user_data/library.txt", true)));
                        
                        writer.println(name);
                        writer.println(getDescription(name));
                        
                        reader.close();
                        writer.close();
                  } catch (IOException ioe) {
                  }
            }
      }
      
      // Checks if a given game is already in the user's library.
      private static boolean isInLibrary (String name) {
            String line;
            
            try {
                  BufferedReader file = new BufferedReader(new FileReader("kappystudios/data/user_data/library.txt"));
                  
                  line = file.readLine();
                        
                  while (line != null) {
                        if (line.equals(name))
                              return true;
                        
                        line = file.readLine();
                  }
                  
                  file.close();
            } catch (IOException ioe) {
            }
            
            return false;
      }
      
      // Gets a game's description, given its name.
      private static String getDescription (String name) {
            String line;
            boolean nameFound = false;
            
            try {
                  BufferedReader file = new BufferedReader(new FileReader("kappystudios/data/catalog_data/catalog.txt"));
                  
                  line = file.readLine();
                        
                  while (line != null) {
                        if (line.equals(name))
                              nameFound = true;
                        else if (nameFound) 
                              return line;
                        
                        line = file.readLine();
                  }
                  
                  file.close();
            } catch (IOException ioe) {
            }
            
            return "";
      }
      
      // Removes a game from the user's library.
      public static void deleteFromLibrary (String name) {
            String line;
            
            try {
                  File libFile = new File("kappystudios/data/user_data/library.txt");
                  File tempFile = new File("kappystudios/data/temp_data/temp.txt");
                  
                  BufferedReader reader = new BufferedReader(new FileReader(libFile));
                  PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(tempFile)));
                  line = reader.readLine();
                  
                  while (line != null) {
                        if (line.equals(name))
                              line = reader.readLine();
                        else 
                              writer.println(line);
                        
                        if (line != null)
                              line = reader.readLine();
                  }
                  
                  writer.close(); 
                  reader.close(); 
                  
                  libFile.delete();
                  
                  tempFile.renameTo(libFile);
            } catch (IOException ioe) {
            }
      }
      
      // Gets the user's library games by name and description.
      public static String[][] getLibrary() {
            String line;
            String[] names = new String[MAX_LIBRARY_GAMES];
            String[] descs = new String[MAX_LIBRARY_GAMES];
            String[][] library;
            int gameCount = 0;
            int mode = 0;
            
            try {
                  BufferedReader file = new BufferedReader(new FileReader("kappystudios/data/user_data/library.txt"));
                  
                  line = file.readLine();
                        
                  while (line != null) {
                        if (mode == 0) {
                              names[gameCount] = line;
                              mode++;
                        } else {
                              descs[gameCount] = line;
                              gameCount++;
                              mode = 0;
                        }
                        
                        line = file.readLine();
                  }
                  
                  file.close();
            } catch (IOException ioe) {
            }
            
            library = new String[2][gameCount];
            
            for (int i = 0; i < gameCount; i++) {
                  library[0][i] = names[i];
                  library[1][i] = descs[i];
            }
            
            return library;
      }
      
      // Gets the colour scheme.
      public static String getLayout() {
            String layout = "default";
            
            try {
                  BufferedReader file = new BufferedReader(new FileReader("kappystudios/data/user_data/layout.txt"));
                  
                  layout = file.readLine();
                  
                  file.close();
            } catch (IOException ioe) {
            }
            
            return layout;
      }
      
      // Sets the colour scheme.
      public static void setLayout (String layout) {
            try {
                  PrintWriter file = new PrintWriter(new FileWriter("kappystudios/data/user_data/layout.txt"));
                  
                  file.print(layout);
                  
                  file.close();
            } catch (IOException ioe) {
            }
      }
      
      // Gets a game's name to be downloaded.
      public static String getDownload() {
            String name = "";
            
            try {
                  BufferedReader file = new BufferedReader(new FileReader("kappystudios/data/temp_data/download.txt"));
                  
                  name = file.readLine();
                  
                  file.close();
                  
                  (new File("kappystudios/data/temp_data/download.txt")).delete();
            } catch (IOException ioe) {
            }
            
            return name;
      }
      
      // Sets a game's name to be downloaded.
      public static void setDownload (String name) {
            try {
                  PrintWriter file = new PrintWriter(new FileWriter("kappystudios/data/temp_data/download.txt"));
                  
                  file.print(name);
                  
                  file.close();
            } catch (IOException ioe) {
            }
      }
      
      // Gets a game's name to be deleted.
      public static String getDelete() {
            String name = "";
            
            try {
                  BufferedReader file = new BufferedReader(new FileReader("kappystudios/data/temp_data/delete.txt"));
                  
                  name = file.readLine();
                  
                  file.close();

                  (new File("kappystudios/data/temp_data/delete.txt")).delete();
            } catch (IOException ioe) {
            }
            
            return name;
      }
      
      // Sets a game's name to be deleted.
      public static void setDelete (String name) {
            try {
                  PrintWriter file = new PrintWriter(new FileWriter("kappystudios/data/temp_data/delete.txt"));
                  
                  file.print(name);
                  
                  file.close();
            } catch (IOException ioe) {
            }
      }
}