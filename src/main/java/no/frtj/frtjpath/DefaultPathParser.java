package no.frtj.frtjpath;

public class DefaultPathParser implements FrtjPathParser {

    public FrtjPath parsePath(String path) {

        boolean isRoot = false;
        if (path.startsWith("/")) {
            isRoot = true;
            path = path.substring(1); // skip / to avoid empty start elems
        }
        boolean lastElemIsFile = path.length() >= 1 && !path.endsWith("/");

        String[] split = path.split("/");
        PathElement current = null;

        if (lastElemIsFile && split.length == 1) {
            current = new File(split[0]);
        } else if (split.length > 0) {
            current = new Directory(split[0]);
        }

        PathElement rootElement = null;
        if(current != null) {
            rootElement = current;
        }

        for (int i = 1; i < split.length; i++) {

            if (i == split.length - 1) {
                if (lastElemIsFile) {
                    current.setNext(new File(split[i]));
                } else {
                    current.setNext(new Directory(split[i]));
                }

            } else {
                current.setNext(new Directory(split[i]));
            }
            current = current.getNext();

        }

        return FrtjPathFactory.createFrtjPath(isRoot, rootElement);
    }
}
