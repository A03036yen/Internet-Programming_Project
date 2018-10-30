import java.util.*;
public class Resource {   /* data resource */

    private int resourceID;					// resource-ID
	private String name;					// resource's name
	private String description;	// resource's description
	private String typesOfResource;		// resource's type
	private double cost;
	private static int totalResource = 0; 	// # of resource
    private static int nextID = 1;
	/* constructor */
	private Resource() {
		resourceID = nextID++;
        totalResource++;
	}
	
	public Resource(String name, String typesOfResource, String description, double cost) {
        this();
		this.name = name;
		this.typesOfResource = typesOfResource;
		this.description = description;
		this.cost = cost;
	}
    
    public Resource(int resourceID, String name, String typesOfResource, String description, double cost) {
        this.resourceID = resourceID;
		this.name = name;
		this.typesOfResource = typesOfResource;
		this.description = description;
		this.cost = cost;
        totalResource++;
        nextID = Math.max(resourceID, nextID)+1;
	}
	/* method */
    public int getID() {
        return this.resourceID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getTypeOfResource() {
        return this.typesOfResource;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public double getCost() {
        return this.cost;
    }
    
	public String toString() {
        String s = "Resource-ID : " + resourceID
				+ "\nname : " + name
				+ "\ntypesOfResource : " + typesOfResource
				+ "\ncost : " + cost
				+ "\ndescription : " + description;
        return s;
	}
}