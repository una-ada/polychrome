package dev.xn__z7x.polychrome.block;

import dev.xn__z7x.polychrome.Polychrome;
import dev.xn__z7x.polychrome.api.Materials;
import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.blay09.mods.balm.world.level.block.BalmBlockRegistration;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static dev.xn__z7x.polychrome.api.Materials.*;

public class ModBlocks{
  public static final ModelTemplate
    COLUMN = Model.block("block/column", TextureSlot.SIDE, TextureSlot.END),
    PERSIAN_BRACKET_BULL = Model.block("block/persian_bracket_bull", TextureSlot.SIDE),
    PERSIAN_VOLUTE = Model.block("block/persian_volute", TextureSlot.SIDE, TextureSlot.END, TextureSlot.LAYER0),
    PERSIAN_CAPITAL = Model.block("block/persian_capital", TextureSlot.SIDE);

  public static Model
    flutedColumns = new Model("fluted_column", "Fluted Column", Column::new, CONCRETE, STONES)
      .withModel(COLUMN, material -> new TextureMapping()
        .put(TextureSlot.SIDE, Polychrome.id("block/" + material + "_column_fluting"))
        .put(TextureSlot.END, Polychrome.id("block/" + material + "_column_top"))),
    persianBracketsBull = new Model("persian_bracket_bull", "Persian Bracket (Bull)", PersianBracket::new, CONCRETE, STONES)
      .withModel(PERSIAN_BRACKET_BULL)
      .withState("horizontal"),
    persianVolutes = new Model("persian_volute", "Persian Capital Volute", PersianVolute::new, CONCRETE, STONES)
      .withModel(PERSIAN_VOLUTE, material -> new TextureMapping()
        .put(TextureSlot.SIDE,   Polychrome.id("block/" + material + "_column_fluting"))
        .put(TextureSlot.END,    Polychrome.id("block/" + material + "_column_top"))
        .put(TextureSlot.LAYER0, Polychrome.id("block/" + material + "_volute"))),
    persianCapitals = new Model("persian_capital", "Persian Capital Bell", Column::new, CONCRETE, STONES)
      .withModel(PERSIAN_CAPITAL);

  public static Model[] blocks = new Model[]{
    flutedColumns,
    persianBracketsBull,
    persianVolutes,
    persianCapitals
  };


  public static void initialize(BalmBlockRegistrar regis){
    for(Model model : blocks){
      model.init(regis);
    }
  }

  public static class Model {
    public static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
      return new ModelTemplate(Optional.of(Polychrome.id(parent)), Optional.empty(), requiredTextureKeys);
    }
    public static ModelTemplate block(String parent, String variant, TextureSlot... requiredTextureKeys) {
      return new ModelTemplate(Optional.of(Polychrome.id(parent)), Optional.of(variant), requiredTextureKeys);
    }

    public String name, displayName;
    public Function<Properties, Block> constructor;
    public Function<Properties, Properties> propertiesBuilder;
    public ModelTemplate model;
    public Function<String, TextureMapping> mappingFunction;
    public String stateBuilder;
    public String[] materials;
    public Map<String, DeferredBlock> deferredBlocks;

    public static Function<Properties, Block> defaultConstructor = Block::new;
    public static Function<Properties, Properties> defaultPropertiesBuilder = it -> it;
    public static ModelTemplate defaultModel = null;
    public static String defaultStateBuilder = "model";
    public static String[] defaultMaterials = NONE;
    public static Function<String, TextureMapping> defaultMappingFunction(String name){
      return material -> new TextureMapping().put(TextureSlot.SIDE, Polychrome.id("block/" + material + "_" + name));
    }

    public Model(String name, String displayName) {
      this(name, displayName, defaultMaterials);
    }

    public Model(String name, String displayName,
                 String[]... materials) {
      this(name, displayName, defaultConstructor, materials);
    }

    public Model(  String name, String displayName,
                   Function<Properties, Block> constructor,
                   String[]... materials){
      this.name = name;
      this.displayName = displayName;
      this.constructor = constructor;
      this.propertiesBuilder = defaultPropertiesBuilder;
      this.model = defaultModel;
      this.mappingFunction = defaultMappingFunction(name);
      this.stateBuilder = defaultStateBuilder;
      this.materials = Materials.list(materials);
      this.deferredBlocks = new HashMap<>();
    }

    public void init(BalmBlockRegistrar registrar) {
      for(String material: materials){
        this.put(material, registrar.register(material + "_" + name, constructor, propertiesBuilder));
      }
    }

    public Model withProperties(Function<Properties, Properties> propertiesBuilder){
      this.propertiesBuilder = propertiesBuilder;
      return this;
    }
    public Model withModel(ModelTemplate model){
      this.model = model;
      return this;
    }
    public Model withModel(ModelTemplate model, Function<String, TextureMapping> mappingFunction){
      this.model = model;
      this.mappingFunction = mappingFunction;
      return this;
    }
    public Model withState(String stateBuilder){
      this.stateBuilder = stateBuilder;
      return this;
    }

    public void forEach(Consumer<DeferredBlock> callback){
      deferredBlocks.forEach((material, block) -> {
        callback.accept(block);
      });
    }
    public void forEach(BiConsumer<String, DeferredBlock> callback){
      deferredBlocks.forEach(callback);
    }
    public void forEachBlock(Consumer<Block> callback){
      deferredBlocks.forEach((material, block) -> {
        callback.accept(block.asBlock());
      });
    }
    public void forEachBlock(BiConsumer<String, Block> callback){
      deferredBlocks.forEach((material, block) -> {
        callback.accept(material, block.asBlock());
      });
    }

    public DeferredBlock get(String material){
      return deferredBlocks.get(material);
    }
    public void put(String material, BalmBlockRegistration block){
      deferredBlocks.put(material, block.withDefaultItem().asDeferredBlock());
    }
  }
}
