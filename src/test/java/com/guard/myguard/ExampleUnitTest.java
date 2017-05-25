package com.guard.myguard;

import com.guard.myguard.model.Crime;
import com.guard.myguard.services.interfaces.JsonRestService;
import com.guard.myguard.services.interfaces.RestService;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private RestService<Crime[]> crimeRestService;
    private static final String URL = "https://data.police.uk/api/crimes-at-location?date=2012-02&lat=52.629729&lng=-1.131592";
    private static final String CRIMES_AT_LOCATION_REQUEST = "crimes-at-location?date=2012-02&lat=52.629729&lng=-1.131592";

    @Before
    public void prepareForTests() {
        this.crimeRestService = new JsonRestService<>(URL, Crime[].class);
    }

    @Test
    public void testIfRestServiceWorksProperly() throws Exception {
        String jsonData = crimeRestService.getJsonForRequest(CRIMES_AT_LOCATION_REQUEST);
        System.out.println(jsonData);
    }

    @Test
    public void testIfRestServiceParsesRequestedDataProperly() throws Exception {
        String jsonData = crimeRestService.getJsonForRequest(CRIMES_AT_LOCATION_REQUEST);
        Crime[] crimes = crimeRestService.parseJsonData(jsonData);
        System.out.println(Arrays.toString(crimes));
    }
}