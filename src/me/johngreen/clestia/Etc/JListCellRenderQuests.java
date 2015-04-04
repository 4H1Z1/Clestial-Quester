package me.johngreen.clestia.Etc;

import me.johngreen.clestia.Clestia;
import me.johngreen.clestia.Quests.QuestBase;

import javax.swing.*;
import java.awt.*;

/**
 * Created by johngreen on 31/03/2015.
 */
public class JListCellRenderQuests  extends JLabel implements ListCellRenderer {

    public JListCellRenderQuests() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Assumes the stuff in the list has a pretty toString

        QuestBase base = Clestia.manager.getQuestByName(value.toString().replace("(Mem)","").replace("(FTP)",""));

        setText(value.toString());
        if(base.hasStarted()){
            if(base.isCompleated()){
                setForeground(Color.green);
            }else{
                setForeground(Color.orange);
            }
        }else{
            setForeground(Color.red);
        }

        if(isSelected) {
            setBackground(Color.lightGray);
        }else{
            setBackground(Color.white);
        }
        return this;
    }
}
