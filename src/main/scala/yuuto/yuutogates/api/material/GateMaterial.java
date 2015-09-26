package yuuto.yuutogates.api.material;

/**
 * Created by Yuuto on 9/25/2015.
 */
public class GateMaterial {
    String matID=null;
    public int maxNodes;
    public int maxParameters;

    public GateMaterial(String matID, int maxNodes, int maxParameters){
        this.maxNodes=maxNodes;
        this.maxParameters=maxParameters;
        this.matID=matID;
    }
    public String getID(){
        return matID;
    }
}
