package com.kovan.app.define;

public interface DefineCode {

    /*
     * Define code for Main Properties
     */

    public static final String SERVER_LISTEN_PORT 	= "SERVER_LISTEN_PORT";
    public static final String ENCODING           	= "ENCODING";
    public static final String SERVER_MODE          = "SERVER_MODE";
    public static final String SERVER_ID            = "SERVER_ID";

    public static final String FORMATTER_FL         = "FORMATTER_FL";
    public static final String FORMATTER_SP         = "FORMATTER_SP";

    public static final String KNS_HEADER_TEMPLATE_NAME   = "KNS_HEADER_TEMPLATE_NAME";

    public static final String SERVER_MODE_HP_01   = "KNSHP-01";
    public static final String SERVER_MODE_HP_02   = "KNSHP-02";
    public static final String SERVER_MODE_HP_03   = "KNSHP-03";
    public static final String SERVER_MODE_HP_04   = "KNSHP-04";
    public static final String SERVER_MODE_HP_05   = "KNSHP-05";
    public static final String SERVER_MODE_HP_06   = "KNSHP-06";
    public static final String SERVER_MODE_HP_07   = "KNSHP-07";
    public static final String SERVER_MODE_HP_08   = "KNSHP-08";
    public static final String SERVER_MODE_HP_09   = "KNSHP-09";


    public static final String MSG_TYPE_NETREG          = "1000";
    public static final String MSG_TYPE_HEALTHCHECK     = "8000";
    public static final String MSG_TYPE_SND_TERMINAL    = "SNDT";


    public static final int BOSS_GROUP_N_THREADS 	= 1;
    public static final int WORKER_GROUP_N_THREADS 	= 5;
    public static final int WORKER_BIZ_GROUP_THREAD_NO = 5;


    public static final String KNSAS_CONNECT_TIMEOUT    = "KNSAS_CONNECT_TIMEOUT";
    public static final String KNSAS_RECV_TIMEOUT       = "KNSAS_RECV_TIMEOUT";


    /*
     * Define Result Code
     */
    public static final int     RET_SUCCESS =  0;
    public static final int     RET_FAIL    =  -1;




    public static final String ERROR_EC0000 =  "0000";    /// 성공
    public static final String ERROR_EC5001 =  "5001";    /// 전문 인코딩 실패
    public static final String ERROR_EC5002 =  "5002";    /// 전문 디코딩 실패
    public static final String ERROR_EC5003 =  "5003";    /// 전문 암호화 실패
    public static final String ERROR_EC5004 =  "5004";    /// 전문 복호화 실패
    public static final String ERROR_EC5005 =  "5005";    /// 전문 개인정보 암호화 실패
    public static final String ERROR_EC5006 =  "5006";    /// 전문 개인정보 복호화 실패
    public static final String ERROR_EC5007 =  "5007";    /// 미등록 전문 및 URI
    public static final String ERROR_EC5008 =  "5008";    /// 인증 및 무결성 검증 실패(해시)
    public static final String ERROR_EC5009 =  "5009";    /// 필수항목 유효성 검증 실패
    public static final String ERROR_EC5010 =  "5010";    /// 전문 암복호화 키 취득 실패
    public static final String ERROR_EC5011 =  "5011";    /// Failover 처리 실패
    public static final String ERROR_EC5012 =  "5012";    /// 데이터 암복호화 키 취득 실패
    public static final String ERROR_EC5013 =  "5013";    /// 데이터 암호화 실패
    public static final String ERROR_EC5014 =  "5014";    /// 데이터 복호화 실패
    public static final String ERROR_EC5015 =  "5015";    /// 데이터 해시값 생성 실패
    public static final String ERROR_EC5016 =  "5016";    /// 키 생성 실패
    public static final String ERROR_EC5017 =  "5017";    /// 가맹점연동실패
    public static final String ERROR_EC5018 =  "5018";    /// 결제서버연동실패
    public static final String ERROR_EC5019 =  "5019";    /// 전문포맷오류
    public static final String ERROR_EC5020 =  "5020";    /// 모든결제서버연동실패
    public static final String ERROR_EC5021 =  "5021";    /// 데이타수신실패
    public static final String ERROR_EC9999 =  "9999";    /// 시스템에러

    public static final String ERROR_EC0000_MSG =  "성공";    /// 성공
    public static final String ERROR_EC5001_MSG =  "전문 인코딩 실패";
    public static final String ERROR_EC5002_MSG =  "전문 디코딩 실패";
    public static final String ERROR_EC5003_MSG =  "전문 암호화 실패";
    public static final String ERROR_EC5004_MSG =  "전문 복호화 실패";
    public static final String ERROR_EC5005_MSG =  "전문 개인정보 암호화 실패";
    public static final String ERROR_EC5006_MSG =  "전문 개인정보 복호화 실패";
    public static final String ERROR_EC5007_MSG =  "미등록 전문 및 URI";
    public static final String ERROR_EC5008_MSG =  "인증 및 무결성 검증 실패(해시)";
    public static final String ERROR_EC5009_MSG =  "필수항목 유효성 검증 실패";
    public static final String ERROR_EC5010_MSG =  "전문 암복호화 키 취득 실패";
    public static final String ERROR_EC5011_MSG =  "Failover 처리 실패";
    public static final String ERROR_EC5012_MSG =  "데이터 암복호화 키 취득 실패";
    public static final String ERROR_EC5013_MSG =  "데이터 암호화 실패";
    public static final String ERROR_EC5014_MSG =  "데이터 복호화 실패";
    public static final String ERROR_EC5015_MSG =  "데이터 해시값 생성 실패";
    public static final String ERROR_EC5016_MSG =  "키 생성 실패";
    public static final String ERROR_EC5017_MSG =  "가맹점연동실패";
    public static final String ERROR_EC5018_MSG =  "결제서버연동실패";
    public static final String ERROR_EC5019_MSG =  "전문포맷오류";
    public static final String ERROR_EC5020_MSG =  "모든결제서버연동실패";
    public static final String ERROR_EC5021_MSG =  "데이타수신실패";
    public static final String ERROR_EC9999_MSG =  "시스템에러";

    /*
     * 로드밸런스 항목명
     */
    public static final String KNSAS_FT_LOAD_BALANCER_CONFIG = "KNSAS_FT_LOAD_BALANCER_CONFIG";
}
