package no.frtj.frtjpath;

public class Directory extends PathElement {
    String name;

    public Directory(String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }
}
