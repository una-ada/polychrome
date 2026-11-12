package dev.xn__z7x.polychrome.fabric.datagen;

import dev.xn__z7x.polychrome.block.PolychromeBlocks;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

import static dev.xn__z7x.polychrome.util.BlockStateGenerators.CONNECTING;
import static dev.xn__z7x.polychrome.util.BlockStateGenerators.CONNECTING_S;

public class ModModelProvider extends FabricModelProvider{
  public ModModelProvider(FabricDataOutput output){
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockModelGenerators generator){
    for(var blocks : PolychromeBlocks.blocks){
      blocks.forEachBlock((material, block) -> {
        TextureMapping mapping = blocks.getTextureMap(material);

        Map<String, ResourceLocation> models = new HashMap<>();
        blocks.models.forEach((state, model) -> models.put(state, model.create(block, mapping, generator.modelOutput)));

        switch(blocks.stateBuilder){
          case "horizontal" -> generator.createNonTemplateHorizontalBlock(block);
          case "connector_s" -> generator.blockStateOutput.accept(CONNECTING_S.apply(block, models));
          case "connector" -> generator.blockStateOutput.accept(CONNECTING.apply(block, models));
          default -> generator.createNonTemplateModelBlock(block);
        }
      });
    }
  }

  @Override
  public void generateItemModels(ItemModelGenerators generator){

  }
}
