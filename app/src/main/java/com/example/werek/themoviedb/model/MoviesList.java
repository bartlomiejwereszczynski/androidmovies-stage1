package com.example.werek.themoviedb.model;

/**
 * Created by werek on 21.01.2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesList
{

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Movie> results = null;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoviesList)) return false;

        MoviesList that = (MoviesList) o;

        if (getPage() != null ? !getPage().equals(that.getPage()) : that.getPage() != null)
            return false;
        if (getResults() != null ? !getResults().equals(that.getResults()) : that.getResults() != null)
            return false;
        if (getTotalResults() != null ? !getTotalResults().equals(that.getTotalResults()) : that.getTotalResults() != null)
            return false;
        return getTotalPages() != null ? getTotalPages().equals(that.getTotalPages()) : that.getTotalPages() == null;

    }

    @Override
    public int hashCode() {
        int result = getPage() != null ? getPage().hashCode() : 0;
        result = 31 * result + (getResults() != null ? getResults().hashCode() : 0);
        result = 31 * result + (getTotalResults() != null ? getTotalResults().hashCode() : 0);
        result = 31 * result + (getTotalPages() != null ? getTotalPages().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MoviesList{" +
                "page=" + page +
                ", results=" + results +
                ", totalResults=" + totalResults +
                ", totalPages=" + totalPages +
                '}';
    }
}
