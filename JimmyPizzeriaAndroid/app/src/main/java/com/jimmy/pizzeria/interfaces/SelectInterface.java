package com.jimmy.pizzeria.interfaces;

import com.jimmy.pizzeria.object.Pizza;

public interface SelectInterface {
    boolean itemSelected(Pizza pizza, boolean checked);

    boolean itemCheck(Pizza pizza);
}
