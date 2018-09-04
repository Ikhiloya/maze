package com.loya.maze.security.yaml;

public class Authentication {
    private JWT jwt;

    public Authentication() {
    }

    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }
}
