package dev.xn__z7x.polychrome.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import dev.xn__z7x.polychrome.Polychrome;
import dev.xn__z7x.polychrome.block.ModBlocks;
import dev.xn__z7x.polychrome.item.ModItems;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class ModRecipeProvider extends FabricRecipeProvider{
  public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> provider){
    super(output, provider);
  }

  @Override
  public void buildRecipes(RecipeOutput exporter){
  }

  @Override
  public String getName(){
    return Polychrome.MOD_ID;
  }
}
