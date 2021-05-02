package challenge8;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DndClassData {
    public String index;
    public String name;
    public String url;
}
