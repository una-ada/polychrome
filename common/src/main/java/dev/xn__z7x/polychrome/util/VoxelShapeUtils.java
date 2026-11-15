package dev.xn__z7x.polychrome.util;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class VoxelShapeUtils{

  private static final Vec3 fromOrigin = new Vec3(-0.5, -0.5, -0.5);

  public static AABB rotate(AABB box, Rotation rotation){
    return switch(rotation){
      case NONE -> box;
      case CLOCKWISE_90 ->
        new AABB(-box.minZ, box.minY, box.minX, -box.maxZ, box.maxY, box.maxX);
      case CLOCKWISE_180 ->
        new AABB(-box.minX, box.minY, -box.minZ, -box.maxX, box.maxY, -box.maxZ);
      case COUNTERCLOCKWISE_90 ->
        new AABB(box.minZ, box.minY, -box.minX, box.maxZ, box.maxY, -box.maxX);
    };
  }

  public static VoxelShape rotate(VoxelShape shape, Rotation rotation){
    List<VoxelShape> rotatedPieces = new ArrayList<>();
    for(AABB box : shape.toAabbs())
      rotatedPieces.add(Shapes.create(rotate(box.move(fromOrigin.x, fromOrigin.y, fromOrigin.z), rotation)
        .move(-fromOrigin.x, -fromOrigin.z, -fromOrigin.z)));
    return combine(rotatedPieces);
  }

  public static AABB mirror(AABB box, Direction.Axis axis){
    return switch(axis){
      case X ->
        new AABB(1.0 - box.maxX, box.minY, box.minZ, 1.0 - box.minX, box.maxY, box.maxZ);
      case Y ->
        new AABB(box.minX, 1.0 - box.maxY, box.minZ, box.maxX, 1.0 - box.minY, box.maxZ);
      case Z ->
        new AABB(box.minX, box.minY, 1.0 - box.maxZ, box.maxX, box.maxY, 1.0 - box.minZ);
    };
  }

  public static VoxelShape mirror(VoxelShape shape, Direction.Axis axis){
    List<VoxelShape> mirroredPieces = new ArrayList<>();
    for(AABB box : shape.toAabbs())
      mirroredPieces.add(Shapes.create(mirror(box, axis)));
    return combine(mirroredPieces);
  }

  public static VoxelShape combine(VoxelShape... shapes){
    return batchCombine(Shapes.empty(), BooleanOp.OR, true, shapes);
  }

  public static VoxelShape combine(Collection<VoxelShape> shapes){
    return batchCombine(Shapes.empty(), BooleanOp.OR, true, shapes);
  }

  public static VoxelShape batchCombine(VoxelShape initial, BooleanOp function, boolean simplify, Collection<VoxelShape> shapes){
    VoxelShape combinedShape = initial;
    for(VoxelShape shape : shapes){
      combinedShape = Shapes.joinUnoptimized(combinedShape, shape, function);
    }
    return simplify ? combinedShape.optimize() : combinedShape;
  }

  public static VoxelShape batchCombine(VoxelShape initial, BooleanOp function, boolean simplify, VoxelShape... shapes){
    VoxelShape combinedShape = initial;
    for(VoxelShape shape : shapes){
      combinedShape = Shapes.joinUnoptimized(combinedShape, shape, function);
    }
    return simplify ? combinedShape.optimize() : combinedShape;
  }

}