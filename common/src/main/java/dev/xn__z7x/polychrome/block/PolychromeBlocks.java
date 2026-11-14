package dev.xn__z7x.polychrome.block;

import dev.xn__z7x.polychrome.Polychrome;
import dev.xn__z7x.polychrome.util.BlockModel;
import dev.xn__z7x.polychrome.util.VoxelShapeUtils;
import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.*;
import java.util.function.Function;

import static dev.xn__z7x.polychrome.util.Materials.*;
import static net.minecraft.world.level.block.Block.box;

public class PolychromeBlocks{
  public static final ModelTemplate
    COLUMN = BlockModel.block("block/column", TextureSlot.SIDE, TextureSlot.END),
    PILASTER = BlockModel.block("block/pilaster", TextureSlot.SIDE, TextureSlot.END),
    SIMPLE_MOULDING_STRAIGHT = BlockModel.block("block/simple_moulding", TextureSlot.SIDE, TextureSlot.END),
    SIMPLE_MOULDING_INNER = BlockModel.block("block/simple_moulding_inner", "_inner", TextureSlot.SIDE, TextureSlot.END),
    SIMPLE_MOULDING_OUTER = BlockModel.block("block/simple_moulding_outer", "_outer", TextureSlot.SIDE, TextureSlot.END),
    PERSIAN_MERLON_DECO_STRAIGHT = BlockModel.block("block/persian_merlon_deco_straight", TextureSlot.SIDE),
    PERSIAN_MERLON_DECO_CORNER = BlockModel.block("block/persian_merlon_deco_corner", "_corner", TextureSlot.SIDE),
    PERSIAN_BRACKET_BULL = BlockModel.block("block/persian_bracket_bull", TextureSlot.SIDE),
    PERSIAN_COLUMN_VOLUTE = BlockModel.block("block/persian_column_volute", TextureSlot.SIDE, TextureSlot.END, TextureSlot.LAYER0),
    PERSIAN_PILASTER_VOLUTE = BlockModel.block("block/persian_pilaster_volute", TextureSlot.SIDE, TextureSlot.END, TextureSlot.LAYER0),
    PERSIAN_COLUMN_CAPITAL = BlockModel.block("block/persian_column_capital", TextureSlot.SIDE),
    PERSIAN_PILASTER_CAPITAL = BlockModel.block("block/persian_pilaster_capital", TextureSlot.SIDE);

  public static final Map<String, ModelTemplate>
    SIMPLE_MOULDING = Map.of(
      "straight", SIMPLE_MOULDING_STRAIGHT,
      "inner", SIMPLE_MOULDING_INNER,
      "outer", SIMPLE_MOULDING_OUTER
    );

  public static final Function<BlockBehaviour.Properties, Block>
    Column = properties -> new BasicBlock(properties, box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)),
    Pilaster = properties -> new XZRotatingBlock(properties, VoxelShapeUtils.horizontal(box(2, 0, 0, 14, 16, 6))),
    SimpleMoulding = properties -> new XZWrappingBlock(properties, VoxelShapeUtils.connecting(
      box(0, 0, 0, 16, 16, 8),
      VoxelShapeUtils.combine(
        box(0, 0, 8, 8, 16, 16),
        box(0, 0, 0, 16, 16, 8)
      ),
      box(0, 0, 0, 8, 16, 8)
    )),
    PersianBracket = properties -> new XZRotatingBlock(properties, VoxelShapeUtils.horizontal(
      VoxelShapeUtils.combine(
        box(3.0, 0.0, -8.0, 13.0, 8.0, 10.0),
        box(4.0, 8.0, -4.0, 12.0, 12.0, 13.0),
        box(4.0, 12.0, -3.0, 12.0, 13.0, 13.0),
        box(4.0, 13.0, -1.0, 12.0, 16.0, 14.0),
        box(4.0, 13.0, -3.0, 12.0, 14.0, -1.0)
      )
    )),
    PersianColumnVolute = properties -> new BasicBlock(properties, VoxelShapeUtils.combine(
      box(3, -8, 3, 13, -6, 13),
      box(3, 14, 3, 13, 16, 13),
      box(3, -6, 2, 13, 0, 3),
      box(3, 0, 2, 13, 14, 3),
      box(13, -6, 3, 14, 0, 13),
      box(13, 0, 3, 14, 14, 13),
      box(3, -6, 13, 13, 0, 14),
      box(2, -6, 3, 3, 0, 13),
      box(2, 0, 3, 3, 14, 13),
      box(3, 0, 13, 13, 14, 14)
    )),
    PersianPilasterVolute = properties -> new XZRotatingBlock(properties, VoxelShapeUtils.horizontal(
      VoxelShapeUtils.combine(
        Block.box(3, -8, 0, 13, 0, 5),
        Block.box(3, 0, 0, 13, 16, 5),
        Block.box(3, -6, 5, 13, 0, 6),
        Block.box(3, 0, 5, 13, 14, 6),
        Block.box(13, -6, 0, 14, 0, 5),
        Block.box(13, 0, 0, 14, 14, 5),
        Block.box(2, -6, 0, 3, 0, 5),
        Block.box(2, 0, 0, 3, 14, 5)
      )
    )),
    PersianColumnCapital = properties -> new BasicBlock(properties, VoxelShapeUtils.combine(
      Block.box(2, 0, 2, 14, 2, 14),
      Block.box(5, 2, 13, 11, 9, 16),
      Block.box(5, 9, 13, 11, 11, 15),
      Block.box(0, 2, 5, 3, 9, 11),
      Block.box(1, 9, 5, 3, 11, 11),
      Block.box(5, 2, 0, 11, 9, 3),
      Block.box(5, 9, 1, 11, 11, 3),
      Block.box(13, 2, 5, 16, 9, 11),
      Block.box(13, 9, 5, 15, 11, 11),
      Block.box(4, 12, 13, 12, 13, 14),
      Block.box(3, 12, 12, 13, 13, 13),
      Block.box(2, 12, 4, 14, 13, 12),
      Block.box(3, 12, 3, 13, 13, 4),
      Block.box(4, 12, 2, 12, 13, 3),
      Block.box(3, 11, 3, 13, 12, 13),
      Block.box(6, 13, 13, 10, 19, 14),
      Block.box(6, 19, 13, 10, 20, 15),
      Block.box(6, 20, 13, 10, 22, 17),
      Block.box(6, 22, 13, 10, 23, 16),
      Block.box(6, 13, 13, 10, 19, 14),
      Block.box(6, 19, 13, 10, 20, 15),
      Block.box(6, 20, 13, 10, 22, 17),
      Block.box(6, 22, 13, 10, 23, 16),
      Block.box(2, 13, 6, 3, 19, 10),
      Block.box(1, 19, 6, 3, 20, 10),
      Block.box(-1, 20, 6, 3, 22, 10),
      Block.box(0, 22, 6, 3, 23, 10),
      Block.box(2, 13, 6, 3, 19, 10),
      Block.box(1, 19, 6, 3, 20, 10),
      Block.box(-1, 20, 6, 3, 22, 10),
      Block.box(0, 22, 6, 3, 23, 10),
      Block.box(6, 13, 2, 10, 19, 3),
      Block.box(6, 19, 1, 10, 20, 3),
      Block.box(6, 20, -1, 10, 22, 3),
      Block.box(6, 22, 0, 10, 23, 3),
      Block.box(6, 13, 2, 10, 19, 3),
      Block.box(6, 19, 1, 10, 20, 3),
      Block.box(6, 20, -1, 10, 22, 3),
      Block.box(6, 22, 0, 10, 23, 3),
      Block.box(13, 13, 6, 14, 19, 10),
      Block.box(13, 19, 6, 15, 20, 10),
      Block.box(13, 20, 6, 17, 22, 10),
      Block.box(13, 22, 6, 16, 23, 10),
      Block.box(13, 13, 6, 14, 19, 10),
      Block.box(13, 19, 6, 15, 20, 10),
      Block.box(13, 20, 6, 17, 22, 10),
      Block.box(13, 22, 6, 16, 23, 10),
      Block.box(3, 23, 3, 13, 24, 13)
    )),
    PersianPilasterCapital = properties -> new XZRotatingBlock(properties, VoxelShapeUtils.horizontal(
      VoxelShapeUtils.combine(
        Block.box(13, 2, 0, 16, 9, 3),
        Block.box(13, 9, 0, 15, 11, 3),
        Block.box(5, 2, 5, 11, 9, 8),
        Block.box(5, 9, 5, 11, 11, 7),
        Block.box(0, 2, 0, 3, 9, 3),
        Block.box(1, 9, 0, 3, 11, 3),
        Block.box(2, 12, 0, 14, 13, 4),
        Block.box(3, 12, 4, 13, 13, 5),
        Block.box(4, 12, 5, 12, 13, 6),
        Block.box(3, 11, 0, 13, 12, 5),
        Block.box(3, 2, 0, 13, 11, 1),
        Block.box(13, 13, 0, 14, 19, 2),
        Block.box(13, 19, 0, 15, 20, 2),
        Block.box(13, 20, 0, 17, 22, 2),
        Block.box(13, 22, 0, 16, 23, 2),
        Block.box(6, 13, 5, 10, 19, 6),
        Block.box(6, 19, 5, 10, 20, 7),
        Block.box(6, 20, 5, 10, 22, 9),
        Block.box(6, 22, 5, 10, 23, 8),
        Block.box(2, 13, 0, 3, 19, 2),
        Block.box(1, 19, 0, 3, 20, 2),
        Block.box(-1, 20, 0, 3, 22, 2),
        Block.box(0, 22, 0, 3, 23, 2),
        Block.box(3, 23, 0, 13, 24, 5),
        Block.box(2, 0, 0, 14, 2, 6),
        Block.box(3, 13, 0, 13, 23, 1)
      )
    )),
    PersianMerlonDeco = properties -> new XZWrappingBlock(properties, VoxelShapeUtils.connecting(
      VoxelShapeUtils.combine(
        box(5, 11, 6, 11, 18, 10),
        box(0, 0, 6, 16, 4, 10),
        box(3, 4, 6, 13, 11, 10)
      ),
      VoxelShapeUtils.combine(
        box(6, 0, 6, 10, 4, 16),
        box(10, 0, 6, 16, 4, 10),
        box(6, 4, 6, 10, 11, 13),
        box(6, 11, 6, 10, 18, 11),
        box(10, 11, 6, 11, 18, 10),
        box(10, 4, 6, 13, 11, 10)
      )
    ));

  public static BlockModel
    columns = new BlockModel("column", "Column", Column, STONES, WOODS)
      .withModel(COLUMN, material -> Map.of(
        TextureSlot.SIDE,  Polychrome.id("block/" + material + "/column"),
        TextureSlot.END,   Polychrome.id("block/" + material + "/column_top"))),
    flutedColumns = new BlockModel("fluted_column", "Fluted Column", Column, STONES)
      .withModel(COLUMN, material -> Map.of(
        TextureSlot.SIDE,  Polychrome.id("block/" + material + "/column_fluting"),
        TextureSlot.END,   Polychrome.id("block/" + material + "/column_top"))),
    pilasters = new BlockModel("pilaster", "Pilaster", Pilaster, STONES, WOODS)
      .withModel(PILASTER, material -> Map.of(
        TextureSlot.SIDE,  Polychrome.id("block/" + material + "/column"),
        TextureSlot.END,   Polychrome.id("block/" + material + "/column_top")))
      .withState("horizontal"),
    flutedPilasters = new BlockModel("fluted_pilaster", "Fluted Pilaster", Pilaster, STONES)
      .withModel(PILASTER, material -> Map.of(
        TextureSlot.SIDE,  Polychrome.id("block/" + material + "/column_fluting"),
        TextureSlot.END,   Polychrome.id("block/" + material + "/column_top")))
      .withState("horizontal"),
    persianMerlonsDeco = new BlockModel("persian_merlon_deco", "Decorative Persian Merlon", PersianMerlonDeco, STONES)
      .withModel(Map.of(
          "straight", PERSIAN_MERLON_DECO_STRAIGHT,
          "corner", PERSIAN_MERLON_DECO_CORNER),
        BlockModel.defaultMappingFunction("persian_merlon_deco"))
      .withState("connector_s"),
    persianBracketsBull = new BlockModel("persian_bracket_bull", "Persian Bracket (Bull)", PersianBracket, STONES)
      .withModel(PERSIAN_BRACKET_BULL)
      .withState("horizontal"),
    persianColumnVolutes = new BlockModel("persian_column_volute", "Persian Column Volute", PersianColumnVolute, STONES)
      .withModel(PERSIAN_COLUMN_VOLUTE, material -> Map.of(
        TextureSlot.SIDE,   Polychrome.id("block/" + material + "/column_fluting"),
        TextureSlot.END,    Polychrome.id("block/" + material + "/column_top"),
        TextureSlot.LAYER0, Polychrome.id("block/" + material + "/volute"))),
    persianPilasterVolutes = new BlockModel("persian_pilaster_volute", "Persian Pilaster Volute", PersianPilasterVolute, STONES)
      .withModel(PERSIAN_PILASTER_VOLUTE, persianColumnVolutes.mappingFunction)
      .withState("horizontal"),
    persianColumnCapitals = new BlockModel("persian_column_capital", "Persian Column Capital", PersianColumnCapital, STONES)
      .withModel(PERSIAN_COLUMN_CAPITAL, BlockModel.defaultMappingFunction("persian_capital")),
    persianPilasterCapitals = new BlockModel("persian_pilaster_capital", "Persian Pilaster Capital", PersianPilasterCapital, STONES)
      .withModel(PERSIAN_PILASTER_CAPITAL, persianColumnCapitals.mappingFunction)
      .withState("horizontal"),
    persianReliefLions = new BlockModel("persian_relief_lion", "Persian Relief Lion", SimpleMoulding, STONES)
      .withModel(SIMPLE_MOULDING, material -> Map.of(
        TextureSlot.SIDE, Polychrome.id("block/" + material + "/persian_relief_lion"),
        TextureSlot.END, Polychrome.id("block/" + material + "/blank")))
      .withState("connector");

  public static BlockModel[] blocks = new BlockModel[]{
    columns,
    flutedColumns,
    pilasters,
    flutedPilasters,
    persianMerlonsDeco,
    persianReliefLions,
    persianBracketsBull,
    persianColumnVolutes,
    persianColumnCapitals,
    persianPilasterVolutes,
    persianPilasterCapitals
  };

  public static void initialize(BalmBlockRegistrar regis){
    for(BlockModel model : blocks){
      model.init(regis);
    }
  }
}
