package no.frtj.frtjpath;


import java.util.Objects;

public class FrtjPath {
    boolean root = false;
    PathElement path;

    private static FrtjPathFactory factory = new FrtjPathFactory();


    public static FrtjPath create(String path) {
        return factory.parsePath(path);
    }

    public static FrtjPathFactory getFactory() {
        return factory;
    }

    public boolean isAbsolute() {
        return root;
    }

    public FrtjPath get(int i) {
        FrtjPath frtjPath = new FrtjPath();
        int y = 0;
        PathElement p = path;
        while (p != null) {
            if (y == i) {
                if (p.isFile())
                    frtjPath.path = new File(p.getName());
                else
                    frtjPath.path = new Directory(p.getName());

                return frtjPath;
            }
            p = p.getNext();
            y++;
        }
        return null;
    }

    public int getCount() {
        PathElement p = path;
        int i = 0;
        while (p != null) {
            p = p.getNext();
            i++;
        }
        return i;
    }

    @Override
    public String toString() {
        return factory.printPath(this);
    }

    public boolean isFile() {
        return getLast().isFile();
    }

    private PathElement getLast() {
        PathElement p = path;
        while (p != null && p.getNext() != null) {
            p = p.getNext();
        }
        return p;
    }

    public boolean startsWith(FrtjPath candidate) {
        if (this.root != candidate.root)
            return false;
        /*for (int i = 0; i < candidate.getCount() && this.get(i) != null; i++) {
            FrtjPath c = candidate.get(i);
            FrtjPath t = this.get(i);
            //if (c.isFile() != t.isFile())
            //    return false;
            if (!c.path.getName().toString().equals(t.path.getName().toString()))
                return false;
        }
        return true;*/
        return this.toString().startsWith(candidate.toString());
    }

    public boolean startsWith(String path) {
        FrtjPath candidate = FrtjPath.create(path);
        return startsWith(candidate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FrtjPath)) return false;
        FrtjPath frtjPath = (FrtjPath) o;

        if (frtjPath.root != root)
            return false;

        return frtjPath.path.equals(path);
    }

    @Override
    public int hashCode() {

        return Objects.hash(root, path);
    }

    public FrtjPath subPath(int i) {
        if (i >= getCount())
            throw new RuntimeException("outside boundary");
        FrtjPath frtjPath = new FrtjPath();
        PathElement elem = path;
        int y;
        for (y = 0; y < i; y++)
            elem = elem.getNext();

        PathElement newElem = copy(elem);
        frtjPath.path = newElem;
        for (int x = y; x < getCount() - 1; x++) {
            elem = elem.getNext();

            PathElement copy = copy(elem);
            newElem.setNext(copy);
            newElem = copy;
        }
        return frtjPath;
    }

    private PathElement copy(PathElement elem) {
        if (elem.isFile()) {
            return new File(elem.getName());
        } else {
            return new Directory(elem.getName());
        }
    }

    public String getName(int i) {
        return get(i).path.getName();
    }
}
