package yuuto.yuutogates.miltipart

import codechicken.lib.data.MCDataInput
import codechicken.multipart.MultiPartRegistry.IPartFactory2
import codechicken.multipart.{MultipartGenerator, MultiPartRegistry, TMultiPart}
import net.minecraft.nbt.NBTTagCompound
import yuuto.yuutogates.api.gates.GateHelper

/**
 * Created by Yuuto on 9/26/2015.
 */
object MultiPartFactory extends IPartFactory2{

  override def createPart(name:String, nbt:NBTTagCompound):TMultiPart={
    name match {
      case GateHelper.PART_ID_GATE_AND=>new PartGateAND();
      case GateHelper.PART_ID_GATE_OR=>new PartGateOR();
      case GateHelper.PART_ID_GATE_XOR=>new PartGateXOR();
      case s=>null;
    }
  }

  override def createPart(name:String, packet:MCDataInput):TMultiPart={
    name match {
      case GateHelper.PART_ID_GATE_AND=>new PartGateAND();
      case GateHelper.PART_ID_GATE_OR=>new PartGateOR();
      case GateHelper.PART_ID_GATE_XOR=>new PartGateXOR();
      case s=>null;
    }
  }

  def registerMultiParts(){
    MultiPartRegistry.registerParts(this, GateHelper.PART_ID_GATE_AND, GateHelper.PART_ID_GATE_OR, GateHelper.PART_ID_GATE_XOR);
    MultiPartRegistry.registerConverter(MultiPartConverter);
  }
}
