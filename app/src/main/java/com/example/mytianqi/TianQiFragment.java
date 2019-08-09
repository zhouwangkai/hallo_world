package com.example.mytianqi;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.view.View.*;

public class TianQiFragment extends Fragment {
    List<Data> lists;
    String[] time;
    String[] Temp;
    String[] day;
    String[] weekday;
    String[] temp;
    int i;
    TextView textView;
    String string;
    ListView listView;
    List<Map<String, Object>> Week;
    String WEEK;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tianqi_fragment, container, false);
        time = new String[]{"1点", "3点"};
        Temp = new String[]{"10", "13"};
        day = new String[]{"8/8", "8/9"};
        weekday = new String[]{"周四", "周五"};
        temp = new String[]{"26~32", "27~32"};
        Week = new ArrayList<>();
        for (i = 0; i < day.length; i++) {
            Map<String, Object> week = new HashMap<>();
            week.put("day", day[i]);
            week.put("weekday", weekday[i]);
            week.put("temp", temp[i]);
            Week.add(week);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), Week, R.layout.days_main,
                new String[]{"day", "weekday", "temp"}
                , new int[]{R.id.day, R.id.week, R.id.temp});

        lists = new ArrayList<>();
        for (i = 0; i < time.length; i++) {
            Data list = new Data(time[i], Temp[i]);
            lists.add(list);
        }
        RecyclerView recyclerView = view.findViewById(R.id.hours);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);//设置为横向排列
        recyclerView.setLayoutManager(layout);
        HoursAdapter madapter = new HoursAdapter(lists, getActivity());
        recyclerView.setAdapter(madapter);
        ListView listView = view.findViewById(R.id.days);
        textView=view.findViewById(R.id.time);
        listView.setAdapter(adapter);
        ImageText imageText = (ImageText) view.findViewById(R.id.tu1);
        ImageText imageText2 = (ImageText) view.findViewById(R.id.tu2);
        ImageText imageText3 = (ImageText) view.findViewById(R.id.tu3);
        imageText.setImageResource(R.drawable.ic_launcher_foreground);
        imageText.setText("测试");
        imageText.setTextSize(10);
        imageText2.setImageResource(R.drawable.ic_launcher_foreground);
        imageText2.setText("测试");
        imageText2.setTextSize(10);
        imageText3.setImageResource(R.drawable.ic_launcher_foreground);
        imageText3.setText("测试");
        imageText3.setTextSize(10);


        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
        Request request = new Request.Builder()
                .url("https://www.tianqiapi.com/api/?version=v1")
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("tag", "请求失败");
            }

            //
//        @Override
            public void onResponse(Call call, final Response response) throws IOException {
                string = response.body().string();
                Weather_TianQijson weather = new Weather_TianQijson();
                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(string);
                    int cityid = jsonObject.optInt("cityid");
                    String update_time = jsonObject.optString("update_time");
                    String city = jsonObject.optString("city");
                    String cityEn = jsonObject.optString("cityEn");
                    String country = jsonObject.optString("country");
                    String countryEn = jsonObject.optString("countryEn");
                    JSONArray data = jsonObject.optJSONArray("data");
                    weather.setCityid(cityid);
                    weather.setUpdate_time(update_time);
                    weather.setCity(city);
                    weather.setCityEn(cityEn);
                    weather.setCountry(country);
                    weather.setCountryEn(countryEn);
                    List<Weather_TianQijson.Allday> datas = new ArrayList<>();
                    weather.setData(datas);
                    String[] ti = new String[]{"cityid", "update_time", "city", "cityEn", "country", "countryEn"};
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject jsonObject1 = data.getJSONObject(i);
                        String day = jsonObject1.optString("day");
                        String date = jsonObject1.optString("date");
                        String week = jsonObject1.optString("week");
                        String wea = jsonObject1.optString("wea");
                        String wea_img = jsonObject1.optString("wea_img");
                        int air = jsonObject1.optInt("air");
                        int humidity = jsonObject1.optInt("humidity");
                        String air_level = jsonObject1.optString("air_level");
                        String air_tips = jsonObject1.optString("air_tips");
                        String tem1 = jsonObject1.optString("tem1");
                        String tem2 = jsonObject1.optString("tem2");
                        String tem = jsonObject1.optString("tem");
                        String win = jsonObject1.optString("win");
                        String win_speed = jsonObject1.optString("win_speed");
                        JSONArray hours = jsonObject1.optJSONArray("hours");
                        JSONArray index = jsonObject1.optJSONArray("index");
                        Weather_TianQijson.Allday allday = new Weather_TianQijson.Allday();
                        allday.setDay(day);
                        allday.setDate(date);
                        allday.setWeek(week);
                        allday.setWea(wea);
                        allday.setWea_img(wea_img);
                        allday.setAir(air);
                        allday.setHumidity(humidity);
                        allday.setAir_level(air_level);
                        allday.setAir_tips(air_tips);
                        allday.setTem(tem);
                        allday.setTem1(tem1);
                        allday.setTem2(tem2);
                        allday.setWin(win);
                        allday.setWin_speed(win_speed);
                        List<Weather_TianQijson.Hours> hoursList = new ArrayList<>();
                        List<Weather_TianQijson.Index> indexList = new ArrayList<>();
                        datas.add(allday);
                        allday.setHours(hoursList);
                        allday.setIndex(indexList);
                        if (i==0){
                            WEEK=week;

                        }
                        for (int j = 0; j < hours.length(); j++) {
                            JSONObject jsonObject2 = hours.getJSONObject(j);
                            String day1 = jsonObject2.optString("day");
                            String wea1 = jsonObject2.optString("wea");
                            String tem3 = jsonObject2.optString("tem");
                            String win1 = jsonObject2.optString("win");
                            String win_speed1 = jsonObject2.optString("win_speed");
                            Weather_TianQijson.Hours hours1 = new Weather_TianQijson.Hours();
                            hours1.setDay(day1);
                            hours1.setWea(wea1);
                            hours1.setTem(tem3);
                            hours1.setWin(win1);
                            hours1.setWin_speed(win_speed1);
                            hoursList.add(hours1);
                        }
                        for (int j = 0; j < index.length(); j++) {
                            JSONObject jsonObject2 = index.getJSONObject(j);
                            String title = jsonObject2.optString("title");
                            String level = jsonObject2.optString("level");
                            String desc = jsonObject2.optString("desc");
                            Weather_TianQijson.Index index1 = new Weather_TianQijson.Index();
                            index1.setTitle(title);
                            index1.setLevel(level);
                            index1.setDesc(desc);
                            indexList.add(index1);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("今天"+"   "+WEEK);
                    }
                });
            };
        });
        return view;
    }
}












