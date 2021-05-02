package challenge9;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DnDSpellAreaOfEffect {
    public String type;
    public int size;
}
