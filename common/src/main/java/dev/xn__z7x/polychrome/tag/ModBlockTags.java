package dev.xn__z7x.polychrome.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static dev.xn__z7x.polychrome.Polychrome.id;

public class ModBlockTags {
    public static final TagKey<Block> YOUR_TAG = TagKey.create(Registries.BLOCK, id("your_tag"));
}
