package com.tubb.cityindex;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CitySelectorActivity extends Activity{

     ListView lvCity;
     SectionBar sbCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        lvCity = (ListView)findViewById(R.id.lvCity);
        sbCity = (SectionBar)findViewById(R.id.sbCity);
        setLvCity();
    }

    private void setLvCity() {
        City city = new City();
        city.setId("#");
        city.setName("北京");
        City city2 = new City();
        city2.setId("a");
        city2.setName("阿拉斯加");
        City city3 = new City();
        city3.setId("a");
        city3.setName("安吉");
        City city4 = new City();
        city4.setId("e");
        city4.setName("鄂尔多斯");
        City city5 = new City();
        city5.setId("e");
        city5.setName("鄂尔多斯韭");
        City city6 = new City();
        city6.setId("r");
        city6.setName("热河");
        City city7 = new City();
        city7.setId("y");
        city7.setName("伊川");
        City city8 = new City();
        city8.setId("b");
        city8.setName("北海道");
        City city9 = new City();
        city9.setId("s");
        city9.setName("上海");
        City city10 = new City();
        city10.setId("c");
        city10.setName("长北");
        City city11 = new City();
        city11.setId("i");
        city11.setName("爱尔兰");
        City city12 = new City();
        city12.setId("j");
        city12.setName("姜丝");
        City city13 = new City();
        city13.setId("j");
        city13.setName("建州");
        City city14 = new City();
        city14.setId("m");
        city14.setName("马总");
        City city15 = new City();
        city15.setId("y");
        city15.setName("雨荷");
        final List<City> citys = new ArrayList<City>();
        citys.add(city);
        citys.add(city2);
        citys.add(city3);
        citys.add(city4);
        citys.add(city5);
        citys.add(city6);
        citys.add(city7);
        citys.add(city8);
        citys.add(city9);
        citys.add(city10);
        citys.add(city11);
        citys.add(city12);
        citys.add(city13);
        citys.add(city14);
        citys.add(city15);
        Collections.sort(citys);
        CitySelectorAdapter adapter = new CitySelectorAdapter(this,citys);
        lvCity.setAdapter(adapter);
        sbCity.setListView(lvCity);
        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO
            }
        });
    }

}
