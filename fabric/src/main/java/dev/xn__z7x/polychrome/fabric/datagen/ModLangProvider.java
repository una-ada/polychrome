package dev.xn__z7x.polychrome.fabric.datagen;

import dev.xn__z7x.polychrome.api.Materials;
import dev.xn__z7x.polychrome.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class ModLangProvider extends FabricLanguageProvider {
  public ModLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup){
    super(dataOutput, "en_us", registryLookup);
  }

  @Override
  public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder builder){
    try {
      Path existingFile = dataOutput.getModContainer().findPath("assets/polychrome/lang/en_us.existing.json").orElseThrow();
      builder.add(existingFile);
    } catch(IOException e) {
      throw new RuntimeException("Failed to add existing language file", e);
    }
    ModBlocks.flutedColumns.forEach((material, block) -> {
      builder.add(block.asBlock(), Materials.asString(material) + " Fluted Column");
    });
    ModBlocks.persianBracketsBull.forEach((material, block) -> {
      builder.add(block.asBlock(), Materials.asString(material) + " Persian Bracket (Bull)");
    });
    ModBlocks.persianVolutes.forEach((material, block) -> {
      builder.add(block.asBlock(), Materials.asString(material) + " Persian Capital Volute");
    });
    ModBlocks.persianCapitals.forEach((material, block) -> {
      builder.add(block.asBlock(), Materials.asString(material) + " Persian Capital Bell");
    });
  }
}
