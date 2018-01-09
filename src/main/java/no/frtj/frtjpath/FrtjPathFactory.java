package no.frtj.frtjpath;

public class FrtjPathFactory {

    FrtjPathParser parser;
    FrtjPathPrinter printer;

    public FrtjPathFactory() {
        parser = new DefaultPathParser();
        printer = new DefaultPathPrinter();
    }

    public FrtjPath parsePath(String path) {
        return parser.parsePath(path);
    }

    public String printPath(FrtjPath path) {
        return printer.printPath(path);
    }

    public static FrtjPath createFrtjPath(boolean root, PathElement element) {
        FrtjPath frtjPath = new FrtjPath();
        frtjPath.root = root;
        frtjPath.path = element;
        return frtjPath;
    }

    public void setParser(FrtjPathParser parser) {
        this.parser = parser;
    }

    public void setPrinter(FrtjPathPrinter printer) {
        this.printer = printer;
    }
}
