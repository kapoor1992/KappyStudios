package kappystudios;

import kappystudios.display.LaunchBox;
import kappystudios.display.Library;

// Launches the Kappy Studios application.
public class Main {
      public static void main (String[] args) {
            // Display launcher.
            LaunchBox launcher = new LaunchBox();
            
            // Allow launcher to remain for at least two seconds. 
            try {
                  Thread.sleep(2000);
            } catch (InterruptedException ie) {
            }
            
            // Display user library.
            Library library = new Library();
            
            // Make the launcher go away.
            launcher.disappear();
      } 
}