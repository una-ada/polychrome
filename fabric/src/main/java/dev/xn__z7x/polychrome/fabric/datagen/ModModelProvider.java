package dev.xn__z7x.polychrome.fabric.datagen;

import dev.xn__z7x.polychrome.Polychrome;
import dev.xn__z7x.polychrome.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider{

  // Helpers from https://docs.fabricmc.net/develop/data-generation/block-models
  public static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
    return new ModelTemplate(Optional.of(Polychrome.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
  }
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
          default           -> generator.createNonTemplateModelBlock(block);
        }
      });
    }
  }

  @Override
  public void generateItemModels(ItemModelGenerators generator){

  }
}
