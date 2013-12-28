package korgeek.aradon.common.unit;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ResourceBundle 을 사용하는 메시지 정보. 
 */
public class Bundle {
    /** 메시지 프로퍼티 파일 명칭 : 'message.properties' */
    public static final String MESSAGE_FILENAME = "message";
    
    /** 번들명칭 */
    private final String name;
    /** 로케일 */
    private Locale locale;
    /** ResourceBundle */
    private transient static ResourceBundle bundle;

    /**
     * Constructor.
     * 번들명칭은 이 클래스 또는 이를 상속한 객체의 패키지명.message 이 된다.
     * 해당 패키지내에 message.properties 및 Locale 별 메시지 파일이 있어야 한다.
     */
    public Bundle() {
        this.name = this.getClass().getPackage().getName() + "." + MESSAGE_FILENAME;
        this.locale = Locale.getDefault();
        initBundle();
    }

    /**
     * 이름 지정 Constructor.
     * @param name 이름.
     */
    public Bundle(String name) {
        this.name = name;
        this.locale = Locale.getDefault();
        initBundle();
    }
    
    /**
     * Locale 지정 Constructor.
     * 번들명칭은 이 클래스 또는 이를 상속한 객체의 패키지명.message 에 해당한다.  
     * 해당 패키지내에 message.properties 및 Locale 별 메시지 파일이 있어야 한다.
     * @param locale Locale
     */
    public Bundle(Locale locale) {
        this.name = this.getClass().getPackage().getName() + "." + MESSAGE_FILENAME;
        this.locale = locale;
        initBundle();
    }

    /**
     * 이름, Locale 지정 Constructor.
     * @param name 이름
     * @param locale Locale
     */
    public Bundle(String name,Locale locale) {
        this.name = name;
        this.locale = locale;
        initBundle();
    }
    
    /**
     * 초기화.
     */
    private synchronized void initBundle() {
        if ( this.bundle == null ) { 
            this.bundle = ResourceBundle.getBundle(name,locale);
        }
    }
    
    /**
     * 명칭 반환.
     * @return 명칭.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Locale 설정
     * @param locale Locale
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        this.bundle = null;
        initBundle();
    }
    
    /**
     * Locale 반환.
     * @return Locale
     */
    public Locale getLocale() {
        return this.locale;
    }
    
    /**
     * ResourceBundle 반환.
     * @return 리소스번들
     */
    public ResourceBundle getResourceBundle() {
        initBundle();
        return this.bundle;
    }
    
    /**
     * 메시지 반환.
     * @param key 키
     * @return 메시지
     */
    public String getMessage(String key) {
        initBundle();
        return this.bundle.getString(key);
    }

    /**
     * 메시지 반환.
     * @param key 키
     * @param arg 아규먼트
     * @return 메시지
     */
    public String getMessage(String key, Object arg) {
        return getMessage(key,new Object[]{arg});
    }

    /**
     * 메시지 반환.
     * @param key 키
     * @param arg1 아규먼트
     * @param arg2 아큐먼트
     * @return 메시지
     */
    public String getMessage(String key, Object arg1, Object arg2) {
        return getMessage(key,new Object[]{arg1,arg2});
    }

    /**
     * 메시지 반환.
     * @param key 키
     * @param arg1 아규먼트
     * @param arg2 아규먼트
     * @param arg3 아규먼트
     * @return 메시지
     */
    public String getMessage(String key, Object arg1, Object arg2, Object arg3) {
        return getMessage(key,new Object[]{arg1,arg2,arg3});
    }

    /**
     * 메시지 반환.
     * @param key 키
     * @param arg1 아규먼트
     * @param arg2 아규먼트
     * @param arg3 아규먼트
     * @param arg4 아규먼트
     * @return 메시지
     */
    public String getMessage(String key, Object arg1, Object arg2, Object arg3, Object arg4) {
        return getMessage(key,new Object[]{arg1,arg2,arg3,arg4});
    }
    
    /**
     * 메시지 반환.
     * @param key 키
     * @param args 아규먼트
     * @return 메시지
     */
    public String getMessage(String key, Object[] args) {
        return MessageFormat.format(getMessage(key),args);
    }
    
    /**
     * 키목록 반환.
     * @return 키목록
     */
    public Enumeration getKeys() {
        return this.bundle.getKeys();
    }
}
