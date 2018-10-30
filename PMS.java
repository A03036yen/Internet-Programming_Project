import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.*;
import javax.swing.*;

public class PMS extends JFrame {
    public static JFrame frame;
    public static Container contentPane;
    public static JPanel lPanel;
    public static JPanel rPanel;
    private static HashMap<String, HashMap<String, String>> loginData = loadLoginData();
    private static ArrayList<Member> memberList = loadMemberData();
    private static ArrayList<Resource> resourceList = loadResourceData();
    private static ArrayList<Project> projectList = loadProjectData(memberList, resourceList);
    
    public PMS() {
        this.setTitle("Ubibug Gaming Industry Project Management System");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPane = this.getContentPane();
        this.setVisible(true);
    }
    
    public static void frontPage() {
        contentPane.removeAll();
        JLabel title = new JLabel("Ubibug Gaming Industry Project Management System");
        title.setFont(new Font("Times New Roman", 1, 36));
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(event -> {
            Member.login(loginData, memberList);
        });
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(event -> {
            ExternalMember.register(loginData, memberList);
        });
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(event -> {
            System.exit(0);
        });
        JPanel panel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel();
        titlePanel.setSize(1000, 100);
        titlePanel.add(title);
        GridLayout gridLayout = new GridLayout(3,1);
        gridLayout.setVgap(20);
        JPanel buttonPanel = new JPanel(gridLayout);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 350, 150, 350));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(quitButton);
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(panel);
        frame.setVisible(true);
    }
    
    public static void mainMenu(Member user) {
        contentPane.removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        GridLayout gridLayout = new GridLayout(7,1);
        gridLayout.setVgap(20);
        lPanel = new JPanel(gridLayout);
        lPanel.setPreferredSize(new Dimension(250, 600));
        lPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
        if(user instanceof Administrator) {
            Administrator admin = (Administrator) user;
            JButton dispAccB = new JButton("Display Account Information");
            dispAccB.addActionListener(event -> {
                admin.displayInfo();
            });
            JButton manageAccB = new JButton("Manage Account");
            manageAccB.addActionListener(event -> {
                admin.manageAccount(loginData, memberList, projectList);
            });
            JButton searchB = new JButton("Search/List");
            searchB.addActionListener(event -> {
                admin.search(memberList, projectList);
            });
            JButton manageProjB = new JButton("Manage Project/Task");
            manageProjB.addActionListener(event -> {
                admin.manageProject(memberList, projectList, resourceList);
            });
            JButton performanceB = new JButton("Performance");
            performanceB.addActionListener(event -> {
                admin.performance(memberList, projectList);
            });
            lPanel.add(dispAccB);
            lPanel.add(manageAccB);
            lPanel.add(searchB);
            lPanel.add(manageProjB);
            lPanel.add(performanceB);
        }
        else if(user instanceof Manager) {
            Manager manager = (Manager) user;
            JButton dispAccB = new JButton("Display Account Information");
            dispAccB.addActionListener(event -> {
                manager.displayInfo();
            });
            JButton searchB = new JButton("Search/List");
            searchB.addActionListener(event -> {
                manager.search(memberList, projectList, resourceList);
            });
            JButton manageProjB = new JButton("Manage Project/Task");
            manageProjB.addActionListener(event -> {
                manager.manageProjectTask(memberList, projectList, resourceList);
            });
            JButton showRequestB = new JButton("Show Requests");
            showRequestB.addActionListener(event -> {
                manager.showRequests(projectList);
            });
            lPanel.add(dispAccB);
            lPanel.add(searchB);
            lPanel.add(manageProjB);
            lPanel.add(showRequestB);
        }
        else if(user instanceof InternalMember) {
            InternalMember im = (InternalMember) user;
            JButton dispAccB = new JButton("Display Account Information");
            dispAccB.addActionListener(event -> {
                im.displayInfo();
            });
            JButton searchB = new JButton("Search/List Project");
            searchB.addActionListener(event -> {
                im.searchProject(projectList);
            });
            lPanel.add(dispAccB);
            lPanel.add(searchB);
        }
        else if(user instanceof ExternalMember) {
            ExternalMember em = (ExternalMember) user;
            JButton dispAccB = new JButton("Display Account Information");
            dispAccB.addActionListener(event -> {
                em.displayInfo();
            });
            JButton browseAvailB = new JButton("Browse Available Project/Task");
            browseAvailB.addActionListener(event -> {
                em.browseAvailableProject(memberList, projectList);
            });
            JButton dispJoinedB = new JButton("Display Joined Project/Task");
            dispJoinedB.addActionListener(event -> {
                em.displayJoinedProject(projectList);
            });
            lPanel.add(dispAccB);
            lPanel.add(browseAvailB);
            lPanel.add(dispJoinedB);
        }
        JButton logoutB = new JButton("Logout");
        logoutB.addActionListener(event -> {
            user.promptSaveAllData(loginData, memberList, projectList);
            frontPage(); // put into above method;
        });
        JButton quitB = new JButton("Quit");
        quitB.addActionListener(event -> {
            user.promptSaveAllData(loginData, memberList, projectList);
            System.exit(0);
        });
        lPanel.add(logoutB);
        lPanel.add(quitB);
        rPanel = new JPanel();
        rPanel.setPreferredSize(new Dimension(725, 600));
        rPanel.setBorder(BorderFactory.createTitledBorder("Display Account Information"));
        user.displayInfo();
        panel.add(lPanel, BorderLayout.WEST);
        panel.add(rPanel, BorderLayout.EAST);
        contentPane.add(panel);
        frame.setVisible(true);
    }
    
	public static void main(String[] args) {
        loadRequest(memberList);
        frame = new PMS();
        frontPage();
	}
    
    private static HashMap<String, HashMap<String, String>> loadLoginData(){
        String filename = "Login Data.csv";
        File inFile = new File(filename);
        if(!inFile.exists() || !inFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Load File", JOptionPane.ERROR_MESSAGE);
        }
        try {
            HashMap<String, HashMap<String, String>> loginData = new HashMap<>();
            Scanner scanner = new Scanner(inFile);
            String regex = "((?:(?<=,\"|^\")[^\"]*?(?=\"))|(?:(?<=,|^)(?:(?:[^\",][^,]*[^\",])|(?:[^\",]*?))(?=,|$)))";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(scanner.nextLine());
            ArrayList<String> key = new ArrayList<>();
            while(m.find())
                    key.add(m.group(1));
            while(scanner.hasNextLine()) {
                HashMap<String, String> hash = new HashMap<>();
                m.reset(scanner.nextLine());
                m.find();
                String username = m.group(1);
                int i = 1;
                while(m.find()) {
                    hash.put(key.get(i), m.group(1));
                    i++;
                }
                loginData.put(username, hash);
            }
            return loginData;
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
        return null;
    }
    
    private static ArrayList<Member> loadMemberData(){
        String filename = "Member Data.csv";
        File inFile = new File(filename);
        if(!inFile.exists() || !inFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Load File", JOptionPane.ERROR_MESSAGE);
        }
        try {
            ArrayList<HashMap<String, String>> memberData = new ArrayList<>();
            Scanner scanner = new Scanner(inFile);
            String regex = "((?:(?<=,\"|^\")[^\"]*?(?=\"))|(?:(?<=,|^)(?:(?:[^\",][^,]*[^\",])|(?:[^\",]*?))(?=,|$)))";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(scanner.nextLine());
            ArrayList<String> key = new ArrayList<>();
            while(m.find())
                    key.add(m.group(1));
            while(scanner.hasNextLine()) {
                HashMap<String, String> hash = new HashMap<>();
                m.reset(scanner.nextLine());
                int i = 0;
                while(m.find()) {
                    hash.put(key.get(i), m.group(1));
                    i++;
                }
                memberData.add(hash);
            }
            ArrayList<Member> memberList = new ArrayList<>();
            for(int i = 1; i <= memberData.size(); i++) {
                int memberID = Integer.parseInt(memberData.get(i-1).get("id"));
                String name = memberData.get(i-1).get("name");
                LocalDate dateOfBirth = LocalDate.parse(memberData.get(i-1).get("dateOfBirth"));
                char gender = memberData.get(i-1).get("gender").charAt(0);
                ArrayList<String> abilities = new ArrayList<String>(Arrays.asList(memberData.get(i-1).get("abilities").split(",")));
                ArrayList<String> roles = new ArrayList<String>(Arrays.asList(memberData.get(i-1).get("roles").split(",")));
                String type = memberData.get(i-1).get("type");
                if(type.equals("EM"))
                    memberList.add(new ExternalMember(memberID, name, dateOfBirth, gender, abilities, roles));
                else if(type.equals("Manager"))
                    memberList.add(new Manager(memberID, name, dateOfBirth, gender, abilities, roles));
                else if(type.equals("Worker"))
                    memberList.add(new InternalMember(memberID, name, dateOfBirth, gender, abilities, roles));
                else if(type.equals("Admin"))
                    memberList.add(new Administrator(memberID, name, dateOfBirth, gender, abilities, roles));
            }
            return memberList;
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "There are something wrong with your 'Member Data.csv' file");
            System.exit(0);
        }
        return null;
    }

    private static ArrayList<Resource> loadResourceData() {
        String filename = "Resource Data.csv";
        File inFile = new File(filename);
        if(!inFile.exists() || !inFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Load File", JOptionPane.ERROR_MESSAGE);
        }
        try {
            ArrayList<HashMap<String, String>> resourceData = new ArrayList<>();
            Scanner scanner = new Scanner(inFile);
            String regex = "((?:(?<=,\"|^\")[^\"]*?(?=\"))|(?:(?<=,|^)(?:(?:[^\",][^,]*[^\",])|(?:[^\",]*?))(?=,|$)))";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(scanner.nextLine());
            ArrayList<String> key = new ArrayList<>();
            while(m.find())
                    key.add(m.group(1));
            while(scanner.hasNextLine()) {
                HashMap<String, String> hash = new HashMap<>();
                m.reset(scanner.nextLine());
                int i = 0;
                while(m.find()) {
                    hash.put(key.get(i), m.group(1));
                    i++;
                }
                resourceData.add(hash);
            }
            ArrayList<Resource> resourceList = new ArrayList<>();
            for(int i = 1; i <= resourceData.size(); i++) {
                int resourceID = Integer.parseInt(resourceData.get(i-1).get("id"));
                String name = resourceData.get(i-1).get("name");
                String description = resourceData.get(i-1).get("description");
                String typesOfResource = resourceData.get(i-1).get("type");
                double cost = Double.parseDouble(resourceData.get(i-1).get("cost"));
                resourceList.add(new Resource(resourceID, name, typesOfResource, description, cost));
            }
            return resourceList;
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "There are something wrong with your 'Resource Data.csv' file");
            System.exit(0);
        }
        return null;
    }
    
    private static ArrayList<Project> loadProjectData(ArrayList<Member> memberList, ArrayList<Resource> resourceList) {
        String filename = "Task Data.csv";
        File inFile = new File(filename);
        if(!inFile.exists() || !inFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Load File", JOptionPane.ERROR_MESSAGE);
        }
        ArrayList<Task> allTaskList = new ArrayList<>();
        try {
            ArrayList<HashMap<String, String>> taskData = new ArrayList<>();
            Scanner scanner = new Scanner(inFile);
            String regex = "((?:(?<=,\"|^\")[^\"]*?(?=\"))|(?:(?<=,|^)(?:(?:[^\",][^,]*[^\",])|(?:[^\",]*?))(?=,|$)))";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(scanner.nextLine());
            ArrayList<String> key = new ArrayList<>();
            while(m.find())
                    key.add(m.group(1));
            while(scanner.hasNextLine()) {
                HashMap<String, String> hash = new HashMap<>();
                m.reset(scanner.nextLine());
                int i = 0;
                while(m.find()) {
                    hash.put(key.get(i), m.group(1));
                    i++;
                }
                taskData.add(hash);
            }
            for(int i = 1; i <= taskData.size(); i++) {
                int taskID = Integer.parseInt(taskData.get(i-1).get("id"));
                String name = taskData.get(i-1).get("name");
                String description = taskData.get(i-1).get("description");
                int projectID = Integer.parseInt(taskData.get(i-1).get("project"));
                LocalDate originalST = LocalDate.parse(taskData.get(i-1).get("oST"));
                LocalDate acutalST = taskData.get(i-1).get("aST").equals("") ? LocalDate.MAX : LocalDate.parse(taskData.get(i-1).get("aST"));
                int originalDuration = Integer.parseInt(taskData.get(i-1).get("oDuration"));
                int actualDuration = Integer.parseInt(taskData.get(i-1).get("aDuration"));
                ArrayList<String> taskMemberNum = new ArrayList<>(Arrays.asList(taskData.get(i-1).get("member").split(",")));
                ArrayList<Member> member = new ArrayList<>();
                for(Member mem : memberList)
                    if(taskMemberNum.contains(Integer.toString(mem.getID())))
                        member.add(mem);
                ArrayList<String> taskResourceNum = new ArrayList<>(Arrays.asList(taskData.get(i-1).get("resource").split(",")));
                ArrayList<Resource> resource = new ArrayList<>();
                for(Resource r : resourceList)
                    if(taskResourceNum.contains(Integer.toString(r.getID())))
                        resource.add(r);
                Task t = new Task(taskID, name, description, projectID, originalST, acutalST, originalDuration, actualDuration, member, resource);
                allTaskList.add(t);
            }
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "There are something wrong with your 'Task Data.csv' file");
            System.exit(0);
        }
        
        filename = "Project Data.csv";
        inFile = new File(filename);
        if(!inFile.exists() || !inFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Load File", JOptionPane.ERROR_MESSAGE);
        }
        try {
            ArrayList<HashMap<String, String>> projectData = new ArrayList<>();
            Scanner scanner = new Scanner(inFile);
            String regex = "((?:(?<=,\"|^\")[^\"]*?(?=\"))|(?:(?<=,|^)(?:(?:[^\",][^,]*[^\",])|(?:[^\",]*?))(?=,|$)))";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(scanner.nextLine());
            ArrayList<String> key = new ArrayList<>();
            while(m.find())
                    key.add(m.group(1));
            while(scanner.hasNextLine()) {
                HashMap<String, String> hash = new HashMap<>();
                m.reset(scanner.nextLine());
                int i = 0;
                while(m.find()) {
                    hash.put(key.get(i), m.group(1));
                    i++;
                }
                projectData.add(hash);
            }
            ArrayList<Project> projectList = new ArrayList<>();
            for(int i = 1; i <= projectData.size(); i++) {
                int projectID = Integer.parseInt(projectData.get(i-1).get("id"));
                String name = projectData.get(i-1).get("name");
                String description = projectData.get(i-1).get("description");
                int managerID = Integer.parseInt(projectData.get(i-1).get("manager"));
                LocalDate originalST = LocalDate.parse(projectData.get(i-1).get("oST"));
                LocalDate acutalST = projectData.get(i-1).get("aST").equals("") ? LocalDate.MAX : LocalDate.parse(projectData.get(i-1).get("aST"));
                int originalDuration = Integer.parseInt(projectData.get(i-1).get("oDuration"));
                int actualDuration = Integer.parseInt(projectData.get(i-1).get("aDuration"));
                ArrayList<String> taskListNum = new ArrayList<>(Arrays.asList(projectData.get(i-1).get("taskList").split(",")));
                ArrayList<Task> taskList = new ArrayList<>();
                for(Task task : allTaskList)
                    if(taskListNum.contains(Integer.toString(task.getID())))
                        taskList.add(task);
                projectList.add(new Project(projectID, name, description, managerID, originalST, acutalST, originalDuration, actualDuration, taskList));
            }
            return projectList;
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "There are something wrong with your 'Project Data.csv' file");
            System.exit(0);
        }
        return null;
    }
    
    private static void loadRequest(ArrayList<Member> memberList) {
        String filename = "Request.csv";
        File inFile = new File(filename);
        if(!inFile.exists() || !inFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Load File", JOptionPane.ERROR_MESSAGE);
        }
        try {
            Scanner scanner = new Scanner(inFile);
            String regex = "((?:(?<=,\"|^\")[^\"]*?(?=\"))|(?:(?<=,|^)(?:(?:[^\",][^,]*[^\",])|(?:[^\",]*?))(?=,|$)))";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(scanner.nextLine());
            while(scanner.hasNextLine()) {
                m.reset(scanner.nextLine());
                while(m.find()) {
                    int id = Integer.parseInt(m.group(1));
                    Member member = null;
                    for(Member mem : memberList) {
                        if(mem.getID() == id) {
                            member = mem;
                            break;
                        }
                    }
                    if(member == null)
                        throw new Exception();
                    m.find();
                    int taskID = Integer.parseInt(m.group(1));
                    Task task = null;
                    for(Project q : projectList) {
                        for(Task t : q.getTaskList()) {
                            if(t.getID() == taskID) {
                                task = t;
                                break;
                            }
                        }
                    }
                    if(task == null)
                        throw new Exception();
                    task.getRequest().add(member);
                }
            }
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "There are something wrong with your 'Request.csv' file");
            System.exit(0);
        }
    }
}