package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1. Problem
 * Design a data structure that simulates an in-memory file system.
 *
 * Implement the FileSystem class:
 *
 * FileSystem() Initializes the object of the system.
 * List<String> ls(String path)
 * If path is a file path, returns a list that only contains this file's name.
 * If path is a directory path, returns the list of file and directory names in this directory.
 * The answer should in lexicographic order.
 * void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist.
 * If the middle directories in the path do not exist, you should create them as well.
 * void addContentToFile(String filePath, String content)
 * If filePath does not exist, creates that file containing given content.
 * If filePath already exists, appends the given content to original content.
 * String readContentFromFile(String filePath) Returns the content in the file at filePath.
 *
 * 2. Examples
 * Example 1
 * Input
 * ["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
 * [[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
 * Output
 * [null, [], null, null, ["a"], "hello"]
 *
 * Explanation
 * FileSystem fileSystem = new FileSystem();
 * fileSystem.ls("/");                         // return []
 * fileSystem.mkdir("/a/b/c");
 * fileSystem.addContentToFile("/a/b/c/d", "hello");
 * fileSystem.ls("/");                         // return ["a"]
 * fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
 *
 * 3. Constraints
 * 1 <= path.length, filePath.length <= 100
 * path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
 * You can assume that all directory names and file names only contain lowercase letters, and the same names will not exist in the same directory.
 * You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file content or list a directory or file that does not exist.
 * 1 <= content.length <= 50
 * At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.
 */
public class DesignInMemoryFileSystem {

    /**
     * 1. Approach
     * Trie
     *
     * This approach models both File and Directory as FileNode. However, another approach is to model only directory as
     * node and all files could be stored as a map from file name to content within the node itself. In the meanwhile,
     * we could have another map to store the linkage between directory and sub-directory
     */
    private final FileNode root;

    // - Data Structure Trie/Tree
    //   FileNode: relativePath/name (String), isDirectory(boolean), content(String), childrens(List<FileNode>)
    public DesignInMemoryFileSystem() {
        this.root = new FileNode("", true, "");
    }

    // String path -> List<String> all paths under this directory
    // File Path -> return file name
    // Dir Path -> return all paths under this dir =>
    // Q: for all the directory paths, do we need to just return the dir itself or all file paths under this dir? /a/b => c/ => fileA, fileB => only return /a/b/c?
    // Q: relative path or absolute path => absolute path => /a/b/c
    // The answer needs to be sorted in lexicographic order
    public List<String> ls(String path) {
        final String[] filePath = path.split("/");
        FileNode curr = this.root;
        for (int i = 1; i < filePath.length; i++) {
            curr = curr.childrens.get(filePath[i]);
        }
        if (curr.isDirectory) {
            return new ArrayList<>(curr.childrens.keySet());
        } else {
            return List.of(curr.name);
        }
    }

    // String path -> return void
    // Q: does it guarantee the given path will not exist? => Yes
    public void mkdir(String path) {
        final String[] filePath = path.split("/");
        FileNode curr = this.root;
        for (int i = 1; i < filePath.length - 1; i++) {
            if (!curr.childrens.containsKey(filePath[i])) {
                curr.childrens.put(filePath[i], new FileNode(filePath[i], true, ""));
            }
            curr = curr.childrens.get(filePath[i]);
        }
        final FileNode newDir = new FileNode(filePath[filePath.length - 1], true, "");
        curr.childrens.put(newDir.name, newDir);
    }

    // String filePath, String content -> return void
    // Q: will it be possible that the given file path happens to be a dir path? No
    public void addContentToFile(String filePath, String content) {
        final String[] filePaths = filePath.split("/");
        FileNode curr = this.root;
        for (int i = 1; i < filePaths.length - 1; i++) {
            curr = curr.childrens.get(filePaths[i]);
        }
        final FileNode newFile = curr.childrens.getOrDefault(filePaths[filePaths.length - 1], new FileNode(filePaths[filePaths.length - 1], false, ""));
        newFile.content.append(content);
        curr.childrens.put(newFile.name, newFile);
    }

    // String path -> String content
    public String readContentFromFile(String filePath) {
        final String[] filePaths = filePath.split("/");
        FileNode curr = this.root;
        for (int i = 1; i < filePaths.length; i++) {
            curr = curr.childrens.get(filePaths[i]);
        }
        return curr.content.toString();
    }

    private static class FileNode {
        private final String name;
        private final boolean isDirectory;
        private final StringBuilder content; // The content could be set as null if this is a directory
        private final Map<String, FileNode> childrens;

        private FileNode(final String name, final boolean isDirectory, final String content) {
            this.name = name;
            this.isDirectory = isDirectory;
            this.content = new StringBuilder(content);
            this.childrens = new TreeMap<>();
        }
    }
}
