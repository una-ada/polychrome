package dev.xn__z7x.polychrome.fabric.datagen;

import dev.xn__z7x.polychrome.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.TextureMapping;

import java.util.Map;

public class ModModelProvider extends FabricModelProvider{
  public ModModelProvider(FabricDataOutput output){
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockModelGenerators generator){
    for(var blocks : ModBlocks.blocks){
      blocks.forEachBlock((material, block) -> {
        TextureMapping mapping = blocks.mappingFunction.apply(material);

        switch(blocks.stateBuilder){
          case "horizontal" -> {
            blocks.models.get("base").create(block, mapping, generator.modelOutput);
            generator.createNonTemplateHorizontalBlock(block);
          }
          case "connector_s" -> {
            var corner = blocks.models.get("corner").create(block, mapping, generator.modelOutput);
            generator.blockStateOutput.accept(ModBlocks.STATE_CONNECTING.apply(block, Map.of(
              "straight", blocks.models.get("straight").create(block, mapping, generator.modelOutput),
              "inner", corner,
              "outer", corner
            )));
          }
          case "connector" -> {
            generator.blockStateOutput.accept(ModBlocks.STATE_CONNECTING.apply(block, Map.of(
              "straight", blocks.models.get("straight").create(block, mapping, generator.modelOutput),
              "inner", blocks.models.get("inner").create(block, mapping, generator.modelOutput),
              "outer", blocks.models.get("outer").create(block, mapping, generator.modelOutput)
            )));
          }
          default -> {
            blocks.models.get("base").create(block, mapping, generator.modelOutput);
            generator.createNonTemplateModelBlock(block);
          }
        }
      });
    }
  }

  @Override
  public void generateItemModels(ItemModelGenerators generator){

  }
}
