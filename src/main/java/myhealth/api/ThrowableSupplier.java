package myhealth.api;

/**
 * 検査例外をthrow出来るsupplierインターフェース
 *
 * @param <R>
 */
public interface ThrowableSupplier<R> {
	R get() throws Exception;
}
