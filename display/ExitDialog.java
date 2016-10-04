package kappystudios.display;

import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import kappystudios.display.utility.FileController;

// Displays a confirmation dialog to exit the application.
public class ExitDialog extends Dialog {
      // Displays dialog.
      public ExitDialog () {
            listener = new ExitDialogListener(this);
            
            title = "Are you sure that you want to exit?";
            setColors(FileController.getLayout());
            setWindow();
            setHeader();
            setBody();
            
            window.setVisible(true);
      }
}  

// Responds to button pushes on the exit dialog.
class ExitDialogListener extends DialogListener {
      protected ExitDialogListener (ExitDialog dialog) {
            this.dialog = dialog;
      }
      
      // Event handler.
      public void actionPerformed (ActionEvent event) {
            Toolkit.getDefaultToolkit().beep();
            dialog.window.setVisible(false);
            
            JButton butt = (JButton)event.getSource();
            String name = butt.getName();
            
            if (name.equals("yes")) // Exit application.
                  System.exit(0);
      }
}