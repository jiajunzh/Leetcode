import java.util.*;

public class FavoriteGenres {
    public Map<String, List<String>> favoritegenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
        Map<String, List<String>> result = new HashMap<>();
        Map<String, List<String>> songToGenre = new HashMap<>();
        
        for (Map.Entry<String, List<String>> entry: genreMap.entrySet()) {
            String genre = entry.getKey();
            for (String song: entry.getValue()) {
                List<String> genreList = songToGenre.getOrDefault(song, new ArrayList<>());
                genreList.add(genre);
                songToGenre.put(song, genreList);
            }
        }
        
        for (String user: userMap.keySet()) {
            List<String> fvrtSong = userMap.get(user);
            int max = 0;
            Map<String, Integer> genreCount = new HashMap<>();
            List<String> genreOfUser = new ArrayList<>();
            for (String song: fvrtSong) {
                if (songToGenre.containsKey(song)) {
                    List<String> genreListOfSong = songToGenre.get(song);
                    for(String genreOfSong: genreListOfSong) {
                        int count = genreCount.getOrDefault(genreOfSong, 0) + 1;
                        if (count == max) {
                            genreOfUser.add(genreOfSong);
                        } else if (count > max) {
                            genreOfUser.clear();
                            max = count;
                            genreOfUser.add(genreOfSong);
                        }
                        genreCount.put(genreOfSong, count);
                    }
                }
                
            }
            result.put(user, genreOfUser);
        }
        return result;
    }
    public static void main(String[] args) {
        Map<String, List<String>> userMap = new HashMap<>();
        List<String> list1 = Arrays.asList("song1", "song2", "song3", "song4", "song8");
        List<String> list2 = Arrays.asList("song5", "song6", "song7");
        userMap.put("David", list1);
        userMap.put("Emma", list2);
    
        Map<String, List<String>> genreMap = new HashMap<>();
        List<String> list3 = Arrays.asList("song1", "song3");
        List<String> list4 = Arrays.asList("song7");
        List<String> list5 = Arrays.asList("song2", "song4");
        List<String> list6 = Arrays.asList("song5", "song6");
        List<String> list7 = Arrays.asList("song8", "song9");
        genreMap.put("Rock", list3);
        genreMap.put("Dubstep", list4);
        genreMap.put("Techno", list5);
        genreMap.put("Pop", list6);
        genreMap.put("Jazz", list7);
            
            /*Map<String, List<String>> userMap = new HashMap<>();
            List<String> list1 = Arrays.asList("song1", "song2");
            List<String> list2 = Arrays.asList("song3", "song4");
            userMap.put("David", list1);
            userMap.put("Emma", list2);
            
            Map<String, List<String>> genreMap = new HashMap<>();*/
    
        System.out.println(new FavoriteGenres().favoritegenre(userMap, genreMap));
    }
}
