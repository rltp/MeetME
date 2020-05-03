package com.esme.meetme.application;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

class Operation<E>{
    public String path;
    public E value;
}

public class Data<E>{

    private ArrayList<E> list = new ArrayList<E>();

    public Data(){

    }

    public void add(E element) throws Exception{
        this.list.add(element);
    }

    public void replace(String key, String value, E element) throws Exception{
        Integer index = null;

        for(E el : this.list)
            if (el.getClass().getField(key).get(el).toString().equals(value.toString()))
                index = this.list.indexOf(el);

        if(index == null) throw new Exception(key + " not found");
        this.list.set(index, element);
    }

    public void delete(String key, String value) throws Exception{
        E element = null;

        for(E el : this.list)
            if (el.getClass().getField(key).get(el).toString().equals(value.toString()))
                element = el;

        if(element == null) throw new Exception(key + " not found");
        this.list.remove(element);
    }

    public void patch(String key, String value, Operation patch) throws Exception{
        Boolean updated = Boolean.FALSE;
        ObjectMapper objectMapper = new ObjectMapper();

        for(E el : this.list)
            if (el.getClass().getField(key).get(el).toString().equals(value.toString())){
                Class type = el.getClass().getField(patch.path).getType();
                el.getClass().getField(patch.path).set(el, objectMapper.convertValue(patch.value, type));
                updated = Boolean.TRUE;
            }
        if(updated == Boolean.FALSE) throw new Exception(key + " not found");
    }

    public ArrayList<E> getAll(){
        return this.list;
    }

    public E find(String key, Object value) throws Exception {
        E element = null;

        for(E el : this.list)
            if (el.getClass().getField(key).get(el).toString().equals(value.toString()))
                element = el;

        if(element == null) throw new Exception(key + " not found");

        return element;
    }

}
