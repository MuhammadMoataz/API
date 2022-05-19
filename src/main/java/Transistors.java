import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Transistors extends Component{
    // Data Members
    private String drain;
    private String gate;
    private String source;
    private ArrayList<String> val;

    // Transistor Components Constructor
    Transistors(JSONObject comp){
        super(comp.get("type").toString(), comp.get("id").toString());
        // Get list of values from "m(l)" Object
        JSONObject obj = (JSONObject)comp.get("m(l)");
        val.add(obj.get("default").toString());
        val.add(obj.get("min").toString());
        val.add(obj.get("max").toString());
        // Get netlist nodes from "netlist" Object
        obj = (JSONObject)comp.get("netlist");
        this.drain = obj.get("drain").toString();
        this.gate = obj.get("gate").toString();
        this.source = obj.get("source").toString();
    }

    // Getters

    public String getDrain() { return drain; }

    public String getGate() { return gate; }

    public String getSource(){ return source; }

    // If the netlistNode is connected to any of component terminals, return true;
    @Override
    public boolean isConnected(String netlistID) {
        if(drain == netlistID || source == netlistID || gate == netlistID) return true;
        return false;
    }

}
