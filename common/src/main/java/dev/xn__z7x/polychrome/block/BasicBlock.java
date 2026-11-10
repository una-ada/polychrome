package dev.xn__z7x.polychrome.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class BasicBlock extends Block{
  public VoxelShape VOXEL_SHAPE;

  public BasicBlock(Properties properties, VoxelShape shape){
    super(properties);
    this.VOXEL_SHAPE = shape;
  }

  @Override
  protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context){
    return VOXEL_SHAPE;
  }
}
