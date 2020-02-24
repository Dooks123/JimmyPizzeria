package com.jimmy.pizzeria.interfaces;

import com.jimmy.pizzeria.object.Pizza;

public interface SelectCheckListener {
    boolean checked(Pizza pizza, boolean checked);
}