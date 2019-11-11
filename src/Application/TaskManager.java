package Application;

import Database.*;
import java.util.HashMap;

public class TaskManager {

    public static HashMap<Integer, Task> existingTasks = new HashMap<>();

    public static class Task {

        public int taskID;
        public String taskName;
        public String taskDescription;

        public Task(String taskName, String taskDescription) {
            this.taskName = taskName;
            this.taskDescription = taskDescription;
            this.taskID = Database.saveNewTask(this);
            existingTasks.put(this.taskID, this);
        }

        public Task(int taskID, String taskName, String taskDescription) {
            this.taskID = taskID;
            this.taskName = taskName;
            this.taskDescription = taskDescription;
        }

        public int getTaskID() {
            return taskID;
        }

        public String getTaskName() {
            return taskName;
        }

        public String getTaskDescription() {
            return taskDescription;
        }
    }

    public static class AssignedTask {

        public Task assignedTask;
        public Employee assignedToEmployee;
        public Kennel assignedToKennel;
        public Animal assignedToAnimal;

        public AssignedTask(Task assignedTask, Employee assignedToEmployee) {
            this.assignedTask = assignedTask;
            this.assignedToEmployee = assignedToEmployee;
            this.assignedToKennel = null;
            this.assignedToAnimal = null;
        }

        public AssignedTask(Task assignedTask, Kennel assignedToKennel) {
            this.assignedTask = assignedTask;
            this.assignedToKennel = assignedToKennel;
            this.assignedToEmployee = null;
            this.assignedToAnimal = null;
        }

        public AssignedTask(Task assignedTask, Animal assignedToAnimal) {
            this.assignedTask = assignedTask;
            this.assignedToAnimal = assignedToAnimal;
            this.assignedToEmployee = null;
            this.assignedToKennel = null;
        }
    }

    public static Task createTask(String taskName, String taskDescription) {
        Task newTask = new Task(taskName, taskDescription);
        existingTasks.put(newTask.getTaskID(), newTask);
        return newTask;
    }

    public static java.util.Collection<Task> getTasks() {
        return existingTasks.values();
    }
}
