package com.example.mytianqi;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import java.sql.Time;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.view.View.*;

public class TianQiFragment extends Fragment {
    List<Data> lists;
    String[] weekdate,time,temp_day,temp_today,weekday;
    TextView tv_air,tv_tem,tv_time,tv_point,tv_nowtem;
    String string,string_week,string_tem,string_air_level,string_win,
           string_win_speed,string_air_tips,string_wea,string_time,string_now;
    ListView lv_days;
    List<Map<String, Object>> weeks;;
    int i,k=0,l=0,int_humidity,int_air;
    RecyclerView recyclerView;
    int[] hour,images;
    ImageText img_tu1,img_tu2,img_tu3;
    Calendar call;
    ImageView iv_img;
        @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tianqi_fragment, container, false);
        weekdate = new String[6];
        weekday = new String[6];
        temp_day = new String[6];
        weeks = new ArrayList<>();
        time=new String[8];
        temp_today=new String[8];
        images=new int[9];
        hour=new int[8];
        lists = new ArrayList<>();
        call=Calendar.getInstance();
        tv_nowtem=view.findViewById(R.id.tv_nowtem);
        call.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        recyclerView = view.findViewById(R.id.reycler_hours);
        string_time=String.valueOf(call.get(Calendar.HOUR));
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);//设置为横向排列
        recyclerView.setLayoutManager(layout);
        lv_days = view.findViewById(R.id.lv_days);
        iv_img=view.findViewById(R.id.iv_img);
        img_tu1=view.findViewById(R.id.img_tu1);
        img_tu2=view.findViewById(R.id.img_tu2);
        img_tu3=view.findViewById(R.id.img_tu3);
        tv_point=view.findViewById(R.id.tv_point);
        tv_air=view.findViewById(R.id.tv_air);
        tv_tem=view.findViewById(R.id.tv_tem);
        tv_time=view.findViewById(R.id.tv_time);
//        final ImageText imageText = (ImageText) view.findViewById(R.id.tu1);
//        ImageText imageText2 = (ImageText) view.findViewById(R.id.tu2);
//        ImageText imageText3 = (ImageText) view.findViewById(R.id.tu3);
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
        Request request = new Request.Builder()
                .url("https://www.tianqiapi.com/api/?version=v1")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("tag", "请求失败");
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
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
                        if (i==0) {
                            string_now=tem;
                            if (string_now.equals("多云")){
                                images[8]=R.mipmap.duoyun;
                            }else if (string_now.equals("晴")){
                                images[8]=R.mipmap.qing;
                            }else if (string_now.equals("小雨")){
                                images[8]=R.mipmap.xiaoyu;
                            }
                            string_week=week;
                            string_tem = tem2 + "~" + tem1;
                            string_air_level = air_level;
                            int_humidity=humidity;
                            int_air=air;
                            string_win=win;
                            string_win_speed=win_speed;
                            string_air_tips=air_tips;
                        } else {
                            weekdate[k]=day;
                            weekday[k]=week;
                            temp_day[k]=tem2+"~"+tem1;
                            k++;
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
                            if (i == 0) {
                                if (j==0) {
                                    hour[0] = 8;
                                }
                                if (j > 0) {
                                    hour[j] = hour[j - 1] + 3;
                                }
                                if (hour[j] >= 24) {
                                    hour[j] = hour[j] - 24;
                                }
                                if (hour[j] < 10) {
                                    day1 = "0" + hour[j] + "时";
                                } else {
                                    day1 = hour[j] + "时";
                                }

                                time[j] = day1;
                                temp_today[j] = tem3;

                                string_wea=wea;
                                if (string_wea.equals("多云")){
                                    images[j]=R.mipmap.duoyun;
                                }else if (string_wea.equals("晴")){
                                    images[j]=R.mipmap.qing;
                                }else if (string_wea.equals("小雨")){
                                    images[j]=R.mipmap.xiaoyu;
                                }
                            }
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
                        tv_tem.setText(string_tem);
                        tv_time.setText("今天   "+string_week);
                        tv_air.setText("空气  "+string_air_level);
                        for (i = 0; i < time.length; i++) {
                            Data list = new Data(time[i],images[i], temp_today[i]);
                            lists.add(list);
                        }
                        tv_nowtem.setText(string_now);
                        iv_img.setImageResource(images[8]);
//                        Time.setText((CharSequence) call);
                        for (i = 0; i < weekdate.length; i++) {
                            Map<String, Object> week = new HashMap<>();
                            week.put("date", weekdate[i]);
                            week.put("weekday", weekday[i]);
                            week.put("temp", temp_day[i]);
                            weeks.add(week);
                        }
                        SimpleAdapter adapter = new SimpleAdapter(getActivity(), weeks, R.layout.days_main,
                                new String[]{"date", "weekday", "temp"}
                                , new int[]{R.id.tv_day, R.id.tv_week, R.id.tv_daytem});
                        HoursAdapter madapter = new HoursAdapter(lists, getActivity());
                        recyclerView.setAdapter(madapter);
                        lv_days.setAdapter(adapter);
                        img_tu1.setTextView("湿度");
                        img_tu1.setText(""+int_humidity+"%");
                        img_tu1.setImageResource(R.mipmap.shui);
                        img_tu2.setTextView("空气质量");
                        img_tu2.setText(int_air+"  "+string_air_level);
                        img_tu2.setImageResource(R.mipmap.kongqizhiliang);
                        img_tu3.setTextView("风向和风力");
                        img_tu3.setText(string_win+" "+string_win_speed);
                        img_tu3.setImageResource(R.mipmap.feng);
                        tv_point.setText(string_air_tips);
                    }
                });
            };
        });
        return view;
    }
}












