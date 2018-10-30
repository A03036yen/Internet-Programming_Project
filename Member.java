import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;
public abstract class Member {
	/* data member */
    private int memberID;					// Member-ID
	private String name;					// member's name
	private LocalDate dateOfBirth;			// member's date of birth
	private char gender;					// member's gender('M'/'F')
	private ArrayList<String> abilities;	// member's abilities
	private ArrayList<String> roles;		// member's roles
	private static int totalMember = 0; 	// # of members
    private static int nextID = 1;
	/* constructor */
	private Member() {	// It is private because it should not be called outside
		memberID = nextID++;
        totalMember++;
	}
	
	public Member(String name, LocalDate dateOfBirth, char gender, ArrayList<String> abilities, ArrayList<String> roles) {
        this();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.abilities = abilities;
		this.roles = roles;
	}
    // This Constructor is for get back data from .csv
    public Member(int id, String name, LocalDate dateOfBirth, char gender, ArrayList<String> abilities, ArrayList<String> roles) {
        this.memberID = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.abilities = abilities;
		this.roles = roles;
        ++totalMember;
        nextID = Math.max(id, nextID)+1;
    }
	/* method */
    public int getID() {
        return this.memberID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public char getGender() {
        return this.gender;
    }
    
    public ArrayList<String> getAbilities() {
        return this.abilities;
    }
    
    public ArrayList<String> getRoles() {
        return this.roles;
    }
    
	// for testing purpose only Display Account Information
    public void displayInfo() {
        PMS.rPanel.removeAll();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Display Account Information"));
        String s = "<html>Member-ID : " + memberID
                    + "<br>Name : " + name
                    + "<br>Date of Birth : " + dateOfBirth
                    + "<br>Gender : " + gender
                    + "<br>Abilities : ";
        for(String str : abilities)
            s += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + str;
        s += "<br>Roles : ";
        for(String str : roles)
            s += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + str;
        s += "</html>";
        JLabel infoL = new JLabel(s);
        infoL.setFont(new Font("dejavu sans mono", 1, 22));
        PMS.rPanel.add(infoL);
        PMS.frame.setVisible(true);
    }
    
    public static void login(HashMap<String, HashMap<String, String>> loginData, ArrayList<Member> memberList) {
        PMS.contentPane.removeAll();
        JLabel warningL = new JLabel(" ");
        warningL.setFont(new Font("Times New Roman", 1, 22));
        JLabel usernameL = new JLabel("username");
        usernameL.setFont(new Font("Times New Roman", 1, 22));
        JTextField usernameTF = new JTextField();
        usernameTF.setFont(new Font("Times New Roman", 1, 22));
        JLabel passwordL = new JLabel("password");
        passwordL.setFont(new Font("Times New Roman", 1, 22));
        JPasswordField passwordPF = new JPasswordField();
        passwordPF.setFont(new Font("Times New Roman", 1, 22));
        JButton loginB = new JButton("Login");
        loginB.addActionListener(event -> {
            String username = usernameTF.getText();
            String password = new String(passwordPF.getPassword());
            if(username.equals("") || password.equals(""))
                warningL.setText("Username and Password cannot be empty");
            else if(loginData.get(username) != null && loginData.get(username).get("password").equals(password)) {
                for(Member m : memberList)
                    if(m.getID() == Integer.parseInt(loginData.get(username).get("id"))) {
                        PMS.mainMenu(m);
                        break;
                    }
            }
            else
                warningL.setText("Wrong username or password");
        });
        JButton cancelB = new JButton("Cancel");
        cancelB.addActionListener(event -> {
            PMS.frontPage();
        });
        JPanel panel = new JPanel(new BorderLayout());
        JPanel warningPanel = new JPanel();
        warningPanel.add(warningL);
        GridLayout gridLayout = new GridLayout(3,2);
        gridLayout.setVgap(20);
        gridLayout.setHgap(20);
        JPanel inputPanel = new JPanel(gridLayout);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(150, 350, 250, 350));
        inputPanel.add(usernameL);
        inputPanel.add(usernameTF);
        inputPanel.add(passwordL);
        inputPanel.add(passwordPF);
        inputPanel.add(loginB);
        inputPanel.add(cancelB);
        panel.add(warningPanel, BorderLayout.NORTH);
        panel.add(inputPanel, BorderLayout.CENTER);
        PMS.contentPane.add(panel);
        PMS.frame.setVisible(true);
    }
    
    public void promptSaveAllData(HashMap<String, HashMap<String, String>> loginData, ArrayList<Member> memberList, ArrayList<Project> projectList) {
        String[] choice = {"Yes", "No"};
        int select = JOptionPane.showOptionDialog(null, "Do you want to save all the unrecorded data(if any)?", "save data?", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choice, 0);
        if(select == 0)
            saveAllData(loginData, memberList, projectList);
    }
    
    protected static boolean saveLoginData(String username, String password, int id) {
        String filename = "Login Data.csv";
        File outFile = new File(filename);
        if(!outFile.exists() || !outFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Save File", JOptionPane.ERROR_MESSAGE);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outFile, true);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            String data = username + "," + password + "," + id;
            printWriter.println();
            printWriter.print(data);
            printWriter.close();
        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
        return true;
    }
    
    protected static boolean saveMemberData(Member m) {
        String filename = "Member Data.csv";
        File outFile = new File(filename);
        if(!outFile.exists() || !outFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Save File", JOptionPane.ERROR_MESSAGE);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outFile, true);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            String data = m.getID() + ","
                            + m.getName() + ","
                            + m.getDateOfBirth() + ","
                            + m.getGender() + ",\"";
            if(m.getAbilities().size() == 0)
                    data += "\",\"";
            for(int i = 0; i < m.getAbilities().size(); i++)
            {
                if(i != m.getAbilities().size()-1)
                    data += m.getAbilities().get(i) + ",";
                else
                    data += m.getAbilities().get(i) + "\",\"";
            }
            if(m.getRoles().size() == 0)
                    data += "\",";
            for(int i = 0; i < m.getRoles().size(); i++)
            {
                if(i != m.getRoles().size()-1)
                    data += m.getRoles().get(i) + ",";
                else
                    data += m.getRoles().get(i) + "\",";
            }
            if(m instanceof Administrator)
                data += "Admin";
            else if(m instanceof Manager)
                data += "Manager";
            else if(m instanceof InternalMember)
                data += "Worker";
            else if(m instanceof ExternalMember)
                data += "EM";
            else {
                JOptionPane.showMessageDialog(null, "Wrong Member Type(in saveMemberData())", "ERROR: No Member Type Match", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            printWriter.println();
            printWriter.print(data);
            printWriter.close();
        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
        return true;
    }
    
    protected static void saveAllData(HashMap<String, HashMap<String, String>> loginData, ArrayList<Member> memberList, ArrayList<Project> projectList) {
        rewriteAllMemberData(memberList);
        rewriteAllLoginData(loginData);
        rewriteAllProjectData(projectList);
        rewriteAllTaskData(projectList);
        rewriteAllRequest(projectList);
    }
    
    protected static void rewriteAllMemberData(ArrayList<Member> memberList) {
        String filename = "Member Data.csv";
        File outFile = new File(filename);
        if(!outFile.exists() || !outFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Save File", JOptionPane.ERROR_MESSAGE);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.print("id,name,dateOfBirth,gender,abilities,roles,type");
            for(Member m : memberList) {
                String data = m.getID() + ","
                                + m.getName() + ","
                                + m.getDateOfBirth() + ","
                                + m.getGender() + ",\"";
                if(m.getAbilities().size() == 0)
                    data += "\",\"";
                for(int i = 0; i < m.getAbilities().size(); i++)
                {
                    if(i != m.getAbilities().size()-1)
                        data += m.getAbilities().get(i) + ",";
                    else
                        data += m.getAbilities().get(i) + "\",\"";
                }
                if(m.getRoles().size() == 0)
                    data += "\",";
                for(int i = 0; i < m.getRoles().size(); i++)
                {
                    if(i != m.getRoles().size()-1)
                        data += m.getRoles().get(i) + ",";
                    else
                        data += m.getRoles().get(i) + "\",";
                }
                if(m instanceof Administrator)
                    data += "Admin";
                else if(m instanceof Manager)
                    data += "Manager";
                else if(m instanceof InternalMember)
                    data += "Worker";
                else if(m instanceof ExternalMember)
                    data += "EM";
                else {
                    JOptionPane.showMessageDialog(null, "Wrong Member Type(in saveMemberData())", "ERROR: No Member Type Match", JOptionPane.ERROR_MESSAGE);
                }
                printWriter.println();
                printWriter.print(data);
            }
            printWriter.close();
        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
    }
    
    protected static void rewriteAllLoginData(HashMap<String, HashMap<String, String>> loginData) {
        String filename = "Login Data.csv";
        File outFile = new File(filename);
        if(!outFile.exists() || !outFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Save File", JOptionPane.ERROR_MESSAGE);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.print("username,password,id");
            for(Map.Entry<String, HashMap<String, String>> entry : loginData.entrySet()) {
                String key = entry.getKey();
                HashMap<String, String> value = entry.getValue();
                String data = key + "," + value.get("password") + "," + value.get("id");
                printWriter.println();
                printWriter.print(data);
            }
            printWriter.close();
        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
    }
    
    protected static void rewriteAllProjectData(ArrayList<Project> projectList) {
        String filename = "Project Data.csv";
        File outFile = new File(filename);
        if(!outFile.exists() || !outFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Save File", JOptionPane.ERROR_MESSAGE);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.print("id,name,description,manager,oST,aST,oDuration,aDuration,taskList");
            for(Project p : projectList) {
                String data = p.getID()+","+p.getName()+","+p.getDescription()+","+p.getManagerID()+","+p.getOriginalST()+","+p.getActualST()+","+p.getOriginalDuration()+","+p.getActualDuration()+",\"";
                for(int i = 0; i < p.getTaskList().size(); i++)
                    if(i != p.getTaskList().size()-1)
                        data += p.getTaskList().get(i).getID() + ",";
                    else
                        data += p.getTaskList().get(i).getID();
				data += "\"";
                printWriter.println();
                printWriter.print(data);
            }
            printWriter.close();
        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
    }
    
    protected static void rewriteAllTaskData(ArrayList<Project> projectList) {
        String filename = "Task Data.csv";
        File outFile = new File(filename);
        if(!outFile.exists() || !outFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Save File", JOptionPane.ERROR_MESSAGE);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.print("id,name,description,project,oST,aST,oDuration,aDuration,member,resource");
            for(Project p : projectList) {
                for(Task t : p.getTaskList()) {
                    String data = t.getID()+","+t.getName()+","+t.getDescription()+","+t.getProjectID()+","+t.getOriginalST()+","+t.getActualST()+","+t.getOriginalDuration()+","+t.getActualDuration()+",\"";
                    if(t.getMember().size() == 0)
                        data += "\",\"";
                    for(int i = 0; i < t.getMember().size(); i++) {
                        if(i != t.getMember().size()-1)
                            data += t.getMember().get(i).getID() + ",";
                        else
                            data += t.getMember().get(i).getID() + "\",\"";
                    }
                    if(t.getResource().size() == 0)
                        data += "\"";
                    for(int i = 0; i < t.getResource().size(); i++) {
                        if(i != t.getResource().size()-1)
                            data += t.getResource().get(i).getID() + ",";
                        else
                            data += t.getResource().get(i).getID() + "\"";
                    }
                    printWriter.println();
                    printWriter.print(data);
                }
            }
            printWriter.close();
        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
    }
    
    public static void rewriteAllRequest(ArrayList<Project> projectList) {
        String filename = "Request.csv";
        File outFile = new File(filename);
        if(!outFile.exists() || !outFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Save File", JOptionPane.ERROR_MESSAGE);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.print("memberID,taskID");
            for(Project p : projectList) {
                for(Task t : p.getTaskList()) {
                    if(t.getRequest().size() > 0) {
                        printWriter.println();
                        printWriter.print(t.getRequest().get(0).getID() + "," + t.getID());
                    }
                }
            }
            printWriter.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}