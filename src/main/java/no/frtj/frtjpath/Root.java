package no.frtj.frtjpath;

public class Root extends PathElement {


    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
}
