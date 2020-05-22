package org.igormokritsky.entity;

public class SwimCompet extends Entity {

    private Integer competitionId;
    private Integer swimmerId;
    private Integer time;

    public SwimCompet() {

    }

    public SwimCompet(Integer competitionId, Integer swimmerId, Integer time) {
        this.competitionId = competitionId;
        this.swimmerId = swimmerId;
        this.time = time;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public Integer getSwimmerId() {
        return swimmerId;
    }

    public void setSwimmerId(Integer swimmerId) {
        this.swimmerId = swimmerId;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
