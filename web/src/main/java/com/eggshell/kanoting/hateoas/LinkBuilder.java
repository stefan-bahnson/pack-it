package com.eggshell.kanoting.hateoas;

import javax.ws.rs.core.UriInfo;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by tailage on 11/9/15.
 */
public class LinkBuilder {

    public List<Link> links = new ArrayList<>();
    private Link link;
    private UriInfo info;

    public LinkBuilder(UriInfo info) {
        this.info = info;
    }

    public LinkBuilder forItem(String rel) {
        String uriStr = info
                .getAbsolutePath()
                .toString();

        this.link = new Link(rel, uriStr);
        links.add(link);

        return this;
    }

    public LinkBuilder forItem(String rel, long id) {
        String uriStr = info
                .getAbsolutePathBuilder()
                .path(Long.toString(id))
                .build()
                .toString();

        this.link = new Link(rel, uriStr);
        links.add(link);

        return this;
    }

    public LinkBuilder forItem(String rel, Class<?> resource, long id) {
        String uriStr = info
                .getBaseUriBuilder()
                .path(resource)
                .path(Long.toString(id))
                .build()
                .toString();

        this.link = new Link(rel, uriStr);
        links.add(link);

        return this;
    }

    public LinkBuilder forCollection(String rel) {
        String uriStr = info
                .getAbsolutePath()
                .toString();

        this.link = new Link(rel, uriStr);
        links.add(link);

        return this;
    }

    public LinkBuilder forCollection(String rel, Class<?> resource) {
        String uriStr = info
                .getBaseUriBuilder()
                .path(resource)
                .build()
                .toString();

        this.link = new Link(rel, uriStr);
        links.add(link);

        return this;
    }

    public LinkBuilder forCollection(String rel, Class<?> resource, long id) {
        String uriStr = info
                .getBaseUriBuilder()
                .path(resource)
                .path(Long.toString(id))
                .build()
                .toString();

        this.link = new Link(rel, uriStr);
        links.add(link);

        return this;
    }


    // SUB RESOURCES

    public LinkBuilder toSubResource(String rel, Class<?> subResource, String methodName) {

        String uriStr = null;

        try {
            // TODO: Make general. take method and class or method as params?
            Method method = subResource.getMethod(methodName, subResource);
            uriStr = info.getAbsolutePathBuilder()
                    .path(method)
                    .build()
                    .toString();
        } catch (NoSuchMethodException e) {
            System.out.println(":::COULD NOT BUILD URI FOR SUB-RESOURCE");
        }

        this.link = new Link(rel, uriStr);
        links.add(link);

        return this;
    }


    public LinkBuilder forRoot(String rel) {
        String uriStr = info
                .getBaseUri()
                .toString();

        this.link = new Link(rel, uriStr);
        links.add(link);

        return this;
    }

    public List<Link> buildLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Link buildLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}

