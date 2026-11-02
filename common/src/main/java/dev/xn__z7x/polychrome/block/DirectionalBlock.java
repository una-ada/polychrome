package dev.xn__z7x.polychrome.block;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

public class DirectionalBlock extends Block {
  public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

  public DirectionalBlock(Properties properties){ super(properties); }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
    builder.add(FACING);
  }

  @Override
  public @Nullable BlockState getStateForPlacement(BlockPlaceContext context){
    return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
  }

  @Override
  public BlockState rotate(BlockState state, Rotation rotation){
    return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
  }

  @Override
  public BlockState mirror(BlockState state, Mirror mirror){
    return state.rotate(mirror.getRotation(state.getValue(FACING)));
  }

}
