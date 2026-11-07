package dev.xn__z7x.polychrome.fabric.datagen;

import dev.xn__z7x.polychrome.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.TextureMapping;

public class ModModelProvider extends FabricModelProvider{
  public ModModelProvider(FabricDataOutput output){
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockModelGenerators generator){
    for(var blocks : ModBlocks.blocks){
      blocks.forEachBlock((material, block) -> {
        TextureMapping mapping = blocks.mappingFunction.apply(material);
        blocks.model.create(block, mapping, generator.modelOutput);
        switch(blocks.stateBuilder){
          case "horizontal" -> generator.createNonTemplateHorizontalBlock(block);
          // case "stair"      -> generator.blockStateOutput.accept(BlockModelGenerators.createStairs(block, inner, straight, outer));
          default           -> generator.createNonTemplateModelBlock(block);
        }
      });
    }
  }

  @Override
  public void generateItemModels(ItemModelGenerators generator){

  }
}
