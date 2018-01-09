package no.frtj.frtjpath;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.fail;

public class FrtjPathTest {

    @Test
    public void should_be_absolute() {
        FrtjPath path = FrtjPath.create("/");

        Assertions.assertThat(path.isAbsolute()).isTrue();
    }

    @Test
    public void should_be_relative() {
        FrtjPath path = FrtjPath.create("hmm");

        Assertions.assertThat(path.isAbsolute()).isFalse();
    }

    @Test
    public void should_be_file() {
        FrtjPath path = FrtjPath.create("/hmm");

        Assertions.assertThat(path.isAbsolute()).isTrue();
        Assertions.assertThat(path.get(0).toString()).isEqualTo("hmm");
        Assertions.assertThat(path.get(0).isAbsolute()).isFalse();
        Assertions.assertThat(path.get(0).isFile()).isTrue();
        Assertions.assertThat(path.isFile()).isTrue();
    }

    @Test
    public void should_parse_directory() {
        FrtjPath path = FrtjPath.create("/hmm/");

        Assertions.assertThat(path.isAbsolute()).isTrue();
        Assertions.assertThat(path.get(0).toString()).isEqualTo("hmm/");
        Assertions.assertThat(path.get(0).isAbsolute()).isFalse();
        Assertions.assertThat(path.get(0).isFile()).isFalse();
        Assertions.assertThat(path.isFile()).isFalse();
    }

    @Test
    public void should_parse_path() {
        FrtjPath path = FrtjPath.create("/a/b/c");

        Assertions.assertThat(path.get(0).isFile()).isFalse();
        Assertions.assertThat(path.get(1).isFile()).isFalse();
        Assertions.assertThat(path.get(2).isFile()).isTrue();
    }

    @Test
    public void should_start_with() {
        FrtjPath path = FrtjPath.create("/a/b/c/");

        Assertions.assertThat(path.startsWith(FrtjPath.create("/a/b/"))).isTrue();
        Assertions.assertThat(path.startsWith(FrtjPath.create("/a/b"))).isTrue();
        Assertions.assertThat(path.startsWith(FrtjPath.create("/a/b/c"))).isTrue();
        Assertions.assertThat(path.startsWith(FrtjPath.create("/a/b/c/d"))).isFalse();
        Assertions.assertThat(path.startsWith(FrtjPath.create("/"))).isTrue();
    }

    @Test
    public void should_toString() {
        Assertions.assertThat(FrtjPath.create("/").toString()).isEqualTo("/");
        Assertions.assertThat(FrtjPath.create("/a").toString()).isEqualTo("/a");
        Assertions.assertThat(FrtjPath.create("/a/").toString()).isEqualTo("/a/");
    }

    @Test
    public void should_equal() {

        Assertions.assertThat(FrtjPath.create("/a/b/c/")).isEqualTo(FrtjPath.create("/a/b/c/"));
        Assertions.assertThat(FrtjPath.create("/")).isEqualTo(FrtjPath.create("/"));
        Assertions.assertThat(FrtjPath.create("/a")).isEqualTo(FrtjPath.create("/a"));
        Assertions.assertThat(FrtjPath.create("a")).isEqualTo(FrtjPath.create("a"));
        Assertions.assertThat(FrtjPath.create("/a/b/c")).isEqualTo(FrtjPath.create("/a/b/c"));
        Assertions.assertThat(FrtjPath.create("a/b/c")).isEqualTo(FrtjPath.create("a/b/c"));
        Assertions.assertThat(FrtjPath.create("a/b/c")).isEqualTo(FrtjPath.create("a/b/c"));

        Assertions.assertThat(FrtjPath.create("/a")).isNotEqualTo(FrtjPath.create("/a/"));
        Assertions.assertThat(FrtjPath.create("/a/b/c")).isNotEqualTo(FrtjPath.create("/a/"));
        Assertions.assertThat(FrtjPath.create("/a/b/c")).isNotEqualTo(FrtjPath.create("a/"));
    }
    
    @Test
    public void should_subpath() {
        Assertions.assertThat(FrtjPath.create("/a/b/c").subPath(0)).isEqualTo(FrtjPath.create("a/b/c"));
        Assertions.assertThat(FrtjPath.create("a/b/c").subPath(0)).isEqualTo(FrtjPath.create("a/b/c"));
        Assertions.assertThat(FrtjPath.create("/a/b/c").subPath(1)).isEqualTo(FrtjPath.create("b/c"));
        Assertions.assertThat(FrtjPath.create("/a/b/c").subPath(2)).isEqualTo(FrtjPath.create("c"));

        //test java nio
        //Path path = Paths.get("/a/b/c").subpath(0, 3);
        //System.out.println(path);
    }

    @Test
    public void should_fail_subpath() {
        try {
            FrtjPath.create("/a/b/c").subPath(3);
        } catch (RuntimeException e) {
            Assertions.assertThat(e.getMessage()).contains("outside boundary");
            return;
        }
        fail("should not get here");
    }

    @Test
    public void should_use_custom_parser() {
        FrtjPath expected = FrtjPath.create("/a/q22/");
        FrtjPath.getFactory().setParser(new CustomParser());
        FrtjPath frtjPath = FrtjPath.create("/a");

        // assert
        Assertions.assertThat(frtjPath).isEqualTo(expected);

        // cleanup
        FrtjPath.getFactory().setParser(new DefaultPathParser()); // reset
    }

    class CustomParser implements FrtjPathParser {

        private final DefaultPathParser parser;

        public CustomParser() {
            parser = new DefaultPathParser();
        }

        @Override
        public FrtjPath parsePath(String path) {
            if(path.endsWith("/"))
                path = path + "q22/";
            else
                path = path + "/q22/";
            return parser.parsePath(path);
        }
    }
}