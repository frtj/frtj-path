## ** **Under Construction, Alpha version Status** **

#### Rationale:
This small library was made because I needed to handle some path stuff in URL 
in one of my private projects, and decided to roll my own code.

It is inspired by Java Nio2 Path. While java.util.Path is closely linked to 
operating system files and paths and can represent Windows and *Nix, this library tries to be more generic.


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