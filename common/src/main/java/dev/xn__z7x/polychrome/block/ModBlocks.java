package dev.xn__z7x.polychrome.block;

import dev.xn__z7x.polychrome.Polychrome;
import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import java.util.*;
import static dev.xn__z7x.polychrome.api.Materials.*;

public class ModBlocks{

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
}
