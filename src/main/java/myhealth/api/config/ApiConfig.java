package myhealth.api.config;

/**
 * API全体の設定値クラス
 * static参照前提なのでBeanに登録しない
 */
public class ApiConfig {
	public static final byte RESULT_SUCCESS = 0;
	public static final byte RESUTL_ERROR = 1;
	public static final String yyyyMMddPattern = "yyyyMMdd";
	public static final String uuuuMMddPattern  = "uuuuMMdd";
	public static final byte[] TIME_ZONE = {1,2,3};
}
