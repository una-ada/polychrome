package dev.xn__z7x.polychrome.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import dev.xn__z7x.polychrome.block.ModBlocks;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider{
  protected ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> provider){
    super(dataOutput, provider);
  }

  @Override
  public void generate(){
    ModBlocks.persianBracketsBull.forEach((mat, block) -> { dropSelf(block.asBlock()); });
    ModBlocks.persianVolutes.forEach((mat, block) -> { dropSelf(block.asBlock()); });
  }
}
