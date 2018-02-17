package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import com.udacity.sandwichclub.utils.ListUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView alsoKnownLabelTv = findViewById(R.id.also_known_label_tv);
        TextView alsoKnownTv = findViewById(R.id.also_known_tv);
        setTextOrHide(
                alsoKnownLabelTv,
                alsoKnownTv,
                ListUtils.join(sandwich.getAlsoKnownAs())
        );

        TextView originLabelTv = findViewById(R.id.origin_label_tv);
        TextView originTv = findViewById(R.id.origin_tv);
        setTextOrHide(
                originLabelTv,
                originTv,
                sandwich.getPlaceOfOrigin()
        );

        TextView descriptionLabelTv = findViewById(R.id.description_label_tv);
        TextView descriptionTv = findViewById(R.id.description_tv);
        setTextOrHide(
                descriptionLabelTv,
                descriptionTv,
                sandwich.getDescription()
        );

        TextView ingredientsLabelTv = findViewById(R.id.ingredients_label_tv);
        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        setTextOrHide(
                ingredientsLabelTv,
                ingredientsTv,
                ListUtils.join(sandwich.getIngredients())
        );
    }

    private void setTextOrHide(TextView label, TextView tv, String text) {
        if (text.isEmpty()) {
            label.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
        } else {
            label.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
        }
    }
}
