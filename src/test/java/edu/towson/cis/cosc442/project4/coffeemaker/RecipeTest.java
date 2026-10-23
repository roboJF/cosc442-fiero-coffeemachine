package edu.towson.cis.cosc442.project4.coffeemaker;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RecipeTest {

    @Test
    public void newRecipeHasDefaults(){
        Recipe recipe = new Recipe();

        assertNull(recipe.getName());
        assertNull(recipe.toString());
        assertEquals(0, recipe.getPrice());
        assertEquals(0, recipe.getAmtCoffee());
        assertEquals(0, recipe.getAmtMilk());
        assertEquals(0, recipe.getAmtSugar());
        assertEquals(0, recipe.getAmtChocolate());
    }

    //checks that name setter and toString both work as intended
    @Test
    public void nameSetterandtoStringTest(){
        Recipe recipe = new Recipe();

        recipe.setName("Latte");
        assertEquals("Latte", recipe.getName());
        assertEquals("Latte", recipe.toString());

        recipe.setName(null);
        assertNull(recipe.getName());
        assertNull(recipe.toString());
    }

    //checks to make sure that price setter handles positive, zero, and negative properly
    @ParameterizedTest
    @CsvSource({
        "20, 20",
        "0, 0",
        "-1, 0"
    })
    public void priceSetterTest(int amount, int expected){
        Recipe recipe = new Recipe();
        recipe.setPrice(amount);
        assertEquals(expected, recipe.getPrice());
    }
    //checks to make sure that price setter handles positive, zero, and negative properly
    @ParameterizedTest
    @CsvSource({
        "20, 20",
        "0, 0",
        "-1, 0"
    })
    public void coffeeSetterTest(int amount, int expected){
        Recipe recipe = new Recipe();
        recipe.setAmtCoffee(amount);
        assertEquals(expected, recipe.getAmtCoffee());
    }

    //checks to make sure that price setter handles positive, zero, and negative properly
    @ParameterizedTest
    @CsvSource({
        "20, 20",
        "0, 0",
        "-1, 0"
    })
    public void milkSetterTest(int amount, int expected){
        Recipe recipe = new Recipe();
        recipe.setAmtMilk(amount);
        assertEquals(expected, recipe.getAmtMilk());
    }

    //checks to make sure that price setter handles positive, zero, and negative properly
    @ParameterizedTest
    @CsvSource({
        "20, 20",
        "0, 0",
        "-1, 0"
    })
    public void sugarSetterTest(int amount, int expected){
        Recipe recipe = new Recipe();
        recipe.setAmtSugar(amount);
        assertEquals(expected, recipe.getAmtSugar());
    }

    //checks to make sure that price setter handles positive, zero, and negative properly
    @ParameterizedTest
    @CsvSource({
        "20, 20",
        "0, 0",
        "-1, 0"
    })
    public void chocolateSetterTest(int amount, int expected){
        Recipe recipe = new Recipe();
        recipe.setAmtChocolate(amount);
        assertEquals(expected, recipe.getAmtChocolate());
    }

    //checks recipe equality matching for names
    //figuring out the syntax for this was strange, to say the least
    @ParameterizedTest
    @CsvSource(value = {
        "Latte, Latte, true",
        "Latte, Mocha, false",
        "Latte, NULL, false",
        "NULL, Latte, false",
        "NULL, NULL, false"
    }, nullValues= "NULL")
    public void recipeEqualsTest(String name, String name2, boolean expected){
        Recipe recipe = new Recipe();
        Recipe recipe2 = new Recipe();

        recipe.setName(name);
        recipe2.setName(name2);

        //i added the setPrice to test that they will still be equal even if the price is different, or any other value
        recipe.setPrice(10);
        recipe2.setPrice(11);
        assertEquals(expected, recipe.equals(recipe2));
        assertEquals(expected, recipe2.equals(recipe));
    }
}
