import java.time.LocalDate;
import java.util.*;

class Project {
    /* data member */
	private int projectID;
	private String name;
	private String description;
	private int managerID;
	private LocalDate originalST;
	private LocalDate actualST;
	private int originalDuration;
    private int actualDuration;
	private ArrayList<Task> taskList;
	private static int totalProject = 0;
    private static int nextID = 1;
    /* constructor */
	private Project(){
		projectID = nextID++;
        totalProject++;
	}
	
	public Project (String name, String description, int managerID, LocalDate originalST, LocalDate actualST, int originalDuration, int actualDuration, ArrayList<Task> taskList){
		this();
		this.name = name;
		this.description = description;
		this.managerID = managerID;
		this.originalST = originalST;
		this.actualST = actualST;
		this.originalDuration = originalDuration;
        this.actualDuration = actualDuration;
		this.taskList = taskList;
	}
    
    public Project (int projectID, String name, String description, int managerID, LocalDate originalST, LocalDate actualST, int originalDuration, int actualDuration, ArrayList<Task> taskList){
		this.projectID = projectID;
		this.name = name;
		this.description = description;
		this.managerID = managerID;
		this.originalST = originalST;
		this.actualST = actualST;
		this.originalDuration = originalDuration;
        this.actualDuration = actualDuration;
		this.taskList = taskList;
        ++totalProject;
        nextID = Math.max(projectID, nextID)+1;
	}
    /* method */
    public int getID() {
        return this.projectID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getManagerID() {
        return this.managerID;
    }
    
    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }
    
    public LocalDate getOriginalST() {
        return this.originalST;
    }
    
    public void setOriginalST(LocalDate originalST) {
        this.originalST = originalST;
    }
    
    public LocalDate getActualST() {
        return this.actualST;
    }
    
    public void setActualST(LocalDate actualST) {
        this.actualST = actualST;
    }
    
    public int getOriginalDuration() {
        return this.originalDuration;
    }
    
    public void setOriginalDuration(int originalST) {
        this.originalDuration = originalST;
    }
    
    public int getActualDuration() {
        return this.actualDuration;
    }
    
    public void setActualDuration(int actualDuration) {
        this.actualDuration = actualDuration;
    }
    
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
    
    public String toString() {
        String s = "projectID : " + projectID
		+"\nname : " + name
		+"\ndescription : " + description
		+"\nmanagerID : " + managerID
		+"\noriginalST : " + originalST
		+"\nactualST : " + (actualST.equals(LocalDate.MAX) ? "Not yet started" : actualST)
		+"\noriginalDuration : " + originalDuration
		+"\nactualDuration : " + (actualST.equals(LocalDate.MAX) ? "Not yet started" : (actualDuration == -1 ? "Processing" : actualDuration))
		+"\ntaskList : ";
        if(taskList.size() == 0)
            s += "";
        for(int i = 0; i < taskList.size(); i++) {
            if(i != taskList.size()-1)
                s += taskList.get(i).getName() + ",";
            else
                s += taskList.get(i).getName() + "";
        }
        return s;
    }
}