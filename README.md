# To-Do List Application

## Description

This is a Java-based To-Do List application built using Maven and Swing. The application allows users to manage tasks efficiently by providing a graphical user interface (GUI) to add, edit, mark as done, and remove tasks. It uses SQLite as the database to store and retrieve task information, ensuring that tasks persist between application sessions.

## Features

- **Task Management**: Add new tasks, edit existing tasks, mark tasks as done, and remove tasks.
- **Persistence**: Tasks are stored and retrieved from an SQLite database.
- **Graphical User Interface**: Built with Swing for a responsive and interactive user experience.

## Getting Started

To get started with this project, follow these steps:

### Prerequisites

- **Java Development Kit (JDK)**: Ensure that you have JDK 8 or higher installed.
- **Maven**: Install Maven for managing project dependencies and building the project.
- **SQLite**: SQLite database should be set up for task storage.

### Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/todolist.git
   cd todolist
   ```

2. **Configure the Database**

   - Place your `todolist.db` SQLite database file in the `src/main/resources` directory of the project.

3. **Build the Project**

   Use Maven to build the project and download dependencies:

   ```bash
   mvn clean install
   ```

4. **Run the Application**

   Execute the main class to start the application:

   ```bash
   mvn exec:java -Dexec.mainClass="todolist.Main"
   ```

### Usage

- **Adding Tasks**: Click on the "ADD TASK" button to create a new task.
- **Editing Tasks**: Use the "EDIT TASK" button on any task to change its name.
- **Marking Tasks as Done**: Click the "DONE" button on any task to mark it as completed.
- **Removing Tasks**: Click the "REMOVE" button on any task to delete it.
- **Clearing All Tasks**: Click the "CLEAR TASK" button to remove all tasks from the list.

## Contributing

Feel free to contribute to this project by submitting issues or pull requests
