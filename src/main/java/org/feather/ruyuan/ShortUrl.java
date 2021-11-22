package org.feather.ruyuan;

/**
 * @program: concurrency
 * @description:长链转换成短链
 * @author: 杜雪松(feather)
 * @since: 2021-11-22 11:03
 **/
public class ShortUrl {

    private static final String[] X36_ARRAY = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");

    public String getShortUrl(String url){
        long shortUrlSeed=jedis.incr("short_url_seed");
        StringBuffer buffer=new StringBuffer();
        while (shortUrlSeed>0){
            buffer.append(X36_ARRAY[(int)(shortUrlSeed % 36)]);
            shortUrlSeed/=shortUrlSeed;
        }
        //获取到短链
        return  buffer.reverse().toString();
    }
}
