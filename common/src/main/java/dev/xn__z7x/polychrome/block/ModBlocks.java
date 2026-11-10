package dev.xn__z7x.polychrome.block;

import dev.xn__z7x.polychrome.Polychrome;
import dev.xn__z7x.polychrome.api.Materials;
import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.blay09.mods.balm.world.level.block.BalmBlockRegistration;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import static dev.xn__z7x.polychrome.api.Materials.*;
import static net.minecraft.core.Direction.*;
import static net.minecraft.data.models.blockstates.VariantProperties.*;
import static net.minecraft.data.models.blockstates.VariantProperties.Rotation.*;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.STAIRS_SHAPE;
import static net.minecraft.world.level.block.state.properties.StairsShape.*;

public class ModBlocks{
  public static final BiFunction<Block, Map<String, ResourceLocation>, BlockStateGenerator>
    STATE_CONNECTING = (block, models) -> MultiVariantGenerator.multiVariant(block)
      .with(PropertyDispatch.properties(HORIZONTAL_FACING, STAIRS_SHAPE)
        .select(NORTH, STRAIGHT, Variant.variant().with(MODEL, models.get("straight")).with(Y_ROT, R0))
        .select(EAST, STRAIGHT, Variant.variant().with(MODEL, models.get("straight")).with(Y_ROT, R90))
        .select(SOUTH, STRAIGHT, Variant.variant().with(MODEL, models.get("straight")).with(Y_ROT, R180))
        .select(WEST, STRAIGHT, Variant.variant().with(MODEL, models.get("straight")).with(Y_ROT, R270))
        .select(NORTH, INNER_LEFT, Variant.variant().with(MODEL, models.get("inner")).with(Y_ROT, R0))
        .select(EAST, INNER_LEFT, Variant.variant().with(MODEL, models.get("inner")).with(Y_ROT, R90))
        .select(SOUTH, INNER_LEFT, Variant.variant().with(MODEL, models.get("inner")).with(Y_ROT, R180))
        .select(WEST, INNER_LEFT, Variant.variant().with(MODEL, models.get("inner")).with(Y_ROT, R270))
        .select(NORTH, INNER_RIGHT, Variant.variant().with(MODEL, models.get("inner")).with(Y_ROT, R90))
        .select(EAST, INNER_RIGHT, Variant.variant().with(MODEL, models.get("inner")).with(Y_ROT, R180))
        .select(SOUTH, INNER_RIGHT, Variant.variant().with(MODEL, models.get("inner")).with(Y_ROT, R270))
        .select(WEST, INNER_RIGHT, Variant.variant().with(MODEL, models.get("inner")).with(Y_ROT, R0))
        .select(NORTH, OUTER_LEFT, Variant.variant().with(MODEL, models.get("outer")).with(Y_ROT, R180))
        .select(EAST, OUTER_LEFT, Variant.variant().with(MODEL, models.get("outer")).with(Y_ROT, R270))
        .select(SOUTH, OUTER_LEFT, Variant.variant().with(MODEL, models.get("outer")).with(Y_ROT, R0))
        .select(WEST, OUTER_LEFT, Variant.variant().with(MODEL, models.get("outer")).with(Y_ROT, R90))
        .select(NORTH, OUTER_RIGHT, Variant.variant().with(MODEL, models.get("outer")).with(Y_ROT, R270))
        .select(EAST, OUTER_RIGHT, Variant.variant().with(MODEL, models.get("outer")).with(Y_ROT, R0))
        .select(SOUTH, OUTER_RIGHT, Variant.variant().with(MODEL, models.get("outer")).with(Y_ROT, R90))
        .select(WEST, OUTER_RIGHT, Variant.variant().with(MODEL, models.get("outer")).with(Y_ROT, R180))
      );

  public static final ModelTemplate
    COLUMN = Model.block("block/column", TextureSlot.SIDE, TextureSlot.END),
    PILASTER = Model.block("block/pilaster", TextureSlot.SIDE, TextureSlot.END),
    PERSIAN_MERLON_DECO_STRAIGHT = Model.block("block/persian_merlon_deco_straight", TextureSlot.SIDE),
    PERSIAN_MERLON_DECO_CORNER = Model.block("block/persian_merlon_deco_corner", "_corner", TextureSlot.SIDE),
    PERSIAN_BRACKET_BULL = Model.block("block/persian_bracket_bull", TextureSlot.SIDE),
    PERSIAN_VOLUTE = Model.block("block/persian_column_volute", TextureSlot.SIDE, TextureSlot.END, TextureSlot.LAYER0),
    PERSIAN_CAPITAL = Model.block("block/persian_column_capital", TextureSlot.SIDE);

  public static Model
    columns = new Model("column", "Column", Column::new, CONCRETE, STONES, WOODS)
      .withModel(COLUMN, material -> new TextureMapping()
        .put(TextureSlot.SIDE,  Polychrome.id("block/" + material + "_column"))
        .put(TextureSlot.END,   Polychrome.id("block/" + material + "_column_top"))),
    flutedColumns = new Model("fluted_column", "Fluted Column", Column::new, CONCRETE, STONES)
      .withModel(COLUMN, material -> new TextureMapping()
        .put(TextureSlot.SIDE,  Polychrome.id("block/" + material + "_column_fluting"))
        .put(TextureSlot.END,   Polychrome.id("block/" + material + "_column_top"))),
    pilasters = new Model("pilaster", "Pilaster", Pilaster::new, CONCRETE, STONES, WOODS)
      .withModel(PILASTER, material -> new TextureMapping()
        .put(TextureSlot.SIDE,  Polychrome.id("block/" + material + "_column"))
        .put(TextureSlot.END,   Polychrome.id("block/" + material + "_column_top")))
      .withState("horizontal"),
    flutedPilasters = new Model("fluted_pilaster", "Fluted Pilaster", Pilaster::new, CONCRETE, STONES)
      .withModel(PILASTER, material -> new TextureMapping()
        .put(TextureSlot.SIDE,  Polychrome.id("block/" + material + "_column_fluting"))
        .put(TextureSlot.END,   Polychrome.id("block/" + material + "_column_top")))
      .withState("horizontal"),
    persianMerlonsDeco = new Model("persian_merlon_deco", "Decorative Persian Merlon", PersianMerlonDeco::new, CONCRETE, STONES)
      .withModel(Map.of(
          "straight", PERSIAN_MERLON_DECO_STRAIGHT,
          "corner", PERSIAN_MERLON_DECO_CORNER),
        Model.defaultMappingFunction("persian_merlon_deco"))
      .withState("connector_s"),
    persianBracketsBull = new Model("persian_bracket_bull", "Persian Bracket (Bull)", PersianBracket::new, CONCRETE, STONES)
      .withModel(PERSIAN_BRACKET_BULL)
      .withState("horizontal"),
    persianVolutes = new Model("persian_column_volute", "Persian Column Volute", PersianVolute::new, CONCRETE, STONES)
      .withModel(PERSIAN_VOLUTE, material -> new TextureMapping()
        .put(TextureSlot.SIDE,   Polychrome.id("block/" + material + "_column_fluting"))
        .put(TextureSlot.END,    Polychrome.id("block/" + material + "_column_top"))
        .put(TextureSlot.LAYER0, Polychrome.id("block/" + material + "_volute"))),
    persianCapitals = new Model("persian_column_capital", "Persian Column Capital", Column::new, CONCRETE, STONES)
      .withModel(PERSIAN_CAPITAL, Model.defaultMappingFunction("persian_capital"));

  public static Model[] blocks = new Model[]{
    columns,
    flutedColumns,
    pilasters,
    flutedPilasters,
    persianMerlonsDeco,
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
    public Map<String, ModelTemplate> models;
    public Function<String, TextureMapping> mappingFunction;
    public String stateBuilder;
    public String[] materials;
    public Map<String, DeferredBlock> deferredBlocks;

    public static Function<Properties, Properties> defaultPropertiesBuilder = it -> it;
    public Map<String, ModelTemplate> defaultModel = new HashMap<>();
    public static String defaultStateBuilder = "model";
    public static Function<String, TextureMapping> defaultMappingFunction(String name){
      return material -> new TextureMapping().put(TextureSlot.SIDE, Polychrome.id("block/" + material + "_" + name));
    }

    public Model(String name, String displayName,
                 Function<Properties, Block> constructor,
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

    public Model withProperties(Function<Properties, Properties> propertiesBuilder){
      this.propertiesBuilder = propertiesBuilder;
      return this;
    }
    public Model withModel(ModelTemplate model){
      this.models.put("base", model);
      return this;
    }
    public Model withModel(ModelTemplate model, Function<String, TextureMapping> mappingFunction){
      this.models.put("base", model);
      this.mappingFunction = mappingFunction;
      return this;
    }
    public Model withModel(Map<String, ModelTemplate> models, Function<String, TextureMapping> mappingFunction){
      this.models = models;
      this.mappingFunction = mappingFunction;
      return this;
    }
    public Model withState(String stateBuilder){
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
    public void put(String material, BalmBlockRegistration block){
      deferredBlocks.put(material, block.withDefaultItem().asDeferredBlock());
    }
  }
}
