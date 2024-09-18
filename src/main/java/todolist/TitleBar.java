package todolist;

import javax.swing.*;
import java.awt.*;

public class TitleBar extends JPanel {
    public TitleBar() {
        // Set the layout manager to BoxLayout with vertical alignment
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set preferred size and background color
        this.setPreferredSize(new Dimension(400, 100));
        this.setBackground(new Color(88, 146, 167));

        // Create and configure the JLabel
        JLabel title = new JLabel("To-Do List");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT); // Center text horizontally

        // Add glue to center the label vertically
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(Box.createVerticalGlue());
    }
}
