package dev.xn__z7x.polychrome.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Pilaster extends DirectionalBlock{
  public static final VoxelShape[] SHAPE = new VoxelShape[]{
    // South
    box(2, 0, 0, 14, 16, 6),
    // West
    box(10, 0, 2, 16, 16, 14),
    // North
    box(2, 0, 10, 14, 16, 16),
    // East
    box(0, 0, 2, 6, 16, 14)
  };

  public Pilaster(Properties properties){
    super(properties);
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context){
    Direction direction = state.getValue(FACING);
    return SHAPE[direction.get2DDataValue()];
  }
}
