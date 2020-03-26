/*
 * Copyright (c) 2020 RedGalaxy
 * All rights reserved. Do not distribute.
 *
 * Date:   02 - 03 - 2020
 * Author: rgsw
 */

package modernity.client.colors.deserializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import modernity.client.colors.ColorDeserializeContext;
import modernity.client.colors.ColorDeserializeException;
import modernity.client.colors.IColorProvider;
import modernity.client.colors.IColorProviderDeserializer;
import modernity.client.colors.provider.ExpressionColorProvider;
import net.redgalaxy.expression.DefaultFunctions;
import net.redgalaxy.expression.Expression;
import net.redgalaxy.expression.ExpressionParser;
import net.redgalaxy.expression.ExpressionSyntaxException;

import java.util.HashMap;
import java.util.Map;

public class ExpressionColorProviderDeserializer implements IColorProviderDeserializer {
    @Override
    public IColorProvider deserialize( JsonElement element, ColorDeserializeContext ctx ) throws ColorDeserializeException {
        if( element.isJsonObject() ) {
            JsonObject obj = element.getAsJsonObject();
            if( ! obj.has( "expression" ) )
                throw ctx.exception( "Missing required 'expression'" );
            if( ! obj.get( "expression" ).isJsonPrimitive() )
                throw ctx.exception( "'expression' must be a string" );
            if( ! obj.getAsJsonPrimitive( "expression" ).isString() )
                throw ctx.exception( "'expression' must be a string" );

            String expr = obj.get( "expression" ).getAsString();
            ExpressionParser parser = makeParser( expr );
            Expression expression;
            try {
                expression = parser.parse().threadLocal();
            } catch( ExpressionSyntaxException exc ) {
                throw ctx.exception( "Failed to parse expression:" + exc.getMessage(), exc );
            }

            HashMap<String, IColorProvider> inputs = new HashMap<>();

            if( obj.has( "inputs" ) && obj.get( "inputs" ).isJsonObject() ) {
                JsonObject inputsObj = obj.getAsJsonObject( "inputs" );
                for( Map.Entry<String, JsonElement> entry : inputsObj.entrySet() ) {
                    if( entry.getKey().equals( "x" ) ) {
                        throw ctx.exception( "Input name 'x' is reserved" );
                    }
                    if( entry.getKey().equals( "y" ) ) {
                        throw ctx.exception( "Input name 'y' is reserved" );
                    }
                    if( entry.getKey().equals( "z" ) ) {
                        throw ctx.exception( "Input name 'z' is reserved" );
                    }

                    inputs.put( entry.getKey(), ctx.silentDeserialize( entry.getValue(), entry.getKey() ) );
                }
            }

            HashMap<Integer, IColorProvider> inputIDs = new HashMap<>();
            int size = 0;

            for( Map.Entry<String, IColorProvider> entry : inputs.entrySet() ) {
                int i = expression.indexOfVariable( entry.getKey() );
                if( i > size ) size = i;
                inputIDs.put( i, entry.getValue() );
            }

            IColorProvider[] providers = new IColorProvider[ size ];

            for( Map.Entry<Integer, IColorProvider> entry : inputIDs.entrySet() ) {
                providers[ entry.getKey() ] = entry.getValue();
            }

            return new ExpressionColorProvider( expression, providers );
        } else {
            throw ctx.exception( "Expected an object" );
        }
    }

    private static ExpressionParser makeParser( String expr ) {
        ExpressionParser parser = new ExpressionParser( expr );
        DefaultFunctions.addDefaults( parser );
        return parser;
    }
}