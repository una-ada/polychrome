package dev.xn__z7x.polychrome.util;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static net.minecraft.core.Direction.*;
import static net.minecraft.world.level.block.Rotation.*;
import static net.minecraft.world.level.block.state.properties.StairsShape.*;

public final class VoxelShapeUtils{

  private static final Vec3 fromOrigin = new Vec3(-0.5, -0.5, -0.5);

  public static AABB rotate(AABB box, Direction side){
    return switch(side){
      case DOWN -> box;
      case UP ->
        new AABB(box.minX, -box.minY, -box.minZ, box.maxX, -box.maxY, -box.maxZ);
      case NORTH ->
        new AABB(box.minX, -box.minZ, box.minY, box.maxX, -box.maxZ, box.maxY);
      case SOUTH ->
        new AABB(-box.minX, -box.minZ, -box.minY, -box.maxX, -box.maxZ, -box.maxY);
      case WEST ->
        new AABB(box.minY, -box.minZ, -box.minX, box.maxY, -box.maxZ, -box.maxX);
      case EAST ->
        new AABB(-box.minY, -box.minZ, box.minX, -box.maxY, -box.maxZ, box.maxX);
    };
  }

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

  public static VoxelShape rotate(VoxelShape shape, Direction side){
    return rotate(shape, side, VoxelShapeUtils::rotate);
  }

  public static VoxelShape rotate(VoxelShape shape, Rotation rotation){
    return rotate(shape, rotation, VoxelShapeUtils::rotate);
  }

  public static <DATA> VoxelShape rotate(VoxelShape shape, DATA data, BiFunction<AABB, DATA, AABB> rotateFunction){
    List<VoxelShape> rotatedPieces = new ArrayList<>();
    List<AABB> sourceBoundingBoxes = shape.toAabbs();
    for(AABB sourceBoundingBox : sourceBoundingBoxes){
      rotatedPieces.add(Shapes.create(rotateFunction.apply(sourceBoundingBox.move(fromOrigin.x, fromOrigin.y, fromOrigin.z), data)
        .move(-fromOrigin.x, -fromOrigin.z, -fromOrigin.z)));
    }
    return combine(rotatedPieces);
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

  public static Map<Direction, VoxelShape> horizontal(VoxelShape shape){
    return Map.of(
      NORTH, shape,
      EAST, rotate(shape, CLOCKWISE_90),
      SOUTH, rotate(shape, CLOCKWISE_180),
      WEST, rotate(shape, COUNTERCLOCKWISE_90)
    );
  }

  public static Map<StairsShape, Map<Direction, VoxelShape>> connecting(VoxelShape straight, VoxelShape inner, VoxelShape outer){
    return Map.of(
      STRAIGHT, horizontal(straight),
      INNER_LEFT, horizontal(inner),
      INNER_RIGHT, horizontal(rotate(inner, CLOCKWISE_90)),
      OUTER_LEFT, horizontal(outer),
      OUTER_RIGHT, horizontal(rotate(outer, CLOCKWISE_90))
    );
  }

  public static Map<StairsShape, Map<Direction, VoxelShape>> connecting(VoxelShape straight, VoxelShape corner){
    return connecting(straight, corner, rotate(corner, CLOCKWISE_180));
  }
}