package com.guard.myguard.model.rest;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "age_range",
        "self_defined_ethnicity",
        "outcome_linked_to_object_of_search",
        "datetime",
        "removal_of_more_than_outer_clothing",
        "operation",
        "officer_defined_ethnicity",
        "object_of_search",
        "involved_person",
        "gender",
        "legislation",
        "location",
        "outcome",
        "type",
        "operation_name"
})
public class Crime {

    @JsonProperty("age_range")
    private String ageRange;
    @JsonProperty("self_defined_ethnicity")
    private String selfDefinedEthnicity;
    @JsonProperty("outcome_linked_to_object_of_search")
    private Object outcomeLinkedToObjectOfSearch;
    @JsonProperty("datetime")
    private String datetime;
    @JsonProperty("removal_of_more_than_outer_clothing")
    private Object removalOfMoreThanOuterClothing;
    @JsonProperty("operation")
    private Object operation;
    @JsonProperty("officer_defined_ethnicity")
    private String officerDefinedEthnicity;
    @JsonProperty("object_of_search")
    private String objectOfSearch;
    @JsonProperty("involved_person")
    private Boolean involvedPerson;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("legislation")
    private String legislation;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("outcome")
    private String outcome;
    @JsonProperty("type")
    private String type;
    @JsonProperty("operation_name")
    private Object operationName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("age_range")
    public String getAgeRange() {
        return ageRange;
    }

    @JsonProperty("age_range")
    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    @JsonProperty("self_defined_ethnicity")
    public String getSelfDefinedEthnicity() {
        return selfDefinedEthnicity;
    }

    @JsonProperty("self_defined_ethnicity")
    public void setSelfDefinedEthnicity(String selfDefinedEthnicity) {
        this.selfDefinedEthnicity = selfDefinedEthnicity;
    }

    @JsonProperty("outcome_linked_to_object_of_search")
    public Object getOutcomeLinkedToObjectOfSearch() {
        return outcomeLinkedToObjectOfSearch;
    }

    @JsonProperty("outcome_linked_to_object_of_search")
    public void setOutcomeLinkedToObjectOfSearch(Object outcomeLinkedToObjectOfSearch) {
        this.outcomeLinkedToObjectOfSearch = outcomeLinkedToObjectOfSearch;
    }

    @JsonProperty("datetime")
    public String getDatetime() {
        return datetime;
    }

    @JsonProperty("datetime")
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @JsonProperty("removal_of_more_than_outer_clothing")
    public Object getRemovalOfMoreThanOuterClothing() {
        return removalOfMoreThanOuterClothing;
    }

    @JsonProperty("removal_of_more_than_outer_clothing")
    public void setRemovalOfMoreThanOuterClothing(Object removalOfMoreThanOuterClothing) {
        this.removalOfMoreThanOuterClothing = removalOfMoreThanOuterClothing;
    }

    @JsonProperty("operation")
    public Object getOperation() {
        return operation;
    }

    @JsonProperty("operation")
    public void setOperation(Object operation) {
        this.operation = operation;
    }

    @JsonProperty("officer_defined_ethnicity")
    public String getOfficerDefinedEthnicity() {
        return officerDefinedEthnicity;
    }

    @JsonProperty("officer_defined_ethnicity")
    public void setOfficerDefinedEthnicity(String officerDefinedEthnicity) {
        this.officerDefinedEthnicity = officerDefinedEthnicity;
    }

    @JsonProperty("object_of_search")
    public String getObjectOfSearch() {
        return objectOfSearch;
    }

    @JsonProperty("object_of_search")
    public void setObjectOfSearch(String objectOfSearch) {
        this.objectOfSearch = objectOfSearch;
    }

    @JsonProperty("involved_person")
    public Boolean getInvolvedPerson() {
        return involvedPerson;
    }

    @JsonProperty("involved_person")
    public void setInvolvedPerson(Boolean involvedPerson) {
        this.involvedPerson = involvedPerson;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("legislation")
    public String getLegislation() {
        return legislation;
    }

    @JsonProperty("legislation")
    public void setLegislation(String legislation) {
        this.legislation = legislation;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("outcome")
    public String getOutcome() {
        return outcome;
    }

    @JsonProperty("outcome")
    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("operation_name")
    public Object getOperationName() {
        return operationName;
    }

    @JsonProperty("operation_name")
    public void setOperationName(Object operationName) {
        this.operationName = operationName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}