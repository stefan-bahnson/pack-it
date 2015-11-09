package com.eggshell.kanoting.hateoas;

import java.io.Serializable;
// Daniel Laine was here
/**
 * Created by tailage on 11/9/15.
 */
public class Link implements Serializable{

    private String rel;
    private String href;

    public Link(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }


    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (rel != null ? !rel.equals(link.rel) : link.rel != null) return false;
        return !(href != null ? !href.equals(link.href) : link.href != null);

    }

    @Override
    public int hashCode() {
        int result = rel != null ? rel.hashCode() : 0;
        result = 31 * result + (href != null ? href.hashCode() : 0);
        return result;
    }
}
