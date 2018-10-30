import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.event.*;

public class ExternalMember extends Member {
    /* data member */
    
    /* constructor */
    public ExternalMember(String name, LocalDate dateOfBirth, char gender, ArrayList<String> abilities, ArrayList<String> roles) {
        super(name, dateOfBirth, gender, abilities, roles);
    }
    
    public ExternalMember(int id, String name, LocalDate dateOfBirth, char gender, ArrayList<String> abilities, ArrayList<String> roles) {
        super(id, name, dateOfBirth, gender, abilities, roles);
    }
    /* method */
    public static void register(HashMap<String, HashMap<String, String>> loginData, ArrayList<Member> memberList) {
        PMS.contentPane.removeAll();
		FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        JPanel basePanel = new JPanel(flowLayout);
        PMS.contentPane.add(basePanel);
        basePanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
		String[] labelName = {"Username", "Password", "Name", "Gender", "Date Of Birth", "Roles", "Abilities"};
        JLabel[] warnings = new JLabel[7];
        JTextField[] fields = new JTextField[3];
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton[] radioButtons = new JRadioButton[2];
		ArrayList<JComboBox<String>> comboBoxes = new ArrayList<>();
        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500,88));
        for(int i = 0; i < labelName.length; i++) {
            JLabel warningL = new JLabel(" ");
            warningL.setPreferredSize(new Dimension(700,22));
            warningL.setForeground(Color.RED);
            warningL.setFont(new Font("Times New Roman", 1, 22));
            warnings[i] = warningL;
            JLabel label = new JLabel(labelName[i]);
            label.setPreferredSize(new Dimension(350,22));
            label.setFont(new Font("Times New Roman", 1, 22));
            basePanel.add(warningL);
            basePanel.add(label);
            if(i < 3) {
                JTextField textField = new JTextField();
                textField.setPreferredSize(new Dimension(350,22));
                textField.setFont(new Font("Times New Roman", 1, 22));
                fields[i] = textField;
                basePanel.add(textField);
            } else if(i == 3) {
                JRadioButton male = new JRadioButton("Male");
                male.setPreferredSize(new Dimension(170,22));
                male.setFont(new Font("Times New Roman", 1, 22));
                JRadioButton female = new JRadioButton("Female");
                female.setPreferredSize(new Dimension(170,22));
                female.setFont(new Font("Times New Roman", 1, 22));
                buttonGroup.add(male);
                buttonGroup.add(female);
                radioButtons[0] = male;
                radioButtons[1] = female;
                basePanel.add(male);
                basePanel.add(female);
            } else if(i == 4) {
				String[] day = new String[31];
				for(int j = 0; j < 31; j++)
					day[j] = Integer.toString(j+1);
                JComboBox<String> comboBox1 = new JComboBox<>(day);
                comboBox1.setPreferredSize(new Dimension(100,22));
                comboBox1.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox1);
                basePanel.add(comboBox1);
				String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                JComboBox<String> comboBox2 = new JComboBox<>(month);
                comboBox2.setPreferredSize(new Dimension(100,22));
                comboBox2.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox2);
                basePanel.add(comboBox2);
				String[] year = new String[100];
				for(int j = 0; j < 100; j++)
					year[j] = Integer.toString(2016-j);
                JComboBox<String> comboBox3 = new JComboBox<>(year);
                comboBox3.setPreferredSize(new Dimension(100,22));
                comboBox3.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox3);
                basePanel.add(comboBox3);
            } else if(i == 5) {
				String[] type = {"Artist", "Audio Engineer", "Programmer", "Tester", "Administrator"};
                JComboBox<String> comboBox = new JComboBox<>(type);
                comboBox.setPreferredSize(new Dimension(350,22));
                comboBox.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox);
                basePanel.add(comboBox);
			} else if(i == 6) {
				String[] type = {"Characters", "Scenery", "Objects"};
                for(String s : type) {
                    JCheckBox checkBox = new JCheckBox(s);
                    checkBox.setPreferredSize(new Dimension(350,22));
                    checkBox.setFont(new Font("Times New Roman", 1, 22));
                    panel.add(checkBox);
                    checkBoxes.add(checkBox);
                }
                basePanel.add(panel);
			}
        }
		comboBoxes.get(3).addItemListener(event -> {
			checkBoxes.clear();
            panel.removeAll();
			if(comboBoxes.get(3).getSelectedIndex() == 0) {
				String[] type = {"Characters", "Scenery", "Objects"};
                for(String s : type) {
                    JCheckBox checkBox = new JCheckBox(s);
                    checkBox.setPreferredSize(new Dimension(350,22));
                    checkBox.setFont(new Font("Times New Roman", 1, 22));
                    panel.add(checkBox);
                    checkBoxes.add(checkBox);
                }
            }
			else if(comboBoxes.get(3).getSelectedIndex() == 1) {
				String[] type = {"Character Voices", "Music", "Sound Effects"};
                for(String s : type) {
                    JCheckBox checkBox = new JCheckBox(s);
                    checkBox.setPreferredSize(new Dimension(350,22));
                    checkBox.setFont(new Font("Times New Roman", 1, 22));
                    panel.add(checkBox);
                    checkBoxes.add(checkBox);
                }
            }
			else if(comboBoxes.get(3).getSelectedIndex() == 2) {
				String[] type = {"Java", "C++"};
                for(String s : type) {
                    JCheckBox checkBox = new JCheckBox(s);
                    checkBox.setPreferredSize(new Dimension(350,22));
                    checkBox.setFont(new Font("Times New Roman", 1, 22));
                    panel.add(checkBox);
                    checkBoxes.add(checkBox);
                }
            }
			else if(comboBoxes.get(3).getSelectedIndex() == 3) {
				String[] type = {"Testing"};
                for(String s : type) {
                    JCheckBox checkBox = new JCheckBox(s);
                    checkBox.setPreferredSize(new Dimension(350,22));
                    checkBox.setFont(new Font("Times New Roman", 1, 22));
                    panel.add(checkBox);
                    checkBoxes.add(checkBox);
                }
            }
            else if(comboBoxes.get(3).getSelectedIndex() == 4) {
				String[] type = {"Administration"};
                for(String s : type) {
                    JCheckBox checkBox = new JCheckBox(s);
                    checkBox.setPreferredSize(new Dimension(350,22));
                    checkBox.setFont(new Font("Times New Roman", 1, 22));
                    panel.add(checkBox);
                    checkBoxes.add(checkBox);
                }
            }
            PMS.frame.setVisible(true);
		});
        JButton register = new JButton("Register");
        register.addActionListener(event -> {
            boolean tf = true;
            if(fields[0].getText().trim().equals("")) {
                warnings[0].setText("Username cannot be empty");
                tf = false;
            }
			else if(Pattern.compile("[^a-zA-Z0-9]").matcher(fields[0].getText()).find()) {
				warnings[0].setText("Username can only contain alphanumeric characers");
				tf = false;
			}
            else {
                if(loginData.get(fields[0].getText().trim()) != null) {
                    warnings[0].setText("Username exist");
                    tf = false;
                }
                else
                    warnings[0].setText("");
            }
            if(fields[1].getText().trim().equals("")){
                warnings[1].setText("Password cannot be empty");
                tf = false;
            }
			else if(Pattern.compile("[^a-zA-Z0-9]").matcher(fields[1].getText()).find()) {
				warnings[1].setText("Password can only contain alphanumeric characers");
				tf = false;
			}
            else
                warnings[1].setText("");
            if(fields[2].getText().trim().equals("")){
                warnings[2].setText("Name cannot be empty");
                tf = false;
            }
			else if(Pattern.compile("[^a-zA-Z0-9. -]").matcher(fields[2].getText()).find()) {
				warnings[2].setText("Name can only contain alphanumeric characers or '.', '-'");
				tf = false;
			}
            else
                warnings[2].setText("");
            if(buttonGroup.getSelection() == null){
                warnings[3].setText("Select a gender");
                tf = false;
            }
            else
                warnings[3].setText("");
            boolean checkBoxSelected = false;
            for(JCheckBox checkBox : checkBoxes) {
                if(checkBox.isSelected()) {
                    checkBoxSelected = true;
                    break;
                }
            }
            if(checkBoxSelected)
                warnings[6].setText("");
            else {
                warnings[6].setText("Select at least 1 abilities");
                tf = false;
            }
            if(tf) {
				String name = fields[2].getText().trim();
				String year = comboBoxes.get(2).getItemAt(comboBoxes.get(2).getSelectedIndex());
				String[] monthStr = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
				String month = comboBoxes.get(1).getItemAt(comboBoxes.get(1).getSelectedIndex());
                if(month.equals("Jan")) month = "01";
                else if(month.equals("Feb")) month = "02";
                else if(month.equals("Mar")) month = "03";
                else if(month.equals("Apr")) month = "04";
                else if(month.equals("May")) month = "05";
                else if(month.equals("Jun")) month = "06";
                else if(month.equals("Jul")) month = "07";
                else if(month.equals("Aug")) month = "08";
                else if(month.equals("Sep")) month = "09";
                else if(month.equals("Oct")) month = "10";
                else if(month.equals("Nov")) month = "11";
                else if(month.equals("Dec")) month = "12";
				String day = comboBoxes.get(0).getItemAt(comboBoxes.get(0).getSelectedIndex());
				if(day.length() == 1)
					day = "0" + day;
				LocalDate dateOfBirth = LocalDate.parse(year+"-"+month+"-"+day);
				char gender = (radioButtons[0].isSelected() ? 'M' : 'F');
				ArrayList<String> abilities = new ArrayList<>();
				for(JCheckBox checkBox : checkBoxes) {
                    if(checkBox.isSelected()) {
                        abilities.add(checkBox.getText());
                    }
                }
				ArrayList<String> roles = new ArrayList<>();
				roles.add(comboBoxes.get(3).getItemAt(comboBoxes.get(3).getSelectedIndex()));
				ExternalMember member = new ExternalMember(name, dateOfBirth, gender, abilities, roles);
				memberList.add(member);
                String username = fields[0].getText().trim();
                String password = fields[1].getText().trim();
                HashMap<String, String> data = new HashMap<>();
                data.put("password", password);
                data.put("id", Integer.toString(member.getID()));
                loginData.put(username, data);
                rewriteAllMemberData(memberList);
                rewriteAllLoginData(loginData);
                JOptionPane.showMessageDialog(null, "Create Account Successfully");
				PMS.mainMenu(member);
			}
        });
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(event -> {
            PMS.frontPage();
        });
        GridLayout gridLayout = new GridLayout(1,2);
        gridLayout.setHgap(20);
        JPanel buttonPanel = new JPanel(gridLayout);
        buttonPanel.add(register);
        buttonPanel.add(cancel);
        basePanel.add(buttonPanel);
        PMS.frame.setVisible(true);
    }
    
    public void browseAvailableProject(ArrayList<Member> memberList, ArrayList<Project> projectList) {
        PMS.rPanel.removeAll();
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Search Project"));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
		ArrayList<Project> allAvailiableProject = new ArrayList<>();
        ArrayList<Project> filteredProject = new ArrayList<>();
        for(Project p : projectList) {
            for(Task t : p.getTaskList()) {
                if(this.getAbilities().contains(t.getName()) && !t.getMember().contains(this) && !filteredProject.contains(p)) {
                        filteredProject.add(p);
						allAvailiableProject.add(p);
                        break;
                }
            }
        }
        int i = -1;
        String[] nameList = new String[filteredProject.size()];
        for(Project p : filteredProject) {
            nameList[++i] = p.getName();
        }
        if(filteredProject.size() == 0) {
            JOptionPane.showMessageDialog(null, "No Project Available");
            this.displayInfo();
            return;
        }
        JLabel filterL = new JLabel("Filter: ");
        filterL.setPreferredSize(new Dimension(100,22));
        filterL.setFont(new Font("Times New Roman", 1, 22));
        JTextField filterTF = new JTextField();
        filterTF.setPreferredSize(new Dimension(600,22));
        filterTF.setFont(new Font("Times New Roman", 1, 22));
        PMS.rPanel.add(filterL);
        PMS.rPanel.add(filterTF);
        JList<String> list = new JList<>(nameList);
        list.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(200,480));
        JPanel panel = new JPanel(flowLayout);
        panel.setPreferredSize(new Dimension(500,480));
        Project selected = filteredProject.get(list.getSelectedIndex());
        JLabel idL = new JLabel("ID: " + selected.getID());
        idL.setPreferredSize(new Dimension(450,22));
        idL.setFont(new Font("Times New Roman", 1, 22));
        JLabel nameL = new JLabel("Name: " + selected.getName());
        nameL.setPreferredSize(new Dimension(450,22));
        nameL.setFont(new Font("Times New Roman", 1, 22));
        JLabel descriptionL = new JLabel("Description: " + selected.getDescription());
        descriptionL.setPreferredSize(new Dimension(450,22));
        descriptionL.setFont(new Font("Times New Roman", 1, 22));
        JLabel managerIDL = new JLabel("Manager ID: " + selected.getManagerID());
        managerIDL.setPreferredSize(new Dimension(450,22));
        managerIDL.setFont(new Font("Times New Roman", 1, 22));
        JLabel originalSTL = new JLabel("Original Start Time: " + selected.getOriginalST());
        originalSTL.setPreferredSize(new Dimension(450,22));
        originalSTL.setFont(new Font("Times New Roman", 1, 22));
        JLabel actualSTL = new JLabel("Actural Start Time: " + (selected.getActualST().equals(LocalDate.MAX) ? "Not yet started" : selected.getActualST()));
        actualSTL.setPreferredSize(new Dimension(450,22));
        actualSTL.setFont(new Font("Times New Roman", 1, 22));
        JLabel originalDurationL = new JLabel("Original Duration: " + selected.getOriginalDuration());
        originalDurationL.setPreferredSize(new Dimension(450,22));
        originalDurationL.setFont(new Font("Times New Roman", 1, 22));
        JLabel actualDurationL = new JLabel("Actural Duration: " + (selected.getActualDuration() == -1 ? "Not yet finished" : selected.getActualDuration()));
        actualDurationL.setPreferredSize(new Dimension(450,22));
        actualDurationL.setFont(new Font("Times New Roman", 1, 22));
        String taskList = "<html>Task: ";
        for(Task t : selected.getTaskList())
            taskList += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + t.getName();
        taskList += "</html>";
        JLabel taskListL = new JLabel(taskList);
        taskListL.setPreferredSize(new Dimension(450,30*selected.getTaskList().size()+35));
        taskListL.setFont(new Font("Times New Roman", 1, 22));
        panel.add(idL);
        panel.add(nameL);
        panel.add(descriptionL);
        panel.add(managerIDL);
        panel.add(originalSTL);
        panel.add(actualSTL);
        panel.add(originalDurationL);
        panel.add(actualDurationL);
        panel.add(taskListL);
        filterTF.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                String filterS = filterTF.getText();
                filteredProject.clear();
                for(Project p : allAvailiableProject)
                    if(p.getName().toLowerCase().contains(filterS.toLowerCase()))
                        filteredProject.add(p);
                String[] names = new String[filteredProject.size()];
                int j = -1;
                for(Project p : filteredProject)
                    names[++j] = p.getName();
                list.setListData(names);
                list.setSelectedIndex(0);
            }
        });
        list.addListSelectionListener(event -> {
            if(list.getSelectedIndex() != -1) {
                Project select = filteredProject.get(list.getSelectedIndex());
                idL.setText("ID: " + select.getID());
                nameL.setText("Name: " + select.getName());
                descriptionL.setText("Description: " + select.getDescription());
                managerIDL.setText("ManagerID: " + (select.getManagerID() == -1 ? "No Manager" : select.getManagerID()));
                originalSTL.setText("Original Start Time: " + select.getOriginalST());
                actualSTL.setText("Actural Start Time: " + (select.getActualST().equals(LocalDate.MAX) ? "Not yet started" : select.getActualST()));
                originalDurationL.setText("Original Duration: " + select.getOriginalDuration());
                actualDurationL.setText("Actural Duration: " + (select.getActualDuration() == -1 ? "Not yet finished" : select.getActualDuration()));
                String tasks = "<html>Task: ";
                for(Task t : select.getTaskList())
                    tasks += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + t.getName();
                tasks += "</html>";
                taskListL.setPreferredSize(new Dimension(450,30*selected.getTaskList().size()+35));
                taskListL.setText(tasks);
            }
        });
        JButton requestB = new JButton("Request");
        requestB.addActionListener(event -> {
            if(list.getSelectedIndex() != -1) {
                Project project = filteredProject.get(list.getSelectedIndex());
                Task task = null;
                for(Task t : project.getTaskList()) {
                    if(this.getAbilities().contains(t.getName())) {
                        task = t;
                        break;
                    }
                }
                task.getRequest().add(this);
                saveRequestData(this.getID(), task.getID());
            }
        });
        panel.add(requestB);
        PMS.rPanel.add(scrollPane);
        PMS.rPanel.add(panel);
        PMS.frame.setVisible(true);
    }
    
    protected static boolean saveRequestData(int memberID, int taskID) {
        String filename = "Request.csv";
        File outFile = new File(filename);
        if(!outFile.exists() || !outFile.isFile()) {
            JOptionPane.showMessageDialog(null, filename + " not exist or not a file", "ERROR: Cannot Save File", JOptionPane.ERROR_MESSAGE);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outFile, true);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            String data = memberID + "," + taskID;
            printWriter.println();
            printWriter.print(data);
            printWriter.close();
        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
        return true;
    }
    
    public void displayJoinedProject(ArrayList<Project> projectList) {
        PMS.rPanel.removeAll();
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Search Project"));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        ArrayList<Project> filteredProject = new ArrayList<>();
        for(Project p : projectList) {
            for(Task t : p.getTaskList()) {
                for(Member m : t.getMember()) {
                    if(m == this && !filteredProject.contains(p)) {
                        filteredProject.add(p);
                        break;
                    }
                }
            }
        }
        int i = -1;
        String[] nameList = new String[filteredProject.size()];
        for(Project p : filteredProject) {
            nameList[++i] = p.getName();
        }
        if(filteredProject.size() == 0) {
            JOptionPane.showMessageDialog(null, "No Project Joined");
            this.displayInfo();
            return;
        }
        JLabel filterL = new JLabel("Filter: ");
        filterL.setPreferredSize(new Dimension(100,22));
        filterL.setFont(new Font("Times New Roman", 1, 22));
        JTextField filterTF = new JTextField();
        filterTF.setPreferredSize(new Dimension(600,22));
        filterTF.setFont(new Font("Times New Roman", 1, 22));
        PMS.rPanel.add(filterL);
        PMS.rPanel.add(filterTF);
        JList<String> list = new JList<>(nameList);
        list.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(200,480));
        JPanel panel = new JPanel(flowLayout);
        panel.setPreferredSize(new Dimension(500,480));
        Project selected = filteredProject.get(list.getSelectedIndex());
        JLabel idL = new JLabel("ID: " + selected.getID());
        idL.setPreferredSize(new Dimension(450,22));
        idL.setFont(new Font("Times New Roman", 1, 22));
        JLabel nameL = new JLabel("Name: " + selected.getName());
        nameL.setPreferredSize(new Dimension(450,22));
        nameL.setFont(new Font("Times New Roman", 1, 22));
        JLabel descriptionL = new JLabel("Description: " + selected.getDescription());
        descriptionL.setPreferredSize(new Dimension(450,22));
        descriptionL.setFont(new Font("Times New Roman", 1, 22));
        JLabel managerIDL = new JLabel("Manager ID: " + selected.getManagerID());
        managerIDL.setPreferredSize(new Dimension(450,22));
        managerIDL.setFont(new Font("Times New Roman", 1, 22));
        JLabel originalSTL = new JLabel("Original Start Time: " + selected.getOriginalST());
        originalSTL.setPreferredSize(new Dimension(450,22));
        originalSTL.setFont(new Font("Times New Roman", 1, 22));
        JLabel actualSTL = new JLabel("Actural Start Time: " + (selected.getActualST().equals(LocalDate.MAX) ? "Not yet started" : selected.getActualST()));
        actualSTL.setPreferredSize(new Dimension(450,22));
        actualSTL.setFont(new Font("Times New Roman", 1, 22));
        JLabel originalDurationL = new JLabel("Original Duration: " + selected.getOriginalDuration());
        originalDurationL.setPreferredSize(new Dimension(450,22));
        originalDurationL.setFont(new Font("Times New Roman", 1, 22));
        JLabel actualDurationL = new JLabel("Actural Duration: " + (selected.getActualDuration() == -1 ? "Not yet finished" : selected.getActualDuration()));
        actualDurationL.setPreferredSize(new Dimension(450,22));
        actualDurationL.setFont(new Font("Times New Roman", 1, 22));
        String taskList = "<html>Task: ";
        for(Task t : selected.getTaskList())
            taskList += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + t.getName();
        taskList += "</html>";
        JLabel taskListL = new JLabel(taskList);
        taskListL.setPreferredSize(new Dimension(450,30*selected.getTaskList().size()+35));
        taskListL.setFont(new Font("Times New Roman", 1, 22));
        panel.add(idL);
        panel.add(nameL);
        panel.add(descriptionL);
        panel.add(managerIDL);
        panel.add(originalSTL);
        panel.add(actualSTL);
        panel.add(originalDurationL);
        panel.add(actualDurationL);
        panel.add(taskListL);
        filterTF.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                String filterS = filterTF.getText();
                filteredProject.clear();
                for(Project p : projectList)
                    if(p.getName().toLowerCase().contains(filterS.toLowerCase()))
                        filteredProject.add(p);
                String[] names = new String[filteredProject.size()];
                int j = -1;
                for(Project p : filteredProject)
                    names[++j] = p.getName();
                list.setListData(names);
                list.setSelectedIndex(0);
            }
        });
        list.addListSelectionListener(event -> {
            if(list.getSelectedIndex() != -1) {
                Project select = filteredProject.get(list.getSelectedIndex());
                idL.setText("ID: " + select.getID());
                nameL.setText("Name: " + select.getName());
                descriptionL.setText("Description: " + select.getDescription());
                managerIDL.setText("ManagerID: " + (select.getManagerID() == -1 ? "No Manager" : select.getManagerID()));
                originalSTL.setText("Original Start Time: " + select.getOriginalST());
                actualSTL.setText("Actural Start Time: " + (select.getActualST().equals(LocalDate.MAX) ? "Not yet started" : select.getActualST()));
                originalDurationL.setText("Original Duration: " + select.getOriginalDuration());
                actualDurationL.setText("Actural Duration: " + (select.getActualDuration() == -1 ? "Not yet finished" : select.getActualDuration()));
                String tasks = "<html>Task: ";
                for(Task t : select.getTaskList())
                    tasks += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + t.getName();
                tasks += "</html>";
                taskListL.setPreferredSize(new Dimension(450,30*selected.getTaskList().size()+35));
                taskListL.setText(tasks);
            }
        });
        PMS.rPanel.add(scrollPane);
        PMS.rPanel.add(panel);
        PMS.frame.setVisible(true);
    }
}