package com.guard.myguard;

import com.guard.myguard.model.rest.Crime;
import com.guard.myguard.services.impl.JsonRestService;
import com.guard.myguard.services.interfaces.RestService;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private RestService<Crime[]> crimeRestService;
    private static final String URL = "https://data.police.uk/api/stops-street?lat=52.629729&lng=-1.131592";

    @Before
    public void prepareForTests() {
        this.crimeRestService = new JsonRestService<>(URL, Crime[].class);
    }

    @Test
    public void testIfRestServiceWorksProperly() throws Exception {
        String jsonData = crimeRestService.getJsonForRequest(URL);
        System.out.println(jsonData);
    }

    @Test
    public void checkIfMonthsAreGeneratedProperly(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        for (int i = 1; i < 13; i++) {
            if(month == 0){
                year--;
                month+=12;

            }
            String actual = year + "-" + String.format(Locale.ENGLISH, "%02d", (month--));
            System.out.println(actual);
        }
    }

    @Test
    public void testIfRestServiceParsesRequestedDataProperly() throws Exception {
        String jsonData = crimeRestService.getJsonForRequest(URL);
        Crime[] crimes = crimeRestService.parseJsonData(jsonData);
        System.out.println(Arrays.toString(crimes));
    }
}