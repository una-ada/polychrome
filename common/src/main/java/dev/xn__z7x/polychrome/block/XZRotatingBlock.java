package dev.xn__z7x.polychrome.block;

import dev.xn__z7x.polychrome.util.VoxelShapeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static net.minecraft.core.Direction.*;
import static net.minecraft.core.Direction.WEST;
import static net.minecraft.world.level.block.Rotation.*;

public class XZRotatingBlock extends Block {
  public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
  public Map<Direction, VoxelShape> voxelShapes;

  public static Map<Direction, VoxelShape> generateShapes(VoxelShape shape){
    return Map.of(
      NORTH, shape,
      EAST, VoxelShapeUtils.rotate(shape, CLOCKWISE_90),
      SOUTH, VoxelShapeUtils.rotate(shape, CLOCKWISE_180),
      WEST, VoxelShapeUtils.rotate(shape, COUNTERCLOCKWISE_90)
    );
  }

  public XZRotatingBlock(Properties properties, Map<Direction, VoxelShape> shapes){
    super(properties);
    this.voxelShapes = shapes;
  }
  public XZRotatingBlock(Properties properties, VoxelShape shape){
    this(properties, generateShapes(shape));
  }

  @Override
  public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context){
    return this.voxelShapes.get(state.getValue(FACING));
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
    builder.add(FACING);
  }

  @Override
  public @Nullable BlockState getStateForPlacement(BlockPlaceContext context){
    return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
  }

  @Override
  public @NotNull BlockState rotate(BlockState state, Rotation rotation){
    return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
  }

  @Override
  public @NotNull BlockState mirror(BlockState state, Mirror mirror){
    return state.rotate(mirror.getRotation(state.getValue(FACING)));
  }

}
