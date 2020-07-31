package com.x.atrs.util.encryption.sm4;

/**
 * All rights Reserved, Designed By 云安宝
 *
 * @author xuewenke
 * @version V3.0
 * @Package util.encryption.sm4
 * @Description (用一句话描述该文件做什么)
 * @date 2018/11/13 10:28 AM
 * @Copyright 2018 www.yunanbao.com.cn Inc. All rights reserved.
 * <p>
 * <p>
 * <p>
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 */
public class SM4_Context {

    public int mode;

    public long[] sk;

    public boolean isPadding;

    public SM4_Context() {
        this.mode = 1;
        this.isPadding = true;
        this.sk = new long[32];
    }

}
