package co.sentinel.lite.util;

public class AppConstants {
    // Tag used for logging
    public static final String TAG = "SENTINEL_LITE_DEBUG";

    public static final int POSITIVE_BUTTON = 1;
    public static final int NEGATIVE_BUTTON = -1;
    public static final int NEUTRAL_BUTTON = 0;

    // Keys used in SharedPreference
    public static final String PREFS_IS_NEW_DEVICE = "prefs_is_new_device";
    public static final String PREFS_IS_INFO_SHOWN = "prefs_is_info_shown";
    public static final String PREFS_IS_HELPER_SHOWN = "is_helper_shown";
    public static final String PREFS_CONFIG_PATH = "prefs_config_path";
    public static final String PREFS_VPN_ADDRESS = "prefs_vpn_address";
    public static final String PREFS_SESSION_NAME = "prefs_session_name";
    public static final String PREFS_CONNECTION_START_TIME = "prefs_connection_start_time_in_millis";
    public static final String PREFS_IP_ADDRESS = "prefs_ip_address";
    public static final String PREFS_IP_PORT = "prefs_ip_port";
    public static final String PREFS_VPN_TOKEN = "prefs_vpn_token";
    public static final String PREFS_SELECTED_LANGUAGE_CODE = "prefs_selected_language";
    public static final String PREFS_REF_ID = "prefs_ref_id";
    public static final String PREFS_FILE_URL = "prefs_file_url";
    public static final String PREFS_FILE_NAME = "prefs_file_name";
    public static final String PREFS_BRANCH_REFERRER_ID = "prefs_branch_referrer_id";

    // Request codes
    public static final int REQ_CODE_NULL = -1;
    public static final int REQ_CODE_PERMISSION = 100;
    public static final int REQ_VPN_USAGE = 101;
    public static final int REQ_LANGUAGE = 200;
    public static final int REQ_VPN_CONNECT = 201;
    public static final int REQ_HELPER_SCREENS = 202;

    // EXTRA used in Intent
    public static final String EXTRA_NOTIFICATION_ACTIVITY = "notification_activity";
    public static final String EXTRA_VPN_LIST = "vpn_list";
    public static final String EXTRA_REQ_CODE = "req_code";

    // Fragment Tags
    public static final String TAG_PROGRESS_DIALOG = "co.sentinel.sentinellite.progress_dialog";
    public static final String TAG_SINGLE_ACTION_DIALOG = "co.sentinel.sentinellite.single_action_dialog";
    public static final String TAG_DOUBLE_ACTION_DIALOG = "co.sentinel.sentinellite.double_action_dialog";
    public static final String TAG_TRIPLE_ACTION_DIALOG = "co.sentinel.sentinellite.triple_action_dialog";
    public static final String TAG_RATING_DIALOG = "co.sentinel.sentinellite.rating_dialog";
    public static final String SORT_BY_DIALOG_TAG = "co.sentinel.sentinellite.sort_by_dialog";
    public static final String TAG_ADDRESS_TO_CLAIM = "co.sentinel.sentinellite.address_to_claim";
    public static final String TAG_ERROR = "error";
    public static final String TAG_UPDATE = "update";
    public static final String TAG_DOWNLOAD = "download";
    public static final String TAG_LINK_ACCOUNT_CONFIRMATION = "link_account_confirmation";
    public static final String TAG_SORT_BY = "SORT_BY";

    // Error Constants
    public static final String ERROR_GENERIC = "error_generic";
    public static final String ERROR_VERSION_FETCH = "error_version_undefined";

    // Other app constants
    public static final int VALUE_DEFAULT = -1;
    public static final int REFERRAL_CODE_LENGHT = 13;
    public static final String CONFIG_NAME = "client.ovpn";
    public static final String URL_BUILDER = "http://%1s:%d/ovpn";
    public static final String DISCONNECT_URL_BUILDER = "http://%1s:%d/disconnect";
    public static final String HOME = "home";
    public static final double MAX_NODE_RATING = 5.0;

    // Branch URL constants
    public static final String BRANCH_REFERRAL_ID = "referralId";

    // Sort constants
    public static final String SORT_BY_DEFAULT = "0";
    public static final String SORT_BY_COUNTRY_A = "11";
    public static final String SORT_BY_COUNTRY_D = "12";
    public static final String SORT_BY_LATENCY_I = "21";
    public static final String SORT_BY_LATENCY_D = "22";
    public static final String SORT_BY_BANDWIDTH_I = "31";
    public static final String SORT_BY_BANDWIDTH_D = "32";
    public static final String SORT_BY_PRICE_I = "41";
    public static final String SORT_BY_PRICE_D = "42";
    public static final String SORT_BY_RATING_I = "51";
    public static final String SORT_BY_RATING_D = "52";
}