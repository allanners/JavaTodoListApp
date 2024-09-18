package todolist;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ButtonPanel extends JPanel {

    private JButton addTask;
    private JButton clear;

    public ButtonPanel(){
        this.setPreferredSize(new Dimension(400, 80));
        this.setBackground(new Color(102, 171, 195));

        addTask = new JButton("ADD TASK");
        addTask.setFocusable(false);
        addTask.setBackground(new Color(211, 208, 208));
        addTask.setBorder(new EmptyBorder(10, 20, 10, 20)); // (top, left, bottom, right) padding
        addTask.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.add(addTask);

        this.add(Box.createHorizontalStrut(20));

        clear = new JButton("CLEAR TASK");
        clear.setFocusable(false);
        clear.setBackground(new Color(211, 208, 208));
        clear.setBorder(new EmptyBorder(10, 20, 10, 20)); // (top, left, bottom, right) padding
        clear.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.add(clear);
    }

    public JButton getAddTaskButton() {
        return addTask;
    }

    public JButton getClearButton(){
        return clear;
    }
}
