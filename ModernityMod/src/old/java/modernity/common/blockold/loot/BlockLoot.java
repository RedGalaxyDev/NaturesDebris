/*
 * Copyright (c) 2020 RedGalaxy & contributors
 * All rights reserved. Do not distribute.
 *
 * For a full license, see LICENSE.txt
 */

package modernity.common.blockold.loot;

import com.google.common.collect.ImmutableSet;
import modernity.common.blockold.MDBlockStateProperties;
import modernity.common.blockold.base.SlabType;
import modernity.common.loot.func.MulCornerCount;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.IProperty;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.*;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.CopyName;
import net.minecraft.world.storage.loot.functions.ExplosionDecay;
import net.minecraft.world.storage.loot.functions.SetCount;
import modernity.util.Lazy;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class BlockLoot {
    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder SHEARS_OR_SILK_TOUCH = SHEARS.alternative(SILK_TOUCH);

    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(
        Blocks.DRAGON_EGG,
        Blocks.BEACON,
        Blocks.CONDUIT,
        Blocks.SKELETON_SKULL,
        Blocks.WITHER_SKELETON_SKULL,
        Blocks.PLAYER_HEAD,
        Blocks.ZOMBIE_HEAD,
        Blocks.CREEPER_HEAD,
        Blocks.DRAGON_HEAD,
        Blocks.SHULKER_BOX,
        Blocks.BLACK_SHULKER_BOX,
        Blocks.BLUE_SHULKER_BOX,
        Blocks.BROWN_SHULKER_BOX,
        Blocks.CYAN_SHULKER_BOX,
        Blocks.GRAY_SHULKER_BOX,
        Blocks.GREEN_SHULKER_BOX,
        Blocks.LIGHT_BLUE_SHULKER_BOX,
        Blocks.LIGHT_GRAY_SHULKER_BOX,
        Blocks.LIME_SHULKER_BOX,
        Blocks.MAGENTA_SHULKER_BOX,
        Blocks.ORANGE_SHULKER_BOX,
        Blocks.PINK_SHULKER_BOX,
        Blocks.PURPLE_SHULKER_BOX,
        Blocks.RED_SHULKER_BOX,
        Blocks.WHITE_SHULKER_BOX,
        Blocks.YELLOW_SHULKER_BOX
    ).map(IItemProvider::asItem).collect(ImmutableSet.toImmutableSet());

    private BlockLoot() {
    }

    private static <T> T explosionFunc(IItemProvider item, ILootFunctionConsumer<T> consumer) {
        return !EXPLOSION_RESISTANT.contains(item.asItem())
               ? consumer.acceptFunction(ExplosionDecay.builder())
               : consumer.cast();
    }

    private static <T> T explosionCond(IItemProvider item, ILootConditionConsumer<T> consumer) {
        return !EXPLOSION_RESISTANT.contains(item.asItem())
               ? consumer.acceptCondition(SurvivesExplosion.builder())
               : consumer.cast();
    }

    public static IBlockLoot self() {
        return block -> explosionCond(block, ItemLootEntry.builder(block));
    }

    public static IBlockLoot item(IItemProvider item) {
        return block -> explosionCond(item, ItemLootEntry.builder(item));
    }

    public static IBlockLoot item(IItemProvider item, int count) {
        return block -> explosionFunc(item, ItemLootEntry.builder(item).acceptFunction(SetCount.builder(ConstantRange.of(count))));
    }

    public static IBlockLoot item(IItemProvider item, float chance) {
        return block -> explosionFunc(item, ItemLootEntry.builder(item).acceptCondition(RandomChance.builder(chance)));
    }

    public static IBlockLoot item(IItemProvider item, int min, int max) {
        return block -> explosionFunc(item, ItemLootEntry.builder(item).acceptFunction(SetCount.builder(RandomValueRange.of(min, max))));
    }

    public static IBlockLoot item(IItemProvider item, int n, float p) {
        return block -> explosionFunc(item, ItemLootEntry.builder(item).acceptFunction(SetCount.builder(BinomialRange.of(n, p))));
    }

    public static IBlockLoot item(Supplier<IItemProvider> item) {
        Lazy<Item> lazy = Lazy.of(item).map(IItemProvider::asItem);
        return block -> explosionCond(lazy.get(), ItemLootEntry.builder(lazy.get()));
    }

    public static IBlockLoot item(Supplier<IItemProvider> item, int count) {
        Lazy<Item> lazy = Lazy.of(item).map(IItemProvider::asItem);
        return block -> explosionFunc(lazy.get(), ItemLootEntry.builder(lazy.get()).acceptFunction(SetCount.builder(ConstantRange.of(count))));
    }

    public static IBlockLoot item(Supplier<IItemProvider> item, float chance) {
        Lazy<Item> lazy = Lazy.of(item).map(IItemProvider::asItem);
        return block -> explosionFunc(lazy.get(), ItemLootEntry.builder(lazy.get()).acceptCondition(RandomChance.builder(chance)));
    }

    public static IBlockLoot item(Supplier<IItemProvider> item, int min, int max) {
        Lazy<Item> lazy = Lazy.of(item).map(IItemProvider::asItem);
        return block -> explosionFunc(lazy.get(), ItemLootEntry.builder(lazy.get()).acceptFunction(SetCount.builder(RandomValueRange.of(min, max))));
    }

    public static IBlockLoot item(Supplier<IItemProvider> item, int n, float p) {
        Lazy<Item> lazy = Lazy.of(item).map(IItemProvider::asItem);
        return block -> explosionFunc(lazy.get(), ItemLootEntry.builder(lazy.get()).acceptFunction(SetCount.builder(BinomialRange.of(n, p))));
    }

    public static IBlockLoot slab() {
        return block -> explosionFunc(
            block,
            ItemLootEntry.builder(block).acceptFunction(
                SetCount.builder(ConstantRange.of(2)).acceptCondition(
                    stateProp(block, MDBlockStateProperties.SLAB_TYPE, SlabType.DOUBLE)
                )
            )
        );
    }

    public static <T extends Comparable<T>> BlockStateProperty.Builder stateProp(Block block, IProperty<T> prop, T val) {
        return BlockStateProperty.builder(block).fromProperties(
            StatePropertiesPredicate.Builder
                .newBuilder()
                .withStringProp(prop, val instanceof IStringSerializable ? ((IStringSerializable) val).getName() : val.toString())
        );
    }

    public static IBlockLoot corner() {
        return block -> explosionFunc(
            block,
            ItemLootEntry.builder(block).acceptFunction(MulCornerCount.builder())
        );
    }


    public static IBlockLoot select(ILootCondition.IBuilder cond, IBlockLoot with) {
        return block -> with.createLootEntry(block)
                            .acceptCondition(cond);
    }


    public static IBlockLoot select(ILootCondition.IBuilder cond, IBlockLoot with, IBlockLoot without) {
        return block -> with.createLootEntry(block)
                            .acceptCondition(cond)
                            .alternatively(without.createLootEntry(block));
    }

    public static IBlockLoot silkTouch(IBlockLoot with, IBlockLoot without) {
        return select(SILK_TOUCH, with, without);
    }

    public static IBlockLoot silkTouch(IBlockLoot with) {
        return select(SILK_TOUCH, with);
    }

    public static IBlockLoot shears(IBlockLoot with, IBlockLoot without) {
        return select(SHEARS, with, without);
    }

    public static IBlockLoot shears(IBlockLoot with) {
        return select(SHEARS, with);
    }

    public static IBlockLoot silkTouchOrShears(IBlockLoot with, IBlockLoot without) {
        return select(SHEARS_OR_SILK_TOUCH, with, without);
    }

    public static IBlockLoot silkTouchOrShears(IBlockLoot with) {
        return select(SHEARS_OR_SILK_TOUCH, with);
    }

    public static <T extends Comparable<T>> IBlockLoot blockProperty(IProperty<T> prop, T val, IBlockLoot with) {
        return block -> with.createLootEntry(block)
                            .acceptCondition(stateProp(block, prop, val));
    }

    public static <T extends Comparable<T>> IBlockLoot blockProperty(IProperty<T> prop, T val, IBlockLoot with, IBlockLoot without) {
        return block -> with.createLootEntry(block)
                            .acceptCondition(stateProp(block, prop, val))
                            .alternatively(without.createLootEntry(block));
    }


    public static IBlockLoot chest() {
        return block -> explosionCond(block, ItemLootEntry.builder(block).acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)));
    }

    public static IBlockLoot oreItem(Supplier<IItemProvider> item) {
        Lazy<Item> lazy = Lazy.of(item).map(IItemProvider::asItem);
        return block -> explosionCond(lazy.get(), ItemLootEntry.builder(lazy.get()).acceptFunction(
            ApplyBonus.oreDrops(Enchantments.FORTUNE)
        ));
    }

    public static IBlockLoot oreItem(Supplier<IItemProvider> item, int min, int max) {
        Lazy<Item> lazy = Lazy.of(item).map(IItemProvider::asItem);
        return block -> explosionCond(
            lazy.get(),
            ItemLootEntry.builder(lazy.get())
                         .acceptFunction(SetCount.builder(RandomValueRange.of(min, max)))
                         .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))
        );
    }

    public static IBlockLoot oreItem(Supplier<IItemProvider> item, int min, int max, float moreChance) {
        Lazy<Item> lazy = Lazy.of(item).map(IItemProvider::asItem);
        return block -> explosionCond(
            lazy.get(),
            ItemLootEntry.builder(lazy.get())
                         .acceptFunction(SetCount.builder(RandomValueRange.of(min, max)))
                         .acceptFunction(ApplyBonus.binomialWithBonusCount(Enchantments.FORTUNE, moreChance, 0))
        );
    }
}
