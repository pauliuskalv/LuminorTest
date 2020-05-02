package lt.pauliusk.luminor.rest.request;

public interface RequestCompletedListener<T> {
    void requestComplete(T results);
}
