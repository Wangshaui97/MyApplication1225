package com.bawei.www.myapplication1225;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.lang.reflect.Constructor;


public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView draweeView, draweeView2;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initImg();
        initTest();

    }

    private void initTest() {

        //通过反射获取包名类名
        Person person = new Person();
        System.out.println("包名为:" + person.getClass().getPackage().getName());
        System.out.println("完整的包名为:" + person.getClass().getName());
        //换行
        System.out.println("↑↑↑↑↑↑↑↑↑↑方法1===============================================");


        //所有的类都是Class类的实例对象
        Class<?> class1 = null;
        Class<?> class2 = null;
        try {
            class1 = Class.forName("com.bawei.www.myapplication1225.Person");
            System.out.println("(class1)包名为:" + class1.getPackage().getName());
            System.out.println("(class1)完整的包名为:" + class1.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        class2 = Person.class;
        System.out.println("(class2)包名为:" + class1.getPackage().getName());
        System.out.println("(class2)完整的包名为:" + class1.getName());

        //换行
        System.out.println("↑↑↑↑↑↑↑↑↑↑方法2===============================================");

        //用反射来给Class创建类对象
        Class<?> class3 = null;
        try {
            class3 = Class.forName("com.bawei.www.myapplication1225.Person");
            Person person1 = (Person) class3.newInstance();
            person1.setName("ws");
            person1.setAge(1998);
            System.out.println("(class3)   " + person1.getName() + "       (class3)" + person1.getAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //换行
        System.out.println("↑↑↑↑↑↑↑↑↑↑方法3===============================================");


        //对带参的构造函数对象  使用反射创建
        Class<?> class4 = null;
        Person person2 = null;
        Person person3 = null;

        try {
            class4 = Class.forName("com.bawei.www.myapplication1225.Person");
            Constructor<?>[] constructors = class4.getConstructors();
            person2 = (Person) constructors[0].newInstance();

            person2.setName("WS");
            person2.setAge(1997);

            person3 = (Person) constructors[0].newInstance("WS", 1997);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("(class4)   person2   :" + person2.getName() + "       (class4)" + person2.getAge());

        //System.out.println("(class4)   person3   :" + person3.getName() + "       (class4)" + person3.getAge());

        //换行
        System.out.println("↑↑↑↑↑↑↑↑↑↑方法4===============================================");


    }


    private void initImg() {
        uri = Uri.parse("http://m.xxxiao.com/wp-content/uploads/sites/3/2015/06/m.xxxiao.com_4f5d4bcd03ee2ef0c60bfc0e17a00ea6-760x500.jpg");
        draweeView = findViewById(R.id.img);
        draweeView.setImageURI(uri);

        draweeView2 = findViewById(R.id.img2);
        draweeView2.setImageURI(uri);

        showUrlBlur(draweeView, uri);
    }

    private void showUrlBlur(SimpleDraweeView draweeView, Uri uri) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setPostprocessor(new IterativeBoxBlurPostProcessor(6, 6))
                .build();
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(draweeView.getController())
                .setImageRequest(request)
                .build();
        draweeView.setController(controller);
    }


}
