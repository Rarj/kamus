package dev.arjuna.ri0.projetckamus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static final String ITEM_KATA = "item_kata";
    public static final String ITEM_TRANSLATE = "item_translate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvKata = findViewById(R.id.tvKata);
        TextView tvTranslate = findViewById(R.id.tvTranslate);

        tvKata.setText(getIntent().getStringExtra(ITEM_KATA));
        tvTranslate.setText(getIntent().getStringExtra(ITEM_TRANSLATE));
    }
}
