package com.hfocean.common.redis;

/**
 * 回调函数
 *
 * @author Leslie.Lam
 * @create 2017-06-21 19:18
 **/
public interface Function<E,F> {
    F callback(E e);
}
