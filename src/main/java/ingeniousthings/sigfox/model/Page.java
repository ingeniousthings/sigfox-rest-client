package ingeniousthings.sigfox.model;

import java.util.List;
import java.util.stream.Stream;

public class Page<T> {

    private List<T> data;
    private Paging paging;


    private class Paging {

        private String next;
    }

    public boolean hasContent() {
        return !data.isEmpty();
    }

    public List<T> getContent() {
        return data;
    }

    public Stream<T> getContentAsStream() {
        return data.stream();
    }

    public int getSize() {
        return data.size();
    }

    public boolean hasNext() {
        return paging != null;
    }

    public String getNextLink() {
        return paging.next;
    }

    @Override
    public String toString() {
        return "Page{" +
            "data=" + data +
            ", paging=" + paging +
            '}';
    }
}
