package dev.xn__z7x.polychrome.fabric.datagen;

import dev.xn__z7x.polychrome.Polychrome;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import dev.xn__z7x.polychrome.block.ModBlocks;
import dev.xn__z7x.polychrome.item.ModItems;
import net.minecraft.data.models.model.TextureSlot;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider{
  public static final ModelTemplate PERSIAN_VOLUTE = block("persian_volute");

  // Helpers from https://docs.fabricmc.net/develop/data-generation/block-models
  private static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
    return new ModelTemplate(Optional.of(Polychrome.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
  }
  private static ModelTemplate block(String parent, String variant, TextureSlot... requiredTextureKeys) {
    return new ModelTemplate(Optional.of(Polychrome.id("block/" + parent)), Optional.of(variant), requiredTextureKeys);
  }

  public ModModelProvider(FabricDataOutput output){
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockModelGenerators generator){
    generator.createGenericCube(ModBlocks.persianVolute.asBlock());
  }

  @Override
  public void generateItemModels(ItemModelGenerators generator){
    generator.generateFlatItem(ModItems.yourItem.asItem(), ModelTemplates.FLAT_ITEM);
  }

}
