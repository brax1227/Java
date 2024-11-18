import java.util.*;

class SocialNetwork {
    private Map<String, List<String>> network;

    public SocialNetwork() {
        network = new HashMap<>();
    }

    public void addUser(String user) {
        network.putIfAbsent(user, new ArrayList<>());
    }

    public void addFriendship(String user1, String user2) {
        network.getOrDefault(user1, new ArrayList<>()).add(user2);
        network.getOrDefault(user2, new ArrayList<>()).add(user1);
    }

    public void suggestFriends(String user) {
        if (!network.containsKey(user)) {
            System.out.println("User not found.");
            return;
        }

        Set<String> mutualFriends = new HashSet<>();
        for (String friend : network.get(user)) {
            for (String friendOfFriend : network.get(friend)) {
                if (!friendOfFriend.equals(user) && !network.get(user).contains(friendOfFriend)) {
                    mutualFriends.add(friendOfFriend);
                }
            }
        }

        System.out.println("Friend suggestions for " + user + ": " + mutualFriends);
    }

    public void shortestPath(String user1, String user2) {
        if (!network.containsKey(user1) || !network.containsKey(user2)) {
            System.out.println("One or both users not found.");
            return;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> prev = new HashMap<>();
        Set<String> visited = new HashSet<>();
        queue.add(user1);
        visited.add(user1);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(user2)) {
                printPath(prev, user1, user2);
                return;
            }

            for (String neighbor : network.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    prev.put(neighbor, current);
                }
            }
        }

        System.out.println("No path found between " + user1 + " and " + user2 + ".");
    }

    private void printPath(Map<String, String> prev, String start, String end) {
        List<String> path = new LinkedList<>();
        for (String at = end; at != null; at = prev.get(at)) {
            path.add(0, at);
        }
        System.out.println("Shortest path from " + start + " to " + end + ": " + path);
    }

    public void displayNetwork() {
        System.out.println("Social Network:");
        for (Map.Entry<String, List<String>> entry : network.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

public class SocialNetworkGraph {
    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();

        network.addUser("Alice");
        network.addUser("Bob");
        network.addUser("Charlie");
        network.addUser("David");

        network.addFriendship("Alice", "Bob");
        network.addFriendship("Alice", "Charlie");
        network.addFriendship("Bob", "David");

        network.displayNetwork();

        network.suggestFriends("Alice");
        network.shortestPath("Alice", "David");
    }
}
