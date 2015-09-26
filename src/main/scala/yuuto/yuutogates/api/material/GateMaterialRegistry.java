package yuuto.yuutogates.api.material;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuuto on 9/25/2015.
 */
public final class GateMaterialRegistry {
    private GateMaterialRegistry(){}

    public static final GateMaterial defaultMaterial=new GateMaterial("redstone", 2, 1);
    static Map<String, GateMaterial> materialMap = new HashMap<>();
    static Map<GateMaterial, String> materialIDMap = new HashMap<>();

    static{
        registerGateMaterial(defaultMaterial);
    }

    public static GateMaterial registerGateMaterial(GateMaterial material){
        if(containsMaterial(material.getID()))
            removeGateMaterial(material.getID());
        materialMap.put(material.getID(), material);
        materialIDMap.put(material, material.getID());
        return materialMap.get(material.getID());
    }
    public static void removeGateMaterial(String id){
        GateMaterial material = materialMap.get(id);
        materialMap.remove(id);
        materialIDMap.remove(material);
    }
    public static GateMaterial getGateMaterial(String id){
        return materialMap.get(id);
    }
    public static String getID(GateMaterial material){
        return materialIDMap.get(material);
    }

    public static boolean containsMaterial(GateMaterial material){
        return materialIDMap.containsKey(material);
    }
    public static boolean containsMaterial(String id){
        return materialMap.containsKey(id);
    }
}
