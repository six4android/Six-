package com.google.android.gms.games.appcontent;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import org.json.simple.parser.Yytoken;

public class AppContentTupleEntityCreator implements Creator<AppContentTupleEntity> {
    static void zza(AppContentTupleEntity appContentTupleEntity, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zza(parcel, 1, appContentTupleEntity.getName(), false);
        zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, appContentTupleEntity.getVersionCode());
        zzb.zza(parcel, 2, appContentTupleEntity.getValue(), false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzek(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgm(i);
    }

    public AppContentTupleEntity zzek(Parcel parcel) {
        String str = null;
        int zzau = zza.zzau(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    str2 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                case AdError.NETWORK_ERROR_CODE /*1000*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new AppContentTupleEntity(i, str2, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public AppContentTupleEntity[] zzgm(int i) {
        return new AppContentTupleEntity[i];
    }
}
