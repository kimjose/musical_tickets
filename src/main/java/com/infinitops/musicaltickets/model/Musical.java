package com.infinitops.musicaltickets.model;

public class Musical {

    private int _id;
    private String title;
    private int runtime;
    private String category;
    private int minAge;

    public Musical(int _id, String title, int runtime, String category, int minAge) {
        this._id = _id;
        this.title = title;
        this.runtime = runtime;
        this.category = category;
        this.minAge = minAge;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
