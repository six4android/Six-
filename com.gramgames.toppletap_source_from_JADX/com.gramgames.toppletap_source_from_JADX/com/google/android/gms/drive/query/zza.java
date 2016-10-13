package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import java.util.List;
import org.json.simple.parser.Yytoken;

public class zza implements Creator<Query> {
    static void zza(Query query, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, query.mVersionCode);
        zzb.zza(parcel, 1, query.zzatV, i, false);
        zzb.zza(parcel, 3, query.zzatW, false);
        zzb.zza(parcel, 4, query.zzatX, i, false);
        zzb.zzb(parcel, 5, query.zzatY, false);
        zzb.zza(parcel, 6, query.zzatZ);
        zzb.zzc(parcel, 7, query.zzapB, false);
        zzb.zza(parcel, 8, query.zzarL);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzcj(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzee(i);
    }

    public Query zzcj(Parcel parcel) {
        boolean z = false;
        List list = null;
        int zzau = com.google.android.gms.common.internal.safeparcel.zza.zzau(parcel);
        boolean z2 = false;
        List list2 = null;
        SortOrder sortOrder = null;
        String str = null;
        LogicalFilter logicalFilter = null;
        int i = 0;
        while (parcel.dataPosition() < zzau) {
            int zzat = com.google.android.gms.common.internal.safeparcel.zza.zzat(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    logicalFilter = (LogicalFilter) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzat, LogicalFilter.CREATOR);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    sortOrder = (SortOrder) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzat, SortOrder.CREATOR);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    list2 = com.google.android.gms.common.internal.safeparcel.zza.zzD(parcel, zzat);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    z2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzat);
                    break;
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    list = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzat, DriveSpace.CREATOR);
                    break;
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    z = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzat);
                    break;
                case AdError.NETWORK_ERROR_CODE /*1000*/:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzat);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new Query(i, logicalFilter, str, sortOrder, list2, z2, list, z);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public Query[] zzee(int i) {
        return new Query[i];
    }
}
