package com.example.jordan.peoplelistapp;

import android.content.Intent;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class PeopleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);

        loadPeople();

        registerForContextMenu(getPeopleList());

        getPeopleList().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> peopleList, View view, int pos, long l) {
                Person person = (Person) peopleList.getItemAtPosition(pos);
                Intent intent = new Intent(PeopleListActivity.this, PersonFormActivity.class);
                intent.putExtra("person", person);
                startActivity(intent);
            }
        });

        Button addButton = (Button) findViewById(R.id.people_list_add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PeopleListActivity.this, PersonFormActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPeople();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.equals(getPeopleList())) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            final Person person = (Person) getPeopleList().getItemAtPosition(info.position);
            CreateContextMenuForPerson(menu, person);
        }
    }

    private void CreateContextMenuForPerson(ContextMenu menu, final Person person) {
        MenuItem remove = menu.add("Remove");
        remove.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                PersonDAO dao = new PersonDAO(PeopleListActivity.this);
                dao.remove(person);
                dao.close();

                loadPeople();

                Toast.makeText(PeopleListActivity.this, person.getName() + " was removed", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private ListView getPeopleList() {
        return (ListView) findViewById(R.id.people_list_list);
    }

    private List<Person> loadPeople() {
        PersonDAO dao = new PersonDAO(this);
        List<Person> people = dao.listAll();
        dao.close();

        ArrayAdapter<Person> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, people);
        getPeopleList().setAdapter(adapter);

        return people;
    }
}
