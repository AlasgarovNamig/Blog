package com.proxyseller.Blog.common;

import java.util.List;
/*
* @author Namig Alasgarov
* Generic CI is a created input Type
* Generic UI is a created input Type
* Generic O is output Dto type
* */
public interface BaseCrud<CI,UI,O> {
    public List<O> GetAll();
    public O GetById(String id);
    public void Create(CI obj);
    public void Update(UI obj);
    public void Delete(String id);
}
