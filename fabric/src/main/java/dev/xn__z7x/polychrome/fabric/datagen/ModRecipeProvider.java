package dev.xn__z7x.polychrome.fabric.datagen;

import dev.xn__z7x.polychrome.util.Materials;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import dev.xn__z7x.polychrome.Polychrome;
import dev.xn__z7x.polychrome.block.PolychromeBlocks;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class ModRecipeProvider extends FabricRecipeProvider{
  private void stonecutting(String material, DeferredBlock block, RecipeOutput exporter){
    SingleItemRecipeBuilder.stonecutting(
        Ingredient.of(Materials.asItem(material)),
        RecipeCategory.DECORATIONS, block.asItem(),
        1
      )
      .unlockedBy("has_" + material, has(Materials.asItem(material)))
      .save(exporter, block.getRegisteredName() + "_from_" + material);
  }

  public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> provider){
    super(output, provider);
  }

  @Override
  public void buildRecipes(RecipeOutput exporter){
    for(var blocks : PolychromeBlocks.blocks){
      blocks.forEach((material, block) -> stonecutting(material, block, exporter));
    }
  }

  @Override
  public String getName(){
    return Polychrome.MOD_ID;
  }
}
