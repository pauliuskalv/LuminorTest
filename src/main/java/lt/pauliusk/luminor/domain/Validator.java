package lt.pauliusk.luminor.domain;

public interface Validator<T> {
    public boolean isValid(T bean);
}
