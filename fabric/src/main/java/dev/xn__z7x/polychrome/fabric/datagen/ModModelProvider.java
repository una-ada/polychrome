package dev.xn__z7x.polychrome.fabric.datagen;

import dev.xn__z7x.polychrome.block.PolychromeBlocks;
import dev.xn__z7x.polychrome.item.PolychromeItems;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

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
        generator.blockStateOutput.accept(blocks.stateBuilder.apply(block, models));
      });
    }
  }

  @Override
  public void generateItemModels(ItemModelGenerators generator){
    generator.generateFlatItem(PolychromeItems.stateOfDisplacement.asItem(), ModelTemplates.FLAT_ITEM);
  }
}
