import java.time.LocalDate;
import java.util.*;
class Task{
    /* data member */
	private int taskID;
	private String name;
	private String description;
	private int projectID;
	private LocalDate originalST;
	private LocalDate actualST;
	private int originalDuration;
	private int actualDuration;
	private ArrayList<Member> member;
	private ArrayList<Resource> resource;
    private ArrayList<Member> request;
	private static int totalTask = 0;
    private static int nextID = 1;
    /* constructor */
	private Task() {
		taskID = nextID++;
        totalTask++;
	}
	
	public Task(String name, String description, int projectID, LocalDate originalST, LocalDate actualST, int originalDuration, int actualDuration, ArrayList<Member> member, ArrayList<Resource> resource) {
		this();
		this.name = name;
		this.description = description;
		this.projectID = projectID;
		this.originalST = originalST;
		this.actualST = actualST;
		this.originalDuration = originalDuration;
		this.actualDuration = actualDuration;
		this.member = member;
		this.resource = resource;
        this.request = new ArrayList<Member>();
	}
    
    public Task(int taskID, String name, String description, int projectID, LocalDate originalST, LocalDate actualST, int originalDuration, int actualDuration, ArrayList<Member> member, ArrayList<Resource> resource) {
		this.taskID = taskID;
		this.name = name;
		this.description = description;
		this.projectID = projectID;
		this.originalST = originalST;
		this.actualST = actualST;
		this.originalDuration = originalDuration;
		this.actualDuration = actualDuration;
		this.member = member;
		this.resource = resource;
        this.request = new ArrayList<Member>();
        totalTask++;
        nextID = Math.max(nextID, taskID)+1;
	}
    /* method */
    public int getID() {
        return this.taskID;
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
    
	public int getProjectID() {
        return this.projectID;
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
    
    public ArrayList<Member> getMember() {
        return this.member;
    }
    
    public void setMember(ArrayList<Member> member) {
        this.member = member;
    }
    
    public ArrayList<Resource> getResource() {
        return this.resource;
    }
    
    public void setResource(ArrayList<Resource> resource) {
        this.resource = resource;
    }
    
    public ArrayList<Member> getRequest() {
        return request;
    }
    
    public String toString() {
        String s = "taskID : " + taskID
		+"\nname : " + name
		+"\ndescription : " + description
		+"\nprojectID : " + projectID
		+"\noriginalST : " + originalST
		+"\nactualST : " + (actualST.equals(LocalDate.MAX) ? "Processing" : actualST)
		+"\noriginalDuration : " + originalDuration
		+"\nactualDuration : " + (actualDuration == -1 ? "Processing" : actualDuration)
		+"\nmember : ";
        if(member.size() == 0)
            s += "";
        for(int i = 0; i < member.size(); i++) {
            if(i != member.size()-1)
                s += member.get(i).getName() + ",";
            else
                s += member.get(i).getName() + "";
        }
		s += "\nresource : ";
        if(resource.size() == 0)
            s += "";
        for(int i = 0; i < resource.size(); i++) {
            if(i != resource.size()-1)
                s += resource.get(i).getName() + ",";
            else
                s += resource.get(i).getName() + "";
        }
        return s;
    }
}