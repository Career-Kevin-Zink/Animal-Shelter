package Application;

import Database.*;
import java.util.HashMap;

public class TaskManager {

    public static HashMap<Integer, Task> existingTasks = new HashMap<>();
    public static HashMap<Integer, AssignedTask> assignedTasks = new HashMap<>();

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

        public String toString(){return String.format("(%d) : %s", taskID, taskName);}
    }

    public static class AssignedTask {
        public int id;
        public Task task;
        public Employee employee;
        public Kennel kennel;
        public Animal animal;

        public AssignedTask(int id, Task task, Employee employee, Kennel kennel, Animal animal) {
            this.id = id;
            this.task = task;
            this.employee = employee;
            this.kennel = kennel;
            this.animal = animal;
            System.out.println(this);
        }

        public void setID(int ID){
            this.id = id;
        }
        public String toString(){
            return String.format("ID: %d | Task: %s | Animal: %s | Employee: %s | Kennel: %s",
                this.id, this.task, this.animal, this.employee, this.kennel);

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
