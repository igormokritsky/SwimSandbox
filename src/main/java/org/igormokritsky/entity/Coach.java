package org.igormokritsky.entity;

public class Coach extends Entity {

    private String name;
    private String awards;
    private Integer countryId;
    private Integer userId;

    public Coach(String name, String awards, Integer countryId, Integer userId) {
        this.name = name;
        this.awards = awards;
        this.countryId = countryId;
        this.userId = userId;
    }

    public Coach() {

    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
