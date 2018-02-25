package com.sample.gitsquare.Model;

public class ContributeModel {
    private String username;
    private String avtar_url;
    private String repos_url;
    private int contributions;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAvtar_url() {
        return avtar_url;
    }
    public void setAvtar_url(String avtar_url) {
        this.avtar_url = avtar_url;
    }
    public String getRepos_url() {
        return repos_url;
    }
    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }
    public int getContributions() {
        return contributions;
    }
    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

}