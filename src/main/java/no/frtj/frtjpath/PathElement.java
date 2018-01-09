package no.frtj.frtjpath;

import java.util.Objects;

public abstract class PathElement {
    private PathElement next;

    public void setNext(PathElement next) {
        this.next = next;
    }

    public PathElement getNext() {
        return next;
    }

    public abstract String getName();

    public abstract boolean isFile();

    public abstract boolean isDirectory();

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(getName());
        if(isDirectory())
            b.append("/");
        return b.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PathElement)) return false;
        PathElement that = (PathElement) o;
        if(that.isFile()!=isFile())
            return false;
        if(!that.getName().equals(getName()))
            return false;
        if(that.getNext() ==null && getNext()==null)
            return true;
        if(that.getNext() ==null || getNext()==null)
            return false;
        return that.getNext().equals(getNext());
    }

    @Override
    public int hashCode() {

        return Objects.hash(next);
    }
}
