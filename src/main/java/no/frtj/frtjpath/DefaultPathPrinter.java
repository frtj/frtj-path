package no.frtj.frtjpath;

public class DefaultPathPrinter implements FrtjPathPrinter {
    @Override
    public String printPath(FrtjPath frtjPath) {
        StringBuilder b = new StringBuilder();
        if (frtjPath.root)
            b.append("/");
        PathElement p = frtjPath.root && frtjPath.path.getNext() == null && StringUtil.isEmpty(frtjPath.path.getName()) ? null : frtjPath.path;
        while (p != null) {
            b.append(p.toString());
            p = p.getNext();
        }
        return b.toString();
    }
}
