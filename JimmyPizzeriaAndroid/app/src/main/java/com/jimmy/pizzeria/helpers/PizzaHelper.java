package com.jimmy.pizzeria.helpers;

import com.jimmy.pizzeria.R;
import com.jimmy.pizzeria.object.Pizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaHelper {

    private List<Pizza> pizzaList;

    private static PizzaHelper instance;

    public static PizzaHelper getInstance() {
        if (instance == null) {
            instance = new PizzaHelper();
        }
        return instance;
    }

    private PizzaHelper() {
        pizzaList = new ArrayList<>();

        //R39.99 Pizzas
        pizzaList.add(new Pizza(1, "BEEF 'N CHEESE (NEW)", "Tomato base, mozzarella cheese & cubed pressed beef", R.drawable.pizza_beef_n_cheese, 39.99));
        pizzaList.add(new Pizza(2, "HAWAIIAN", "Tomato base, ham & pineapple", R.drawable.pizza_hawaiian_pan, 39.99));
        pizzaList.add(new Pizza(3, "REGINA", "Tomato base, ham & mushroom", R.drawable.pizza_regina_pan, 39.99));
        pizzaList.add(new Pizza(4, "PEPPERONI DELUXE", "Tomato base, pepperoni & garlic", R.drawable.pizza_pepperoni_deluxe_pan, 39.99));
        pizzaList.add(new Pizza(5, "TROPICAL", "Tomato base, bacon & pineapple", R.drawable.pizza_tropical_pan, 39.99));
        pizzaList.add(new Pizza(6, "VEGETARIAN", "Tomato base, mushroom, green pepper, onion & tomato chunks", R.drawable.pizza_vegetarian_pan, 39.99));
        pizzaList.add(new Pizza(7, "BBQ CHICKELICIOUS", "Barbeque base & chicken", R.drawable.pizza_bbq_chicken_pan, 39.99));
        pizzaList.add(new Pizza(8, "TRIPLE CHEESE", "Tomato base, cheddar cheese, mozzarella cheese & feta cheese", R.drawable.pizza_triple_cheese_pan, 39.99));

        //R29.99 Pizzas
        pizzaList.add(new Pizza(9, "CLASSIC CHEESE", "Tomato base & mozzarella cheese", R.drawable.pizza_classic_pan, 29.99));
        pizzaList.add(new Pizza(10, "MARGHERITA", "Tomato base, olive oil, origanum & garlic", R.drawable.pizza_margherita_pan, 29.99));

        //R49.99 Pizzas
        pizzaList.add(new Pizza(11, "GREEK", "Tomato base, feta cheese & olives", R.drawable.pizza_greek_olives_pan, 49.99));
        pizzaList.add(new Pizza(12, "TANGY RUSSIAN", "Chutney base, russians & caramelised onion", R.drawable.pizza_tangy_russian_pan, 49.99));
        pizzaList.add(new Pizza(13, "BACON SUPREME", "Tomato base, bacon, mushroom, green pepper & onion", R.drawable.pizza_bacon_supreme_pan, 49.99));
        pizzaList.add(new Pizza(14, "HOT ONE", "Chilli base, green pepper, cubed pressed beef & onion", R.drawable.pizza_hotone_pan, 49.99));
        pizzaList.add(new Pizza(15, "BOLOGNAISE (JALAPEÑO OPTIONAL)", "Bolognaise mince, green pepper & onion (JALAPEÑO OPTIONAL)", R.drawable.pizza_bolognaise_pan, 49.99));

        //R59.99 Pizzas
        pizzaList.add(new Pizza(16, "FETARONI", "Tomato base, feta cheese, pepperoni & fresh green chilli", R.drawable.pizza_fetaroni_pan, 59.99));
        pizzaList.add(new Pizza(17, "SUPREME", "Tomato base, pepperoni, pressed beef, mushroom, green pepper & onion", R.drawable.pizza_supreme_pan, 59.99));
        pizzaList.add(new Pizza(18, "FOUR IN ONE (NOTHING BUT NYAMA)", "Tomato base, pepperoni, ham, cubed pressed beef & bacon", R.drawable.pizza_fourinone_pan, 59.99));
        pizzaList.add(new Pizza(19, "BBQ CHICKEN & MUSHROOM", "Barbeque base, chicken & mushroom", R.drawable.pizza_bbqchickmush_pan, 59.99));
        pizzaList.add(new Pizza(20, "BBQ CHICKEN & PINEAPPLE", "Barbeque base, chicken & pineapple", R.drawable.pizza_bbqchickpine_pan, 59.99));
        pizzaList.add(new Pizza(21, "BBQ CHICKEN SUPREME", "Barbeque base, chicken, onion & green pepper", R.drawable.pizza_bbqchicksup_pan, 59.99));
        pizzaList.add(new Pizza(22, "PERI-PERI CHICKEN (NEW RECIPE)", "Peri-peri base, chicken, green pepper, onion & tomato chunks", R.drawable.pizza_perichick_pan, 59.99));

        //R69.99 Pizzas
        pizzaList.add(new Pizza(23, "CHICK 'N MAYO (BACON)", "Mayo base, chicken & bacon", R.drawable.pizza_chickmayobacon_pan, 69.99));
        pizzaList.add(new Pizza(24, "CHICK 'N MAYO (FETA)", "Mayo base, chicken & feta cheese", R.drawable.pizza_chickmayofeta_pan, 69.99));
        pizzaList.add(new Pizza(25, "BBQ SPARE RIB & MUSHROOM", "Smokey barbeque base, spare rib & mushroom", R.drawable.pizza_bbqribsmush_pan, 69.99));
        pizzaList.add(new Pizza(26, "BBQ SPARE RIB & PINEAPPLE", "Smokey barbeque base, spare rib & pineapple", R.drawable.pizza_bbqribspine_pan, 69.99));
        pizzaList.add(new Pizza(27, "SEAFOOD", "Tomato base, mussels, shrimp, calamari, garlic, herbs & olive oil", R.drawable.pizza_seafood_pan, 69.99));
        pizzaList.add(new Pizza(28, "QUATTRO", "Tomato base, mushroom, ham, anchovy & olives", R.drawable.pizza_quattro_pan, 69.99));
        pizzaList.add(new Pizza(29, "SWEET CHILLI CHICKEN", "Sweet chilli base, chicken, piquanté peppers & feta cheese", R.drawable.pizza_sweetchilli_chicken_pan, 69.99));
        pizzaList.add(new Pizza(30, "BACON, AVO & FETA", "Tomato base, bacon, avocado & feta cheese", R.drawable.pizza_baconavofeta_pan, 69.99));
    }

    public List<Pizza> pizzaGetAll() {
        return pizzaList;
    }
}