package dev.xn__z7x.polychrome.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import dev.xn__z7x.polychrome.item.ModItems;
import dev.xn__z7x.polychrome.tag.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider{
  public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture){
    super(output, registriesFuture);
  }

  @Override
  protected void addTags(HolderLookup.Provider lookup){

  }
}
