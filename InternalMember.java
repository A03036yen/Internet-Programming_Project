import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class InternalMember extends Member {
    /* data member */
    
    /* constructor */
    public InternalMember(String name, LocalDate dateOfBirth, char gender, ArrayList<String> abilities, ArrayList<String> roles) {
        super(name, dateOfBirth, gender, abilities, roles);
    }
    
    public InternalMember(int id, String name, LocalDate dateOfBirth, char gender, ArrayList<String> abilities, ArrayList<String> roles) {
        super(id, name, dateOfBirth, gender, abilities, roles);
    }
    /* method */
    public void searchProject(ArrayList<Project> projectList) {
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