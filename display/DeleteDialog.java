package kappystudios.display;

import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import kappystudios.display.utility.FileController;

// Displays a dialog to confirm a game deletion.
public class DeleteDialog extends Dialog {
      // Displays dialog.
      public DeleteDialog () {
            listener = new DeleteDialogListener(this);
            
            title = "Are you sure that you want to delete this game?";
            setColors(FileController.getLayout());
            setWindow();
            setHeader();
            setBody();
            
            window.setVisible(true);
      }
}  

// Manages responses to the deletion dialog.
class DeleteDialogListener extends DialogListener {
      protected DeleteDialogListener (DeleteDialog dialog) {
            this.dialog = dialog;
      }
      
      // Event handler.
      public void actionPerformed (ActionEvent event) {
            Toolkit.getDefaultToolkit().beep();
            dialog.window.setVisible(false);
            
            JButton butt = (JButton)event.getSource();
            String name = butt.getName();
            
            if (name.equals("yes")) // Remove game.
                  FileController.deleteFromLibrary(FileController.getDelete());
            
            Library library = new Library();
      }
}