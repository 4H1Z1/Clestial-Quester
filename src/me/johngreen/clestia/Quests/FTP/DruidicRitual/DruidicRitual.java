package me.johngreen.clestia.Quests.FTP.DruidicRitual;

import me.johngreen.clestia.Clestia;
import me.johngreen.clestia.Item;
import me.johngreen.clestia.Quests.QuestBase;
import me.johngreen.clestia.Values.Quests;
import org.powerbot.script.rt6.ClientContext;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by johngreen on 31/03/2015.
 */
public class DruidicRitual extends QuestBase {

    public DruidicRitual(ClientContext ctx,Clestia clestia){
        super(111, Quests.DRUIDIC_RITUAL,"Druidic Ritual",ctx,clestia);
    }

    @Override
    public ArrayList<Item> getRequiredItems() {
        return null;
    }

    @Override
    public void addToPannel(JPanel panel) {

    }

    @Override
    public boolean shouldEnableStartButton() {
        return false;
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {

    }
}
