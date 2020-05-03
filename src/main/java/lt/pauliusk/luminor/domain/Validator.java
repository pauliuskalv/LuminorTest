package lt.pauliusk.luminor.domain;

public interface Validator<T> {
    boolean isValid(T bean);
}
