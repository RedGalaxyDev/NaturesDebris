/*
 * Copyright (c) 2019 RedGalaxy
 * All rights reserved. Do not distribute.
 *
 * Date:   12 - 29 - 2019
 * Author: rgsw
 */

package modernity.common.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Objects;
import java.util.Optional;

public class CleaningRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CleaningRecipe> {
    private final int defaultCookingTime;
    private final Fluid defaultFluid;
    private final float defaultFluidConsumption;

    public CleaningRecipeSerializer( int defaultCookingTime, Fluid defaultFluid, float defaultFluidConsumption ) {
        this.defaultCookingTime = defaultCookingTime;
        this.defaultFluid = defaultFluid;
        this.defaultFluidConsumption = defaultFluidConsumption;
    }

    @Override
    public CleaningRecipe read( ResourceLocation id, JsonObject json ) {
        String s = JSONUtils.getString( json, "group", "" );
        JsonElement ingredientElement = JSONUtils.isJsonArray( json, "ingredient" )
                                        ? JSONUtils.getJsonArray( json, "ingredient" )
                                        : JSONUtils.getJsonObject( json, "ingredient" );

        Ingredient ingredient = Ingredient.deserialize( ingredientElement );

        if( ! json.has( "result" ) )
            throw new JsonSyntaxException( "Missing result, expected to find a string or object" );
        ItemStack result;
        if( json.get( "result" ).isJsonObject() ) {
            result = ShapedRecipe.deserializeItem( JSONUtils.getJsonObject( json, "result" ) );
        } else {
            String resName = JSONUtils.getString( json, "result" );
            ResourceLocation resID = new ResourceLocation( resName );
            result = new ItemStack(
                Optional.ofNullable( ForgeRegistries.ITEMS.getValue( resID ) )
                        .orElseThrow( () -> new IllegalStateException( "Item: " + resName + " does not exist" ) )
            );
        }
        float xp = JSONUtils.getFloat( json, "experience", 0 );
        int cookingTime = JSONUtils.getInt( json, "cookingtime", defaultCookingTime );

        Fluid fluid = defaultFluid;
        if( json.has( "fluid" ) ) {
            String fluidName = JSONUtils.getString( json, "fluid" );
            ResourceLocation fluidID = new ResourceLocation( fluidName );
            fluid = ForgeRegistries.FLUIDS.getValue( fluidID );
            if( fluid == null ) {
                throw new IllegalStateException( "Fluid: " + fluidName + " does not exist" );
            }
        }

        float fluidAmount = JSONUtils.getFloat( json, "fluidamount", defaultFluidConsumption );

        return new CleaningRecipe( MDRecipeTypes.CLEANING, id, s, ingredient, result, xp, cookingTime, fluidAmount, fluid );
    }

    @Override
    public CleaningRecipe read( ResourceLocation id, PacketBuffer buffer ) {
        String group = buffer.readString();
        Ingredient ingredient = Ingredient.read( buffer );
        ItemStack result = buffer.readItemStack();
        float xp = buffer.readFloat();
        int cookTime = buffer.readVarInt();
        float fluidAmount = buffer.readFloat();

        ResourceLocation fluidRes = buffer.readResourceLocation();
        Fluid fluid = ForgeRegistries.FLUIDS.getValue( fluidRes );
        return new CleaningRecipe( MDRecipeTypes.CLEANING, id, group, ingredient, result, xp, cookTime, fluidAmount, fluid );
    }

    @Override
    public void write( PacketBuffer buffer, CleaningRecipe recipe ) {
        buffer.writeString( recipe.getGroup() );
        recipe.getIngredient().write( buffer );
        buffer.writeItemStack( recipe.getRecipeOutput() );
        buffer.writeFloat( recipe.getExperience() );
        buffer.writeVarInt( recipe.getCookTime() );
        buffer.writeFloat( recipe.getFluidAmount() );
        buffer.writeResourceLocation( Objects.requireNonNull( recipe.getRequiredFluid().getRegistryName() ) );
    }
}