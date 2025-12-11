package org.modules.SystemDesign.FileSystem;
/*
You need to design an in-memory file storage system, similar to a simplified Dropbox / Google Drive.

FR:
1. File upload
2. Maintain directories
3. Different kind of media type (.docx, .txt, .pdf)
4. create, read, delete, move


NFR:
1. access level
2. limit of storage
3. sharing

1. deserializable -> object
2. directly storing in disk space

fileId -> UUID -> fetch object -> serialize

millions of req per sec

 */

import java.util.*;

class FileSystem {
    class Node {
        boolean isFile = false; // folder or file
        String content = ""; //
        Map<String, Node> children = new TreeMap<>(); // children tree
    }
    private Node root;

    // a/b/c
    // a/b/c/b
    public FileSystem() {
        root = new Node();
    }

    public void uploadFile(String path, String content) {
        String[] dir = path.split("/");
        String filename = dir[dir.length-1];

        Node curr = root;

        for(int i=1; i< dir.length-1; i++){
            System.out.println(dir[i]);

            if(curr.isFile) {
                System.out.println("curr node is folder");
                return;
            }

            if (!curr.children.containsKey(dir[i])) {
                Node child = new Node();
                curr.children.putIfAbsent(dir[i], child);
            }
            curr = curr.children.get(dir[i]);
        }

        Node file = new Node();
        file.isFile = true;
        file.content = content;
        curr.children.putIfAbsent(filename, file);
    }
    public List<String> ls(String path) {
        if (path.equals("/")) {
            return new ArrayList<>(root.children.keySet());
        }

        String[] parts = path.split("/");
        Node curr = root;


        for (int i = 1; i < parts.length; i++) {
            curr = curr.children.get(parts[i]);
            if (curr == null) return new ArrayList<>();
            if (curr.isFile) {
                return List.of(parts[parts.length - 1]);
            }

        }

        return new ArrayList<>(curr.children.keySet());
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        fs.uploadFile("/a/b/c.txt", "hello world");
        fs.uploadFile("/a/b/d.txt", "another file");
        fs.uploadFile("/x/y", "folderFile");
        System.out.println(fs.ls("/"));
        System.out.println(fs.ls("/a"));
        System.out.println(fs.ls("/a/b"));
        System.out.println(fs.ls("/a/b/c.txt"));
        System.out.println(fs.ls("/x"));
        System.out.println(fs.ls("/x/y"));
    }
}


