package dev.xn__z7x.polychrome.block;

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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.StairsShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ConnectorBlock extends Block{
  public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
  public static final EnumProperty<StairsShape> SHAPE = BlockStateProperties.STAIRS_SHAPE;

  protected ConnectorBlock(Properties properties) { super(properties); }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
    builder.add(FACING, SHAPE);
  }

  @Override
  public @Nullable BlockState getStateForPlacement(BlockPlaceContext context){
    final var facing = context.getHorizontalDirection();
    final Block thisBlock = this.defaultBlockState().getBlock();
    final BlockGetter level = context.getLevel();
    final BlockPos position = context.getClickedPos();
    final BlockState
      front = level.getBlockState(position.relative(facing.getOpposite())),
      behind = level.getBlockState(position.relative(facing));
    StairsShape shape = StairsShape.STRAIGHT;

    if(behind.getBlock() == thisBlock && behind.hasProperty(FACING) && behind.getValue(FACING).getAxis() != facing.getAxis()){
      if(behind.getValue(FACING) == facing.getClockWise()){
        shape = StairsShape.OUTER_LEFT;
      } else {
        shape = StairsShape.OUTER_RIGHT;
      }
    } else if(front.getBlock() == thisBlock && front.hasProperty(FACING) && front.getValue(FACING).getAxis() != facing.getAxis()){
      if(front.getValue(FACING) == facing.getClockWise()){
        shape = StairsShape.INNER_LEFT;
      } else {
        shape = StairsShape.INNER_RIGHT;
      }
    }

    return this.defaultBlockState()
      .setValue(FACING, facing.getOpposite())
      .setValue(SHAPE, shape);
  }

  @Override
  protected @NotNull BlockState rotate(BlockState state, Rotation rotation){
    return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
  }

  @Override
  protected @NotNull BlockState mirror(@NotNull BlockState state, @NotNull Mirror mirror){
    Map<StairsShape, StairsShape> flipped = new HashMap<>();
    flipped.put(StairsShape.INNER_LEFT, StairsShape.INNER_RIGHT);
    flipped.put(StairsShape.INNER_RIGHT, StairsShape.INNER_LEFT);
    flipped.put(StairsShape.OUTER_LEFT, StairsShape.OUTER_RIGHT);
    flipped.put(StairsShape.OUTER_RIGHT, StairsShape.OUTER_LEFT);

    if((mirror == Mirror.LEFT_RIGHT && state.getValue(FACING).getAxis() == Direction.Axis.Z)
      || (mirror == Mirror.FRONT_BACK && state.getValue(FACING).getAxis() == Direction.Axis.X)){
      return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, flipped.get(state.getValue(SHAPE)));
    }
    return super.mirror(state, mirror);
  }
}
