package com.example.kittisaks.login.manager.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tanapons on 4/4/2559.
 */
public class ParseJsonIncome {
    public static String[] id;
    public static String[] username;
    public static String[] date;
    public static String[] income;
    public static String[] monincome;
    public static String[] note;
    public static final String GET_DATE_Income = "http://wazos.esy.es/hostdroid/select_user_income.php?username=";
    public static final String URL_GET_Income = "http://wazos.esy.es/hostdroid/getEmp.php?id=";
    public static final String URL_UPDATE_Income = "http://wazos.esy.es/hostdroid/updateEmp.php";
    public static final String URL_DELETE_Income = "http://wazos.esy.es/hostdroid/delete_income.php?id=";
    public static final String TAG_JSON_ARRAY_Income ="result";

    public static final String JSON_ARRAY = "response";
    public static final String ID = "id";
    public static final String Username = "username";
    public static final String Date = "Date";
    public static final String Incomes = "income";
    public static final String Monincome = "monincome";
    public static final String Note = "Note";

    private JSONArray Income = null;

    private String json;

    public ParseJsonIncome(String json){
        this.json = json;
    }



    public ArrayList<HashMap<String, String>> ParseJsonIncome(){

        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            Income = jsonObject.getJSONArray(JSON_ARRAY);
            id = new String[Income.length()];
            username = new String[Income.length()];
            date = new String[Income.length()];
            income = new String[Income.length()];
            monincome = new String[Income.length()];
            note = new String[Income.length()];

            for(int i=0;i<Income.length();i++){
                JSONObject jo = Income.getJSONObject(i);
                id[i] = jo.getString(ID);
                username[i] = jo.getString(Username);
                date[i] = jo.getString(Date);
                income[i] = jo.getString(Incomes);
                monincome[i] = jo.getString(Monincome);
                note[i] = jo.getString(Note);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
