package com.test.websocket.demo.model;

public class Header {
    private String serverStatus;
    private int time;
    private int lengthServerStatus;
    private boolean word;
    private String siteName;
    private boolean monitor;
    private String requestWord;

    public Header() {
    }

    public Header(String serverStatus, int time, int lengthServerStatus, boolean word, String siteName, boolean monitor, String requestWord) {
        this.serverStatus = serverStatus;
        this.time = time;
        this.lengthServerStatus = lengthServerStatus;
        this.word = word;
        this.siteName = siteName;
        this.monitor = monitor;
        this.requestWord = requestWord;
    }

    public String getServerStatus() {
        return serverStatus;
    }

    public int getTime() {
        return time;
    }

    public int getLengthServerStatus() {
        return lengthServerStatus;
    }

    public boolean getWord() {
        return word;
    }

    public void setServerStatus(String serverStatus) {
        this.serverStatus = serverStatus;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setLengthServerStatus(int lengthServerStatus) {
        this.lengthServerStatus = lengthServerStatus;
    }

    public void setWord(boolean word) {
        this.word = word;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteName() {
        return siteName;
    }

    public boolean isWord() {
        return word;
    }

    public boolean isMonitor() {
        return monitor;
    }

    public void setMonitor(boolean monitor) {
        this.monitor = monitor;
    }

    public String getRequestWord() {
        return requestWord;
    }

    public void setRequestWord(String requestWord) {
        this.requestWord = requestWord;
    }

    @Override
    public String toString() {
        return "Header{" +
                "serverStatus='" + serverStatus + '\'' +
                ", time=" + time +
                ", lengthServerStatus=" + lengthServerStatus +
                ", word=" + word +
                ", siteName='" + siteName + '\'' +
                ", monitor=" + monitor +
                ", requestWord='" + requestWord + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Header header = (Header) o;

        if (time != header.time) return false;
        if (lengthServerStatus != header.lengthServerStatus) return false;
        if (word != header.word) return false;
        if (monitor != header.monitor) return false;
        if (serverStatus != null ? !serverStatus.equals(header.serverStatus) : header.serverStatus != null)
            return false;
        if (siteName != null ? !siteName.equals(header.siteName) : header.siteName != null) return false;
        return requestWord != null ? requestWord.equals(header.requestWord) : header.requestWord == null;
    }

    @Override
    public int hashCode() {
        int result = serverStatus != null ? serverStatus.hashCode() : 0;
        result = 31 * result + time;
        result = 31 * result + lengthServerStatus;
        result = 31 * result + (word ? 1 : 0);
        result = 31 * result + (siteName != null ? siteName.hashCode() : 0);
        result = 31 * result + (monitor ? 1 : 0);
        result = 31 * result + (requestWord != null ? requestWord.hashCode() : 0);
        return result;
    }
}
