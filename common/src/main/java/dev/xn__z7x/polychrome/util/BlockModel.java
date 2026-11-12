package dev.xn__z7x.polychrome.util;

import dev.xn__z7x.polychrome.Polychrome;
import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.blay09.mods.balm.world.level.block.BalmBlockRegistration;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlockModel{
  public static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
    return new ModelTemplate(Optional.of(Polychrome.id(parent)), Optional.empty(), requiredTextureKeys);
  }
  public static ModelTemplate block(String parent, String variant, TextureSlot... requiredTextureKeys) {
    return new ModelTemplate(Optional.of(Polychrome.id(parent)), Optional.of(variant), requiredTextureKeys);
  }

  public String name, displayName;
  public Function<BlockBehaviour.Properties, Block> constructor;
  public Function<BlockBehaviour.Properties, BlockBehaviour.Properties> propertiesBuilder;
  public Map<String, ModelTemplate> models;
  public Function<String, Map<TextureSlot, ResourceLocation>> mappingFunction;
  public String stateBuilder;
  public String[] materials;
  public Map<String, DeferredBlock> deferredBlocks;

  public static Function<BlockBehaviour.Properties, BlockBehaviour.Properties> defaultPropertiesBuilder = it -> it;
  public Map<String, ModelTemplate> defaultModel = new HashMap<>();
  public static String defaultStateBuilder = "model";
  public static Function<String, Map<TextureSlot, ResourceLocation>> defaultMappingFunction(String name){
    return material -> Map.of(TextureSlot.SIDE, Polychrome.id("block/" + material + "/" + name));
  }

  public BlockModel(String name, String displayName,
                    Function<BlockBehaviour.Properties, Block> constructor,
                    String[]... materials){
    this.name = name;
    this.displayName = displayName;
    this.constructor = constructor;
    this.propertiesBuilder = defaultPropertiesBuilder;
    this.models = defaultModel;
    this.mappingFunction = defaultMappingFunction(name);
    this.stateBuilder = defaultStateBuilder;
    this.materials = Materials.list(materials);
    this.deferredBlocks = new LinkedHashMap<>();
  }

  public void init(BalmBlockRegistrar registrar) {
    for(String material: materials){
      this.put(material, registrar.register(material + "_" + name, constructor, propertiesBuilder));
    }
  }

  public BlockModel withProperties(Function<BlockBehaviour.Properties, BlockBehaviour.Properties> propertiesBuilder){
    this.propertiesBuilder = propertiesBuilder;
    return this;
  }
  public BlockModel withModel(ModelTemplate model){
    this.models.put("base", model);
    return this;
  }
  public BlockModel withModel(ModelTemplate model, Function<String, Map<TextureSlot, ResourceLocation>> mappingFunction){
    this.models.put("base", model);
    this.mappingFunction = mappingFunction;
    return this;
  }
  public BlockModel withModel(Map<String, ModelTemplate> models, Function<String, Map<TextureSlot, ResourceLocation>> mappingFunction){
    this.models = models;
    this.mappingFunction = mappingFunction;
    return this;
  }
  public BlockModel withState(String stateBuilder){
    this.stateBuilder = stateBuilder;
    return this;
  }

  public void forEach(BiConsumer<String, DeferredBlock> callback){
    deferredBlocks.forEach(callback);
  }
  public void forEachBlock(Consumer<Block> callback){
    deferredBlocks.forEach((material, block) -> callback.accept(block.asBlock()));
  }
  public void forEachBlock(BiConsumer<String, Block> callback){
    deferredBlocks.forEach((material, block) -> callback.accept(material, block.asBlock()));
  }

  public DeferredBlock get(String material){
    return deferredBlocks.get(material);
  }
  public TextureMapping getTextureMap(String material){
    TextureMapping map = new TextureMapping();
    mappingFunction.apply(material).forEach(map::put);
    return map;
  }
  public void put(String material, BalmBlockRegistration block){
    deferredBlocks.put(material, block.withDefaultItem().asDeferredBlock());
  }
}
