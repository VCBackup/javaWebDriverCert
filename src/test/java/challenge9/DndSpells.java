package challenge9;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DndSpells {
    public String index;
    public String name;
    public String[] desc;
    public String range;
    public String[] components;
    public Boolean ritual;
    public String duration;
    public Boolean concentration;
    public String casting_time;
    public int level;
    public ArrayList<DnDSpellAreaOfEffect> area_of_effect;
    public ArrayList<DndSpellSchool> school;
    public ArrayList<DndClassData> classes;
    public ArrayList<DndSubClassData> subclasses;
    public String url;
}
