// Drill 05: the parallel stream that lost items. Make ThumbnailsTest pass. Keep it parallel.

import java.util.ArrayList;
import java.util.List;

public class Thumbnails {
    static String resize(int imageId) {
        return "thumb-" + imageId;            // stands in for real work
    }

    static List<String> makeThumbnails(List<Integer> ids) {
        List<String> results = new ArrayList<>();
        ids.parallelStream().forEach(i -> results.add(resize(i)));
        return results;
    }
}
