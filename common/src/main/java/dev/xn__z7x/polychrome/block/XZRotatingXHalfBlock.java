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
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class XZRotatingXHalfBlock extends Block{
  public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
  public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
  public Map<DoorHingeSide, Map<Direction, VoxelShape>> voxelShapes;

  public XZRotatingXHalfBlock(Properties properties, Map<DoorHingeSide, Map<Direction, VoxelShape>> shapes){
    super(properties);
    this.voxelShapes = shapes;
    this.registerDefaultState(this.getStateDefinition().any()
      .setValue(FACING, Direction.NORTH)
      .setValue(HINGE, DoorHingeSide.LEFT));
  }

  @Override
  protected boolean useShapeForLightOcclusion(@NotNull BlockState state){
    return true;
  }

  @Override
  protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context){
    return this.voxelShapes.get(state.getValue(HINGE)).get(state.getValue(FACING));
  }

  @Override
  public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context){
    BlockPos blockPos = context.getClickedPos();
    Direction direction = context.getHorizontalDirection();
    int normalX = direction.getStepX(),
      normalZ = direction.getStepZ();
    Vec3 vec3 = context.getClickLocation();
    double offsetX = vec3.x - blockPos.getX(),
      offsetZ = vec3.z - blockPos.getZ();
    return this.defaultBlockState()
      .setValue(FACING, direction)
      .setValue(HINGE,
        normalX < 0 && offsetX < 0.5F || normalX > 0 && offsetX > 0.5F ||
        normalZ < 0 && offsetZ < 0.5F || normalZ > 0 && offsetZ > 0.5F
          ? DoorHingeSide.RIGHT
          : DoorHingeSide.LEFT
      );
  }

  @Override
  protected @NotNull BlockState rotate(@NotNull BlockState state, @NotNull Rotation rotation){
    return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
  }

  @Override
  protected @NotNull BlockState mirror(@NotNull BlockState state, @NotNull Mirror mirror){
    return state.rotate(mirror.getRotation(state.getValue(FACING))).cycle(HINGE);
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
    builder.add(FACING, HINGE);
  }
}
