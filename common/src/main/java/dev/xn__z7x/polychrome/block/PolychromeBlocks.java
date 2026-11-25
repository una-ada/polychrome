package dev.xn__z7x.polychrome.block;

import dev.xn__z7x.polychrome.Polychrome;
import dev.xn__z7x.polychrome.util.BlockModel;
import dev.xn__z7x.polychrome.util.BlockStateGenerators;
import dev.xn__z7x.polychrome.util.VoxelShapeUtils;
import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Map;
import java.util.function.Function;

import static dev.xn__z7x.polychrome.util.Materials.STONES;
import static dev.xn__z7x.polychrome.util.Materials.WOODS;

public class PolychromeBlocks{
  public static final ModelTemplate
    COLUMN = BlockModel.block("block/column", TextureSlot.SIDE, TextureSlot.END),
    PILASTER = BlockModel.block("block/pilaster", TextureSlot.SIDE, TextureSlot.END);
  public static final Map<String, ModelTemplate>
    SIMPLE_MOULDING = Map.of(
    "straight", BlockModel.block("block/simple_moulding", TextureSlot.SIDE, TextureSlot.END),
    "inner", BlockModel.block("block/simple_moulding_inner", "_inner", TextureSlot.SIDE, TextureSlot.END),
    "outer", BlockModel.block("block/simple_moulding_outer", "_outer", TextureSlot.SIDE, TextureSlot.END)
  );

  public static final Function<BlockBehaviour.Properties, Block>
    Column = properties -> new BasicBlock(properties, Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)),
    Pilaster = properties -> new XZRotatingBlock(properties, Block.box(2, 0, 0, 14, 16, 6)),
    SimpleMoulding = properties -> new XZWrappingBlock(properties,
      Block.box(0, 0, 0, 16, 16, 8),
      VoxelShapeUtils.combine(Block.box(0, 0, 8, 8, 16, 16), Block.box(0, 0, 0, 16, 16, 8)),
      Block.box(0, 0, 0, 8, 16, 8)
    ),
    PersianBracket = properties -> new XZRotatingBlock(properties, VoxelShapeUtils.combine(
      Block.box(3.0, 0.0, -8.0, 13.0, 8.0, 10.0),
      Block.box(4.0, 8.0, -4.0, 12.0, 12.0, 13.0),
      Block.box(4.0, 12.0, -3.0, 12.0, 13.0, 13.0),
      Block.box(4.0, 13.0, -1.0, 12.0, 16.0, 14.0),
      Block.box(4.0, 13.0, -3.0, 12.0, 14.0, -1.0)
    ));

  public static BlockModel
    columns = new BlockModel("column", "Column", Column, STONES, WOODS)
      .withModel(COLUMN, material -> Map.of(
        TextureSlot.SIDE, Polychrome.id("block/" + material + "/column"),
        TextureSlot.END, Polychrome.id("block/" + material + "/column_top"))),
    flutedColumns = new BlockModel("fluted_column", "Fluted Column", Column, STONES)
      .withModel(COLUMN, material -> Map.of(
        TextureSlot.SIDE, Polychrome.id("block/" + material + "/column_fluting"),
        TextureSlot.END, Polychrome.id("block/" + material + "/column_top"))),
    pilasters = new BlockModel("pilaster", "Pilaster", Pilaster, STONES, WOODS)
      .withModel(PILASTER, material -> Map.of(
        TextureSlot.SIDE, Polychrome.id("block/" + material + "/column"),
        TextureSlot.END, Polychrome.id("block/" + material + "/column_top")))
      .withState(BlockStateGenerators.XZ_ROTATING),
    flutedPilasters = new BlockModel("fluted_pilaster", "Fluted Pilaster", Pilaster, STONES)
      .withModel(PILASTER, material -> Map.of(
        TextureSlot.SIDE, Polychrome.id("block/" + material + "/column_fluting"),
        TextureSlot.END, Polychrome.id("block/" + material + "/column_top")))
      .withState(BlockStateGenerators.XZ_ROTATING),
    persianMerlonsDeco = new BlockModel("persian_merlon_deco", "Decorative Persian Merlon", properties -> new XZWrappingBlock(properties,
      VoxelShapeUtils.combine(
        Block.box(5, 11, 6, 11, 18, 10),  Block.box(0, 0, 6, 16, 4, 10),
        Block.box(3, 4, 6, 13, 11, 10)
      ),
      VoxelShapeUtils.combine(
        Block.box(6, 0, 6, 10, 4, 16),    Block.box(10, 0, 6, 16, 4, 10),
        Block.box(6, 4, 6, 10, 11, 13),   Block.box(6, 11, 6, 10, 18, 11),
        Block.box(10, 11, 6, 11, 18, 10), Block.box(10, 4, 6, 13, 11, 10)
      )
    ), STONES)
      .withModel(Map.of(
          "straight", BlockModel.block("block/persian_merlon_deco_straight", TextureSlot.SIDE),
          "corner", BlockModel.block("block/persian_merlon_deco_corner", "_corner", TextureSlot.SIDE)),
        BlockModel.defaultMappingFunction("persian_merlon_deco"))
      .withState(BlockStateGenerators.XZ_WRAPPING_S),
    persianBracketsBull = new BlockModel("persian_bracket_bull", "Persian Bracket (Bull)", PersianBracket, STONES)
      .withModel(BlockModel.block("block/persian_bracket_bull", TextureSlot.SIDE))
      .withState(BlockStateGenerators.XZ_ROTATING),
    persianColumnVolutes = new BlockModel("persian_column_volute", "Persian Column Volute", properties -> new BasicBlock(properties, VoxelShapeUtils.combine(
      Block.box(3, -8, 3, 13, -6, 13),  Block.box(3, 14, 3, 13, 16, 13),
      Block.box(3, -6, 2, 13, 0, 3),    Block.box(3, 0, 2, 13, 14, 3),
      Block.box(13, -6, 3, 14, 0, 13),  Block.box(13, 0, 3, 14, 14, 13),
      Block.box(3, -6, 13, 13, 0, 14),  Block.box(2, -6, 3, 3, 0, 13),
      Block.box(2, 0, 3, 3, 14, 13),    Block.box(3, 0, 13, 13, 14, 14)
    )), STONES)
      .withModel(BlockModel.block("block/persian_column_volute", TextureSlot.SIDE, TextureSlot.END, TextureSlot.LAYER0), material -> Map.of(
        TextureSlot.SIDE, Polychrome.id("block/" + material + "/column_fluting"),
        TextureSlot.END, Polychrome.id("block/" + material + "/column_top"),
        TextureSlot.LAYER0, Polychrome.id("block/" + material + "/volute"))),
    persianPilasterVolutes = new BlockModel("persian_pilaster_volute", "Persian Pilaster Volute", properties -> new XZRotatingBlock(properties, VoxelShapeUtils.combine(
      Block.box(3, -8, 0, 13, 0, 5),    Block.box(3, 0, 0, 13, 16, 5),
      Block.box(3, -6, 5, 13, 0, 6),    Block.box(3, 0, 5, 13, 14, 6),
      Block.box(13, -6, 0, 14, 0, 5),   Block.box(13, 0, 0, 14, 14, 5),
      Block.box(2, -6, 0, 3, 0, 5),     Block.box(2, 0, 0, 3, 14, 5)
    )), STONES)
      .withModel(BlockModel.block("block/persian_pilaster_volute", TextureSlot.SIDE, TextureSlot.END, TextureSlot.LAYER0), persianColumnVolutes.mappingFunction)
      .withState(BlockStateGenerators.XZ_ROTATING),
    persianColumnCapitals = new BlockModel("persian_column_capital", "Persian Column Capital", properties -> new BasicBlock(properties, VoxelShapeUtils.combine(
      Block.box(2, 11, 2, 14, 12, 14),  Block.box(3, 23, 3, 13, 24, 13),
      Block.box(3, 0, 3, 13, 1, 13),    Block.box(2, 19, 2, 14, 20, 14),
      Block.box(3, 12, 3, 13, 19, 13),  Block.box(0, 20, 0, 16, 23, 16),
      Block.box(1, 1, 1, 15, 9, 15),    Block.box(2, 9, 2, 14, 10, 14),
      Block.box(3, 10, 3, 13, 11, 13)
    )), STONES)
      .withModel(BlockModel.block("block/persian_column_capital", TextureSlot.SIDE), BlockModel.defaultMappingFunction("persian_capital")),
    persianPilasterCapitals = new BlockModel("persian_pilaster_capital", "Persian Pilaster Capital", properties -> new XZRotatingBlock(properties, VoxelShapeUtils.combine(
      Block.box(2, 11, 0, 14, 12, 6),   Block.box(3, 23, 0, 13, 24, 5),
      Block.box(2, 19, 0, 14, 20, 6),   Block.box(3, 12, 0, 13, 19, 5),
      Block.box(0, 20, 0, 16, 23, 8),   Block.box(3, 0, 0, 13, 1, 5),
      Block.box(2, 9, 0, 14, 10, 6),    Block.box(1, 1, 0, 15, 9, 7),
      Block.box(3, 10, 0, 13, 11, 5)
    )), STONES)
      .withModel(BlockModel.block("block/persian_pilaster_capital", TextureSlot.SIDE), persianColumnCapitals.mappingFunction)
      .withState(BlockStateGenerators.XZ_ROTATING),
    persianReliefLions = new BlockModel("persian_relief_lion", "Persian Relief Lion", SimpleMoulding, STONES)
      .withModel(SIMPLE_MOULDING, material -> Map.of(
        TextureSlot.SIDE, Polychrome.id("block/" + material + "/persian_relief_lion"),
        TextureSlot.END, Polychrome.id("block/" + material + "/blank")))
      .withState(BlockStateGenerators.XZ_WRAPPING);

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
