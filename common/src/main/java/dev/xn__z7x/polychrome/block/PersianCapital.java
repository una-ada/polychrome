package dev.xn__z7x.polychrome.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PersianCapital extends Block{
  public PersianCapital(Properties properties){ super(properties);}

  @Override
  protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context){
    return Shapes.or(
      Block.box(2, 0, 2, 14, 16, 14),
      Block.box(3, 10, 3, 13, 24, 3)
    );
  }
}
