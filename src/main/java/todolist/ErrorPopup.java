package todolist;

import javax.swing.JOptionPane;

public class ErrorPopup {

    // Method to show error message
    public static void showError(String errorMessage) {
        // Displays a pop-up dialog with an error message
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
