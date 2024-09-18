package todolist;

import javax.swing.*;
import java.awt.*;

public class List extends JPanel {
    public List() {
        GridLayout Layout = new GridLayout(10, 1);
        Layout.setVgap(5);
        this.setLayout(Layout);
    }

    public void updateIndexNum() {
        Component[] componentList = this.getComponents();
        for(int i = 0; i < componentList.length; i ++) {
            if(componentList[i] instanceof Task) {
                // Writes index of the task
                ((Task)componentList[i]).writeIndex(i + 1);
            }
        }
    }
}
