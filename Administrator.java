import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.event.*;

public class Administrator extends Member {
    /* data member */
    
    /* constructor */
    public Administrator(String name, LocalDate dateOfBirth, char gender, ArrayList<String> abilities, ArrayList<String> roles) {
        super(name, dateOfBirth, gender, abilities, roles);
    }
    
    public Administrator(int id, String name, LocalDate dateOfBirth, char gender, ArrayList<String> abilities, ArrayList<String> roles) {
        super(id, name, dateOfBirth, gender, abilities, roles);
    }
    /* method *///Not Done
    public void register(HashMap<String, HashMap<String, String>> loginData, ArrayList<Member> memberList) {
        PMS.rPanel.removeAll();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Register"));
        String[] labelName = {"Username", "Password", "Name", "Gender", "Date Of Birth", "Roles", "Abilities", "Member Type"};
        JLabel[] warnings = new JLabel[8];
        JTextField[] fields = new JTextField[3];
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton[] radioButtons = new JRadioButton[2];
		ArrayList<JComboBox<String>> comboBoxes = new ArrayList<>();
        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(350,88));
        for(int i = 0; i < labelName.length; i++) {
            JLabel warningL = new JLabel(" ");
            warningL.setPreferredSize(new Dimension(700,22));
            warningL.setForeground(Color.RED);
            warningL.setFont(new Font("Times New Roman", 1, 22));
            warnings[i] = warningL;
            JLabel label = new JLabel(labelName[i]);
            label.setPreferredSize(new Dimension(350,22));
            label.setFont(new Font("Times New Roman", 1, 22));
            PMS.rPanel.add(warningL);
            PMS.rPanel.add(label);
            if(i < 3) {
                JTextField textField = new JTextField();
                textField.setPreferredSize(new Dimension(350,22));
                textField.setFont(new Font("Times New Roman", 1, 22));
                fields[i] = textField;
                PMS.rPanel.add(textField);
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
                PMS.rPanel.add(male);
                PMS.rPanel.add(female);
            } else if(i == 4) {
				String[] day = new String[31];
				for(int j = 0; j < 31; j++)
					day[j] = Integer.toString(j+1);
                JComboBox<String> comboBox1 = new JComboBox<>(day);
                comboBox1.setPreferredSize(new Dimension(100,22));
                comboBox1.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox1);
                PMS.rPanel.add(comboBox1);
				String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                JComboBox<String> comboBox2 = new JComboBox<>(month);
                comboBox2.setPreferredSize(new Dimension(100,22));
                comboBox2.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox2);
                PMS.rPanel.add(comboBox2);
				String[] year = new String[100];
				for(int j = 0; j < 100; j++)
					year[j] = Integer.toString(2016-j);
                JComboBox<String> comboBox3 = new JComboBox<>(year);
                comboBox3.setPreferredSize(new Dimension(100,22));
                comboBox3.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox3);
                PMS.rPanel.add(comboBox3);
            } else if(i == 5) {
				String[] type = {"Artist", "Audio Engineer", "Programmer", "Tester", "Administrator"};
                JComboBox<String> comboBox = new JComboBox<>(type);
                comboBox.setPreferredSize(new Dimension(350,22));
                comboBox.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox);
                PMS.rPanel.add(comboBox);
			} else if(i == 6) {
				String[] type = {"Characters", "Scenery", "Objects"};
                for(String s : type) {
                    JCheckBox checkBox = new JCheckBox(s);
                    checkBox.setPreferredSize(new Dimension(350,22));
                    checkBox.setFont(new Font("Times New Roman", 1, 22));
                    panel.add(checkBox);
                    checkBoxes.add(checkBox);
                }
                PMS.rPanel.add(panel);
			} else if(i == 7) {
				String[] type = {"Intenal Member", "Manager", "External Member", "Administrator"};
                JComboBox<String> comboBox = new JComboBox<>(type);
                comboBox.setPreferredSize(new Dimension(350,22));
                comboBox.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox);
                PMS.rPanel.add(comboBox);
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
				Member member;
				int index = comboBoxes.get(4).getSelectedIndex();
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
				
                if(index == 0)
					member = new InternalMember(name, dateOfBirth, gender, abilities, roles);
				else if(index == 1)
					member = new Manager(name, dateOfBirth, gender, abilities, roles);
				else if(index == 2)
					member = new ExternalMember(name, dateOfBirth, gender, abilities, roles);
				else
					member = new Administrator(name, dateOfBirth, gender, abilities, roles);
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
				this.displayInfo();
			}
        });
        PMS.rPanel.add(register);
        PMS.frame.setVisible(true);
    }
    
    public void deregister(HashMap<String, HashMap<String, String>> loginData, ArrayList<Member> memberList, ArrayList<Project> projectList) {
        PMS.rPanel.removeAll();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Deregister"));
        String[] nameList = new String[memberList.size()];
        int i = -1;
        for(Member m : memberList)
            nameList[++i] = m.getName();
        JList<String> list = new JList<>(nameList);
        list.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(200,525));
        JPanel panel = new JPanel(flowLayout);
        panel.setPreferredSize(new Dimension(500,525));
        Member selected = memberList.get(list.getSelectedIndex());
        JLabel idL = new JLabel("ID: " + selected.getID());
        idL.setPreferredSize(new Dimension(450,22));
        idL.setFont(new Font("Times New Roman", 1, 22));
        JLabel typeOfMemberL = new JLabel();
        typeOfMemberL.setPreferredSize(new Dimension(450,22));
        typeOfMemberL.setFont(new Font("Times New Roman", 1, 22));
        if(selected instanceof Administrator)
            typeOfMemberL.setText("Type of Member: Administrator");
        else if(selected instanceof Manager)
            typeOfMemberL.setText("Type of Member: Manager");
        else if(selected instanceof InternalMember)
            typeOfMemberL.setText("Type of Member: Worker");
        else if(selected instanceof ExternalMember)
            typeOfMemberL.setText("Type of Member: External Member");
        JLabel nameL = new JLabel("Name: " + selected.getName());
        nameL.setPreferredSize(new Dimension(450,22));
        nameL.setFont(new Font("Times New Roman", 1, 22));
        JLabel dateOfBirthL = new JLabel("Date of Birth: " + selected.getDateOfBirth());
        dateOfBirthL.setPreferredSize(new Dimension(450,22));
        dateOfBirthL.setFont(new Font("Times New Roman", 1, 22));
        JLabel genderL = new JLabel("Gender: " + selected.getGender());
        genderL.setPreferredSize(new Dimension(450,22));
        genderL.setFont(new Font("Times New Roman", 1, 22));
        String abilities = "<html>Abilities: ";
        for(String s : selected.getAbilities())
            abilities += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
        abilities += "</html>";
        JLabel abilitiesL = new JLabel(abilities);
        abilitiesL.setPreferredSize(new Dimension(450,112));
        abilitiesL.setFont(new Font("Times New Roman", 1, 22));
        String roles = "<html>Roles: ";
        for(String s : selected.getRoles())
            roles += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
        roles += "</html>";
        JLabel rolesL = new JLabel(roles);
        rolesL.setPreferredSize(new Dimension(450,66));
        rolesL.setFont(new Font("Times New Roman", 1, 22));
        panel.add(idL);
        panel.add(typeOfMemberL);
        panel.add(nameL);
        panel.add(dateOfBirthL);
        panel.add(genderL);
        panel.add(abilitiesL);
        panel.add(rolesL);
        list.addListSelectionListener(event -> {
            Member select = memberList.get(list.getSelectedIndex());
            idL.setText("ID: " + select.getID());
            if(select instanceof Administrator)
                typeOfMemberL.setText("Type of Member: Administrator");
            else if(select instanceof Manager)
                typeOfMemberL.setText("Type of Member: Manager");
            else if(select instanceof InternalMember)
                typeOfMemberL.setText("Type of Member: Worker");
            else if(select instanceof ExternalMember)
                typeOfMemberL.setText("Type of Member: External Member");
            nameL.setText("Name: " + select.getName());
            dateOfBirthL.setText("Date of Birth: " + select.getDateOfBirth());
            genderL.setText("Gender: " + select.getGender());
            String ability = "<html>Abilities: ";
            for(String s : select.getAbilities())
                ability += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
            ability += "</html>";
            abilitiesL.setText(ability);
            String role = "<html>Roles: ";
            for(String s : select.getRoles())
                role += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
            role += "</html>";
            rolesL.setText(role);
        });
        JButton removeB = new JButton("Remove This Member");
        removeB.addActionListener(event -> {
            String[] options = {"Yes", "No"};
            int select = JOptionPane.showOptionDialog(null, "Do you really want to delete?", "Remove Member", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 0);
            if(select == 0) {
                Member member = memberList.get(list.getSelectedIndex());
                for(Project p : projectList) {
                    for(Task t : p.getTaskList()) {
                        boolean tf = false;
                        for(Member mem : t.getMember()) {
                            if(member == mem) {
                                tf = true;
                            }
                        }
                        if(tf)
                            t.getMember().remove(member);
                    }
                    if(member.getID() == p.getManagerID())
                        p.setManagerID(-1);
                }
                memberList.remove(member);
                String removeKey = null;
                for(Map.Entry<String, HashMap<String, String>> entry : loginData.entrySet()) {
                    String key = entry.getKey();
                    HashMap<String, String> value = entry.getValue();
                    if(value.get("id").equals(Integer.toString(member.getID()))) {
                        removeKey = key;
                        break;
                    }
                }
                loginData.remove(removeKey);
                saveAllData(loginData, memberList, projectList);
                JOptionPane.showMessageDialog(null, "Remove Successfully");
                this.displayInfo();
            }
        });
        panel.add(removeB);
        PMS.rPanel.add(scrollPane);
        PMS.rPanel.add(panel);
        PMS.frame.setVisible(true);
    }
    
    public void manageAccount(HashMap<String, HashMap<String, String>> loginData, ArrayList<Member> memberList, ArrayList<Project> projectList) {
        PMS.rPanel.removeAll();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Manage Account"));
        GridLayout gridLayout = new GridLayout(3,1);
        gridLayout.setVgap(20);
        JPanel panel = new JPanel(gridLayout);
        panel.setBorder(BorderFactory.createEmptyBorder(150, 200, 0, 0));
        JButton registerB = new JButton("Register");
        registerB.setPreferredSize(new Dimension(300,80));
        registerB.setFont(new Font("Times New Roman", 1, 22));
		JButton deregisterB = new JButton("Deregister");
        deregisterB.setPreferredSize(new Dimension(300,80));
        deregisterB.setFont(new Font("Times New Roman", 1, 22));
		registerB.addActionListener(event -> {
			register(loginData, memberList);
		});
		deregisterB.addActionListener(event -> {
			deregister(loginData, memberList, projectList);
		});
		panel.add(registerB);
		panel.add(deregisterB);
        PMS.rPanel.add(panel);
		PMS.frame.setVisible(true);
    }
    
    public void search(ArrayList<Member> memberList, ArrayList<Project> projectList) {
        PMS.rPanel.removeAll();
		FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
		PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Search/List"));
        GridLayout gridLayout = new GridLayout(3,1);
        gridLayout.setVgap(20);
        JPanel panel = new JPanel(gridLayout);
        panel.setBorder(BorderFactory.createEmptyBorder(100, 200, 0, 0));
		JButton projectB = new JButton("Project");
        projectB.setPreferredSize(new Dimension(300,80));
        projectB.setFont(new Font("Times New Roman", 1, 22));
		JButton imB = new JButton("IMs");
        imB.setPreferredSize(new Dimension(300,80));
        imB.setFont(new Font("Times New Roman", 1, 22));
		JButton emB = new JButton("EMs");
        emB.setPreferredSize(new Dimension(300,80));
        emB.setFont(new Font("Times New Roman", 1, 22));
		projectB.addActionListener(event -> {
			searchProject(projectList);
		});
		imB.addActionListener(event -> {
			searchIMs(memberList);
		});
		emB.addActionListener(event -> {
			searchEMs(memberList);
		});
		panel.add(projectB);
		panel.add(imB);
		panel.add(emB);
        PMS.rPanel.add(panel);
		PMS.frame.setVisible(true);
    }
    
    public void searchIMs(ArrayList<Member> memberList) {
        PMS.rPanel.removeAll();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Search Internal Member"));
        ArrayList<Member> IMList = new ArrayList<>();
        ArrayList<Member> filteredMember = new ArrayList<>();
        for(Member m : memberList) {
            if(m instanceof InternalMember) {
                IMList.add(m);
                filteredMember.add(m);
            }
        }
        int i = -1;
        String[] nameList = new String[filteredMember.size()];
        for(Member m : filteredMember) {
            nameList[++i] = m.getName();
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
        Member selected = memberList.get(list.getSelectedIndex());
        JLabel idL = new JLabel("ID: " + selected.getID());
        idL.setPreferredSize(new Dimension(450,22));
        idL.setFont(new Font("Times New Roman", 1, 22));
        JLabel typeOfMemberL = new JLabel();
        typeOfMemberL.setPreferredSize(new Dimension(450,22));
        typeOfMemberL.setFont(new Font("Times New Roman", 1, 22));
        if(selected instanceof Administrator)
            typeOfMemberL.setText("Type of Member: Administrator");
        else if(selected instanceof Manager)
            typeOfMemberL.setText("Type of Member: Manager");
        else if(selected instanceof InternalMember)
            typeOfMemberL.setText("Type of Member: Worker");
        else if(selected instanceof ExternalMember)
            typeOfMemberL.setText("Type of Member: External Member");
        JLabel nameL = new JLabel("Name: " + selected.getName());
        nameL.setPreferredSize(new Dimension(450,22));
        nameL.setFont(new Font("Times New Roman", 1, 22));
        JLabel dateOfBirthL = new JLabel("Date of Birth: " + selected.getDateOfBirth());
        dateOfBirthL.setPreferredSize(new Dimension(450,22));
        dateOfBirthL.setFont(new Font("Times New Roman", 1, 22));
        JLabel genderL = new JLabel("Gender: " + selected.getGender());
        genderL.setPreferredSize(new Dimension(450,22));
        genderL.setFont(new Font("Times New Roman", 1, 22));
        String abilities = "<html>Abilities: ";
        for(String s : selected.getAbilities())
            abilities += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
        abilities += "</html>";
        JLabel abilitiesL = new JLabel(abilities);
        abilitiesL.setPreferredSize(new Dimension(450,112));
        abilitiesL.setFont(new Font("Times New Roman", 1, 22));
        String roles = "<html>Roles: ";
        for(String s : selected.getRoles())
            roles += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
        roles += "</html>";
        JLabel rolesL = new JLabel(roles);
        rolesL.setPreferredSize(new Dimension(450,66));
        rolesL.setFont(new Font("Times New Roman", 1, 22));
        panel.add(idL);
        panel.add(typeOfMemberL);
        panel.add(nameL);
        panel.add(dateOfBirthL);
        panel.add(genderL);
        panel.add(abilitiesL);
        panel.add(rolesL);
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
                filteredMember.clear();
                for(Member m : IMList)
                    if(m.getName().toLowerCase().contains(filterS.toLowerCase()))
                        filteredMember.add(m);
                String[] names = new String[filteredMember.size()];
                int j = -1;
                for(Member m : filteredMember)
                    names[++j] = m.getName();
                list.setListData(names);
                list.setSelectedIndex(0);
            }
        });
        list.addListSelectionListener(event -> {
            if(list.getSelectedIndex() != -1) {
                Member select = filteredMember.get(list.getSelectedIndex());
                idL.setText("ID: " + select.getID());
                if(select instanceof Administrator)
                    typeOfMemberL.setText("Type of Member: Administrator");
                else if(select instanceof Manager)
                    typeOfMemberL.setText("Type of Member: Manager");
                else if(select instanceof InternalMember)
                    typeOfMemberL.setText("Type of Member: Worker");
                else if(select instanceof ExternalMember)
                    typeOfMemberL.setText("Type of Member: External Member");
                nameL.setText("Name: " + select.getName());
                dateOfBirthL.setText("Date of Birth: " + select.getDateOfBirth());
                genderL.setText("Gender: " + select.getGender());
                String ability = "<html>Abilities: ";
                for(String s : select.getAbilities())
                    ability += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
                ability += "</html>";
                abilitiesL.setText(ability);
                String role = "<html>Roles: ";
                for(String s : select.getRoles())
                    role += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
                role += "</html>";
                rolesL.setText(role);
            }
        });
        PMS.rPanel.add(scrollPane);
        PMS.rPanel.add(panel);
        PMS.frame.setVisible(true);
    }
    
    public void searchEMs(ArrayList<Member> memberList) {
        PMS.rPanel.removeAll();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Search External Member"));
        ArrayList<Member> EMList = new ArrayList<>();
        ArrayList<Member> filteredMember = new ArrayList<>();
        for(Member m : memberList) {
            if(m instanceof ExternalMember) {
                EMList.add(m);
                filteredMember.add(m);
            }
        }
        int i = -1;
        String[] nameList = new String[filteredMember.size()];
        for(Member m : filteredMember) {
            nameList[++i] = m.getName();
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
        Member selected = memberList.get(list.getSelectedIndex());
        JLabel idL = new JLabel("ID: " + selected.getID());
        idL.setPreferredSize(new Dimension(450,22));
        idL.setFont(new Font("Times New Roman", 1, 22));
        JLabel typeOfMemberL = new JLabel();
        typeOfMemberL.setPreferredSize(new Dimension(450,22));
        typeOfMemberL.setFont(new Font("Times New Roman", 1, 22));
        if(selected instanceof Administrator)
            typeOfMemberL.setText("Type of Member: Administrator");
        else if(selected instanceof Manager)
            typeOfMemberL.setText("Type of Member: Manager");
        else if(selected instanceof InternalMember)
            typeOfMemberL.setText("Type of Member: Worker");
        else if(selected instanceof ExternalMember)
            typeOfMemberL.setText("Type of Member: External Member");
        JLabel nameL = new JLabel("Name: " + selected.getName());
        nameL.setPreferredSize(new Dimension(450,22));
        nameL.setFont(new Font("Times New Roman", 1, 22));
        JLabel dateOfBirthL = new JLabel("Date of Birth: " + selected.getDateOfBirth());
        dateOfBirthL.setPreferredSize(new Dimension(450,22));
        dateOfBirthL.setFont(new Font("Times New Roman", 1, 22));
        JLabel genderL = new JLabel("Gender: " + selected.getGender());
        genderL.setPreferredSize(new Dimension(450,22));
        genderL.setFont(new Font("Times New Roman", 1, 22));
        String abilities = "<html>Abilities: ";
        for(String s : selected.getAbilities())
            abilities += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
        abilities += "</html>";
        JLabel abilitiesL = new JLabel(abilities);
        abilitiesL.setPreferredSize(new Dimension(450,112));
        abilitiesL.setFont(new Font("Times New Roman", 1, 22));
        String roles = "<html>Roles: ";
        for(String s : selected.getRoles())
            roles += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
        roles += "</html>";
        JLabel rolesL = new JLabel(roles);
        rolesL.setPreferredSize(new Dimension(450,66));
        rolesL.setFont(new Font("Times New Roman", 1, 22));
        panel.add(idL);
        panel.add(typeOfMemberL);
        panel.add(nameL);
        panel.add(dateOfBirthL);
        panel.add(genderL);
        panel.add(abilitiesL);
        panel.add(rolesL);
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
                filteredMember.clear();
                for(Member m : EMList)
                    if(m.getName().toLowerCase().contains(filterS.toLowerCase()))
                        filteredMember.add(m);
                String[] names = new String[filteredMember.size()];
                int j = -1;
                for(Member m : filteredMember)
                    names[++j] = m.getName();
                list.setListData(names);
                list.setSelectedIndex(0);
            }
        });
        list.addListSelectionListener(event -> {
            if(list.getSelectedIndex() != -1) {
                Member select = filteredMember.get(list.getSelectedIndex());
                idL.setText("ID: " + select.getID());
                if(select instanceof Administrator)
                    typeOfMemberL.setText("Type of Member: Administrator");
                else if(select instanceof Manager)
                    typeOfMemberL.setText("Type of Member: Manager");
                else if(select instanceof InternalMember)
                    typeOfMemberL.setText("Type of Member: Worker");
                else if(select instanceof ExternalMember)
                    typeOfMemberL.setText("Type of Member: External Member");
                nameL.setText("Name: " + select.getName());
                dateOfBirthL.setText("Date of Birth: " + select.getDateOfBirth());
                genderL.setText("Gender: " + select.getGender());
                String ability = "<html>Abilities: ";
                for(String s : select.getAbilities())
                    ability += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
                ability += "</html>";
                abilitiesL.setText(ability);
                String role = "<html>Roles: ";
                for(String s : select.getRoles())
                    role += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
                role += "</html>";
                rolesL.setText(role);
            }
        });
        PMS.rPanel.add(scrollPane);
        PMS.rPanel.add(panel);
        PMS.frame.setVisible(true);
    }
    
    public void searchProject(ArrayList<Project> projectList) {
        PMS.rPanel.removeAll();
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Search Project"));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        ArrayList<Project> filteredProject = new ArrayList<>();
        for(Project p : projectList)
            filteredProject.add(p);
        int i = -1;
        String[] nameList = new String[filteredProject.size()];
        for(Project p : filteredProject) {
            nameList[++i] = p.getName();
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
                taskListL.setPreferredSize(new Dimension(450,30*select.getTaskList().size()+35));
                taskListL.setText(tasks);
            }
        });
        PMS.rPanel.add(scrollPane);
        PMS.rPanel.add(panel);
        PMS.frame.setVisible(true);
    }
    
    public void manageProject(ArrayList<Member> memberList, ArrayList<Project> projectList, ArrayList<Resource> resourceList) {
        PMS.rPanel.removeAll();
		FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
		PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Manage Project"));
        GridLayout gridLayout = new GridLayout(3,1);
        gridLayout.setVgap(20);
        JPanel panel = new JPanel(gridLayout);
        panel.setBorder(BorderFactory.createEmptyBorder(100, 200, 0, 0));
		JButton createB = new JButton("Create Project");
        createB.setPreferredSize(new Dimension(300,80));
        createB.setFont(new Font("Times New Roman", 1, 22));
		JButton updateB = new JButton("Update Project");
        updateB.setPreferredSize(new Dimension(300,80));
        updateB.setFont(new Font("Times New Roman", 1, 22));
		JButton removeB = new JButton("Remove Project");
        removeB.setPreferredSize(new Dimension(300,80));
        removeB.setFont(new Font("Times New Roman", 1, 22));
		createB.addActionListener(event -> {
			createProject(memberList, projectList, resourceList);
		});
		updateB.addActionListener(event -> {
			updateProject(memberList, projectList, resourceList);
		});
		removeB.addActionListener(event -> {
			removeProject(projectList);
		});
		panel.add(createB);
		panel.add(updateB);
		panel.add(removeB);
		PMS.rPanel.add(panel);
        PMS.frame.setVisible(true);
    }
    
    public void createProject(ArrayList<Member> memberList, ArrayList<Project> projectList, ArrayList<Resource> resourceList) {
        PMS.rPanel.removeAll();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Create Project"));
        String[] labelName = {"Name", "Description", "Manager", "Original Start Time", "Original Duration"};
		JLabel[] warnings = new JLabel[5];
		JTextField[] fields = new JTextField[2];
		JTextArea textArea = new JTextArea();
		ArrayList<JComboBox<String>> comboBoxes = new ArrayList<>();
		ArrayList<Member> managerList = new ArrayList<>();
		for(Member m : memberList) {
			if(m instanceof Manager)
				managerList.add(m);
		}
		for(int i = 0; i < labelName.length; i++) {
			JLabel warningL = new JLabel(" ");
            warningL.setPreferredSize(new Dimension(700,22));
            warningL.setForeground(Color.RED);
            warningL.setFont(new Font("Times New Roman", 1, 22));
			PMS.rPanel.add(warningL);
			warnings[i] = warningL;
			JLabel label = new JLabel(labelName[i]);
			label.setPreferredSize(new Dimension(350,22));
            label.setFont(new Font("Times New Roman", 1, 22));
			PMS.rPanel.add(label);
			if(i == 0) {
				JTextField textField = new JTextField();
				textField.setPreferredSize(new Dimension(350,22));
				textField.setFont(new Font("Times New Roman", 1, 22));
				fields[0] = textField;
				PMS.rPanel.add(textField);
			}
			else if(i == 1) {
				textArea.setPreferredSize(new Dimension(350, 112));
				textArea.setFont(new Font("Times New Roman", 1, 22));
				PMS.rPanel.add(textArea);
			}
			else if(i == 2) {
				String[] managers = new String[managerList.size()+1];
				managers[0] = "Not Select Manager";
				for(int j = 0; j < managerList.size(); j++)
					managers[j+1] = managerList.get(j).getName();
				JComboBox<String> comboBox = new JComboBox<>(managers);
				comboBoxes.add(comboBox);
				PMS.rPanel.add(comboBox);
			}
			else if(i == 3) {
				String[] day = new String[31];
				for(int j = 0; j < 31; j++)
					day[j] = Integer.toString(j+1);
                JComboBox<String> comboBox1 = new JComboBox<>(day);
                comboBox1.setPreferredSize(new Dimension(100,22));
                comboBox1.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox1);
                PMS.rPanel.add(comboBox1);
				String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                JComboBox<String> comboBox2 = new JComboBox<>(month);
                comboBox2.setPreferredSize(new Dimension(100,22));
                comboBox2.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox2);
                PMS.rPanel.add(comboBox2);
				String[] year = new String[20];
				for(int j = 0; j < 20; j++)
					year[j] = Integer.toString(2026-j);
                JComboBox<String> comboBox3 = new JComboBox<>(year);
				comboBox3.setSelectedIndex(10);
                comboBox3.setPreferredSize(new Dimension(100,22));
                comboBox3.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox3);
                PMS.rPanel.add(comboBox3);
			}
			else if(i == 4) {
				JTextField textField = new JTextField();
				textField.setPreferredSize(new Dimension(350,22));
				textField.setFont(new Font("Times New Roman", 1, 22));
				fields[1] = textField;
				PMS.rPanel.add(textField);
			}
		}
		JButton nextB = new JButton("Create Project");
		nextB.addActionListener(event -> {
			boolean tf = true;
			if(fields[0].getText().trim().equals("")){
                warnings[0].setText("Name cannot be empty");
                tf = false;
            }
			else if(Pattern.compile("[^a-zA-Z0-9. -]").matcher(fields[0].getText()).find()) {
				warnings[0].setText("Name can only contain alphanumeric characers or '.', '-'");
				tf = false;
			}
            else
                warnings[0].setText("");
			if(textArea.getText().trim().equals("")){
				warnings[1].setText("Description cannot be empty");
				tf = false;
			}
			else
				warnings[1].setText("");
			if(fields[1].getText().trim().equals("")) {
				warnings[4].setText("Original Duration cannot be empty");
				tf = false;
			}
			else if(Pattern.compile("[^0-9]").matcher(fields[1].getText()).find()) {
				warnings[4].setText("Original Duration can only contain numbers");
				tf = false;
			}
			else
				warnings[4].setText("");
			if(tf) {
				String name = fields[0].getText().trim();
				String description = textArea.getText().trim().replace("\n", "");
				int managerID = -1;
				if(comboBoxes.get(0).getSelectedIndex() != 0)
					managerID = managerList.get(comboBoxes.get(0).getSelectedIndex()-1).getID();
				String year = comboBoxes.get(3).getItemAt(comboBoxes.get(3).getSelectedIndex());
				String[] monthStr = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
				String month = comboBoxes.get(2).getItemAt(comboBoxes.get(2).getSelectedIndex());
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
				String day = comboBoxes.get(1).getItemAt(comboBoxes.get(1).getSelectedIndex());
				if(day.length() == 1)
					day = "0" + day;
				LocalDate oST = LocalDate.parse(year+"-"+month+"-"+day);
				int oDuration = Integer.parseInt(fields[1].getText());
				Project project = new Project(name, description, managerID, oST, LocalDate.MAX, oDuration, -1, new ArrayList<Task>());
				projectList.add(project);
				rewriteAllProjectData(projectList);
				JOptionPane.showMessageDialog(null, "Create Project Successfully");
				manageProject(memberList, projectList, resourceList);
			}
		});
		PMS.rPanel.add(nextB);
		PMS.frame.setVisible(true);
    }
    
    public void updateProject(ArrayList<Member> memberList, ArrayList<Project> projectList, ArrayList<Resource> resourceList) {
        PMS.rPanel.removeAll();
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Search Project"));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        ArrayList<Project> filteredProject = new ArrayList<>();
        for(Project p : projectList)
            filteredProject.add(p);
        int i = -1;
        String[] nameList = new String[filteredProject.size()];
        for(Project p : filteredProject) {
            nameList[++i] = p.getName();
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
                taskListL.setPreferredSize(new Dimension(450,30*select.getTaskList().size()+35));
                taskListL.setText(tasks);
            }
        });
		JButton updateProject = new JButton("Update Project");
		updateProject.addActionListener(event -> {
			if(list.getSelectedIndex() != -1) {
				Project project = filteredProject.get(list.getSelectedIndex());
				updateProjectInfo(project, memberList, projectList, resourceList);
			}
		});
		JButton updateTask = new JButton("Update Task");
		updateTask.addActionListener(event -> {
			if(list.getSelectedIndex() != -1) {
				Project project = filteredProject.get(list.getSelectedIndex());
				updateProjectTask(project, memberList, projectList, resourceList);
			}
		});
		panel.add(updateProject);
		panel.add(updateTask);
        PMS.rPanel.add(scrollPane);
        PMS.rPanel.add(panel);
        PMS.frame.setVisible(true);
    }
    
	public void updateProjectInfo(Project project, ArrayList<Member> memberList, ArrayList<Project> projectList, ArrayList<Resource> resourceList) {
		PMS.rPanel.removeAll();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Create Project"));
        String[] labelName = {"Name", "Description", "Manager", "Original Start Time", "Actual Start Time", "Original Duration", "Actual Duration"};
		JLabel[] warnings = new JLabel[8];
		JTextField[] fields = new JTextField[3];
		JTextArea textArea = new JTextArea(project.getDescription());
		ArrayList<JComboBox<String>> comboBoxes = new ArrayList<>();
		ArrayList<Member> managerList = new ArrayList<>();
		ButtonGroup buttonGroup1 = new ButtonGroup();
		ButtonGroup buttonGroup2 = new ButtonGroup();
		JRadioButton[] radioButtons = new JRadioButton[4];
		for(Member m : memberList) {
			if(m instanceof Manager)
				managerList.add(m);
		}
		for(int i = 0; i < labelName.length; i++) {
			JLabel warningL = new JLabel(" ");
            warningL.setPreferredSize(new Dimension(700,22));
            warningL.setForeground(Color.RED);
            warningL.setFont(new Font("Times New Roman", 1, 22));
			PMS.rPanel.add(warningL);
			warnings[i] = warningL;
			JLabel label = new JLabel(labelName[i]);
			label.setPreferredSize(new Dimension(350,22));
            label.setFont(new Font("Times New Roman", 1, 22));
			PMS.rPanel.add(label);
			if(i == 0) {
				JTextField textField = new JTextField(project.getName());
				textField.setPreferredSize(new Dimension(350,22));
				textField.setFont(new Font("Times New Roman", 1, 22));
				fields[0] = textField;
				PMS.rPanel.add(textField);
			}
			else if(i == 1) {
				textArea.setPreferredSize(new Dimension(350, 88));
				textArea.setFont(new Font("Times New Roman", 1, 22));
				PMS.rPanel.add(textArea);
			}
			else if(i == 2) {
				String[] managers = new String[managerList.size()+1];
				managers[0] = "Not Select Manager";
				int k = 0;
				for(int j = 0; j < managerList.size(); j++) {
					managers[j+1] = managerList.get(j).getName();
					if(project.getManagerID() == managerList.get(j).getID())
						k = j+1;
				}
				JComboBox<String> comboBox = new JComboBox<>(managers);
				comboBox.setSelectedIndex(k);
				comboBoxes.add(comboBox);
				PMS.rPanel.add(comboBox);
			}
			else if(i == 3) {
				String[] day = new String[31];
				for(int j = 0; j < 31; j++)
					day[j] = Integer.toString(j+1);
                JComboBox<String> comboBox1 = new JComboBox<>(day);
                comboBox1.setPreferredSize(new Dimension(100,22));
                comboBox1.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox1);
                PMS.rPanel.add(comboBox1);
				String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                JComboBox<String> comboBox2 = new JComboBox<>(month);
                comboBox2.setPreferredSize(new Dimension(100,22));
                comboBox2.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox2);
                PMS.rPanel.add(comboBox2);
				String[] year = new String[20];
				for(int j = 0; j < 20; j++)
					year[j] = Integer.toString(2026-j);
                JComboBox<String> comboBox3 = new JComboBox<>(year);
				comboBox3.setSelectedIndex(10);
                comboBox3.setPreferredSize(new Dimension(100,22));
                comboBox3.setFont(new Font("Times New Roman", 1, 22));
				comboBox1.setSelectedIndex(project.getOriginalST().getDayOfMonth()-1);
				comboBox2.setSelectedIndex(project.getOriginalST().getMonthValue()-1);
				comboBox3.setSelectedIndex(2026-project.getOriginalST().getYear());
                comboBoxes.add(comboBox3);
                PMS.rPanel.add(comboBox3);
			}
			else if(i == 4) {
				JRadioButton notStart;
                JRadioButton started;
				if(!project.getActualST().equals(LocalDate.MAX)) {
					notStart = new JRadioButton("Not Started");
					started = new JRadioButton("Started At", true);
				} else {
					notStart = new JRadioButton("Not Started", true);
					started = new JRadioButton("Started At");
				}
				notStart.setPreferredSize(new Dimension(175,22));
                notStart.setFont(new Font("Times New Roman", 1, 22));
				started.setPreferredSize(new Dimension(175,22));
                started.setFont(new Font("Times New Roman", 1, 22));
				radioButtons[0] = notStart;
				radioButtons[1] = started;
				buttonGroup1.add(notStart);
				buttonGroup1.add(started);
				PMS.rPanel.add(notStart);
				PMS.rPanel.add(started);
				JLabel buffer = new JLabel();
				buffer.setPreferredSize(new Dimension(350,22));
				PMS.rPanel.add(buffer);
				String[] day = new String[31];
				for(int j = 0; j < 31; j++)
					day[j] = Integer.toString(j+1);
                JComboBox<String> comboBox1 = new JComboBox<>(day);
                comboBox1.setPreferredSize(new Dimension(100,22));
                comboBox1.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox1);
                PMS.rPanel.add(comboBox1);
				String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                JComboBox<String> comboBox2 = new JComboBox<>(month);
                comboBox2.setPreferredSize(new Dimension(100,22));
                comboBox2.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox2);
                PMS.rPanel.add(comboBox2);
				String[] year = new String[20];
				for(int j = 0; j < 20; j++)
					year[j] = Integer.toString(2026-j);
                JComboBox<String> comboBox3 = new JComboBox<>(year);
				comboBox3.setSelectedIndex(10);
                comboBox3.setPreferredSize(new Dimension(100,22));
                comboBox3.setFont(new Font("Times New Roman", 1, 22));
				if(!project.getActualST().equals(LocalDate.MAX)) {
					comboBox1.setSelectedIndex(project.getActualST().getDayOfMonth()-1);
					comboBox2.setSelectedIndex(project.getActualST().getMonthValue()-1);
					comboBox3.setSelectedIndex(2026-project.getActualST().getYear());
				} else {
					comboBox1.setEnabled(false);
					comboBox2.setEnabled(false);
					comboBox3.setEnabled(false);
				}
				notStart.addActionListener(event -> {
					comboBox1.setEnabled(false);
					comboBox2.setEnabled(false);
					comboBox3.setEnabled(false);
				});
				started.addActionListener(event -> {
					comboBox1.setEnabled(true);
					comboBox2.setEnabled(true);
					comboBox3.setEnabled(true);
				});
				comboBoxes.add(comboBox3);
                PMS.rPanel.add(comboBox3);
			}
			else if(i == 5) {
				JTextField textField = new JTextField();
				textField.setPreferredSize(new Dimension(350,22));
				textField.setFont(new Font("Times New Roman", 1, 22));
				fields[1] = textField;
				textField.setText(""+project.getOriginalDuration());
				PMS.rPanel.add(textField);
			}
			else if(i == 6) {
				JRadioButton notFinish;
				JRadioButton finished;
				if(project.getActualDuration() != -1) {
					notFinish = new JRadioButton("Not Finish");
					finished = new JRadioButton("Finished At", true);
				} else {
					notFinish = new JRadioButton("Not Finish", true);
					finished = new JRadioButton("Finished At");
				}
				notFinish.setPreferredSize(new Dimension(175,22));
				notFinish.setFont(new Font("Times New Roman", 1, 22));
				finished.setPreferredSize(new Dimension(175,22));
				finished.setFont(new Font("Times New Roman", 1, 22));
				radioButtons[2] = notFinish;
				radioButtons[3] = finished;
				buttonGroup2.add(notFinish);
				buttonGroup2.add(finished);
				PMS.rPanel.add(notFinish);
				PMS.rPanel.add(finished);
				JLabel buffer = new JLabel();
				buffer.setPreferredSize(new Dimension(350,22));
				PMS.rPanel.add(buffer);
				JTextField textField = new JTextField();
				textField.setPreferredSize(new Dimension(350,22));
				textField.setFont(new Font("Times New Roman", 1, 22));
				fields[2] = textField;
				if(project.getActualDuration() != -1)
					textField.setText(""+project.getActualDuration());
                else 
					textField.setEditable(false);
				notFinish.addActionListener(event -> {
					textField.setEditable(false);
				});
				finished.addActionListener(event -> {
					textField.setEditable(true);
				});
				PMS.rPanel.add(textField);
			}
		}
		JButton nextB = new JButton("Update Project");
		nextB.addActionListener(event -> {
			boolean tf = true;
			if(fields[0].getText().trim().equals("")){
                warnings[0].setText("Name cannot be empty");
                tf = false;
            }
			else if(Pattern.compile("[^a-zA-Z0-9. -]").matcher(fields[0].getText()).find()) {
				warnings[0].setText("Name can only contain alphanumeric characers or '.', '-'");
				tf = false;
			}
            else
                warnings[0].setText("");
			if(textArea.getText().trim().equals("")){
				warnings[1].setText("Description cannot be empty");
				tf = false;
			}
			else
				warnings[1].setText("");
			if(fields[1].getText().trim().equals("")) {
				warnings[6].setText("Original Duration cannot be empty");
				tf = false;
			}
			else if(Pattern.compile("[^0-9]").matcher(fields[1].getText()).find()) {
				warnings[4].setText("Original Duration can only contain numbers");
				tf = false;
			}
			else
				warnings[4].setText("");
			if(radioButtons[3].isSelected() && Pattern.compile("[^0-9]").matcher(fields[1].getText()).find()) {
				warnings[5].setText("Actual Duration can only contain numbers");
				tf = false;
			}
			else
				warnings[5].setText("");
			if(tf) {
				String name = fields[0].getText().trim();
				String description = textArea.getText().trim().replace("\n", "");
				int managerID = -1;
				if(comboBoxes.get(0).getSelectedIndex() != 0)
					managerID = managerList.get(comboBoxes.get(0).getSelectedIndex()-1).getID();
				String year = comboBoxes.get(3).getItemAt(comboBoxes.get(3).getSelectedIndex());
				String month = comboBoxes.get(2).getItemAt(comboBoxes.get(2).getSelectedIndex());
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
				String day = comboBoxes.get(1).getItemAt(comboBoxes.get(1).getSelectedIndex());
				if(day.length() == 1)
					day = "0" + day;
				LocalDate oST = LocalDate.parse(year+"-"+month+"-"+day);
				LocalDate aST;
				if(radioButtons[1].isSelected()) {
					year = comboBoxes.get(6).getItemAt(comboBoxes.get(6).getSelectedIndex());
					month = comboBoxes.get(5).getItemAt(comboBoxes.get(5).getSelectedIndex());
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
					day = comboBoxes.get(4).getItemAt(comboBoxes.get(4).getSelectedIndex());
					if(day.length() == 1)
						day = "0" + day;
					aST = LocalDate.parse(year+"-"+month+"-"+day);
				}
				else
					aST = LocalDate.MAX;
				int oDuration = Integer.parseInt(fields[1].getText());
				int aDuration;
				if(radioButtons[3].isSelected())
					aDuration = Integer.parseInt(fields[2].getText());
				else
					aDuration = -1;
				project.setName(name);
				project.setDescription(description);
				project.setManagerID(managerID);
				project.setOriginalST(oST);
				project.setActualST(aST);
				project.setOriginalDuration(oDuration);
				project.setActualDuration(aDuration);
				rewriteAllProjectData(projectList);
				JOptionPane.showMessageDialog(null, "Update Project Successfully");
				updateProject(memberList, projectList, resourceList);
			}
		});
		PMS.rPanel.add(nextB);
		PMS.frame.setVisible(true);
	}
	
    public void updateProjectTask(Project project, ArrayList<Member> memberList, ArrayList<Project> projectList, ArrayList<Resource> resourceList) {
		PMS.rPanel.removeAll();
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Update Task"));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        ArrayList<Task> filteredTask = new ArrayList<>();
        for(Task t : project.getTaskList())
            filteredTask.add(t);
        int i = -1;
        String[] nameList = new String[filteredTask.size()];
        for(Task t : filteredTask) {
            nameList[++i] = t.getName();
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
        Task selected = filteredTask.get(list.getSelectedIndex());
        JLabel idL = new JLabel("ID: " + selected.getID());
        idL.setPreferredSize(new Dimension(450,22));
        idL.setFont(new Font("Times New Roman", 1, 22));
        JLabel nameL = new JLabel("Name: " + selected.getName());
        nameL.setPreferredSize(new Dimension(450,22));
        nameL.setFont(new Font("Times New Roman", 1, 22));
        JLabel descriptionL = new JLabel("Description: " + selected.getDescription());
        descriptionL.setPreferredSize(new Dimension(450,22));
        descriptionL.setFont(new Font("Times New Roman", 1, 22));
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
        String mList = "<html>Member: ";
        for(Member m : selected.getMember())
            mList += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + m.getName();
        mList += "</html>";
        JLabel memberListL = new JLabel(mList);
        memberListL.setPreferredSize(new Dimension(450,30*selected.getMember().size()+35));
        memberListL.setFont(new Font("Times New Roman", 1, 22));
        String rList = "<html>Resource: ";
        for(Resource r : selected.getResource())
            rList += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + r.getName();
        rList += "</html>";
        JLabel resourceListL = new JLabel(rList);
        resourceListL.setPreferredSize(new Dimension(450,30*selected.getResource().size()+35));
        resourceListL.setFont(new Font("Times New Roman", 1, 22));
        panel.add(idL);
        panel.add(nameL);
        panel.add(descriptionL);
        panel.add(originalSTL);
        panel.add(actualSTL);
        panel.add(originalDurationL);
        panel.add(actualDurationL);
        panel.add(memberListL);
        panel.add(resourceListL);
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
                filteredTask.clear();
                for(Task t : project.getTaskList())
                    if(t.getName().toLowerCase().contains(filterS.toLowerCase()))
                        filteredTask.add(t);
                String[] names = new String[filteredTask.size()];
                int j = -1;
                for(Task t : filteredTask)
                    names[++j] = t.getName();
                list.setListData(names);
                list.setSelectedIndex(0);
            }
        });
        list.addListSelectionListener(event -> {
            if(list.getSelectedIndex() != -1) {
                Task select = filteredTask.get(list.getSelectedIndex());
                idL.setText("ID: " + select.getID());
                nameL.setText("Name: " + select.getName());
                descriptionL.setText("Description: " + select.getDescription());
                originalSTL.setText("Original Start Time: " + select.getOriginalST());
                actualSTL.setText("Actural Start Time: " + (select.getActualST().equals(LocalDate.MAX) ? "Not yet started" : select.getActualST()));
                originalDurationL.setText("Original Duration: " + select.getOriginalDuration());
                actualDurationL.setText("Actural Duration: " + (select.getActualDuration() == -1 ? "Not yet finished" : select.getActualDuration()));
                String members = "<html>Member: ";
                for(Member m : select.getMember())
                    members += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + m.getName();
                members += "</html>";
                memberListL.setPreferredSize(new Dimension(450,30*select.getMember().size()+35));
                memberListL.setText(members);
                String resources = "<html>Resource: ";
                for(Resource r : select.getResource())
                    resources += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + r.getName();
                resources += "</html>";
                resourceListL.setPreferredSize(new Dimension(450,30*select.getResource().size()+35));
                resourceListL.setText(resources);
            }
        });
        JButton createTask = new JButton("Create Task");
        createTask.addActionListener(event -> {
			createTask(project, memberList, projectList, resourceList);
		});
		JButton updateTask = new JButton("Update Task");
		updateTask.addActionListener(event -> {
			if(list.getSelectedIndex() != -1) {
				Task task = filteredTask.get(list.getSelectedIndex());
				updateTask(task, project, memberList, projectList, resourceList);
			}
		});
        JButton removeTask = new JButton("Remove Task");
        removeTask.addActionListener(event -> {
            if(list.getSelectedIndex() != -1) {
                String[] options = {"Yes", "No"};
                int select = JOptionPane.showOptionDialog(null, "Do you really want to delete?", "Remove Member", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 0);
                if(select == 0) {
                    project.getTaskList().remove(filteredTask.get(list.getSelectedIndex()));
                    rewriteAllProjectData(projectList);
                    rewriteAllTaskData(projectList);
                    updateProjectTask(project, memberList, projectList, resourceList);
                }
            }
        });
        panel.add(createTask);
		panel.add(updateTask);
        panel.add(removeTask);
        PMS.rPanel.add(scrollPane);
        PMS.rPanel.add(panel);
        PMS.frame.setVisible(true);
    }
    
    public void createTask(Project project, ArrayList<Member> memberList, ArrayList<Project> projectList, ArrayList<Resource> resourceList) {
        PMS.rPanel.removeAll();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Create Task"));
        String[] labelName = {"Name", "Description", "Original Start Time", "Original Duration"};
		JLabel[] warnings = new JLabel[5];
		JTextField[] fields = new JTextField[2];
		JTextArea textArea = new JTextArea();
		ArrayList<JComboBox<String>> comboBoxes = new ArrayList<>();
		for(int i = 0; i < labelName.length; i++) {
			JLabel warningL = new JLabel(" ");
            warningL.setPreferredSize(new Dimension(700,22));
            warningL.setForeground(Color.RED);
            warningL.setFont(new Font("Times New Roman", 1, 22));
			PMS.rPanel.add(warningL);
			warnings[i] = warningL;
			JLabel label = new JLabel(labelName[i]);
			label.setPreferredSize(new Dimension(350,22));
            label.setFont(new Font("Times New Roman", 1, 22));
			PMS.rPanel.add(label);
			if(i == 0) {
				String[] names = {"Characters", "Scenery", "Objects", "Character Voices", "Music", "Sound Effects", "Java", "C++", "Testing"};
				JComboBox<String> name = new JComboBox<>(names);
                name.setPreferredSize(new Dimension(350,22));
				name.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(name);
                PMS.rPanel.add(name);
			}
			else if(i == 1) {
				textArea.setPreferredSize(new Dimension(350, 112));
				textArea.setFont(new Font("Times New Roman", 1, 22));
				PMS.rPanel.add(textArea);
			}
			else if(i == 2) {
				String[] day = new String[31];
				for(int j = 0; j < 31; j++)
					day[j] = Integer.toString(j+1);
                JComboBox<String> comboBox1 = new JComboBox<>(day);
                comboBox1.setPreferredSize(new Dimension(100,22));
                comboBox1.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox1);
                PMS.rPanel.add(comboBox1);
				String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                JComboBox<String> comboBox2 = new JComboBox<>(month);
                comboBox2.setPreferredSize(new Dimension(100,22));
                comboBox2.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox2);
                PMS.rPanel.add(comboBox2);
				String[] year = new String[20];
				for(int j = 0; j < 20; j++)
					year[j] = Integer.toString(2026-j);
                JComboBox<String> comboBox3 = new JComboBox<>(year);
				comboBox3.setSelectedIndex(10);
                comboBox3.setPreferredSize(new Dimension(100,22));
                comboBox3.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox3);
                PMS.rPanel.add(comboBox3);
			}
			else if(i == 3) {
				JTextField textField = new JTextField();
				textField.setPreferredSize(new Dimension(350,22));
				textField.setFont(new Font("Times New Roman", 1, 22));
				fields[0] = textField;
				PMS.rPanel.add(textField);
			}
		}
		JButton nextB = new JButton("Create Task");
		nextB.addActionListener(event -> {
			boolean tf = true;
			if(textArea.getText().trim().equals("")){
				warnings[1].setText("Description cannot be empty");
				tf = false;
			}
			else
				warnings[1].setText("");
			if(fields[0].getText().trim().equals("")) {
				warnings[3].setText("Original Duration cannot be empty");
				tf = false;
			}
			else if(Pattern.compile("[^0-9]").matcher(fields[0].getText()).find()) {
				warnings[3].setText("Original Duration can only contain numbers");
				tf = false;
			}
			else
				warnings[3].setText("");
			if(tf) {
				String name = comboBoxes.get(0).getItemAt(comboBoxes.get(0).getSelectedIndex());
				String description = textArea.getText().trim().replace("\n", "");
				String year = comboBoxes.get(3).getItemAt(comboBoxes.get(3).getSelectedIndex());
				String month = comboBoxes.get(2).getItemAt(comboBoxes.get(2).getSelectedIndex());
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
				String day = comboBoxes.get(1).getItemAt(comboBoxes.get(1).getSelectedIndex());
				if(day.length() == 1)
					day = "0" + day;
				LocalDate oST = LocalDate.parse(year+"-"+month+"-"+day);
				int oDuration = Integer.parseInt(fields[0].getText());
				Task task = new Task(name, description, project.getID(), oST, LocalDate.MAX, oDuration, -1, new ArrayList<Member>(), new ArrayList<Resource>());
				project.getTaskList().add(task);
				rewriteAllTaskData(projectList);
				JOptionPane.showMessageDialog(null, "Create Task Successfully\nThen Turn to Task Member Page");
				updateTaskMember(task, project, memberList, projectList, resourceList);
			}
		});
		PMS.rPanel.add(nextB);
		PMS.frame.setVisible(true);
    }
    
    public void updateTask(Task task, Project project, ArrayList<Member> memberList, ArrayList<Project> projectList, ArrayList<Resource> resourceList) {
        PMS.rPanel.removeAll();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Create Task"));
        String[] labelName = {"Name", "Description", "Original Start Time", "Actual Start Time", "Original Duration", "Actual Duration"};
		JLabel[] warnings = new JLabel[labelName.length];
		JTextField[] fields = new JTextField[2];
		JTextArea textArea = new JTextArea(task.getDescription());
		ArrayList<JComboBox<String>> comboBoxes = new ArrayList<>();
        ButtonGroup buttonGroup1 = new ButtonGroup();
        ButtonGroup buttonGroup2 = new ButtonGroup();
        JRadioButton[] radioButtons = new JRadioButton[4];
		for(int i = 0; i < labelName.length; i++) {
			JLabel warningL = new JLabel(" ");
            warningL.setPreferredSize(new Dimension(700,22));
            warningL.setForeground(Color.RED);
            warningL.setFont(new Font("Times New Roman", 1, 22));
			PMS.rPanel.add(warningL);
			warnings[i] = warningL;
			JLabel label = new JLabel(labelName[i]);
			label.setPreferredSize(new Dimension(350,22));
            label.setFont(new Font("Times New Roman", 1, 22));
			PMS.rPanel.add(label);
			if(i == 0) {
				String[] names = {"Characters", "Scenery", "Objects", "Character Voices", "Music", "Sound Effects", "Java", "C++", "Testing"};
				JComboBox<String> name = new JComboBox<>(names);
                name.setPreferredSize(new Dimension(350,22));
				name.setFont(new Font("Times New Roman", 1, 22));
                for(int j = 0; j < names.length; j++) {
                    if(names[j].equals(task.getName())) {
                        name.setSelectedIndex(j);
                        break;
                    }
                }
                comboBoxes.add(name);
                PMS.rPanel.add(name);
			}
			else if(i == 1) {
				textArea.setPreferredSize(new Dimension(350, 112));
				textArea.setFont(new Font("Times New Roman", 1, 22));
                PMS.rPanel.add(textArea);
			}
			else if(i == 2) {
				String[] day = new String[31];
				for(int j = 0; j < 31; j++)
					day[j] = Integer.toString(j+1);
                JComboBox<String> comboBox1 = new JComboBox<>(day);
                comboBox1.setPreferredSize(new Dimension(100,22));
                comboBox1.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox1);
                PMS.rPanel.add(comboBox1);
				String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                JComboBox<String> comboBox2 = new JComboBox<>(month);
                comboBox2.setPreferredSize(new Dimension(100,22));
                comboBox2.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox2);
                PMS.rPanel.add(comboBox2);
				String[] year = new String[20];
				for(int j = 0; j < 20; j++)
					year[j] = Integer.toString(2026-j);
                JComboBox<String> comboBox3 = new JComboBox<>(year);
				comboBox3.setSelectedIndex(10);
                comboBox3.setPreferredSize(new Dimension(100,22));
                comboBox3.setFont(new Font("Times New Roman", 1, 22));
                comboBox1.setSelectedIndex(task.getOriginalST().getDayOfMonth()-1);
                comboBox2.setSelectedIndex(task.getOriginalST().getMonthValue()-1);
                comboBox3.setSelectedIndex(2026-task.getOriginalST().getYear());
                comboBoxes.add(comboBox3);
                PMS.rPanel.add(comboBox3);
			} else if(i == 3) {
				JRadioButton notStart;
                JRadioButton started;
				if(!project.getActualST().equals(LocalDate.MAX)) {
					notStart = new JRadioButton("Not Started");
					started = new JRadioButton("Started At", true);
				} else {
					notStart = new JRadioButton("Not Started", true);
					started = new JRadioButton("Started At");
				}
				notStart.setPreferredSize(new Dimension(175,22));
                notStart.setFont(new Font("Times New Roman", 1, 22));
				started.setPreferredSize(new Dimension(175,22));
                started.setFont(new Font("Times New Roman", 1, 22));
				radioButtons[0] = notStart;
				radioButtons[1] = started;
				buttonGroup1.add(notStart);
				buttonGroup1.add(started);
				PMS.rPanel.add(notStart);
				PMS.rPanel.add(started);
				JLabel buffer = new JLabel();
				buffer.setPreferredSize(new Dimension(350,22));
				PMS.rPanel.add(buffer);
				String[] day = new String[31];
				for(int j = 0; j < 31; j++)
					day[j] = Integer.toString(j+1);
                JComboBox<String> comboBox1 = new JComboBox<>(day);
                comboBox1.setPreferredSize(new Dimension(100,22));
                comboBox1.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox1);
                PMS.rPanel.add(comboBox1);
				String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                JComboBox<String> comboBox2 = new JComboBox<>(month);
                comboBox2.setPreferredSize(new Dimension(100,22));
                comboBox2.setFont(new Font("Times New Roman", 1, 22));
                comboBoxes.add(comboBox2);
                PMS.rPanel.add(comboBox2);
				String[] year = new String[20];
				for(int j = 0; j < 20; j++)
					year[j] = Integer.toString(2026-j);
                JComboBox<String> comboBox3 = new JComboBox<>(year);
				comboBox3.setSelectedIndex(10);
                comboBox3.setPreferredSize(new Dimension(100,22));
                comboBox3.setFont(new Font("Times New Roman", 1, 22));
				if(!project.getActualST().equals(LocalDate.MAX)) {
					comboBox1.setSelectedIndex(task.getActualST().getDayOfMonth()-1);
					comboBox2.setSelectedIndex(task.getActualST().getMonthValue()-1);
					comboBox3.setSelectedIndex(2026-task.getActualST().getYear());
				} else {
					comboBox1.setEnabled(false);
					comboBox2.setEnabled(false);
					comboBox3.setEnabled(false);
				}
				notStart.addActionListener(event -> {
					comboBox1.setEnabled(false);
					comboBox2.setEnabled(false);
					comboBox3.setEnabled(false);
				});
				started.addActionListener(event -> {
					comboBox1.setEnabled(true);
					comboBox2.setEnabled(true);
					comboBox3.setEnabled(true);
				});
				comboBoxes.add(comboBox3);
                PMS.rPanel.add(comboBox3);
			} else if(i == 4) {
				JTextField textField = new JTextField(""+task.getOriginalDuration());
				textField.setPreferredSize(new Dimension(350,22));
				textField.setFont(new Font("Times New Roman", 1, 22));
				fields[0] = textField;
				PMS.rPanel.add(textField);
			} else if(i == 5) {
				JRadioButton notFinish;
				JRadioButton finished;
				if(project.getActualDuration() != -1) {
					notFinish = new JRadioButton("Not Finish");
					finished = new JRadioButton("Finished At", true);
				} else {
					notFinish = new JRadioButton("Not Finish", true);
					finished = new JRadioButton("Finished At");
				}
				notFinish.setPreferredSize(new Dimension(175,22));
				notFinish.setFont(new Font("Times New Roman", 1, 22));
				finished.setPreferredSize(new Dimension(175,22));
				finished.setFont(new Font("Times New Roman", 1, 22));
				radioButtons[2] = notFinish;
				radioButtons[3] = finished;
				buttonGroup2.add(notFinish);
				buttonGroup2.add(finished);
				PMS.rPanel.add(notFinish);
				PMS.rPanel.add(finished);
				JLabel buffer = new JLabel();
				buffer.setPreferredSize(new Dimension(350,22));
				PMS.rPanel.add(buffer);
				JTextField textField = new JTextField();
				textField.setPreferredSize(new Dimension(350,22));
				textField.setFont(new Font("Times New Roman", 1, 22));
				fields[1] = textField;
				if(project.getActualDuration() != -1)
					textField.setText(""+project.getActualDuration());
                else 
					textField.setEditable(false);
				notFinish.addActionListener(event -> {
					textField.setEditable(false);
				});
				finished.addActionListener(event -> {
					textField.setEditable(true);
				});
				PMS.rPanel.add(textField);
			}
		}
		JButton nextB = new JButton("Update Task");
		nextB.addActionListener(event -> {
			boolean tf = true;
			if(textArea.getText().trim().equals("")){
				warnings[1].setText("Description cannot be empty");
				tf = false;
			}
			else
				warnings[1].setText("");
			if(fields[0].getText().trim().equals("")) {
				warnings[4].setText("Original Duration cannot be empty");
				tf = false;
			}
			else if(Pattern.compile("[^0-9]").matcher(fields[0].getText()).find()) {
				warnings[4].setText("Original Duration can only contain numbers");
				tf = false;
			}
			else
				warnings[4].setText("");
            if(radioButtons[3].isSelected() && fields[1].getText().trim().equals("")) {
				warnings[5].setText("Actual Duration cannot be empty");
				tf = false;
			}
			else if(radioButtons[3].isSelected() && Pattern.compile("[^0-9]").matcher(fields[1].getText()).find()) {
				warnings[5].setText("Actual Duration can only contain numbers");
				tf = false;
			}
			else
				warnings[5].setText("");
			if(tf) {
				String name = comboBoxes.get(0).getItemAt(comboBoxes.get(0).getSelectedIndex());
				String description = textArea.getText().trim().replace("\n", "");
				String year = comboBoxes.get(3).getItemAt(comboBoxes.get(3).getSelectedIndex());
				String month = comboBoxes.get(2).getItemAt(comboBoxes.get(2).getSelectedIndex());
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
				String day = comboBoxes.get(1).getItemAt(comboBoxes.get(1).getSelectedIndex());
				if(day.length() == 1)
					day = "0" + day;
				LocalDate oST = LocalDate.parse(year+"-"+month+"-"+day);
                LocalDate aST;
                if(radioButtons[0].isSelected())
                    aST = LocalDate.MAX;
                else {
                    year = comboBoxes.get(6).getItemAt(comboBoxes.get(6).getSelectedIndex());
                    month = comboBoxes.get(5).getItemAt(comboBoxes.get(5).getSelectedIndex());
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
                    day = comboBoxes.get(4).getItemAt(comboBoxes.get(4).getSelectedIndex());
                    if(day.length() == 1)
                        day = "0" + day;
                    aST = LocalDate.parse(year+"-"+month+"-"+day);
                }
				int oDuration = Integer.parseInt(fields[0].getText());
                int aDuration;
                if(radioButtons[2].isSelected())
                    aDuration = -1;
                else
                    aDuration = Integer.parseInt(fields[1].getText());
				task.setName(name);
                task.setDescription(description);
                task.setOriginalST(oST);
                task.setActualST(aST);
                task.setOriginalDuration(oDuration);
                task.setActualDuration(aDuration);
				project.getTaskList().add(task);
				rewriteAllTaskData(projectList);
				JOptionPane.showMessageDialog(null, "Update Task Successfully\nThen Turn to Task Member Page");
				updateTaskMember(task, project, memberList, projectList, resourceList);
			}
		});
        JButton doneB = new JButton("Done");
        doneB.addActionListener(event -> {
            updateProjectTask(project, memberList, projectList, resourceList);
        });
		PMS.rPanel.add(nextB);
		PMS.frame.setVisible(true);
    }
    
    public void updateTaskMember(Task task, Project project, ArrayList<Member> memberList, ArrayList<Project> projectList, ArrayList<Resource> resourceList) {
        PMS.rPanel.removeAll();
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Update Task Member"));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        ArrayList<Member> IMList = new ArrayList<>();
        ArrayList<Member> filteredMember = new ArrayList<>();
        for(Member m : memberList) {
            if(m instanceof InternalMember && m.getAbilities().contains(task.getName()) && !task.getMember().contains(m)) {
                IMList.add(m);
                filteredMember.add(m);
            }
        }
        int i = -1;
        String[] nameList = new String[filteredMember.size()];
        for(Member m : filteredMember) {
            nameList[++i] = m.getName();
        }
        if(nameList.length == 0) {
            JOptionPane.showMessageDialog(null, "No member available\nTurn to resources page");
            updateTaskResource(task, project, memberList, projectList, resourceList);
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
        Member selected = memberList.get(list.getSelectedIndex());
        JLabel idL = new JLabel("ID: " + selected.getID());
        idL.setPreferredSize(new Dimension(450,22));
        idL.setFont(new Font("Times New Roman", 1, 22));
        JLabel typeOfMemberL = new JLabel();
        typeOfMemberL.setPreferredSize(new Dimension(450,22));
        typeOfMemberL.setFont(new Font("Times New Roman", 1, 22));
        if(selected instanceof Administrator)
            typeOfMemberL.setText("Type of Member: Administrator");
        else if(selected instanceof Manager)
            typeOfMemberL.setText("Type of Member: Manager");
        else if(selected instanceof InternalMember)
            typeOfMemberL.setText("Type of Member: Worker");
        else if(selected instanceof ExternalMember)
            typeOfMemberL.setText("Type of Member: External Member");
        JLabel nameL = new JLabel("Name: " + selected.getName());
        nameL.setPreferredSize(new Dimension(450,22));
        nameL.setFont(new Font("Times New Roman", 1, 22));
        JLabel dateOfBirthL = new JLabel("Date of Birth: " + selected.getDateOfBirth());
        dateOfBirthL.setPreferredSize(new Dimension(450,22));
        dateOfBirthL.setFont(new Font("Times New Roman", 1, 22));
        JLabel genderL = new JLabel("Gender: " + selected.getGender());
        genderL.setPreferredSize(new Dimension(450,22));
        genderL.setFont(new Font("Times New Roman", 1, 22));
        String abilities = "<html>Abilities: ";
        for(String s : selected.getAbilities())
            abilities += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
        abilities += "</html>";
        JLabel abilitiesL = new JLabel(abilities);
        abilitiesL.setPreferredSize(new Dimension(450,112));
        abilitiesL.setFont(new Font("Times New Roman", 1, 22));
        String roles = "<html>Roles: ";
        for(String s : selected.getRoles())
            roles += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
        roles += "</html>";
        JLabel rolesL = new JLabel(roles);
        rolesL.setPreferredSize(new Dimension(450,66));
        rolesL.setFont(new Font("Times New Roman", 1, 22));
        panel.add(idL);
        panel.add(typeOfMemberL);
        panel.add(nameL);
        panel.add(dateOfBirthL);
        panel.add(genderL);
        panel.add(abilitiesL);
        panel.add(rolesL);
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
                filteredMember.clear();
                for(Member m : IMList)
                    if(m.getName().toLowerCase().contains(filterS.toLowerCase()))
                        filteredMember.add(m);
                String[] names = new String[filteredMember.size()];
                int j = -1;
                for(Member m : filteredMember)
                    names[++j] = m.getName();
                list.setListData(names);
                list.setSelectedIndex(0);
            }
        });
        list.addListSelectionListener(event -> {
            if(list.getSelectedIndex() != -1) {
                Member select = filteredMember.get(list.getSelectedIndex());
                idL.setText("ID: " + select.getID());
                if(select instanceof Administrator)
                    typeOfMemberL.setText("Type of Member: Administrator");
                else if(select instanceof Manager)
                    typeOfMemberL.setText("Type of Member: Manager");
                else if(select instanceof InternalMember)
                    typeOfMemberL.setText("Type of Member: Worker");
                else if(select instanceof ExternalMember)
                    typeOfMemberL.setText("Type of Member: External Member");
                nameL.setText("Name: " + select.getName());
                dateOfBirthL.setText("Date of Birth: " + select.getDateOfBirth());
                genderL.setText("Gender: " + select.getGender());
                String ability = "<html>Abilities: ";
                for(String s : select.getAbilities())
                    ability += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
                ability += "</html>";
                abilitiesL.setText(ability);
                String role = "<html>Roles: ";
                for(String s : select.getRoles())
                    role += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
                role += "</html>";
                rolesL.setText(role);
            }
        });
        JButton addB = new JButton("Add to Task");
        addB.addActionListener(event -> {
            if(list.getSelectedIndex() != -1) {
                Member select = filteredMember.get(list.getSelectedIndex());
                task.getMember().add(select);
                rewriteAllTaskData(projectList);
                IMList.remove(select);
                String filterS = filterTF.getText();
                filteredMember.clear();
                for(Member m : IMList)
                    if(m.getName().toLowerCase().contains(filterS.toLowerCase()))
                        filteredMember.add(m);
                String[] names = new String[filteredMember.size()];
                int j = -1;
                for(Member m : filteredMember)
                    names[++j] = m.getName();
                list.setListData(names);
                if(names.length > 0)
                    list.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "" + select.getName() + " join the task");
            }
        });
        JButton nextB = new JButton("Next");
        nextB.addActionListener(event -> {
            updateTaskResource(task, project, memberList, projectList, resourceList);
        });
        panel.add(addB);
        panel.add(nextB);
        PMS.rPanel.add(scrollPane);
        PMS.rPanel.add(panel);
        PMS.frame.setVisible(true);
    }
    
    public void updateTaskResource(Task task, Project project, ArrayList<Member> memberList, ArrayList<Project> projectList, ArrayList<Resource> resourceList) {
        PMS.rPanel.removeAll();
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Search Project"));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        ArrayList<Resource> resources = new ArrayList<>();
        ArrayList<Resource> filteredResource = new ArrayList<>();
		for(Resource r : resourceList) {
            if(!task.getResource().contains(r)) {
                filteredResource.add(r);
                resources.add(r);
            }
		}
        int i = -1;
        String[] nameList = new String[filteredResource.size()];
        for(Resource r : filteredResource) {
            nameList[++i] = r.getName();
        }
        if(filteredResource.size() == 0) {
            JOptionPane.showMessageDialog(null, "No Resource");
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
        Resource selected = filteredResource.get(list.getSelectedIndex());
        JLabel idL = new JLabel("ID: " + selected.getID());
        idL.setPreferredSize(new Dimension(450,22));
        idL.setFont(new Font("Times New Roman", 1, 22));
        JLabel nameL = new JLabel("Name: " + selected.getName());
        nameL.setPreferredSize(new Dimension(450,22));
        nameL.setFont(new Font("Times New Roman", 1, 22));
		JLabel typeL = new JLabel("Type of Resource: " + selected.getTypeOfResource());
        typeL.setPreferredSize(new Dimension(450,22));
        typeL.setFont(new Font("Times New Roman", 1, 22));
		JLabel costL = new JLabel("Cost: $" + selected.getCost());
        costL.setPreferredSize(new Dimension(450,22));
        costL.setFont(new Font("Times New Roman", 1, 22));
        panel.add(idL);
        panel.add(nameL);
		panel.add(typeL);
		panel.add(costL);
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
                filteredResource.clear();
                for(Resource r : resources)
                    if(r.getName().toLowerCase().contains(filterS.toLowerCase()))
                        filteredResource.add(r);
                String[] names = new String[filteredResource.size()];
                int j = -1;
                for(Resource r : filteredResource)
                    names[++j] = r.getName();
                list.setListData(names);
                list.setSelectedIndex(0);
            }
        });
        list.addListSelectionListener(event -> {
            if(list.getSelectedIndex() != -1) {
                Resource select = filteredResource.get(list.getSelectedIndex());
                idL.setText("ID: " + select.getID());
                nameL.setText("Name: " + select.getName());
				typeL.setText("Type of Resource: " + select.getTypeOfResource());
				costL.setText("Cost: " + select.getCost());
            }
        });
        JButton addB = new JButton("Add to Task");
        addB.addActionListener(event -> {
            if(list.getSelectedIndex() != -1) {
                Resource select = filteredResource.get(list.getSelectedIndex());
                task.getResource().add(select);
                rewriteAllTaskData(projectList);
                resources.remove(select);
                String filterS = filterTF.getText();
                filteredResource.clear();
                for(Resource r : resources)
                    if(r.getName().toLowerCase().contains(filterS.toLowerCase()))
                        filteredResource.add(r);
                String[] names = new String[filteredResource.size()];
                int j = -1;
                for(Resource r : filteredResource)
                    names[++j] = r.getName();
                list.setListData(names);
                if(names.length > 0)
                    list.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "" + select.getName() + " include in the task");
            }
        });
        JButton doneB = new JButton("Done");
        doneB.addActionListener(event -> {
            JOptionPane.showMessageDialog(null, "Task Create Process Done!");
            updateProjectTask(project, memberList, projectList, resourceList);
        });
        panel.add(addB);
        panel.add(doneB);
        PMS.rPanel.add(scrollPane);
        PMS.rPanel.add(panel);
        PMS.frame.setVisible(true);
    }
    
    public void removeProject(ArrayList<Project> projectList) {
        PMS.rPanel.removeAll();
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Search Project"));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        ArrayList<Project> filteredProject = new ArrayList<>();
        for(Project p : projectList)
            filteredProject.add(p);
        int i = -1;
        String[] nameList = new String[filteredProject.size()];
        for(Project p : filteredProject) {
            nameList[++i] = p.getName();
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
		JButton removeB = new JButton("Remove Project");
		removeB.addActionListener(event -> {
			if(list.getSelectedIndex() != -1) {
				String[] options = {"Yes", "No"};
				int select = JOptionPane.showOptionDialog(null, "Do you really want to delete?", "Remove Member", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 0);
				if(select == 0) {
					Project project = filteredProject.get(list.getSelectedIndex());
					projectList.remove(project);
					rewriteAllProjectData(projectList);
					rewriteAllTaskData(projectList);
					JOptionPane.showMessageDialog(null, "Remove Project Successfully");
					this.displayInfo();
				}
			}
		});
		panel.add(removeB);
        PMS.rPanel.add(scrollPane);
        PMS.rPanel.add(panel);
        PMS.frame.setVisible(true);
    }
    
    public void performance(ArrayList<Member> memberList, ArrayList<Project> projectList) {
        PMS.rPanel.removeAll();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Performance"));
        GridLayout gridLayout = new GridLayout(3,1);
        gridLayout.setVgap(20);
        JPanel panel = new JPanel(gridLayout);
        panel.setBorder(BorderFactory.createEmptyBorder(150, 200, 0, 0));
        JButton projectB = new JButton("Project");
        projectB.setPreferredSize(new Dimension(300,80));
        projectB.setFont(new Font("Times New Roman", 1, 22));
		JButton memberB = new JButton("Member");
        memberB.setPreferredSize(new Dimension(300,80));
        memberB.setFont(new Font("Times New Roman", 1, 22));
		projectB.addActionListener(event -> {
			projectPerformance(projectList);
		});
		memberB.addActionListener(event -> {
			memberPerformance(memberList, projectList);
		});
		panel.add(projectB);
		panel.add(memberB);
        PMS.rPanel.add(panel);
		PMS.frame.setVisible(true);
    }
    
    public void projectPerformance(ArrayList<Project> projectList) {
        PMS.rPanel.removeAll();
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Search Project"));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        ArrayList<Project> filteredProject = new ArrayList<>();
        for(Project p : projectList)
            filteredProject.add(p);
        int i = -1;
        String[] nameList = new String[filteredProject.size()];
        for(Project p : filteredProject) {
            nameList[++i] = p.getName();
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
		String performance = "";
		if(selected.getActualDuration() == -1)
                performance = "Cannot Determine";
            else if(selected.getActualDuration() - selected.getOriginalDuration() + selected.getActualST().compareTo(selected.getOriginalST()) > 0)
                performance = "Bad";
            else if(selected.getActualDuration() - selected.getOriginalDuration() + selected.getActualST().compareTo(selected.getOriginalST()) == 0)
                performance = "Good";
            else if(selected.getActualDuration() - selected.getOriginalDuration() + selected.getActualST().compareTo(selected.getOriginalST()) < 0)
                performance = "Excellent";
		JLabel performanceL = new JLabel("Performance: " + performance);
		performanceL.setPreferredSize(new Dimension(450,22));
        performanceL.setFont(new Font("Times New Roman", 1, 22));
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
        panel.add(performanceL);
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
				String perform = "";
				if(select.getActualDuration() == -1)
					perform = "Cannot Determine";
				else if(select.getActualDuration() - select.getOriginalDuration() + select.getActualST().compareTo(select.getOriginalST()) > 0)
					perform = "Bad";
				else if(select.getActualDuration() - select.getOriginalDuration() + select.getActualST().compareTo(select.getOriginalST()) == 0)
					perform = "Good";
				else if(select.getActualDuration() - select.getOriginalDuration() + select.getActualST().compareTo(select.getOriginalST()) < 0)
					perform = "Excellent";
				performanceL.setText("Performance: " + perform);
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
                taskListL.setPreferredSize(new Dimension(450,30*select.getTaskList().size()+35));
                taskListL.setText(tasks);
            }
        });
        PMS.rPanel.add(scrollPane);
        PMS.rPanel.add(panel);
        PMS.frame.setVisible(true); 
    }
    
    public void memberPerformance(ArrayList<Member> memberList, ArrayList<Project> projectList) {
        PMS.rPanel.removeAll();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        PMS.rPanel.setLayout(flowLayout);
        PMS.rPanel.setBorder(BorderFactory.createTitledBorder("Search Internal Member"));
        ArrayList<Member> filteredMember = new ArrayList<>();
        for(Member m : memberList) {
            filteredMember.add(m);
        }
        int i = -1;
        String[] nameList = new String[filteredMember.size()];
        for(Member m : filteredMember) {
            nameList[++i] = m.getName();
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
        Member selected = memberList.get(list.getSelectedIndex());
        double score = 0;
		int n = 0;
		for(Project p : projectList) {
			for(Task t : p.getTaskList()) {
				for(Member m : t.getMember()) {
					if(m == selected) {
						if(t.getActualDuration() == -1)  {
							n++;
							continue;
						}
						else if(t.getActualDuration() - t.getOriginalDuration() + t.getActualST().compareTo(t.getOriginalST()) > 0) {
							score -= 1;
							n++;
						}
						else if(t.getActualDuration() - t.getOriginalDuration() + t.getActualST().compareTo(t.getOriginalST()) == 0) {
							n++;
						}
						else if(t.getActualDuration() - t.getOriginalDuration() + t.getActualST().compareTo(t.getOriginalST()) < 0) {
							score += 1;
							n++;
						}
					}
				}
			}
		}
		score /= n;
		String performance = "";
		if(n == 0)
			performance = "Cannot Determine";
		else if(score <= -0.5)
			performance = "Bad";
		else if(score <= 0.5)
			performance = "Good";
		else
			performance = "Excellent";
		JLabel performanceL = new JLabel("Performance: " + performance);
		performanceL.setPreferredSize(new Dimension(450,22));
        performanceL.setFont(new Font("Times New Roman", 1, 22));
		JLabel idL = new JLabel("ID: " + selected.getID());
        idL.setPreferredSize(new Dimension(450,22));
        idL.setFont(new Font("Times New Roman", 1, 22));
        JLabel typeOfMemberL = new JLabel();
        typeOfMemberL.setPreferredSize(new Dimension(450,22));
        typeOfMemberL.setFont(new Font("Times New Roman", 1, 22));
        if(selected instanceof Administrator)
            typeOfMemberL.setText("Type of Member: Administrator");
        else if(selected instanceof Manager)
            typeOfMemberL.setText("Type of Member: Manager");
        else if(selected instanceof InternalMember)
            typeOfMemberL.setText("Type of Member: Worker");
        else if(selected instanceof ExternalMember)
            typeOfMemberL.setText("Type of Member: External Member");
        JLabel nameL = new JLabel("Name: " + selected.getName());
        nameL.setPreferredSize(new Dimension(450,22));
        nameL.setFont(new Font("Times New Roman", 1, 22));
        JLabel dateOfBirthL = new JLabel("Date of Birth: " + selected.getDateOfBirth());
        dateOfBirthL.setPreferredSize(new Dimension(450,22));
        dateOfBirthL.setFont(new Font("Times New Roman", 1, 22));
        JLabel genderL = new JLabel("Gender: " + selected.getGender());
        genderL.setPreferredSize(new Dimension(450,22));
        genderL.setFont(new Font("Times New Roman", 1, 22));
        String abilities = "<html>Abilities: ";
        for(String s : selected.getAbilities())
            abilities += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
        abilities += "</html>";
        JLabel abilitiesL = new JLabel(abilities);
        abilitiesL.setPreferredSize(new Dimension(450,112));
        abilitiesL.setFont(new Font("Times New Roman", 1, 22));
        String roles = "<html>Roles: ";
        for(String s : selected.getRoles())
            roles += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
        roles += "</html>";
        JLabel rolesL = new JLabel(roles);
        rolesL.setPreferredSize(new Dimension(450,66));
        rolesL.setFont(new Font("Times New Roman", 1, 22));
		panel.add(performanceL);
        panel.add(idL);
        panel.add(typeOfMemberL);
        panel.add(nameL);
        panel.add(dateOfBirthL);
        panel.add(genderL);
        panel.add(abilitiesL);
        panel.add(rolesL);
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
                filteredMember.clear();
                for(Member m : memberList)
                    if(m.getName().toLowerCase().contains(filterS.toLowerCase()))
                        filteredMember.add(m);
                String[] names = new String[filteredMember.size()];
                int j = -1;
                for(Member m : filteredMember)
                    names[++j] = m.getName();
                list.setListData(names);
                list.setSelectedIndex(0);
            }
        });
        list.addListSelectionListener(event -> {
            if(list.getSelectedIndex() != -1) {
                Member select = filteredMember.get(list.getSelectedIndex());
                double scores = 0;
				int k = 0;
				for(Project p : projectList) {
					for(Task t : p.getTaskList()) {
						for(Member m : t.getMember()) {
							if(m == select) {
								if(t.getActualDuration() == -1) {
									k++;
									continue;
								}
								else if(t.getActualDuration() - t.getOriginalDuration() + t.getActualST().compareTo(t.getOriginalST()) > 0) {
									scores -= 1;
									k++;
								}
								else if(t.getActualDuration() - t.getOriginalDuration() + t.getActualST().compareTo(t.getOriginalST()) == 0) {
									k++;
								}
								else if(t.getActualDuration() - t.getOriginalDuration() + t.getActualST().compareTo(t.getOriginalST()) < 0) {
									scores += 1;
									k++;
								}
							}
						}
					}
				}
				scores /= k;
				String perform = "";
				if(k == 0)
					perform = "Cannot Determine";
				else if(scores <= -0.5)
					perform = "Bad";
				else if(scores <= 0.5)
					perform = "Good";
				else
					perform = "Excellent";
				performanceL.setText("Performance: " + perform);
				idL.setText("ID: " + select.getID());
                if(select instanceof Administrator)
                    typeOfMemberL.setText("Type of Member: Administrator");
                else if(select instanceof Manager)
                    typeOfMemberL.setText("Type of Member: Manager");
                else if(select instanceof InternalMember)
                    typeOfMemberL.setText("Type of Member: Worker");
                else if(select instanceof ExternalMember)
                    typeOfMemberL.setText("Type of Member: External Member");
                nameL.setText("Name: " + select.getName());
                dateOfBirthL.setText("Date of Birth: " + select.getDateOfBirth());
                genderL.setText("Gender: " + select.getGender());
                String ability = "<html>Abilities: ";
                for(String s : select.getAbilities())
                    ability += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
                ability += "</html>";
                abilitiesL.setText(ability);
                String role = "<html>Roles: ";
                for(String s : select.getRoles())
                    role += "<br>&nbsp;&nbsp;&nbsp;&nbsp;- " + s;
                role += "</html>";
                rolesL.setText(role);
            }
        });
        PMS.rPanel.add(scrollPane);
        PMS.rPanel.add(panel);
        PMS.frame.setVisible(true);
    }
}