import org.json.simple.JSONArray;
import java.util.ArrayList;
public abstract class Component {
    private String componentType;
    private String componentId;

    // Class Constructor
    Component(String componentType, String componentId) {
        this.componentType = componentType;
        this.componentId = componentId;
    }

    // Getters
    public String getComponentType(){ return componentType; }

    public String getComponentId() { return componentId; }

    // Abstract method to check if a given netlist is connect to a certain component or not
    // to be overridden in Transistors and PassiveComponents
    public abstract boolean isConnected(String netlistID);
}
