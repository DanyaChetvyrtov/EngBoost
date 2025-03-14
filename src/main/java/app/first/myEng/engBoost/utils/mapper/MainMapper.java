package app.first.myEng.engBoost.utils.mapper;

public interface MainMapper<T1, T2> {
    T2 toDto(T1 entity);

    T1 toEntity(T2 dto);
}
