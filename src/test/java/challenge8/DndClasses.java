package challenge8;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DndClasses {
    public int count;
    public ArrayList<DndClassData> results;
}
