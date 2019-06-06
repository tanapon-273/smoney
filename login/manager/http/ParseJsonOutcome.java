package com.example.kittisaks.login.manager.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tanapons on 5/4/2559.
 */
public class ParseJsonOutcome {
    public static String[] id;
    public static String[] date;
    public static String[] outcome;
    public static String[] monoutcome;
    public static String[] note;
    public static final String GET_DATE_Outcome = "http://wazos.esy.es/hostdroid/select_user_outcome.php?username=";
    public static final String URL_GET_Outcome = "http://wazos.esy.es/hostdroid/getEmp_outcome.php?id=";
    public static final String URL_UPDATE_Outcome = "http://wazos.esy.es/hostdroid/update_outcome.php";
    public static final String URL_DELETE_Outcome = "http://wazos.esy.es/hostdroid/delete_outcome.php?id=";
    public static final String TAG_JSON_ARRAY_Outcome ="result";

    public static final String JSON_ARRAY = "response";
    public static final String ID = "id";
    public static final String Date = "Date";
    public static final String Outcomes = "outcome";
    public static final String Monoutcome = "monoutcome";
    public static final String Note = "Note";

    private JSONArray Outcome = null;

    private String json;

    public ParseJsonOutcome(String json){
        this.json = json;
    }
    public void ParseJsonOutcome(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            Outcome = jsonObject.getJSONArray(JSON_ARRAY);
            id = new String[Outcome.length()];
            date = new String[Outcome.length()];
            outcome = new String[Outcome.length()];
            monoutcome = new String[Outcome.length()];
            note = new String[Outcome.length()];

            for(int i=0;i<Outcome.length();i++){
                JSONObject jo = Outcome.getJSONObject(i);
                id[i] = jo.getString(ID);
                date[i] = jo.getString(Date);
                outcome[i] = jo.getString(Outcomes);
                monoutcome[i] = jo.getString(Monoutcome);
                note[i] = jo.getString(Note);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
