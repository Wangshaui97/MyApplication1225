package com.bawei.www.myapplication1225.Noto;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author
 * @date 2018/12/25
 */
//作用域的属性
@Target(ElementType.FIELD)
//运行时的时效
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MineAnnotation {
    int value();
}
