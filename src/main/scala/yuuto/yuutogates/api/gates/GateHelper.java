package yuuto.yuutogates.api.gates;

import net.minecraft.item.ItemStack;
import yuuto.yuutogates.api.material.GateMaterial;
import yuuto.yuutogates.api.material.GateMaterialRegistry;

/**
 * Created by Yuuto on 9/25/2015.
 */
public class GateHelper {

    public static final String TAG_GATE_MATERIAL="GateMaterial";
    public static final String TAG_GATE_DATA="GateData";
    public static final String PART_ID_GATE_AND="part.yuutogates:gateAND";
    public static final String PART_ID_GATE_OR="part.yuutogates:gateOR";
    public static final String PART_ID_GATE_XOR="part.yuutogates:gateXOR";
    public static final String PLUG_ID_GATE_AND="plug.yuutogates:gateAND";
    public static final String PLUG_ID_GATE_OR="plug.yuutogates:gateOR";
    public static final String PLUG_ID_GATE_XOR="plug.yuutogates:gateXOR";
    public static final String TILE_ID_GATE_AND="tile.yuutogates:gateAND";
    public static final String TILE_ID_GATE_OR="tile.yuutogates:gateOR";
    public static final String TILE_ID_GATE_XOR="tile.yuutogates:gateXOR";


    public static GateMaterial getGateMaterial(ItemStack stack){
        if(stack == null || !stack.hasTagCompound() || !stack.getTagCompound().hasKey(TAG_GATE_MATERIAL))
            return GateMaterialRegistry.defaultMaterial;
        GateMaterial material = GateMaterialRegistry.getGateMaterial(stack.getTagCompound().getString(TAG_GATE_MATERIAL));
        return material == null ? GateMaterialRegistry.defaultMaterial : material;
    }
}
