package me.johngreen.clestia.Etc;

import me.johngreen.clestia.Clestia;
import me.johngreen.clestia.Quests.QuestBase;

import javax.swing.*;
import java.awt.*;

/**
 * Created by johngreen on 02/04/15.
 */
public class JListCellRenderItems  extends JLabel implements ListCellRenderer {

    public JListCellRenderItems() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Assumes the stuff in the list has a pretty toString

        setText(value.toString());

        if(isSelected) {
            setBackground(Color.lightGray);
        }else{
            setBackground(Color.white);
        }
        return this;
    }
}
