package myhealth.api;

/**
 * 検査例外をthrowできるFuncitonインターフェース
 *
 * @param <T>
 * @param <R>
 */
public interface ThrowableFunction<T, R> {
	R apply(T t) throws Exception;
}
