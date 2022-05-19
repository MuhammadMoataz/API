import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Topology {
    // Store topologyId, list of topology components and JSONObject to return it back to API when needed
    private String id;
    private ArrayList<Component> components;
    private JSONObject obj;

    public Topology(JSONObject json) {
        this.obj = json;
        this.id = json.get("id").toString(); // Get id value
        JSONArray arr = (JSONArray)json.get("components"); // Get Components list
        // iterating through components list and make the appropriate component object, and add them to components list
        for(int i = 0; i < arr.size(); i++){
            JSONObject comp = (JSONObject) arr.get(i);
            if(comp.get("type") == "nmos" || comp.get("type") == "nmos"){
                Transistors trans = new Transistors((JSONObject)arr.get(i));
                components.add(trans);
            }
            else{
                PassiveComponents elem = new PassiveComponents((JSONObject)arr.get(i));
                components.add(elem);
            }
        }
    }

    // Return list of Components connected to certain netlist node
    public ArrayList<Component> getNetlist(String netlistId){
        ArrayList<Component> list = null;
        for(int i = 0; i < components.size(); i++){
            if(components.get(i).isConnected(netlistId)) list.add(components.get(i));
        }
        return list;
    }

    // Getters
    public String getId() { return id; }

    public JSONObject getObj() {
        return obj;
    }

    // Returns List of components in topology
    public ArrayList<Component> getComponents() {
        ArrayList<Component> compList = null;
        for(int i = 0; i < components.size(); i++){
            compList.add(components.get(i));
        }
        return compList;
    }
}
