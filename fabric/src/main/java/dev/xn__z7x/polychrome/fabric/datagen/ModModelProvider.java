package dev.xn__z7x.polychrome.fabric.datagen;

import dev.xn__z7x.polychrome.Polychrome;
import dev.xn__z7x.polychrome.api.Materials;
import dev.xn__z7x.polychrome.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider{
  public static final ModelTemplate
    COLUMN = block("column", TextureSlot.SIDE, TextureSlot.END),
    PERSIAN_VOLUTE = block("persian_volute", TextureSlot.SIDE, TextureSlot.END, TextureSlot.LAYER0),
    PERSIAN_BRACKET_BULL = block("persian_bracket_bull", TextureSlot.SIDE),
    PERSIAN_CAPITAL = block("persian_capital", TextureSlot.SIDE);

  // Helpers from https://docs.fabricmc.net/develop/data-generation/block-models
  public static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
    return new ModelTemplate(Optional.of(Polychrome.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
  }
  public static ModelTemplate block(String parent, String variant, TextureSlot... requiredTextureKeys) {
    return new ModelTemplate(Optional.of(Polychrome.id("block/" + parent)), Optional.of(variant), requiredTextureKeys);
  }
  // Because new Material(Polychrome.id(...)) is too modern apparently
  public static ResourceLocation texture(String name){
    return ResourceLocation.fromNamespaceAndPath(Polychrome.MOD_ID, "block/" + name);
  }

  public ModModelProvider(FabricDataOutput output){
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockModelGenerators generator){
    ModBlocks.flutedColumns.forEach((material, block) -> {
      TextureMapping mapping = new TextureMapping()
        .put(TextureSlot.SIDE, texture(material + "_column_fluting"))
        .put(TextureSlot.END, texture(material + "_column_top"));
      COLUMN.create(block.asBlock(), mapping, generator.modelOutput);
      generator.createNonTemplateModelBlock(block.asBlock());
    });
    ModBlocks.persianBracketsBull.forEach((material, block) -> {
      TextureMapping mapping = new TextureMapping().put(TextureSlot.SIDE, texture(material + "_persian_bracket_bull"));
      PERSIAN_BRACKET_BULL.create(block.asBlock(), mapping, generator.modelOutput);
      generator.createNonTemplateHorizontalBlock(block.asBlock());
    });
    ModBlocks.persianVolutes.forEach((material, block) -> {
      TextureMapping mapping = new TextureMapping()
        .put(TextureSlot.SIDE,   texture(material + "_column_fluting"))
        .put(TextureSlot.END,    texture(material + "_column_top"))
        .put(TextureSlot.LAYER0, texture(material + "_volute"));
      PERSIAN_VOLUTE.create(block.asBlock(), mapping, generator.modelOutput);
      generator.createNonTemplateModelBlock(block.asBlock());
    });
    ModBlocks.persianCapitals.forEach((material, block) -> {
      TextureMapping mapping = new TextureMapping().put(TextureSlot.SIDE, texture(material + "_persian_capital"));
      PERSIAN_CAPITAL.create(block.asBlock(), mapping, generator.modelOutput);
      generator.createNonTemplateModelBlock(block.asBlock());
    });
  }

  @Override
  public void generateItemModels(ItemModelGenerators generator){

  }
}
