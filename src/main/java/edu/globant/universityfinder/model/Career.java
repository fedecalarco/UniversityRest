package edu.globant.universityfinder.model;

/**
 * Created by federico.calarco on 8/17/2016.
 */
public class Career {

    private Long id;
    private String name;

    public Career() {
    }

    public Career(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Career(String name){
        this.name = name;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
