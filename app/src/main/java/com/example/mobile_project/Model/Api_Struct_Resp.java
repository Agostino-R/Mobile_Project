package com.example.mobile_project.Model;

import java.util.List;

public class Api_Struct_Resp
{
    int header;
    List<Anime> test;

    public void setHeader(int header) {
        this.header = header;
    }

    public void setTest(List<Anime> test) {
        this.test = test;
    }

    public int getHeader() {
        return header;
    }

    public List<Anime> getTest() {
        return test;
    }
}
