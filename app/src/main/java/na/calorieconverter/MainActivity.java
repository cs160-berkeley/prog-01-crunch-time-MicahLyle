package na.calorieconverter;

import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner activitySpinner;
    ArrayAdapter<CharSequence> activitiyAdapter;
    TextView userInputDescription;
    EditText valueInput;
    TextView calorieNumber;
    Spinner convertSpinner;
    ArrayAdapter<CharSequence> convertAdapter;
    TextView conversionText;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activitySpinner = (Spinner) this.findViewById(R.id.spinner);
        activitiyAdapter = ArrayAdapter.createFromResource(this, R.array.activity_names, android.R.layout.simple_spinner_item);
        activitiyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(activitiyAdapter);

        userInputDescription = (TextView) this.findViewById(R.id.activityChangingText);

        Resources res = getResources();
        final String[] allActivities = res.getStringArray(R.array.activity_names);

        final float[] conversionFactors = {350,200,225,100,25,25,10,12,20,12,13,15};

        activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
                String selectedActivity = (String) parent.getItemAtPosition(position);
                String grammarSelector = "";
                int i = 0;
                for (String strActivity : allActivities) {
                    if (selectedActivity.equals(strActivity)) {
                        if (i < 4) {
                            grammarSelector = "reps";
                        } else {
                            grammarSelector = "minutes";
                        }
                    }
                    i += 1;
                }
                userInputDescription.setText("How many " + grammarSelector + "?");

                String bottomActivity = convertSpinner.getSelectedItem().toString();
                if (Arrays.asList(allActivities).indexOf(bottomActivity) > 3) {
                    grammarSelector = "minutes";
                } else {
                    grammarSelector = "reps";
                }

                String stringActivityValue = valueInput.getText().toString();
                if (!(stringActivityValue.equals(""))) {
                    try {
                        float intActivityValue = Integer.parseInt(stringActivityValue);
                        String currentActivity = activitySpinner.getSelectedItem().toString();
                        String activityToConvertTo = convertSpinner.getSelectedItem().toString();
                        float convertAmount = 0;
                        int j = 0;
                        int k = 0;
                        for (String currString : allActivities) {
                            if (currString.equals(currentActivity)) {
                                convertAmount = (intActivityValue / conversionFactors[j]) * 100;
                            }
                            if (currString.equals(activityToConvertTo)) {
                                k = j;
                            }
                            j += 1;
                        }
                        convertAmount = convertAmount * (conversionFactors[k] / 100);
                        conversionText.setText("Equivalent to " + Math.round(convertAmount) + " " + grammarSelector + "!");

                    } catch (Exception e) {
                        conversionText.setText("Please input a valid number.");
                    }
                } else {
                    conversionText.setText("Equivalent to 0 " + grammarSelector + "!");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calorieNumber = (TextView) this.findViewById(R.id.calorieText);

        valueInput = (EditText) this.findViewById(R.id.activityUserInputDescriptionText);
        valueInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String stringActivityValue = valueInput.getText().toString();
                String currentActivity = activitySpinner.getSelectedItem().toString();
                String bottomActivity = convertSpinner.getSelectedItem().toString();
                String grammarSelector = "";
                if (Arrays.asList(allActivities).indexOf(bottomActivity) > 3) {
                    grammarSelector = "minutes";
                } else {
                    grammarSelector = "reps";
                }
                if (!(stringActivityValue.equals(""))) {
                    try {
                        float intActivityValue = Integer.parseInt(stringActivityValue);
                        float calorieAmount = 0;
                        int j = 0;
                        int h = 0;
                        for (String currString : allActivities) {
                            if (currString.equals(currentActivity)) {
                                calorieAmount = (intActivityValue / conversionFactors[j]) * 100;
                            }
                            if (currString.equals(bottomActivity)) {
                                h = j;
                            }
                            j += 1;
                        }
                        float convertAmount = calorieAmount * (conversionFactors[h] / 100);
                        calorieNumber.setText("You've Burned " + Math.round(calorieAmount) + " calories!");

                        conversionText.setText("Equivalent to " + Math.round(convertAmount) + " " + grammarSelector + "!");

                    } catch (Exception e) {
                        calorieNumber.setText("Please input a valid number.");
                    }
                } else {
                    calorieNumber.setText("You've Burned 0 calories!");
                    conversionText.setText("Equivalent to 0 " + grammarSelector + "!");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        convertSpinner = (Spinner) this.findViewById(R.id.convertActivitySpinner);
        convertAdapter = ArrayAdapter.createFromResource(this, R.array.activity_names, android.R.layout.simple_spinner_item);
        convertAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        convertSpinner.setAdapter(convertAdapter);

        conversionText = (TextView) this.findViewById(R.id.convertTextView);

        convertSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
                String selectedActivity = (String) parent.getItemAtPosition(position);
                String grammarSelector = "";
                int i = 0;
                for (String strActivity : allActivities) {
                    if (selectedActivity.equals(strActivity)) {
                        if (i < 4) {
                            grammarSelector = "reps";
                        } else {
                            grammarSelector = "minutes";
                        }
                    }
                    i += 1;
                }

                String stringActivityValue = valueInput.getText().toString();
                if (!(stringActivityValue.equals(""))) {
                    try {
                        float intActivityValue = Integer.parseInt(stringActivityValue);
                        String currentActivity = activitySpinner.getSelectedItem().toString();
                        String activityToConvertTo = convertSpinner.getSelectedItem().toString();
                        float convertAmount = 0;
                        int j = 0;
                        int k = 0;
                        for (String currString : allActivities) {
                            if (currString.equals(currentActivity)) {
                                convertAmount = (intActivityValue / conversionFactors[j]) * 100;
                            }
                            if (currString.equals(activityToConvertTo)) {
                                k = j;
                            }
                            j += 1;
                        }
                        convertAmount = convertAmount * (conversionFactors[k] / 100);
                        conversionText.setText("Equivalent to " + Math.round(convertAmount) + " " + grammarSelector + "!");

                    } catch (Exception e) {
                        conversionText.setText("Please input a valid number.");
                    }
                } else {
                    conversionText.setText("Equivalent to 0 " + grammarSelector + "!");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://na.calorieconverter/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://na.calorieconverter/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
