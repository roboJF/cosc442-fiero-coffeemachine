package edu.towson.cis.cosc442.project4.coffeemaker;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
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

    //checks that recipes with duplicate names are rejected
    @Test
    public void addRecipeRejectsDuplicateName(){
        assertTrue(maker.addRecipe(recipe("Latte", 10, 1, 1, 1, 1)));
        assertFalse(maker.addRecipe(recipe("Latte", 20, 5, 6, 5, 6)));
        assertNotNull(maker.getRecipeForName("Latte"));
    }
    
    //checks that the four recipe limit is enforced
    @Test
    public void addRecipeFillsFourSlotsAndNotFive(){
        fillSlots();
        assertFalse(maker.addRecipe(recipe("Fifth")));
        //syntax of doom and despair
        for (int i = 0; i < 4; i++){
            assertEquals("Recipe" + i, maker.getRecipeForName("Recipe" + i).getName());
        }
        assertNull(maker.getRecipeForName("Fifth").getName());
    }

    //checks to make sure that deleting nonexistant recipes wont delete, well, nothing (this is very important)
    @Test
    public void deleteRecipeDeniesNullOrMissing(){
        assertFalse(maker.deleteRecipe(null));
        assertFalse(maker.deleteRecipe(recipe("Nonexistant Latte")));
    }

    //checks that a deleted recipe is, well, deleted
    @Test
    public void deleteRecipeRemovesARecipe(){
        Recipe latte = recipe("Latte");
        assertTrue(maker.addRecipe(latte));
        assertTrue(maker.deleteRecipe(latte));
        assertNull(maker.getRecipeForName("Latte").getName());
    }

    //checks that deleting a recipe actually frees the slot up
    @Test
    public void deletionFromFullMachineFreesSlot(){
        fillSlots();
        assertTrue(maker.deleteRecipe(maker.getRecipeForName("Recipe1")));
        assertTrue(maker.addRecipe(recipe("New Latte")));
    }

    //checks that a recipe can be updated without changing its name
    @Test
    public void editRecipeWithSameNameAndSpace(){
        Recipe recipe = recipe("Latte", 10, 1, 1, 1, 1);
        Recipe newRecipe = recipe("Latte", 20, 1, 1, 1, 1);
        assertTrue(maker.addRecipe(recipe));
        assertTrue(maker.editRecipe(recipe, newRecipe));
        assertSame(newRecipe, maker.getRecipeForName("Latte"));
        assertEquals(20, maker.getRecipeForName("Latte").getPrice());
    }
}
