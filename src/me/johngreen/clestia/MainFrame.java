package me.johngreen.clestia;

import me.johngreen.clestia.Etc.JListCellRenderItems;
import me.johngreen.clestia.Etc.JListCellRenderQuests;
import me.johngreen.clestia.Quests.QuestBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame{
    private Clestia clestia;
    private JFrame frame;
    private JPanel pnl1,pnl2,pnl3,pnl4,pnl5,pnl6;
    private JScrollPane scriptScrollPane,itemsScrollPane;
    private JList scripts,items;
    public JButton startButton;
    private QuestBase selectedQuest;

    public MainFrame(Clestia clestia){
        this.frame = new JFrame("Clestia Quest Selector");
        this.clestia = clestia;
        setupFrame();
    }
    private void setupFrame(){
        frame.setSize(800, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(1, 4));
        pnl1 = new JPanel(new GridLayout());
        pnl2 = new JPanel(new GridLayout(8,1));
        pnl3 = new JPanel(new FlowLayout());
        pnl4 = new JPanel(new GridLayout(4,1));
        pnl5 = new JPanel(new GridLayout());
        pnl6 = new JPanel(new GridLayout());
        pnl1.setPreferredSize(new Dimension(190, 260));
        pnl2.setPreferredSize(new Dimension(190, 260));
        pnl3.setPreferredSize(new Dimension(190, 260));
        pnl4.setPreferredSize(new Dimension(190, 90));
        pnl5.setPreferredSize(new Dimension(190, 170));
        pnl1.setBorder(BorderFactory.createTitledBorder("Select Quest"));
        pnl2.setBorder(BorderFactory.createTitledBorder("Options"));
        pnl4.setBorder(BorderFactory.createTitledBorder("Quest Requirments"));
        pnl5.setBorder(BorderFactory.createTitledBorder("Required Quests"));
        pnl6.setBorder(BorderFactory.createTitledBorder("Required Items"));
        frame.add(pnl1);
        frame.add(pnl2);
        pnl3.add(pnl4);
        pnl3.add(pnl5);
        frame.add(pnl3);
        frame.add(pnl6);

        DefaultListModel scriptList = new DefaultListModel();
        for (QuestBase base : clestia.manager.getQuests()) {
            if(base.isMembersQuest()){
                scriptList.addElement("(Mem)"+base.getName());
            }else{
                scriptList.addElement("(FTP)"+base.getName());
            }
        }
        scripts = new JList(scriptList);
        scripts.setCellRenderer(new JListCellRenderQuests());

        scripts.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                String item = (String) list.getSelectedValue();
                pnl2.removeAll();
                pnl2.revalidate();
                pnl2.repaint();

                selectedQuest = clestia.manager.getQuestByName(item.replace("(Mem)","").replace("(FTP)",""));
                pnl2.add(new JLabel(selectedQuest.getName()));
                selectedQuest.addToPannel(pnl2);

                if (selectedQuest.hasStarted()) {
                    if (selectedQuest.isCompleated()) {
                        startButton.setText("Compleated!");
                        startButton.setEnabled(false);
                    } else {
                        startButton.setText("Continue");
                        startButton.setEnabled(true);
                    }
                } else {
                    startButton.setEnabled(selectedQuest.shouldEnableStartButton());
                    startButton.setText("Start Quest");
                }

                pnl2.add(startButton);

                pnl4.removeAll();
                pnl4.revalidate();
                pnl4.repaint();

                pnl5.removeAll();
                pnl5.revalidate();
                pnl5.repaint();

                pnl6.removeAll();
                pnl6.revalidate();
                pnl6.repaint();

                JLabel cbLevel = new JLabel("Combat Level:");
                JLabel questPoints = new JLabel("Quest Points:" + selectedQuest.getRequiredQuestPoints());
                JLabel members = new JLabel("Members:" + selectedQuest.isMembersQuest());
                pnl4.add(cbLevel);
                pnl4.add(questPoints);
                pnl4.add(members);

                JTextArea quests = new JTextArea();
                String questText = "";
                if (selectedQuest.getRequiredQuests() != null && selectedQuest.getRequiredQuests().size() > 0) {
                    for (String name : selectedQuest.getRequiredQuests()) {
                        questText += name + "\n";
                    }
                } else {
                    questText += "None";
                }
                quests.setEnabled(false);
                quests.setText(questText);
                pnl5.add(quests);


                DefaultListModel itemsList = new DefaultListModel();
                if(Clestia.manager.getQuestByName(selectedQuest.getName()).getRequiredItems()!=null&&Clestia.manager.getQuestByName(selectedQuest.getName()).getRequiredItems().size()>0){
                    for (Item s:Clestia.manager.getQuestByName(selectedQuest.getName()).getRequiredItems()) {
                        itemsList.addElement(s.getItem().getName());
                    }
                }else{
                    itemsList.addElement("None");
                }

                items = new JList(itemsList);
                items.setCellRenderer(new JListCellRenderItems());
            }
        });


        DefaultListModel itemsList = new DefaultListModel();
        items = new JList(itemsList);
        items.setCellRenderer(new JListCellRenderItems());


        scriptScrollPane = new JScrollPane();
        scriptScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scriptScrollPane.setViewportView(scripts);
        pnl1.add(scriptScrollPane);


        itemsScrollPane = new JScrollPane();
        itemsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        itemsScrollPane.setViewportView(items);
        pnl6.add(itemsScrollPane);


        startButton = new JButton();
        startButton.setText("Start");
        startButton.setPreferredSize(new Dimension(80, 30));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(selectedQuest!=null){
                    clestia.manager.setSelectedQuest(selectedQuest);
                    frame.dispose();
                }
            }
        });

        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // handle closing the window
                frame.setVisible(false);
                frame.dispose();
                if(Clestia.manager.getSelectedQuest()==null){
                    System.out.println("No quest selected so closeing");
                    clestia.stop();
                    clestia.suspend();
                }
            }
        });
    }
    public boolean isOpen(){
        return frame.isValid();
    }

}
