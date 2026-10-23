package edu.towson.cis.cosc442.project4.coffeemaker;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    public void setUp(){
        inventory = new Inventory();
    }

    //helper to initialize recipes quicker
    private Recipe recipe(int coffee, int milk, int sugar, int chocolate){
        Recipe r = new Recipe();
        r.setAmtCoffee(coffee);
        r.setAmtMilk(milk);
        r.setAmtSugar(sugar);
        r.setAmtChocolate(chocolate);
        return r;
    }

    //checks that inventory starts w/ 15 of everything
    @Test
    public void constructorSetsAllTo15(){
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());
        assertEquals(15, inventory.getChocolate());
    }

    //checks coffee setter for positive, zero, and negative values
    @ParameterizedTest
    @CsvSource({"21, 21", "0, 0", "-1, 0"})
    public void coffeeSetterTest(int amount, int expected){
        inventory.setCoffee(amount);
        assertEquals(expected, inventory.getCoffee());
    }

    //checks milk setter for positive, zero, and negative values
    @ParameterizedTest
    @CsvSource({"21, 21", "0, 0", "-1, 0"})
    public void milkSetterTest(int amount, int expected){
        inventory.setMilk(amount);
        assertEquals(expected, inventory.getMilk());
    }

    //checks sugar setter for positive, zero, and negative values
    @ParameterizedTest
    @CsvSource({"21, 21", "0, 0", "-1, 0"})
    public void sugarSetterTest(int amount, int expected){
        inventory.setSugar(amount);
        assertEquals(expected, inventory.getSugar());
    }

    //checks chocolate setter for positive, zero, and negative values
    @ParameterizedTest
    @CsvSource({"21, 21", "0, 0", "-1, 0"})
    public void chocolateSetterTest(int amount, int expected){
        inventory.setChocolate(amount);
        assertEquals(expected, inventory.getChocolate());
    }

    //checks to make sure inventory has correct amount of ingredients for different recipes
    @ParameterizedTest
    @CsvSource({
        "1, 2, 3, 4, true",
        "15, 15, 15, 15, true",
        "16, 15, 15, 15, false",
        "15, 16, 15, 15, false",
        "15, 15, 16, 15, false",
        "15, 15, 15, 16, false",
        "16, 16, 16, 16, false"
    })
    public void enoughIngredientsTest(int coffee, int milk, int sugar, int chocolate, boolean expected){
        Recipe recipe = recipe(coffee, milk, sugar, chocolate);
        assertEquals(expected, inventory.enoughIngredients(recipe));
    }

    //checks to make sure that the toString method returns properly
    @Test
    public void toStringTest(){
        inventory.setCoffee(10);
        inventory.setMilk(3);
        inventory.setSugar(2);
        inventory.setChocolate(7);
        assertEquals("Coffee: 10\nMilk: 3\nSugar: 2\nChocolate: 7\n", inventory.toString());
    }
}
