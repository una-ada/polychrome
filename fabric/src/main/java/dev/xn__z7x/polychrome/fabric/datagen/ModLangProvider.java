package dev.xn__z7x.polychrome.fabric.datagen;

import dev.xn__z7x.polychrome.api.Materials;
import dev.xn__z7x.polychrome.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModLangProvider extends FabricLanguageProvider {
  public ModLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup){
    super(dataOutput, "en_us", registryLookup);
  }

  @Override
  public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder builder){
    ModBlocks.persianBracketsBull.forEach((material, block) -> {
      builder.add(block.asBlock(), Materials.asString(material) + " Persian Bracket (Bull)");
    });
  }
}
