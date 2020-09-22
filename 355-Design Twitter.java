import java.util.*;

class Twitter {
    int timeStamp;
    HashMap<Integer, User> userMap;

    /** Initialize your data structure here. */
    public Twitter() {
        timeStamp = 0;
        userMap = new HashMap<>();
    }

    class User {
        int userId;
        Set<Integer> following;
        Tweet head;

        public User(int uid) {
            userId = uid;
            following = new HashSet<>();
            following.add(uid);
        }

        public void follow(int uid) {
            following.add(uid);
        }

        public void unfollow(int uid) {
            following.remove((Integer) uid);
        }

        public void post(int tid) {
            Tweet t = new Tweet(tid);
            t.next = head;
            head = t;
        }
    }

    class Tweet {
        int tweetId;
        int time;
        Tweet next;

        public Tweet(int tid) {
            tweetId = tid;
            time = timeStamp++;
        }
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        userMap.get(userId).post(tweetId);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in
     * the news feed must be posted by users who the user followed or by the user
     * herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> ans = new ArrayList<>();
        if (userMap.containsKey(userId)) {
            Set<Integer> following = userMap.get(userId).following;
            PriorityQueue<Tweet> newsFeed = new PriorityQueue<>((a, b) -> b.time - a.time);
            for (int uid : following) {
                Tweet tweet = userMap.get(uid).head;
                if (tweet != null)
                    newsFeed.offer(tweet);
            }

            for (int i = 0; i < 10 && !newsFeed.isEmpty(); i++) {
                Tweet tweet = newsFeed.poll();
                ans.add(tweet.tweetId);
                if (tweet.next != null) {
                    newsFeed.offer(tweet.next);
                }
            }
        }
        return ans;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a
     * no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new User(followeeId));
        }
        userMap.get(followerId).follow(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a
     * no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId || !userMap.containsKey(followerId) || !userMap.containsKey(followeeId))
            return;
        userMap.get(followerId).unfollow(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such: Twitter obj =
 * new Twitter(); obj.postTweet(userId,tweetId); List<Integer> param_2 =
 * obj.getNewsFeed(userId); obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */