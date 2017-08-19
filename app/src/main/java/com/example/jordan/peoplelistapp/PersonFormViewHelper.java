package com.example.jordan.peoplelistapp;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RatingBar;

class PersonFormViewHelper {
    private final Activity activity;

    PersonFormViewHelper(Activity activity){
        this.activity = activity;
    }

    private String getName() {
        return getFieldValue(R.id.person_form_name);
    }

    private String getAddress() {
        return getFieldValue(R.id.person_form_address);
    }

    private String getEmail() {
        return getFieldValue(R.id.person_form_email);
    }

    private String getPhone() {
        return getFieldValue(R.id.person_form_phone);
    }

    private String getWebsite() {
        return getFieldValue(R.id.person_form_website);
    }

    private String getFieldValue(int fieldID){
        EditText field = activity.findViewById(fieldID);
        return field.getText().toString();
    }

    private double getRate() {
        RatingBar bar = activity.findViewById(R.id.person_form_rating);
        return bar.getRating();
    }

    Person createPerson(){
        return new Person(getName(), getAddress(), getEmail(), getPhone(), getWebsite(), getRate());
    }

    public void fillForm(Person person) {
        fill(R.id.person_form_name, person.getName());
        fill(R.id.person_form_address, person.getAddress());
        fill(R.id.person_form_email, person.getEmail());
        fill(R.id.person_form_phone, person.getPhone());
        fill(R.id.person_form_website, person.getWebsite());

        RatingBar bar = activity.findViewById(R.id.person_form_rating);
        bar.setRating((float) person.getRating());
    }

    private void fill(int id, String value) {
        EditText field = (EditText) activity.findViewById(id);
        field.setText(value);
    }
}
