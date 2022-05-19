import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// IMP: This is main Topology_API Interface that must be used
public class TopologyAPI {
    private String id;
    private static ArrayList<Topology> topologyMem;

    // Read JSON file, create a new topology and add it to topologyMem
    public void readJSON(String filename) throws IOException, ParseException {
        JSONParser json = new JSONParser();
        FileReader readFile = new FileReader(filename);
        Object obj = json.parse(readFile);
        JSONObject jsonObject = (JSONObject) obj;
        Topology top = new Topology(jsonObject);
        topologyMem.add(top);
    }

    // Take certain topology id and write json file of it
    public void writeJSON(String id) throws IOException {
        for(int i = 0; i < topologyMem.size(); i++){
            if(topologyMem.get(i).getId() == id) {
                JSONObject Obj = topologyMem.get(i).getObj();
                FileWriter file = new FileWriter(topologyMem.get(i).getId()+".json");
                file.write(Obj.toJSONString());
                file.close();
                System.out.println("Done");
                break;
            }
        }
    }

    // delete certain topology from topologyMem
    public void deleteTopology(String TopologyId){
        for(int i = 0; i < topologyMem.size(); i++){
            if(topologyMem.get(i).getId() == TopologyId){
                topologyMem.remove(i);
                break;
            }
        }
    }

    // returns list of Topologies currently stored in memory
    public ArrayList<Topology> queryTopologies(){
        return topologyMem;
    }

    // returns a list of components in a certain Topology from memory
    public ArrayList<Component> queryDevices(String TopologyId){
        for(int i = 0; i < topologyMem.size(); i++){
            if(topologyMem.get(i).getId() == TopologyId){
                return topologyMem.get(i).getComponents();
            }
        }
        return null;
    }

    // return a list of components in certain Topology that is connected to certain netlistNode
    public ArrayList<Component> queryDevicesWithNetlistNode(String TopologyId, String NodeID){
        for(int i = 0; i < topologyMem.size(); i++){
            if(topologyMem.get(i).getId() == TopologyId){
                return topologyMem.get(i).getNetlist(NodeID);
            }
        }
        return null;
    }
}
