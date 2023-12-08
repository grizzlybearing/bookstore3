package org.example.beans;

public class UserInfo {
    private int id;
    private String name;
    private boolean isAuth;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setIsAuth(boolean isAuth){
        this.isAuth = isAuth;
    }
    public boolean getIsAuth(){
        return this.isAuth;
    }
}
