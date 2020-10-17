import java.io.File;
import java.util.*;

class FileSystem {

    private Map<String, TreeSet<String>> directories;
    private Map<String, StringBuilder> files;

    public FileSystem() {
        directories = new HashMap<>();
        files = new HashMap<>();
        directories.put("/", new TreeSet());
    }

    public List<String> ls(String path) {
        if (files.containsKey(path)) {
            int index = path.lastIndexOf("/");
            return Arrays.asList(path.substring(index + 1));
        } else {
            return new ArrayList<>(directories.get(path + (path.equals("/") ? "" : "/")));
        }
    }

    public void mkdir(String path) {
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '/') {
                String dir = path.substring(0, i + 1);
                directories.putIfAbsent(dir, new TreeSet());
                int next = path.indexOf("/", i + 1);
                next = next == -1 ? path.length() : next;
                directories.get(dir).add(path.substring(i + 1, next));
            }
        }
    }

    public void addContentToFile(String filePath, String content) {
        int index = filePath.lastIndexOf("/");
        String dir = filePath.substring(0, index + 1);
        String fileName = filePath.substring(index + 1);
        directories.get(dir).add(fileName);
        files.putIfAbsent(filePath, new StringBuilder());
        files.get(filePath).append(content);
    }

    public String readContentFromFile(String filePath) {
        return files.get(filePath).toString();
    }
}

/**
 * Your FileSystem object will be instantiated and called as such: FileSystem
 * obj = new FileSystem(); List<String> param_1 = obj.ls(path); obj.mkdir(path);
 * obj.addContentToFile(filePath,content); String param_4 =
 * obj.readContentFromFile(filePath);
 */

class FileSystemII {

    // use Trie idea to build File class
    static class FileNode {
        Map<String, FileNode> children;
        boolean isFile;
        String content;

        FileNode() {
            this.children = new HashMap<>();
            this.isFile = false;
            this.content = "";
        }
    }

    private FileNode root;

    public FileSystemII() {
        root = new FileNode();
    }

    public List<String> ls(String path) {
        String[] dirs = path.split("/");
        FileNode cur = root;
        List<String> res = new ArrayList<>();
        String name = "";
        for (String dir : dirs) {
            if (dir.length() == 0) {
                continue;
            }
            if (!cur.children.containsKey(dir)) {
                return res;
            }
            cur = cur.children.get(dir);
            name = dir;
        }
        if (cur.isFile) {
            res.add(name);
        } else {
            for (String s : cur.children.keySet()) {
                res.add(s);
            }
        }
        Collections.sort(res);
        return res;
    }

    public void mkdir(String path) {
        String[] dirs = path.split("/");
        search(dirs);
    }

    public void addContentToFile(String filePath, String content) {
        String[] dirs = filePath.split("/");
        FileNode cur = search(dirs);
        cur.isFile = true;
        cur.content += content;
    }

    public String readContentFromFile(String filePath) {
        String[] dirs = filePath.split("/");
        FileNode cur = search(dirs);
        return cur.content;
    }

    private FileNode search(String[] dirs) {
        FileNode cur = root;
        for (String dir : dirs) {
            if (dir.length() == 0) {
                continue;
            }
            if (!cur.children.containsKey(dir)) {
                FileNode f = new FileNode();
                cur.children.put(dir, f);
            }
            cur = cur.children.get(dir);
        }
        return cur;
    }
}