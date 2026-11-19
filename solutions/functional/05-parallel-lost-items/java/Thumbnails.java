// Drill 05, solved: map then toList: the stream collects safely and keeps encounter order.

import java.util.List;

public class Thumbnails {
    static String resize(int imageId) {
        return "thumb-" + imageId;
    }

    static List<String> makeThumbnails(List<Integer> ids) {
        return ids.parallelStream().map(Thumbnails::resize).toList();
    }
}
