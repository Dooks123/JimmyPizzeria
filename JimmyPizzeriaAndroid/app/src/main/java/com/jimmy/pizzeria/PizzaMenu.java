package com.jimmy.pizzeria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.jimmy.pizzeria.adapter.PizzaRecyclerViewAdapter;
import com.jimmy.pizzeria.helpers.PizzaHelper;
import com.jimmy.pizzeria.helpers.TextHelper;
import com.jimmy.pizzeria.helpers.WindowHelper;
import com.jimmy.pizzeria.interfaces.SelectInterface;
import com.jimmy.pizzeria.object.Pizza;
import com.jimmy.pizzeria.provider.PizzaContentProvider;
import com.jimmy.pizzeria.util.Constant;
import com.jimmy.pizzeria.util.CryptoUtils;
import com.jimmy.pizzeria.util.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PizzaMenu extends AppCompatActivity implements SelectInterface {

    private LinearLayout llRoot;
    private RecyclerView recyclerView;
    private LinearLayout btnBuy;
    private TextView lblBuy;

    private PizzaRecyclerViewAdapter adapter;

    private List<Pizza> selectedPizza = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowHelper.setWindow(getWindow());

        setContentView(R.layout.activity_pizza_menu);

        init();

        WindowHelper.setInsets(llRoot);

        setActionBarText();

        btnBuy.setOnClickListener(v -> {

            String description = "Your order total for " + selectedPizza.size() + " pizza" + (selectedPizza.size() == 1 ? "" : "s") + " is:";
            String total = "R #".replace("#", String.format(Locale.getDefault(), "%.2f", getSelectedTotalPrice()));

            Spanned message = TextHelper.fromHtml(description + "<br/><br/><b>" + total + "</b>");
            Utils.getAlert(this)
                    .setTitle("Order Details")
                    .setMessage(message)
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .setPositiveButton("Order", (dialog, which) -> {
                        String folder = Utils.getPublicFolder();
                        String fileName = Utils.getNewOrderFileName();

                        String json = new Gson().toJson(selectedPizza);

                        File outputFile = new File(folder + fileName);

                        if (CryptoUtils.encrypt("Pizza4lf", json, outputFile)) {
                            Intent i = new Intent(Intent.ACTION_SEND);
                            Uri uri = FileProvider.getUriForFile(PizzaMenu.this, PizzaContentProvider.ContentProviderAuthority, outputFile);
                            i.putExtra(Intent.EXTRA_STREAM, uri);
                            i.setType("application/octet-stream");
                            startActivityForResult(Intent.createChooser(i, "Share file to:"), Constant.REQUEST_SHARE_ORDER_FILE);
                        } else {
                            Utils.showToast(PizzaMenu.this, "Generating encrypted file failed.", false);
                        }
                    })
                    .show();
        });
    }

    private void init() {
        llRoot = findViewById(R.id.llRoot);
        recyclerView = findViewById(R.id.recyclerView);
        btnBuy = findViewById(R.id.btnBuy);
        lblBuy = findViewById(R.id.lblBuy);
    }

    private void setActionBarText() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Pizza Menu");

            String subTitle = "";
            if (selectedPizza.size() > 0) {
                String total = " (Total R #)".replace("#", String.format(Locale.getDefault(), "%.2f", getSelectedTotalPrice()));

                subTitle = selectedPizza.size() + " pizza" + (selectedPizza.size() == 1 ? "" : "s") + " selected" + total;

                btnBuy.setVisibility(View.VISIBLE);
                String buyButtonText = "Order Now" + total;
                lblBuy.setText(buyButtonText);
            } else {
                btnBuy.setVisibility(View.GONE);
            }
            actionBar.setSubtitle(subTitle);
        }
    }

    private void setList() {
        List<Pizza> pizzaList = PizzaHelper.getInstance().pizzaGetAll();

        if (adapter == null) {
            adapter = new PizzaRecyclerViewAdapter(this, this);
            adapter.setHasStableIds(true);
            adapter.setItems(pizzaList);

            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
        } else {
            adapter.setItems(pizzaList);
            adapter.notifyDataSetChanged();
        }
    }

    private double getSelectedTotalPrice() {
        double totalPrice = 0.0;
        for (Pizza pizza : selectedPizza) {
            totalPrice += pizza.getPrice();
        }
        return totalPrice;
    }

    @Override
    public boolean itemSelected(Pizza pizza, boolean checked) {
        if (!checked) {
            selectedPizza.remove(pizza);
        } else {
            selectedPizza.add(pizza);
        }
        setActionBarText();

        return true;
    }

    @Override
    public boolean itemCheck(Pizza pizza) {
        return selectedPizza.indexOf(pizza) > -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Clear Selection");
        menu.add("Select All");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getTitle().toString()) {
            case "Clear Selection": {
                if (selectedPizza.size() > 0) {
                    Utils.showToast(this, "Cleared selected pizzas", true);

                    selectedPizza.clear();
                    adapter.notifyDataSetChanged();

                    setActionBarText();
                }
                break;
            }
            case "Select All": {
                Utils.showToast(this, "All pizzas selected", true);

                selectedPizza.clear();
                selectedPizza.addAll(adapter.getItems());
                adapter.notifyDataSetChanged();

                setActionBarText();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_SHARE_ORDER_FILE) {
            if (resultCode == RESULT_OK) {
                Utils.showToast(PizzaMenu.this, "Order file saved successfully", false);

                selectedPizza.clear();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(0);
                }
                setActionBarText();
            } else {
                Utils.showToast(PizzaMenu.this, "Order file not saved", true);
            }
        }
    }
}
