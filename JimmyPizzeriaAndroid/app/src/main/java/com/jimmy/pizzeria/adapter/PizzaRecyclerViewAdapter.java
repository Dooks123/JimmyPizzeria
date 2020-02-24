package com.jimmy.pizzeria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jimmy.pizzeria.R;
import com.jimmy.pizzeria.helpers.ResourceHelper;
import com.jimmy.pizzeria.helpers.TextHelper;
import com.jimmy.pizzeria.interfaces.SelectCheckListener;
import com.jimmy.pizzeria.interfaces.SelectInterface;
import com.jimmy.pizzeria.object.Pizza;
import com.jimmy.pizzeria.util.Utils;

import java.util.List;

public class PizzaRecyclerViewAdapter extends RecyclerView.Adapter<PizzaRecyclerViewAdapter.ViewHolder> implements SelectCheckListener {

    private List<Pizza> items;
    private LayoutInflater inflater;

    private SelectInterface selectInterface;

    public PizzaRecyclerViewAdapter(Context context, SelectInterface selectInterface) {
        this.selectInterface = selectInterface;
        this.inflater = LayoutInflater.from(context);
    }

    public void setItems(List<Pizza> items) {
        this.items = items;
    }

    public List<Pizza> getItems() {
        return items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getPizzaID();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pizza_item, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pizza pizza = items.get(position);
        boolean check = selectInterface.itemCheck(pizza);
        holder.set(pizza, check);
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        holder.recycle();
        super.onViewRecycled(holder);
    }

    @Override
    public boolean checked(Pizza pizza, boolean checked) {
        return selectInterface.itemSelected(pizza, checked);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SelectCheckListener listener;

        private ImageView imgPizza;
        private ImageView imgSelected;
        private TextView lblName;
        private TextView lblDescription;
        private TextView lblPrice;

        private Pizza pizza;
        private boolean check = false;

        ViewHolder(@NonNull View view, SelectCheckListener listener) {
            super(view);

            this.listener = listener;

            imgPizza = view.findViewById(R.id.imgPizza);
            imgSelected = view.findViewById(R.id.imgSelected);
            lblName = view.findViewById(R.id.lblName);
            lblDescription = view.findViewById(R.id.lblDescription);
            lblPrice = view.findViewById(R.id.lblPrice);

            view.setOnClickListener(v -> itemClick(false));
            view.setOnLongClickListener(v -> itemClick(true));
        }

        void set(Pizza pizza, boolean check) {
            this.pizza = pizza;
            this.check = check;

            Glide.with(imgPizza.getContext())
                    .load(pizza.getResourceID())
                    .thumbnail(0.01f)
                    .error(R.drawable.ic_no_pizza_img)
                    .override(ResourceHelper.getDP(70))
                    .into(imgPizza);

            lblName.setText(pizza.getName());
            lblDescription.setText(pizza.getDescription());
            lblPrice.setText(String.valueOf(pizza.getPrice()));

            imgSelected.setVisibility(check ? View.VISIBLE : View.GONE);
        }

        void recycle() {
            Glide.with(imgPizza.getContext()).clear(imgPizza);
            imgSelected.setVisibility(View.GONE);
        }

        private boolean itemClick(boolean longClick) {
            if (!longClick) {
                if (listener.checked(pizza, !check)) {
                    check = !check;
                    imgSelected.setVisibility(check ? View.VISIBLE : View.GONE);
                }
            } else {
                String positiveButton = check ? "Remove from order" : "Add to order";
                String pizzaDescription = pizza.getDescription()
                        + "<br/><br/><b>R " + pizza.getPrice() + "</b>";

                Utils.getAlert(itemView.getContext())
                        .setTitle(pizza.getName())
                        .setMessage(TextHelper.fromHtml(pizzaDescription))
                        .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                        .setPositiveButton(positiveButton, (dialog, which) -> {
                            if (listener.checked(pizza, !check)) {
                                check = !check;
                                imgSelected.setVisibility(check ? View.VISIBLE : View.GONE);
                            }
                        })
                        .show();
            }
            return true;
        }
    }
}
