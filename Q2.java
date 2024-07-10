import java.io.*;
import java.util.*;

public class Quiz4 {

    // Trie Node class
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
        int weight = 0;
        String word = "";
    }

    // Trie class
    static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Insert word into Trie
        public void insert(String word, int weight) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                current = current.children.computeIfAbsent(c, k -> new TrieNode());
            }
            current.isEndOfWord = true;
            current.weight = weight;
            current.word = word;
        }

        // Search words by prefix and return sorted results by weight
        public List<TrieNode> searchByPrefix(String prefix) {
            TrieNode current = root;
            for (char c : prefix.toCharArray()) {
                current = current.children.get(c);
                if (current == null) {
                    return Collections.emptyList();
                }
            }
            return collectAllWords(current);
        }

        // Collect all words in the Trie starting from the given node
        private List<TrieNode> collectAllWords(TrieNode node) {
            List<TrieNode> results = new ArrayList<>();
            if (node.isEndOfWord) {
                results.add(node);
            }
            for (TrieNode child : node.children.values()) {
                results.addAll(collectAllWords(child));
            }
            return results;
        }
    }

    public static void main(String[] args) throws IOException {


        // TODO: Use the first and the second command line argument (args[0] and args[1]) to read the database and the query file.
        // TODO: Your code goes here
        // TODO: Print the solution to STDOUT
        
        if (args.length < 2) {
            System.out.println("Please provide the database and query files as arguments.");
            return;
        }

        String databaseFile = args[0];
        String queryFile = args[1];

        Trie trie = new Trie();

        // Reading database file
        try (BufferedReader br = new BufferedReader(new FileReader(databaseFile))) {
            int N = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < N; i++) {
                String[] parts = br.readLine().split("\t");
                int weight = Integer.parseInt(parts[0].trim());
                String result = parts[1].trim().toLowerCase();
                trie.insert(result, weight);
            }
        }

        // Reading query file and processing queries
        try (BufferedReader br = new BufferedReader(new FileReader(queryFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                String query = parts[0].trim().toLowerCase();
                long limit = Long.parseLong(parts[1].trim());


                System.out.printf("Query received: \"%s\" with limit %d. Showing results:%n", query, limit);

                List<TrieNode> results = trie.searchByPrefix(query);
                results.sort((a, b) -> Integer.compare(b.weight, a.weight));

                if (results.isEmpty() || limit == 0) {
                    System.out.println("No results.");
                } else {
                    int count = 0;
                    for (TrieNode node : results) {
                        if (count >= limit) break;
                        System.out.printf("- %d %s%n", node.weight, node.word);
                        count++;
                    }
                }
            }
        }
    }
}
