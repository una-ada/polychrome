package dev.xn__z7x.polychrome.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import dev.xn__z7x.polychrome.block.ModBlocks;

import java.util.concurrent.CompletableFuture;


public class ModBlockLootTableProvider extends FabricBlockLootTableProvider{
  protected ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> provider){
    super(dataOutput, provider);
  }

  @Override
  public void generate(){
    for(var blocks : ModBlocks.blocks){
      blocks.forEachBlock(this::dropSelf);
    }
    ModBlocks.easyBlocks.forEach((blocks) -> {
      blocks.forEach((mat, block) -> { dropSelf(block.asBlock()); });
    });
  }
}
