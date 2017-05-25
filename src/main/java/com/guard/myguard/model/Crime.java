package com.guard.myguard.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Crime {
    public final String category;
    public final String location_type;
    public final Location location;
    public final String context;
    public final String persistent_id;
    public final long id;
    public final String location_subtype;
    public final String month;
    public final Outcome_status outcome_status;

    @JsonCreator
    public Crime(@JsonProperty("category") String category, @JsonProperty("location_type") String location_type, @JsonProperty("location") Location location, @JsonProperty("context") String context, @JsonProperty("persistent_id") String persistent_id, @JsonProperty("id") long id, @JsonProperty("location_subtype") String location_subtype, @JsonProperty("month") String month, @JsonProperty("outcome_status") Outcome_status outcome_status){
        this.category = category;
        this.location_type = location_type;
        this.location = location;
        this.context = context;
        this.persistent_id = persistent_id;
        this.id = id;
        this.location_subtype = location_subtype;
        this.month = month;
        this.outcome_status = outcome_status;
    }

    public static final class Location {
        public final String latitude;
        public final Street street;
        public final String longitude;

        @JsonCreator
        public Location(@JsonProperty("latitude") String latitude, @JsonProperty("street") Street street, @JsonProperty("longitude") String longitude){
            this.latitude = latitude;
            this.street = street;
            this.longitude = longitude;
        }

        public static final class Street {
            public final long id;
            public final String name;

            @JsonCreator
            public Street(@JsonProperty("id") long id, @JsonProperty("name") String name){
                this.id = id;
                this.name = name;
            }
        }
    }

    public static final class Outcome_status {
        public final String category;
        public final String date;

        @JsonCreator
        public Outcome_status(@JsonProperty("category") String category, @JsonProperty("date") String date){
            this.category = category;
            this.date = date;
        }
    }
}