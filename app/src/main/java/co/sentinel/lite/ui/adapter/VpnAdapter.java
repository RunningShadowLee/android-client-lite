package co.sentinel.lite.ui.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import co.sentinel.lite.regional.World;
import com.haipq.android.flagkit.FlagImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import co.sentinel.lite.R;
import co.sentinel.lite.network.model.VpnListEntity;
import co.sentinel.lite.util.AppConstants;
import co.sentinel.lite.util.Convert;
import co.sentinel.lite.util.Converter;
import co.sentinel.lite.util.SpannableStringUtil;

public class VpnAdapter extends RecyclerView.Adapter<VpnAdapter.ViewHolder> {

    private OnItemClickListener mItemClickListener;

    private List<VpnListEntity> mData;
    private final Context mContext;
    private Application application;
    private SharedPreferences mPreferences;
    private String randomAddress,regionName;
    private int randomIndice,regionFlag,i,j,europeCount,naCount,asiaCount,saCount,africaCount,oceaniaCount,favCount;
    private List<VpnListEntity> europeData,naData,asiaData,saData,africaData,oceaniaData,oldData,rData,bData;

    public VpnAdapter(OnItemClickListener iListener, Context iContext, Application app) { //REVAMP fetch application context to initialize WORLD library
        mItemClickListener = iListener;
        mContext = iContext;
        application = app;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vpn, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VpnListEntity aItemData = mData.get(position);
        holder.mTvLocation.setText(aItemData.getLocation().country);

        // REVAMP initialize WORLD to fetch continents associated with countries
        World.init(application);
        //holder.mTvRegion.setText(World.getCountryFrom(aItemData.getLocation().country).getContinent());

        // Set country flag
        holder.mFvFlag.setCountryCode(Converter.getCountryCode(aItemData.getLocation().country));
        // Construct and set - Bandwidth SpannableString
        String aBandwidthValue = mContext.getString(R.string.vpn_bandwidth_value, Convert.fromBitsPerSecond(aItemData.getNetSpeed().download, Convert.DataUnit.MBPS));
        String aBandwidth = mContext.getString(R.string.vpn_bandwidth, aBandwidthValue);
        SpannableString aStyledBandwidth = new SpannableStringUtil.SpannableStringUtilBuilder(aBandwidth, aBandwidthValue)
                .color(ContextCompat.getColor(mContext, R.color.colorTextWhite))
                .customStyle(Typeface.BOLD)
                .build();
        holder.mTvBandwidth.setText(aStyledBandwidth);
        // Construct and set - Price SpannableString
        String aEncryptionValue = aItemData.getEncryptionMethod();
        String aEncryption = mContext.getString(R.string.vpn_enc_method, aEncryptionValue);
        SpannableString aStyledEncryption = new SpannableStringUtil.SpannableStringUtilBuilder(aEncryption, aEncryptionValue)
                .color(ContextCompat.getColor(mContext, R.color.colorTextWhite))
                .customStyle(Typeface.BOLD)
                .build();
        holder.mTvEncMethod.setText(aStyledEncryption);
        // Construct and set - Latency SpannableString
        String aLatencyValue = mContext.getString(R.string.vpn_latency_value, aItemData.getLatency());
        String aLatency = mContext.getString(R.string.vpn_latency, aLatencyValue);
        SpannableString aStyleLatency = new SpannableStringUtil.SpannableStringUtilBuilder(aLatency, aLatencyValue)
                .color(ContextCompat.getColor(mContext, R.color.colorTextWhite))
                .customStyle(Typeface.BOLD)
                .build();
        holder.mTvLatency.setText(aStyleLatency);
        // Construct and set - Node Version SpannableString
        String aVersion = mContext.getString(R.string.vpn_node_version, aItemData.getVersion());
        SpannableString aStyleVersion = new SpannableStringUtil.SpannableStringUtilBuilder(aVersion, aItemData.getVersion())
                .color(ContextCompat.getColor(mContext, R.color.colorTextWhite))
                .customStyle(Typeface.BOLD)
                .build();
        holder.mTvNodeVersion.setText(aStyleVersion);
        // Construct and set - Node Rating SpannableString
        String aRatingValue;
        if (aItemData.getRating() == 0.0) {
            aRatingValue = "N/A";
        } else {
            aRatingValue = String.format(Locale.getDefault(), "%.1f / %.1f", aItemData.getRating(), AppConstants.MAX_NODE_RATING);
        }
        String aRating = mContext.getString(R.string.vpn_node_rating, aRatingValue);
        SpannableString aStyleRating = new SpannableStringUtil.SpannableStringUtilBuilder(aRating, aRatingValue)
                .color(ContextCompat.getColor(mContext, R.color.colorTextWhite))
                .customStyle(Typeface.BOLD)
                .build();
        holder.mTvNodeRating.setText(aStyleRating);
        holder.mIbBookmark.setImageResource(aItemData.isBookmarked() ? R.drawable.ic_bookmark_active : R.drawable.ic_bookmark_inactive);
    }

    @Override
    public int getItemCount() {
        if (null == mData) return 0;
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mRootView;
        FlagImageView mFvFlag;
        TextView mTvLocation, mTvBandwidth, mTvEncMethod, mTvLatency, mTvNodeVersion, mTvNodeRating, mTvRegion;
        Button mBtnConnect;
        AppCompatImageButton mIbBookmark;

        ViewHolder(View itemView) {
            super(itemView);
            mRootView = itemView.getRootView();
            mFvFlag = itemView.findViewById(R.id.fv_flag);
            mTvLocation = itemView.findViewById(R.id.tv_location);
            mTvRegion = itemView.findViewById(R.id.tv_region);
            mTvBandwidth = itemView.findViewById(R.id.tv_bandwidth);
            mTvEncMethod = itemView.findViewById(R.id.tv_enc_method);
            mTvLatency = itemView.findViewById(R.id.tv_latency);
            mTvNodeVersion = itemView.findViewById(R.id.tv_node_version);
            mTvNodeRating = itemView.findViewById(R.id.tv_node_rating);
            mBtnConnect = itemView.findViewById(R.id.btn_connect);
            mIbBookmark = itemView.findViewById(R.id.ib_bookmark);

            // Set listeners
            mRootView.setOnClickListener(v -> onRootViewClick(mData.get(getAdapterPosition())));
            mBtnConnect.setOnClickListener(v -> onConnectClick(mData.get(getAdapterPosition()).getAccountAddress()));
            mIbBookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBookmarkClicked(mData.get(getAdapterPosition()));
                }
            });
        }
    }

    public void updateFavs(){
        Intent intent = new Intent("count");
        mContext.sendBroadcast(intent);
    }

    public void loadData(List<VpnListEntity> iData){
        if (mData == null||iData!=oldData){
            oldData = iData;
            rData = iData;
            mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            generateRandomAddress();
            scanRegion(iData);
            assignRegionCount();
            mData = iData;
            updateFavs();
            notifyDataSetChanged();
            }
        setRegion();
        }

        public String generateRandomAddress(){
            //REVAMP pick a random vpn address from the list for quick connect
            Random rand = new Random();
            if (rData.size() != 0) {
                randomIndice = rand.nextInt(rData.size());
                randomAddress = rData.get(randomIndice).getAccountAddress();
                mPreferences.edit().putString("randomNodeAddress", randomAddress).apply();
            }
            return randomAddress;
        }


        public void assignRegionCount(){
            mPreferences.edit().putInt("europeCount", europeCount).apply();
            mPreferences.edit().putInt("naCount", naCount).apply();
            mPreferences.edit().putInt("asiaCount", asiaCount).apply();
            mPreferences.edit().putInt("saCount", saCount).apply();
            mPreferences.edit().putInt("africaCount", africaCount).apply();
            mPreferences.edit().putInt("oceaniaCount", oceaniaCount).apply();
            mPreferences.edit().putInt("favCount",favCount).apply();
        }

        public void scanRegion(List<VpnListEntity> iData){
            World.init(application);
            europeData = new ArrayList<>();
            naData = new ArrayList<>();
            asiaData = new ArrayList<>();
            africaData = new ArrayList<>();
            saData = new ArrayList<>();
            oceaniaData = new ArrayList<>();
            bData = new ArrayList<>();
            europeCount = 0;
            asiaCount = 0;
            naCount = 0;
            africaCount = 0;
            saCount = 0;
            oceaniaCount = 0;
            favCount = 0;
            // Fetch number count and add nodes to their respective regions.
            for (i = 0; i < iData.size(); i++) {
                regionName = World.getCountryFrom(iData.get(i).getLocation().country).getContinent();

                //add data to favorites list if item is bookmarked
                if (iData.get(i).isBookmarked()){
                    bData.add(iData.get(i));
                    favCount++;
                }
                switch (regionName){
                    case "Europe":
                        europeCount++;
                        europeData.add(iData.get(i));
                        break;
                    case "North America":
                        naCount++;
                        naData.add(iData.get(i));
                        break;
                    case "Asia":
                        asiaCount++;
                        asiaData.add(iData.get(i));
                        break;
                    case "Africa":
                        africaCount++;
                        africaData.add(iData.get(i));
                        break;
                    case "South America":
                        saCount++;
                        saData.add(iData.get(i));
                        break;
                    case "Oceania":
                        oceaniaCount++;
                        oceaniaData.add(iData.get(i));
                        break;
                }
            }
        }

        public void setRegion(){
            regionFlag = mPreferences.getInt("regionFlag", 0);
            switch (regionFlag) {
                case 1:
                    mData = europeData;
                    break;
                case 2:
                    mData = asiaData;
                    break;
                case 3:
                    mData = naData;
                    break;
                case 4:
                    mData = africaData;
                    break;
                case 5:
                    mData = saData;
                    break;
                case 6:
                    mData = oceaniaData;
                    break;
                case 7:
                    mData = bData;
                    break;
            }
            notifyDataSetChanged();
        }


    // Interface interaction method
    private void onRootViewClick(VpnListEntity iItemData) {
        if (mItemClickListener != null) {
            mItemClickListener.onRootViewClicked(iItemData);
        }
    }

    private void onConnectClick(String iVpnAddress) {
        if (mItemClickListener != null) {
            mItemClickListener.onConnectClicked(iVpnAddress);
        }
    }

    private void onBookmarkClicked(VpnListEntity iItemData) {
        if (mItemClickListener != null) {
            mItemClickListener.onBookmarkClicked(iItemData);
            iItemData.setBookmarked(!iItemData.isBookmarked());
            notifyDataSetChanged();
        }
    }

    public interface OnItemClickListener {
        void onRootViewClicked(VpnListEntity iItemData);

        void onConnectClicked(String iVpnAddress);

        void onBookmarkClicked(VpnListEntity iItemData);
    }
}
