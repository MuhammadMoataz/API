import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

// Class Defining the Passive components exists in Topology
public class PassiveComponents extends Component{
    private String t1; // Store the netlist node of t1
    private String t2; // Store the netlist node of t2
    private ArrayList<String> val; // Store list of values [default, min, max]

    public PassiveComponents(JSONObject comp) {
        super(comp.get("type").toString(), comp.get("id").toString());
        JSONObject obj;
        // Get values object according to the type of component
        if(comp.get("type").toString() == "resistor") obj = (JSONObject)comp.get("resistance");
        else if(comp.get("type").toString() == "capacitor") obj = (JSONObject)comp.get("capacitance");
        else obj = (JSONObject)comp.get("inductance");
        val.add(obj.get("default").toString());
        val.add(obj.get("min").toString());
        val.add(obj.get("max").toString());
        // Get netlist nodes from "netlist" Object
        obj = (JSONObject)comp.get("netlist");
        this.t1 = obj.get("t1").toString();
        this.t2 = obj.get("t2").toString();
    }

    // Getters
    public String getT1() {
        return t1;
    }

    public String getT2() {
        return t2;
    }

    // If the netlistNode is connected to any of component terminals, return true;
    @Override
    public boolean isConnected(String netlistID) {
        if(t1 == netlistID || t2 == netlistID) return true;
        return false;
    }
}
