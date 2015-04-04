package me.johngreen.clestia;

import com.sk.cache.DataSource;
import com.sk.cache.fs.CacheSystem;
import com.sk.cache.wrappers.QuestDefinition;
import com.sk.cache.wrappers.loaders.ItemDefinitionLoader;
import com.sk.cache.wrappers.loaders.QuestDefinitionLoader;
import me.johngreen.clestia.Quests.FTP.AShadowOverAshdale.AShadowOverAshdale;
import me.johngreen.clestia.Quests.FTP.CooksAssistant.CooksAssistant;
import me.johngreen.clestia.Quests.FTP.DeathPlateau.DeathPlateau;
import me.johngreen.clestia.Quests.FTP.DemonSlayer.DemonSlayer;
import me.johngreen.clestia.Quests.FTP.DragonSlayer.DragonSlayer;
import me.johngreen.clestia.Quests.FTP.DruidicRitual.DruidicRitual;
import me.johngreen.clestia.Quests.FTP.ErnestTheChicken.ErnestTheChicken;
import me.johngreen.clestia.Quests.FTP.GoblinDiplomacy.GoblinDiplomacy;
import me.johngreen.clestia.Quests.FTP.GunnarsGround.GunnarsGround;
import me.johngreen.clestia.Quests.FTP.ImpCatcher.ImpCatcher;
import me.johngreen.clestia.Quests.FTP.MythsOfTheWhiteLands.MythsOfTheWhiteLands;
import me.johngreen.clestia.Quests.FTP.PiratesTreasure.PiratesTreasure;
import me.johngreen.clestia.Quests.FTP.RuneMysteries.RuneMysteries;
import me.johngreen.clestia.Quests.FTP.ShieldOfArrav.ShieldOfArrav;
import me.johngreen.clestia.Quests.FTP.StolenHearts.StolenHearts;
import me.johngreen.clestia.Quests.FTP.SweptAway.SweptAway;
import me.johngreen.clestia.Quests.FTP.TheBloodPact.TheBloodPact;
import me.johngreen.clestia.Quests.FTP.TheDeathofChivalry.TheDeathofChivalry;
import me.johngreen.clestia.Quests.FTP.TheKnightsSword.TheKnightsSword;
import me.johngreen.clestia.Quests.FTP.TheRestlessGhost.TheRestlessGhost;
import me.johngreen.clestia.Quests.FTP.VampyreSlayer.VampyreSlayer;
import me.johngreen.clestia.Quests.FTP.WhatsMineIsYours.WhatsMineIsYours;
import me.johngreen.clestia.Quests.FTP.WolfWhistle.WolfWhistle;
import me.johngreen.clestia.Quests.QuestManager;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by johngreen on 29/03/2015.
 * Credit to Strikeskids for cache reader
 */
@Script.Manifest(name="Celestial Quester", description="Dynamic quest bot!", properties = "client=6")
public class Clestia extends PollingScript<ClientContext> implements PaintListener{

    public static QuestManager manager;
    public MainFrame frame;
    private Math math;

    @Override
    public void start() {
        this.math = new Math();
        this.manager = new QuestManager();
        registerQuests();
        this.frame = new MainFrame(this);
    }

    @Override
    public void poll(){
        if(manager.isReady()){
            if(manager.getSelectedQuest().activate()){
                manager.getSelectedQuest().execute();
            }
        }
    }

    @Override
    public void repaint(Graphics g) {
        if(manager!=null&&manager.isReady()){
            manager.getSelectedQuest().paint(g);
        }
    }

    private void registerQuests(){
        //ftp
        manager.register(new AShadowOverAshdale(ctx,this));
        manager.register(new CooksAssistant(ctx,this));
        manager.register(new DeathPlateau(ctx,this));
        manager.register(new DemonSlayer(ctx,this));
        manager.register(new DragonSlayer(ctx,this));
        manager.register(new DruidicRitual(ctx,this));
        manager.register(new ErnestTheChicken(ctx,this));
        manager.register(new GoblinDiplomacy(ctx,this));
        manager.register(new GunnarsGround(ctx,this));
        manager.register(new ImpCatcher(ctx,this));
        manager.register(new MythsOfTheWhiteLands(ctx,this));
        manager.register(new PiratesTreasure(ctx,this));
        manager.register(new RuneMysteries(ctx,this));
        manager.register(new ShieldOfArrav(ctx,this));
        manager.register(new StolenHearts(ctx,this));
        manager.register(new SweptAway(ctx,this));
        manager.register(new TheBloodPact(ctx,this));
        manager.register(new TheDeathofChivalry(ctx,this));
        manager.register(new TheKnightsSword(ctx,this));
        manager.register(new TheRestlessGhost(ctx,this));
        manager.register(new VampyreSlayer(ctx,this));
        manager.register(new WhatsMineIsYours(ctx,this));
        manager.register(new WolfWhistle(ctx,this));

    }

    public Math getMath(){
        return math;
    }
}
