package todolist;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Task extends JPanel {

    private JLabel index;
    private JLabel taskName;
    private JButton editTaskBtn;
    private JButton doneBtn;
    private JButton removeBtn;
    private boolean taskDone;
    private int id;

    public Task() {
        // Set task layout
        GridLayout TaskLayout = new GridLayout(1, 5);
        TaskLayout.setHgap(10);
        this.setPreferredSize(new Dimension(400, 20));
        this.setBackground(new Color(234, 231, 231));
        this.setLayout(TaskLayout);

        // Set task to not yet done
        taskDone = false;

        // Sets up task index
        index = new JLabel("");
        index.setPreferredSize(new Dimension(10, 20));
        index.setHorizontalAlignment(JLabel.CENTER);
        this.add(this.index);

        // Set up task name
        taskName = new JLabel("Enter task");
        taskName.setPreferredSize(new Dimension(10, 20));
        taskName.setHorizontalAlignment(JLabel.CENTER);
        taskName.setBorder(BorderFactory.createEmptyBorder());
        taskName.setBackground(new Color(234, 231, 231));
        this.add(taskName);


        // Set up edit task button
        editTaskBtn = new JButton("Edit Task");
        editTaskBtn.setBorder(new EmptyBorder(5, 5, 5, 5)); // (top, left, bottom, right) padding
        editTaskBtn.setPreferredSize(new Dimension(10, 20));
        editTaskBtn.setBackground(new Color(211, 208, 208));
        this.add(this.editTaskBtn);

        // Set up done button
        doneBtn = new JButton("Done");
        doneBtn.setBorder(new EmptyBorder(5, 5, 5, 5)); // (top, left, bottom, right) padding
        doneBtn.setPreferredSize(new Dimension(10, 20));
        doneBtn.setBackground(new Color(211, 208, 208));
        this.add(this.doneBtn);

        // Set up remove button
        removeBtn = new JButton("Remove");
        removeBtn.setBorder(new EmptyBorder(5, 5, 5, 5)); // (top, left, bottom, right) padding
        removeBtn.setPreferredSize(new Dimension(5, 20));
        removeBtn.setBackground(new Color(211, 208, 208));
        this.add(this.removeBtn);
    }

    public void setDoneStatus() {
        this.taskName.setOpaque(true);  // Set opaque to true so that the background color is shown
        this.taskName.setBackground(Color.GREEN);
        this.taskDone = true;
        this.revalidate();
        this.repaint();  // Ensure the UI updates the color
    }


    public void writeIndex(int n) {
        this.index.setText(String.valueOf(n));
        this.revalidate();
    }

    public JButton getEditTaskBtn() {
        return this.editTaskBtn;
    }

    public JButton getDone() {
        return this.doneBtn;
    }

    public JButton getRemove() {
        return this.removeBtn;
    }

    public String getTaskName() {
        return taskName.getText();
    }

    public void setTaskName(String taskName) {
        this.taskName.setText(taskName);
    }

    public boolean isTaskDone(){
        return taskDone;
    }

    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
