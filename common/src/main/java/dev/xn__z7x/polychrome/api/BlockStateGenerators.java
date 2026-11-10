package dev.xn__z7x.polychrome.api;

import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.BiFunction;

import static net.minecraft.core.Direction.EAST;
import static net.minecraft.core.Direction.NORTH;
import static net.minecraft.core.Direction.SOUTH;
import static net.minecraft.core.Direction.WEST;
import static net.minecraft.data.models.blockstates.VariantProperties.MODEL;
import static net.minecraft.data.models.blockstates.VariantProperties.Rotation.*;
import static net.minecraft.data.models.blockstates.VariantProperties.Rotation.R180;
import static net.minecraft.data.models.blockstates.VariantProperties.Y_ROT;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.STAIRS_SHAPE;
import static net.minecraft.world.level.block.state.properties.StairsShape.*;
import static net.minecraft.world.level.block.state.properties.StairsShape.OUTER_RIGHT;

public class BlockStateGenerators{
  public static final BiFunction<Block, Map<String, ResourceLocation>, BlockStateGenerator>
    CONNECTING = (block, models) -> MultiVariantGenerator.multiVariant(block)
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
    ),
    CONNECTING_S = (block, models) -> CONNECTING.apply(block, Map.of(
      "straight", models.get("straight"),
      "inner", models.get("corner"),
      "outer", models.get("corner")
    ));
}
