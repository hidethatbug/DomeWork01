package com.bawei.domework01;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * Author:程金柱
 * Date:2019/7/6 16:42
 * Description：
 */

public class BaseGlide {
    public static void getGlide(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions();

        Glide.with(context).load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .apply(options).into(imageView);

    }
}
