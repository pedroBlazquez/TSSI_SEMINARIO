package aplicacion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tools {
    
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public static Date DateFormatter (String date)
    {
        try {
            return format.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }
    
    public static ArrayList<String> Convert_jsonArray_toArrayString (JSONArray jsonArray ) throws JSONException
    {
        ArrayList<String> list = new ArrayList<String>(); 
        if (jsonArray != null) { 
           int len = jsonArray.length();
           for (int i=0;i<len;i++){ 
            list.add(jsonArray.get(i).toString());
           } 
        } 
        return list;
    }
    
    public static <T> String Convert_Array_toStringComma (ArrayList<T> list )
    {
        String res = "";
        if (!list.isEmpty()) {
            StringBuilder Builder = new StringBuilder();
            for (T item : list) {
                Builder.append(item).append(",");
            }
            Builder.deleteCharAt(Builder.length() - 1);
            res = Builder.toString();
        }
        return res;
    }
}
