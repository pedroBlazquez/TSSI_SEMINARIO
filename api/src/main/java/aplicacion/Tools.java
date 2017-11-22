package aplicacion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.web.JsonProjectingMethodInterceptorFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import conexion.Conexion;
import modelos.Evento;
import negocio.CompartirNegocio;
import negocio.LikeNegocio;

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
    
    public static String DateFormatter (Date date)
    {
        return format.format(date);
        
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
    
    public static Date GetDateDifference(int days_difference)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -days_difference);
        return cal.getTime();
    }
    
    public static JSONObject convertObj_toJSON(Object obj) throws JsonProcessingException, JSONException
    {
        if(obj == null)
            return null;
        ObjectMapper om = new ObjectMapper();
       // om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(obj);
        return new JSONObject(json);
    }   
    public static List<JSONObject> convertList_toListJSON(List<?> obj) throws JsonProcessingException, JSONException
    {
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(Object o : obj)
        {
            JSONObject jobj = Tools.convertObj_toJSON(o);
            list.add(jobj);
        }
        return list;
    }
    public static JSONArray convertList_toJSON(List<?> obj) throws JsonProcessingException, JSONException
    {
        if(obj == null)
            return null;
        ObjectMapper om = new ObjectMapper();
       // om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(obj);
        return new JSONArray(json);
    }   
    
    public static void addToQuery(String query, String new_condition)
    {
        if(query != "")
            query += " and ";
        query+= " "+new_condition+" ";
    }
}
