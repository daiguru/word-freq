package org.sample.wordfreq.model;

import java.util.List;

public class WordFrequencyOutput {

    public Integer finalCount;
    public List<SearchResult> searchResultList;

    public Integer getFinalCount() {
        return finalCount;
    }

    public void setFinalCount(Integer finalCount) {
        this.finalCount = finalCount;
    }

    public List<SearchResult> getSearchResultList() {
        return searchResultList;
    }

    public void setSearchResultList(List<SearchResult> searchResultList) {
        this.searchResultList = searchResultList;
    }
}
