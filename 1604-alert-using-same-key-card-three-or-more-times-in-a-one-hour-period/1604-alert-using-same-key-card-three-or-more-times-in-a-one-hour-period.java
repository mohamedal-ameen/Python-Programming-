import java.util.*;

class Solution {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        // Map each user to a list of their entry times (in minutes from 00:00)
        Map<String, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < keyName.length; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            
            // Convert "HH:MM" into total minutes
            int minutes = Integer.parseInt(time.substring(0, 2)) * 60 
                        + Integer.parseInt(time.substring(3, 5));
            
            map.putIfAbsent(name, new ArrayList<>());
            map.get(name).add(minutes);
        }
        
        List<String> result = new ArrayList<>();
        
        for (String name : map.keySet()) {
            List<Integer> times = map.get(name);
            Collections.sort(times); // Sort times to check 1-hour windows easily
            
            // Check if any 3 consecutive uses happen within a 60-minute window
            for (int i = 2; i < times.size(); i++) {
                if (times.get(i) - times.get(i - 2) <= 60) {
                    result.add(name);
                    break; // Found an alert for this user, move to the next
                }
            }
        }
        
        // Return names sorted alphabetically as required by the problem
        Collections.sort(result);
        return result;
    }
}