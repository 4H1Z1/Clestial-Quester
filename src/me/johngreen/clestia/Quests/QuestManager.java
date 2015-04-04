package me.johngreen.clestia.Quests;

import com.sk.cache.DataSource;
import com.sk.cache.fs.CacheSystem;
import com.sk.cache.wrappers.loaders.ItemDefinitionLoader;
import com.sk.cache.wrappers.loaders.QuestDefinitionLoader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johngreen on 30/03/2015.
 */
public class QuestManager {
    private List<QuestBase> quests;
    private QuestBase selectedQuest;
    private QuestDefinitionLoader qdl;
    private ItemDefinitionLoader idl;

    public QuestManager(){
        quests = new ArrayList<QuestBase>();
        DataSource ds = null;
        try {
            ds = new DataSource(DataSource.getDefaultCacheDirectory());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CacheSystem cache = new CacheSystem(ds);
        QuestDefinitionLoader qdl = new QuestDefinitionLoader(cache);
        ItemDefinitionLoader idl = new ItemDefinitionLoader(cache);
        this.qdl=qdl;
        this.idl = idl;
    }

    public QuestDefinitionLoader getQuestDefinitionLoader(){
        return qdl;
    }

    public ItemDefinitionLoader getItemDefinitionLoader(){
        return idl;
    }

    public void register(QuestBase quest){
        quests.add(quest);
    }

    public List<QuestBase> getQuests(){
        return quests;
    }

    public boolean isReady(){
        return selectedQuest!=null;
    }

    public QuestBase getSelectedQuest(){
        return selectedQuest;
    }

    public void setSelectedQuest(QuestBase quest){
        this.selectedQuest = quest;
    }

    public QuestBase getQuestByName(String name){
        for(QuestBase base:quests){
            if(base.getName().equals(name)){
                return base;
            }
        }
        return null;
    }

}
