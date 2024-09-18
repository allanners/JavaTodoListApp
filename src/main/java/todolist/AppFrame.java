package todolist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppFrame extends JFrame {

    private JButton addTaskBtn;
    private JButton clearBtn;

    List taskList = new List();
    TitleBar title = new TitleBar();
    ButtonPanel buttonPanel = new ButtonPanel();

    public AppFrame() {
        // Initialize window
        this.setSize(800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("To-Do List");
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.buttonPanel, BorderLayout.SOUTH);
        this.add(this.taskList, BorderLayout.CENTER);

        // Initialize buttons
        addTaskBtn = buttonPanel.getAddTaskButton();
        clearBtn = buttonPanel.getClearButton();

        // Adds listeners
        addListener();

        // Initialize database and load tasks
        DatabaseHelper.initialize();
        loadTasksFromDatabase();
    }

    /**
     * Adds mouse listeners for every task created
     */
    public void addListener() {
        addTaskBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Creates new task and adds it to taskList
                super.mousePressed(e);
                Task task = new Task();
                taskList.add(task);
                taskList.updateIndexNum();
                revalidate();

                // Add task listeners for edit button, done button, and remove button
                setupTaskListeners(task);

                // Add the task to the database
                addTaskToDatabase(task);
            }
        });

        clearBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Component[] taskList = AppFrame.this.taskList.getComponents();
                for (Component component : taskList) {
                    if (component instanceof Task) {
                        // Removes the task
                        removeTaskFromDatabase((Task) component);
                        AppFrame.this.taskList.remove(component);
                    }
                }
                revalidate();
                repaint();
            }
        });
    }

    private void setupTaskListeners(Task task) {
        // Set up done button
        JButton doneBtn = task.getDone();
        doneBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                task.setDoneStatus();
                updateTaskInDatabase(task);
                revalidate();
            }
        });

        // Set up done button
        JButton editTaskBtn = task.getEditTaskBtn();
        editTaskBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                String newTaskName = promptForTaskName();
                if (newTaskName != null && !newTaskName.trim().isEmpty()) {
                    task.setTaskName(newTaskName);
                    updateTaskInDatabase(task);
                    revalidate();
                }
            }
        });

        // Set up remove button
        JButton removeBTN = task.getRemove();
        removeBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                removeTaskFromDatabase(task);
                taskList.remove(task);
                taskList.updateIndexNum();
                revalidate();
                repaint();
            }
        });
    }

    /**
     * Loads tasks from the database
     *
     */

    private void loadTasksFromDatabase() {
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tasks");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Task task = new Task();
                task.setTaskName(rs.getString("name"));
                task.setId(rs.getInt("id"));
                task.setTaskDone(rs.getBoolean("done"));
                if (task.isTaskDone()) {
                    task.setDoneStatus();
                }

                taskList.add(task);
                taskList.updateIndexNum();
                setupTaskListeners(task);  // Add listeners to existing tasks from DB
            }
        } catch (SQLException e) {
            ErrorPopup.showError("Failed to load task from database.\n" + e.getMessage());
        }
    }

    private void addTaskToDatabase(Task task) {
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tasks (name, done) VALUES (?, ?)")) {
            pstmt.setString(1, task.getTaskName());
            pstmt.setBoolean(2, false);
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    task.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            ErrorPopup.showError("Failed to add task to database.\n" + e.getMessage());
        }
    }

    private void updateTaskInDatabase(Task task) {
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE tasks SET name = ?, done = ? WHERE id = ?")) {
            pstmt.setString(1, task.getTaskName());
            pstmt.setBoolean(2, task.isTaskDone());
            pstmt.setInt(3, task.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            ErrorPopup.showError("Failed to update task in database..\n" + e.getMessage());
        }
    }

    private void removeTaskFromDatabase(Task task) {
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM tasks WHERE id = ?")) {
            pstmt.setInt(1, task.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            ErrorPopup.showError("Failed to remove task from database.\n" + e.getMessage());
        }
    }

    private String promptForTaskName() {
        String newTaskName;
        do {
            newTaskName = JOptionPane.showInputDialog("Enter new task name:");
        } while (newTaskName != null && newTaskName.trim().isEmpty());

        return newTaskName;
    }

}
