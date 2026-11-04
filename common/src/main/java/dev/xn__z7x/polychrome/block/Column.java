package dev.xn__z7x.polychrome.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Column extends Block{
  public Column(Properties properties){
    super(properties);
  }

  @Override
  protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context){
    return Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);
  }
}
