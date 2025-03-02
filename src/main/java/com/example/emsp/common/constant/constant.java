package com.example.emsp.common.constant;

public class constant {

    public static final String EMAID_REGX = "^[A-Z]{2}[\\dA-Z]{3}[\\dA-Z]{9}$";

    public static final String EMAID_COUNTRY_CODE_REGX = "^[A-Z]{2}$";

    public static final String EMAID_PROVIDER_ID_REGX = "^[\\dA-Z]{3}$";

    public static final String EMAID_INSTANCE_ID_REGX = "^[\\dA-Z]{9}$";

    public static final String FOUR_BYTES_RFID_REGX = "^[0-9A-F]{8}$";

    public static final String IPV4_PATTERN =
            "^(?:(?:\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(?:\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])$";

}
