package kappystudios.display;

import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import kappystudios.display.utility.FileController;

// Displays a dialog to confirm a game download.
public class DownloadDialog extends Dialog {
      // Displays dialog.
      public DownloadDialog () {
            listener = new DownloadDialogListener(this);
            
            title = "Are you sure that you want to download this game?";
            setColors(FileController.getLayout());
            setWindow();
            setHeader();
            setBody();
            
            window.setVisible(true);
      }
}  

// Responds to the download dialog button pushes.
class DownloadDialogListener extends DialogListener {
      protected DownloadDialogListener (DownloadDialog dialog) {
            this.dialog = dialog;
      }
      
      // Event handler.
      public void actionPerformed (ActionEvent event) {
            Toolkit.getDefaultToolkit().beep();
            dialog.window.setVisible(false);
            
            JButton butt = (JButton)event.getSource();
            String name = butt.getName();
            
            if (name.equals("yes")) // Download game.
                  FileController.addToLibrary(FileController.getDownload());
            
            Catalog catalog = new Catalog();
      }
}