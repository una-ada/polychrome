package dev.xn__z7x.polychrome.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.BooleanOp;

import java.util.Map;
import java.util.stream.Stream;

import static net.minecraft.core.Direction.*;

public class PersianMerlonDeco extends XZConnectingBlock{
  public static final VoxelShape
    STRAIGHT_EW = Stream.of(
      Block.box(5, 11, 6, 11, 18, 10),
      Block.box(0, 0, 6, 16, 4, 10),
      Block.box(3, 4, 6, 13, 11, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
    STRAIGHT_NS = Stream.of(
      Block.box(6, 11, 5, 10, 18, 11),
      Block.box(6, 0, 0, 10, 4, 16),
      Block.box(6, 4, 3, 10, 11, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
    CORNER_0 = Stream.of(
      Block.box(6, 0, 0, 10, 4, 10),
      Block.box(0, 0, 6, 6, 4, 10),
      Block.box(6, 4, 3, 10, 11, 10),
      Block.box(6, 11, 5, 10, 18, 10),
      Block.box(5, 11, 6, 6, 18, 10),
      Block.box(3, 4, 6, 6, 11, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
    CORNER_90 = Stream.of(
      Block.box(6, 0, 6, 16, 4, 10),
      Block.box(6, 0, 0, 10, 4, 6),
      Block.box(6, 4, 6, 13, 11, 10),
      Block.box(6, 11, 6, 11, 18, 10),
      Block.box(6, 11, 5, 10, 18, 6),
      Block.box(6, 4, 3, 10, 11, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
    CORNER_180 = Stream.of(
      Block.box(6, 0, 6, 10, 4, 16),
      Block.box(10, 0, 6, 16, 4, 10),
      Block.box(6, 4, 6, 10, 11, 13),
      Block.box(6, 11, 6, 10, 18, 11),
      Block.box(10, 11, 6, 11, 18, 10),
      Block.box(10, 4, 6, 13, 11, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
    CORNER_270 = Stream.of(
      Block.box(0, 0, 6, 10, 4, 10),
      Block.box(6, 0, 10, 10, 4, 16),
      Block.box(3, 4, 6, 10, 11, 10),
      Block.box(5, 11, 6, 10, 18, 10),
      Block.box(6, 11, 10, 10, 18, 11),
      Block.box(6, 4, 10, 10, 11, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
  public static final Map<StairsShape, Map<Direction, VoxelShape>> SHAPES = Map.of(
    StairsShape.STRAIGHT, Map.of(
      NORTH, STRAIGHT_EW,
      EAST, STRAIGHT_NS,
      SOUTH, STRAIGHT_EW,
      WEST, STRAIGHT_NS
    ),
    StairsShape.INNER_LEFT, Map.of(
      NORTH, CORNER_180,
      EAST, CORNER_270,
      SOUTH, CORNER_0,
      WEST, CORNER_90
    ),
    StairsShape.INNER_RIGHT, Map.of(
      NORTH, CORNER_270,
      EAST, CORNER_0,
      SOUTH, CORNER_90,
      WEST, CORNER_180
    ),
    StairsShape.OUTER_LEFT, Map.of(
      NORTH, CORNER_0,
      EAST, CORNER_90,
      SOUTH, CORNER_180,
      WEST, CORNER_270
    ),
    StairsShape.OUTER_RIGHT, Map.of(
      NORTH, CORNER_90,
      EAST, CORNER_180,
      SOUTH, CORNER_270,
      WEST, CORNER_0
    )
  );

  protected PersianMerlonDeco(Properties properties){ super(properties, SHAPES); }

}
