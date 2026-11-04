package dev.xn__z7x.polychrome.fabric.datagen;

import dev.xn__z7x.polychrome.Polychrome;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider{
  public ModModelProvider(FabricDataOutput output){
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockModelGenerators generator){

  }

  @Override
  public void generateItemModels(ItemModelGenerators generator){

  }

  public static class CustomModel {
    // Helpers from https://docs.fabricmc.net/develop/data-generation/block-models
    public static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
      return new ModelTemplate(Optional.of(Polychrome.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    public static ModelTemplate block(String parent, String variant, TextureSlot... requiredTextureKeys) {
      return new ModelTemplate(Optional.of(Polychrome.id("block/" + parent)), Optional.of(variant), requiredTextureKeys);
    }
    public static ResourceLocation texture(String name){
      return ResourceLocation.fromNamespaceAndPath("polychrome", "block/" + name);
    }
  }
  public static class PersianVolute extends CustomModel {
    public static final ModelTemplate PERSIAN_VOLUTE = block("persian_volute", TextureSlot.SIDE, TextureSlot.END, TextureSlot.LAYER0);
    public static TextureMapping mapMaterial(String material) {
      return new TextureMapping()
        .put(TextureSlot.SIDE,   texture(material + "_fluting"))
        .put(TextureSlot.END,    texture(material + "_top"))
        .put(TextureSlot.LAYER0, texture(material + "_volute"));
    }
  }
}
