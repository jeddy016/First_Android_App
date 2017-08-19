package com.example.jordan.peoplelistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.name;

public class PersonFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_form);

        if(hasUpdateIntention()) {
            Person person = getPersonToUpdate();
            PersonFormViewHelper helper = new PersonFormViewHelper(this);
            helper.fillForm(person);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_person_form, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.person_form_save) {

            PersonFormViewHelper helper = new PersonFormViewHelper(this);
            Person person = helper.createPerson();

            PersonDAO dao = new PersonDAO(this);

            if(hasUpdateIntention()) {
                dao.update(person, getPersonToUpdate().getId());
            }else {
                dao.insert(person);
                dao.close();
            }
            String message = person.getName() + " was saved with rating: " + person.getRating();

            Toast.makeText(PersonFormActivity.this, message, Toast.LENGTH_SHORT).show();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private Person getPersonToUpdate() {
        Intent intent = getIntent();
        return (Person) intent.getSerializableExtra("person");
    }

    private boolean hasUpdateIntention() {
        return getIntent().hasExtra("person");
    }
}
