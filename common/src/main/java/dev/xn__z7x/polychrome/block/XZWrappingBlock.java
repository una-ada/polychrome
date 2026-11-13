package dev.xn__z7x.polychrome.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class XZWrappingBlock extends Block{
  // public static final MapCodec<XZConnectingBlock> CODEC = simpleCodec(XZConnectingBlock::new);
  public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
  public static final EnumProperty<StairsShape> SHAPE = BlockStateProperties.STAIRS_SHAPE;
  public Map<StairsShape, Map<Direction, VoxelShape>> VOXEL_SHAPES;

  protected XZWrappingBlock(Properties properties, Map<StairsShape, Map<Direction, VoxelShape>> voxelShapes) {
    super(properties);
    this.VOXEL_SHAPES = voxelShapes;
    this.registerDefaultState(this.getStateDefinition().any()
      .setValue(FACING, Direction.NORTH)
      .setValue(SHAPE, StairsShape.STRAIGHT)
    );
  }

  @Override
  protected boolean useShapeForLightOcclusion(@NotNull BlockState state){
    return true;
  }

  @Override
  protected @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context){
    return VOXEL_SHAPES.get(state.getValue(SHAPE)).get(state.getValue(FACING));
  }

  @Override
  public @Nullable BlockState getStateForPlacement(BlockPlaceContext context){
    final BlockState state = this.defaultBlockState()
      .setValue(FACING, context.getHorizontalDirection());
    return state.setValue(SHAPE, getStairsShape(state, context.getLevel(), context.getClickedPos()));
  }

  @Override
  protected @NotNull BlockState updateShape(@NotNull BlockState state, Direction direction, @NotNull BlockState adjacent, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos adjacentPos){
    return direction.getAxis().isHorizontal()
      ? state.setValue(SHAPE, getStairsShape(state, level, pos))
      : super.updateShape(state, direction, adjacent, level, pos, adjacentPos);
  }

  private static StairsShape getStairsShape(BlockState state, BlockGetter level, BlockPos pos){
    final Direction facing = state.getValue(FACING);
    final BlockState behind = level.getBlockState(pos.relative(facing));
    if(isConnector(behind)
      && behind.getValue(FACING).getAxis() != facing.getAxis()
      && canTakeShape(state, level, pos, behind.getValue(FACING).getOpposite())){
      if(behind.getValue(FACING) == facing.getCounterClockWise()) return StairsShape.OUTER_LEFT;
      return StairsShape.OUTER_RIGHT;
    }
    final BlockState front = level.getBlockState(pos.relative(facing.getOpposite()));
    if(isConnector(front)
      && front.getValue(FACING).getAxis() != facing.getAxis()
      && canTakeShape(state, level, pos, front.getValue(FACING))){
      if(front.getValue(FACING) == facing.getCounterClockWise()) return StairsShape.INNER_LEFT;
      return StairsShape.INNER_RIGHT;
    }
    return StairsShape.STRAIGHT;
  }

  private static boolean canTakeShape(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
    BlockState facing = level.getBlockState(pos.relative(direction));
    return !isConnector(facing) || facing.getValue(FACING) != state.getValue(FACING);
  }

  private static boolean isConnector(BlockState state) {
    return state.getBlock() instanceof XZWrappingBlock;
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

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
    builder.add(FACING, SHAPE);
  }
}
