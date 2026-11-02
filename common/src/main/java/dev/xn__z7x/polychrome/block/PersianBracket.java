package dev.xn__z7x.polychrome.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PersianBracket extends DirectionalBlock {
  public static final VoxelShape[] SHAPE = new VoxelShape[]{
    Shapes.or( // South
      box(3.0, 0.0, -8.0, 13.0, 8.0, 10.0),
      box(4.0, 8.0, -4.0, 12.0, 12.0, 13.0),
      box(4.0, 12.0, -3.0, 12.0, 13.0, 13.0),
      box(4.0, 13.0, -1.0, 12.0, 16.0, 14.0),
      box(4.0, 13.0, -3.0, 12.0, 14.0, -1.0)
    ),
    Shapes.or( // West
      box(6.0, 0.0, 3.0, 24.0, 8.0, 13.0),
      box(3.0, 8.0, 4.0, 20.0, 12.0, 12.0),
      box(3.0, 12.0, 4.0, 19.0, 13.0, 12.0),
      box(2.0, 13.0, 4.0, 17.0, 16.0, 12.0),
      box(17.0, 13.0, 4.0, 19.0, 14.0, 12.0)
    ),
    Shapes.or( // North
      box(3.0, 0.0, 6.0, 13.0, 8.0, 24.0),
      box(4.0, 8.0, 3.0, 12.0, 12.0, 20.0),
      box(4.0, 12.0, 3.0, 12.0, 13.0, 19.0),
      box(4.0, 13.0, 2.0, 12.0, 16.0, 17.0),
      box(4.0, 13.0, 17.0, 12.0, 14.0, 19.0)
    ).optimize(),
    Shapes.or( // East
      box(-8.0, 0.0, 3.0, 10.0, 8.0, 13.0),
      box(-4.0, 8.0, 4.0, 13.0, 12.0, 12.0),
      box(-3.0, 12.0, 4.0, 13.0, 13.0, 12.0),
      box(-1.0, 13.0, 4.0, 14.0, 16.0, 12.0),
      box(-3.0, 13.0, 4.0, -1.0, 14.0, 12.0)
    )
  };

  public PersianBracket(Properties properties){ super(properties); }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context){
    Direction direction = state.getValue(FACING);
    return SHAPE[direction.get2DDataValue()];
  }

}
