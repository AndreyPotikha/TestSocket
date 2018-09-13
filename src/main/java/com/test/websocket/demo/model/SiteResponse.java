package com.test.websocket.demo.model;

public class SiteResponse {

    private String headStatus;
    private boolean checkWord;
    private long responseTime;

    public SiteResponse() {
    }

    public SiteResponse(String headStatus, boolean checkWord, long responseTime) {
        this.headStatus = headStatus;
        this.checkWord = checkWord;
        this.responseTime = responseTime;
    }

    public SiteResponse(String headStatus, boolean checkWord) {
        this.headStatus = headStatus;
        this.checkWord = checkWord;
    }

    public String getHeadStatus() {
        return headStatus;
    }

    public void setHeadStatus(String headStatus) {
        this.headStatus = headStatus;
    }

    public boolean isCheckWord() {
        return checkWord;
    }

    public void setCheckWord(boolean checkWord) {
        this.checkWord = checkWord;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SiteResponse that = (SiteResponse) o;

        if (checkWord != that.checkWord) return false;
        if (responseTime != that.responseTime) return false;
        return headStatus != null ? headStatus.equals(that.headStatus) : that.headStatus == null;
    }

    @Override
    public int hashCode() {
        int result = headStatus != null ? headStatus.hashCode() : 0;
        result = 31 * result + (checkWord ? 1 : 0);
        result = 31 * result + (int) (responseTime ^ (responseTime >>> 32));
        return result;
    }
}
