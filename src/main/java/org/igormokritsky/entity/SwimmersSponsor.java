package org.igormokritsky.entity;

public class SwimmersSponsor extends Entity {

    private Integer swimmerId;
    private Integer sponsorId;
    private Integer contractAmount;

    public SwimmersSponsor(){

    }

    public Integer getSwimmerId() {
        return swimmerId;
    }

    public void setSwimmerId(Integer swimmerId) {
        this.swimmerId = swimmerId;
    }

    public Integer getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Integer sponsorId) {
        this.sponsorId = sponsorId;
    }

    public Integer getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Integer contractAmount) {
        this.contractAmount = contractAmount;
    }
}
