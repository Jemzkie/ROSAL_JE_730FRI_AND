package com.rosal.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ListItem> items;
    private ListView listView;
    private EditText editText;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);

        // Initialize the item list
        items = new ArrayList<>();
        adapter = new CustomAdapter();
        listView.setAdapter(adapter);

        // Add new item when user enters text
        editText.setOnEditorActionListener((v, actionId, event) -> {
            String text = editText.getText().toString();
            if (!text.isEmpty()) {
                // Add a new item with a default image and unchecked checkbox
                items.add(new ListItem(text, false, R.drawable.catcheeks));
                adapter.notifyDataSetChanged();
                editText.setText(""); // Clear input field
            }
            return true;
        });
    }

    // ListItem class representing each list item
    public class ListItem {
        String text;
        boolean checked;
        int imageResource;

        public ListItem(String text, boolean checked, int imageResource) {
            this.text = text;
            this.checked = checked;
            this.imageResource = imageResource;
        }
    }

    // Custom adapter class for the ListView
    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, android.view.ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            }

            // Get the list item for this position
            ListItem item = items.get(position);

            // Set up the checkbox, text, and image
            CheckBox checkBox = convertView.findViewById(R.id.checkBox);
            checkBox.setChecked(item.checked);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> item.checked = isChecked);

            TextView textView = convertView.findViewById(R.id.textView);
            textView.setText(item.text);

            ImageView imageView = convertView.findViewById(R.id.imageView);
            imageView.setImageResource(item.imageResource);

            return convertView;
        }
    }
}