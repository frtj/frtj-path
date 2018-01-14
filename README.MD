## ** **Under Construction, Alpha version Status, everything may change anytime or not at all** **

A simple lib to represent a Path.

```
/ in beginning means root

/a this is a file

/a/ this is a directory because it ends with a /
```


#### Rationale:
This small library was made because I needed to represent a path to file that may or may not exists. It has unix/linux 
style paths to it, but it is not ment to follow any RFC or whatever. 

java.nio.file.Path is closely attached to the filesystem and the existing of real files and directories. I wanted something
that I could use without real stuff on disk.


#### Todo:
- Better support for relative paths
- Handle . and ..
- Joining/splitting of paths

#### Maven
```
<dependency>
  <groupId>no.frtj</groupId>
  <artifactId>frtj-path</artifactId>
  <version>0.1</version>
</dependency>
```

```
<repositories>
  <repository>
    <id>frtj-frtj-path</id>
    <url>https://packagecloud.io/frtj/frtj-path/maven2</url>
    <releases>
      <enabled>true</enabled>
    </releases>
    <snapshots>
      <enabled>true</enabled>
    </snapshots>
  </repository>
</repositories>
```