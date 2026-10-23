package edu.towson.cis.cosc442.project4.coffeemaker;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CoffeeMakerTest {
    private CoffeeMaker maker;

    @BeforeEach
    public void setUp(){
        maker = new CoffeeMaker();
    }

    //my own homebrew constructor for the recipes, there was a 0% chance i would type this out every time lol
    private Recipe recipe(String name, int price, int coffee, int milk, int sugar, int chocolate){
        Recipe r = new Recipe();
        r.setName(name);
        r.setPrice(price);
        r.setAmtCoffee(coffee);
        r.setAmtMilk(milk);
        r.setAmtSugar(sugar);
        r.setAmtCoffee(chocolate);
        return r;
    }

    //constructor used only when giving a name
    private Recipe recipe(String name){
        return recipe(name, 10, 1, 1, 1, 1);
    }

    //automatically fills every slot of coffee maker
    private void fillSlots(){
        for (int i = 0; i < 4; i++){
            assertTrue(maker.addRecipe(recipe("Recipe" + i)));
        }
    }

    //checks that a maker has 4 empty recipe slots, and 15 of everything
    @Test
    public void constructorCreatesEmptySlotsAndCorrectInventory(){
        Recipe[] recipes = maker.getRecipes();
        assertEquals(4, recipes.length);
        for (Recipe r : recipes){
            assertNotNull(r);
            assertNull(r.getName());
        }
        assertEquals(15, maker.checkInventory().getCoffee());
        assertEquals(15, maker.checkInventory().getMilk());
        assertEquals(15, maker.checkInventory().getSugar());
        assertEquals(15, maker.checkInventory().getChocolate());
    }

    //checks that a recipe goes into the first avaliable slot
    @Test
    public void addRecipeUsesFirstSlot(){
        //i know next to nothing about coffee, so prepare for everything to be named latte lol
        Recipe r = recipe("Latte");
        assertTrue(maker.addRecipe(r));
        assertSame(r, maker.getRecipes()[0]);
    }
}
