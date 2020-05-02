package lt.pauliusk.luminor.rest.dto;

public interface BeanConverter<T1, T2 extends BaseDTO> {
    T2 convertBeanToDTO(T1 bean);
    T1 convertDTOToBean(T2 dto);
}
