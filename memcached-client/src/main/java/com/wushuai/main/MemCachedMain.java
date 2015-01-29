package com.wushuai.main;

import com.sfebiz.product.entity.ProductDetail;
import com.wushuai.memcached.Duration;
import com.wushuai.memcached.KeyGenerator;
import com.wushuai.memcached.MemCachedManager;
import com.wushuai.memcached.TableName;
import com.wushuai.utils.Constant;

/**
 * @author gaochuanjun
 * @since 15/1/29
 */
public class MemCachedMain {
    public static void main(String[] args) {
        String key = KeyGenerator.buildKey(TableName.PRODUCT_DETAIL, TableName.ID, Constant.version, (long) 1, Duration.TEN_MIN.name());
        //ProductDetail productDetail1 = new ProductDetail();
        //productDetail1.setItemId(1);
        //MemCachedManager.getInstance().add(key, productDetail1);
        ProductDetail productDetail2 = (ProductDetail) MemCachedManager.getInstance().get(key);
        System.out.println(productDetail2.toString());
    }
}
