package com.ebe.maverick;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.provider.Settings;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Settings.Secure;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends Activity {

    Set<String> SourceSet = new TreeSet<String>();
    DatabaseHandler db;
    Button Save;
    Spinner State;
    Spinner ContactMethod;
    EditText LastName;
    EditText FirstName;
    EditText MiddleName;
    EditText SocialSecurityNumber;
    EditText DoB;
    EditText Address;
    EditText City;
    EditText Zip;
    EditText HomePhone;
    EditText CellPhone;
    EditText OtherPhone;
    EditText Email1;
    EditText Email2;
    EditText Email3;
    EditText Fax;
    private String androidID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //display the view
        setContentView(R.layout.activity_main);

        //setup and link to all controls on page
        Save = (Button)findViewById(R.id.Save);
        State = (Spinner)findViewById(R.id.StateDropDownBox);
        ContactMethod = (Spinner)findViewById(R.id.ContactMethodDropDownBox);
        LastName = (EditText)findViewById(R.id.LastNameText);
        FirstName = (EditText)findViewById(R.id.FirstNameText);
        MiddleName = (EditText)findViewById(R.id.MiddleNameText);
        SocialSecurityNumber = (EditText)findViewById(R.id.SSNText);
        DoB = (EditText)findViewById(R.id.DOBText);
        Address = (EditText)findViewById(R.id.AddressText);
        City = (EditText)findViewById(R.id.CityText);
        Zip = (EditText)findViewById(R.id.ZipCodeText);
        HomePhone = (EditText)findViewById(R.id.PhoneText);
        CellPhone = (EditText)findViewById(R.id.CellText);
        OtherPhone = (EditText)findViewById(R.id.OtherPhoneText);
        Email1 = (EditText)findViewById(R.id.EMailText);
        Email2 = (EditText)findViewById(R.id.EMailTwoText);
        Email3 = (EditText)findViewById(R.id.EMailThreeText);
        Fax = (EditText)findViewById(R.id.FaxText);
        db = new DatabaseHandler(this);





        //setup drop downs
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this,
                R.array.stateAbbreviationList, android.R.layout.simple_spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        State.setAdapter(stateAdapter);

        ArrayAdapter<CharSequence> contactMethodAdapter = ArrayAdapter.createFromResource(this,
                R.array.contactMethods, android.R.layout.simple_spinner_item);
        contactMethodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ContactMethod.setAdapter(contactMethodAdapter);



        //JSON Receive Initial
        if(isConnected("@string/InitialURL")){
            //receive JSON

        }



        //Save Button Click
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()){
                    shortformSendApplicant applicant = new shortformSendApplicant();
                    applicant.setLName(LastName.getText().toString());
                    applicant.setFName(FirstName.getText().toString());
                    applicant.setMName(MiddleName.getText().toString());
                    applicant.setSSN(SocialSecurityNumber.getText().toString());
                    applicant.setDoB(DoB.getText().toString());
                    applicant.setAddr(Address.getText().toString());
                    applicant.setCity(City.getText().toString());
                    applicant.setState(State.getSelectedItem().toString());
                    applicant.setZip(Zip.getText().toString());
                    applicant.setHomePhone(HomePhone.getText().toString());
                    applicant.setCellPhone(CellPhone.getText().toString());
                    applicant.setOtherPhone(OtherPhone.getText().toString());
                    applicant.setEmail(Email1.getText().toString());
                    applicant.setEmail2(Email2.getText().toString());
                    applicant.setEmail3(Email3.getText().toString());
                    applicant.setFax(Fax.getText().toString());
                    applicant.setCellPhone(ContactMethod.getSelectedItem().toString());

                    db.addApplicant(applicant);
                    db.close();
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.action_settings:
                showConfigDialog();

            case R.id.action_Export:
                showSaveDialog();
        }
        return true;
    }

    void showConfigDialog() {
        FragmentManager fm = getFragmentManager();
        ConfigDialogFragment test = new ConfigDialogFragment();
        test.setRetainInstance(true);
        test.show(fm,"fragment_name");
    }

    void showSaveDialog(){

        String saveLocation;
        try{
            saveLocation = getDataDir();
        }
        catch (Exception e){
            saveLocation ="Unable to get base save directory";
        }
        showToast(saveLocation);
    }

    public void showToast(String message){
        Toast toast = Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG);
        toast.show();
    }

    public String getDataDir() throws Exception{
        return getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0).applicationInfo.dataDir;
    }

    public boolean isConnected(String WebAddress){
        try{
            ConnectivityManager cm = (ConnectivityManager) getSystemService
                    (Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isConnected())
            {
                //Network is available but check if we can get access from the network.
                URL url = new URL(WebAddress);
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(2000); // Timeout 2 seconds.
                urlc.connect();

                if (urlc.getResponseCode() == 200)  //Successful response.
                {
                    return true;
                }
                else
                {
                    Log.d("NO INTERNET", "NO INTERNET");
                    return false;
                }
            }
        }
        catch(Exception e)
        {
            return false;
        }
        return false;
    }

    public boolean validateFields(){
        if(LastName.getText().toString() != ""){
            if(FirstName.getText().toString() != ""){
                if(SocialSecurityNumber.getText().length() == 11){
                    if(DoB.getText().length() == 10){
                        if(Address.getText().length() >= 5){
                            if(City.getText().toString() != ""){
                                if(State.getSelectedItemPosition() != 0){
                                    if(Zip.getText().length() == 5){
                                        if(HomePhone.getText().length() ==12 ||
                                                CellPhone.getText().length() == 12 ||
                                                OtherPhone.getText().length() == 12){
                                            if(Email1.getText().toString().equals("") ||
                                                    (Email1.getText().toString().contains("@") &&
                                                    Email1.getText().toString().contains("."))){
                                                if(Email2.getText().toString().equals("") ||
                                                        (Email2.getText().toString().contains("@") &&
                                                        Email2.getText().toString().contains("."))){
                                                    if(Email3.getText().toString().equals("") ||
                                                            (Email3.getText().toString().contains("@") &&
                                                            Email3.getText().toString().contains("."))){
                                                        if(Fax.getText().length() == 0 ||
                                                                Fax.getText().length() ==14){
                                                            if(validateContactMethod(ContactMethod.getSelectedItemPosition())){
                                                                return true;
                                                            }else showToast("Please Select a Valid Contact Method");
                                                        }else showToast("Please Enter a Valid Fax");
                                                    }else showToast("Please Enter a Valid Email in Email 3");
                                                }else showToast("Please Enter a Valid Email  in Email 2");
                                            }else showToast("Please Enter a Valid Email in Email");
                                        }else showToast("Please Enter a Valid Phone Number");
                                    }else showToast("Please Enter a Valid Zip");
                                }else showToast("Please Select a State");
                            }else showToast("Please Enter a Valid City");
                        }else showToast("Please Enter a Valid Address");
                    }else showToast("Please Enter a Valid Date of Birth");
                }else showToast("Please Enter a Valid Social Security Number");
            }else showToast("First Name Must Not be Blank");
        }else showToast("Last Name Must Not be Blank");
        return false;
    }

    public boolean validateContactMethod(int position){
        switch (position)
        {
            case 0: return false;
            case 1:
                if(HomePhone.getText().length() == 12) return true;
                else return false;
            case 2:
                if(CellPhone.getText().length() == 12) return true;
                else return false;
            case 3:
                if(OtherPhone.getText().length() == 12) return true;
                else return false;
            case 4:
                if(Email1.getText().toString() == "" ||
                        (Email1.getText().toString().contains("@") &&
                                Email1.getText().toString().contains("."))) return true;
                else return false;
            case 5:
                if(Email2.getText().toString() == "" ||
                        (Email2.getText().toString().contains("@") &&
                                Email2.getText().toString().contains("."))) return true;
                else return false;
            case 6:
                if(Email3.getText().toString() == "" ||
                        (Email3.getText().toString().contains("@") &&
                                Email3.getText().toString().contains("."))) return true;
                else return false;
            default: return false;
        }
    }

    public shortformSend getFullPacket(String androidMachineID) {
        shortformSend fullPacket = new shortformSend(androidMachineID);

        try{
            //assigns the next available batch number to all unassigned applicants
            if(db.assignBatchNum()){

                //get list of unsubmitted batches
                ArrayList<Integer> unSubmitted = db.getUnsubmittedBatches();

                //Doesn't do any send if no batches are available to send
                    if(unSubmitted.size() >= 1){
                        for(Integer iterator : unSubmitted){
                            fullPacket.addBatch(db.getBatch(iterator));
                        }
                    }

            }
        }
        //exception thrown if any errors occur
        catch (Exception e){
            showToast(e.toString());
        }

        return fullPacket;
    }


    /*Dialog Fragment Extension Class called ConfigDialogFragment
    This is the interface and handler for the configuration menu
    Which contains the administrative settings and the manual sync
     */

    public class ConfigDialogFragment extends DialogFragment {

        EditText adminPword;
        EditText URL;
        TextView SourceText;
        Spinner Source;
        Button Sync;
        Button ClearDB;
        Button adminLoginBtn;
        Button closebtn;
        boolean enable;
        SharedPreferences.Editor editor;
        SharedPreferences pref;

        public ConfigDialogFragment(){}

        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
        {

            //Initialize all the variables
            View view = inflater.inflate(R.layout.activity_config,container);
            enable = false;
            adminPword = (EditText)view.findViewById(R.id.PasswordText);
            URL = (EditText)view.findViewById(R.id.URLText);
            SourceText = (TextView)view.findViewById(R.id.SourceText);
            Source = (Spinner)view.findViewById(R.id.RecruitersSpinner);
            Sync = (Button)view.findViewById(R.id.SyncButton);
            ClearDB = (Button)view.findViewById(R.id.Clear_DB);
            adminLoginBtn = (Button)view.findViewById(R.id.AdminLogin);
            closebtn = (Button)view.findViewById(R.id.Close);
            editor = getPreferences(MODE_PRIVATE).edit();
            pref = getPreferences(MODE_PRIVATE);



            Set<String> DefaultSet = new TreeSet<String>();
            DefaultSet.add("No Sources Loaded");

            URL.setText(pref.getString("URL","@string/WebServiceURL"));
            SourceText.setText(pref.getString("SourceText","No Source"));
            SourceSet = pref.getStringSet("SourceSet",DefaultSet);

            ArrayList<String> SourceArray = new ArrayList<String>();
            SourceArray.addAll(DefaultSet);

            ArrayAdapter<String> SourceAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,SourceArray);
            SourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Source.setAdapter(SourceAdapter);



            Source.setSelection(0);
            Source.setSelected(false);

            URL.setEnabled(false);
            Source.setEnabled(false);

            //Save & Close button clicked
            closebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(enable){
                        //if the values were changed the values are saved
                        editor.putString("URL",URL.getText().toString());
                        if(Source.getSelectedItemPosition() != 0){
                            editor.putString("Source",Source.getSelectedItem().toString());
                        }
                        //commit the changes to persistent storage
                        editor.commit();
                    }
                    //close the fragment
                    dismiss();
                }
            });

            //Admin Login Button Click Method
            adminLoginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Enable is set to true if the password is correct
                    enable = AdminLogin(adminPword.getText().toString());
                    //If password is correct, the controls are enabled, if not they are disabled.
                    URL.setEnabled(enable);
                    Source.setEnabled(enable);
                    Sync.setClickable(enable);
                    ClearDB.setClickable(enable);
                    adminPword.setText("");
                }
            });

            //Sync Button Clicked
            Sync.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Set the Machine Id
                    androidID = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                            Settings.Secure.ANDROID_ID);

                }
            });

            ClearDB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    //Yes button clicked
                                    db.clearSubmitted();
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    //Do Nothing
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setMessage("Are you sure You want to clear the Submitted Entries from the device's DB? \n" +
                            "This will not effect the unsubmitted entries or the web database.")
                            .setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();

                }
            });

            return view;
        }

        boolean AdminLogin(String text){
            //If the user has entered the correct password then the form becomes editable
            String password = getResources().getString(R.string.derPassword);
            if(text.equals(password))
                return true;
                //If the password is incorrect or on program start up the form is not editable
            else
                return false;
        }
    }

}
