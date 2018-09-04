package com.loya.maze.security.yaml;

public class Auth {

    private Http http;
    private Security security;

    public Auth() {
    }

    public Http getHttp() {
        return http;
    }

    public void setHttp(Http http) {
        this.http = http;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }
}
