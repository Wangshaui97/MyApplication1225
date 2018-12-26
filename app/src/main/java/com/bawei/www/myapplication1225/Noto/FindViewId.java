package com.bawei.www.myapplication1225.Noto;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author
 * @date 2018/12/25
 */
public class FindViewId {
    public static void findById(final Activity activity){
        //反射属性
        //获取对应的类型
        Class<? extends Activity> aClass = activity.getClass();
        //获取该类型中所有的属性信息
        //Field[] declaredFields = activity.getClass().getDeclaredFields();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            declaredField.setAccessible(true);
            MineAnnotation annotation = declaredField.getAnnotation(MineAnnotation.class);
            if(annotation!=null){
                int value = annotation.value();
                View view = activity.findViewById(value);
                try {
                    declaredField.set(activity,view);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }
    /*//遍历 他们
        for (Field field:declaredFields){
            //01判断是否加的有我自定义注解的ID  先拿到注解
            MineAnnotation annotation = field.getAnnotation(MineAnnotation.class);
            //02 不等于空就是有了
            if(annotation!=null){
                //03判断该属性是否是属于View子类类型，并且不是静态属性
                if(View.class.isAssignableFrom(field.getType())
                        &&!Modifier.isStatic(field.getModifiers())){
                    try {
                        //拿到注解上的ID
                        int value = annotation.value();
                        //引用activity 的方法 (findViewById)
                        Method findViewById = aClass.getMethod("findViewById", int.class);
                        //把activity 和 id  反射进去
                        Object invoke = findViewById.invoke(activity, findViewById);
                        field.setAccessible(true);
                        field.set(activity,invoke);

                    }catch (Exception e){
                        Log.i("TAG",e+"");
                    }
                }
            }
        }*/
}
