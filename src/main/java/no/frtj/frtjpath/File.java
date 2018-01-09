package no.frtj.frtjpath;

public class File extends PathElement {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
}
