package challenge9;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DndSpellSchool {
    public String index;
    public String name;
    public String url;
}
