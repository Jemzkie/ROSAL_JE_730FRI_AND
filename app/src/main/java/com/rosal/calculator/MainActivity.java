package com.rosal.calculator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set title and subtitle for the Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Main Title");
            getSupportActionBar().setSubtitle("Sub Title");
        }

        // Load the first fragment
        loadFragment(new FirstFragment());
    }

    void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);  // Inflate the menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_fragment) {
            loadFragment(new FirstFragment());
            return true;
        } else if (id == R.id.menu_dialog) {
            DialogFragment dialog = new MyDialogFragment();
            dialog.show(getSupportFragmentManager(), "MyDialog");
            return true;
        } else if (id == R.id.menu_exit) {
            finishAffinity();  // Exit the app
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}