package org.sample.wordfreq.model;

public class SearchResult {
    private Integer count;
    private String foundInFileName;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getFoundInFileName() {
        return foundInFileName;
    }

    public void setFoundInFileName(String foundInFileName) {
        this.foundInFileName = foundInFileName;
    }
}
