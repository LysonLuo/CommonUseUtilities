package util.use.common.api;

/**
 * API请求参数对象
 * Created by Lyson on 15/8/28.
 */
public class APIParam {
    private String key;
    private String value;

    public APIParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
