package com.lzy.common.tool;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.jollycorp.android.libs.common.test.R;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * desc: {@link ToolView} 单元测试类 <br/>
 * time: 2018/8/23 上午11:19 <br/>
 * author: 匡衡 <br/>
 * since V 1.2
 */
public class ToolViewTest {

    /* 空视图 */
    private final TextView nullTextView = null;

    private TextView textView1;
    private TextView textView2;
    private Context context;

    @Before
    public void setup() {
        context = getInstrumentation().getContext();
        textView1 = new TextView(context);
        textView2 = new TextView(context);
    }

    /**
     * {@link ToolView#setOnClickListener(View.OnClickListener, View...)}单元测试
     */
    @Test
    public void setOnClickListener() {
        View.OnClickListener onClickListener = v -> {
            if (v == textView1) {
                ((TextView) v).setText("textView1");
            } else if (v == textView2) {
                ((TextView) v).setText("textView2");
            }
        };

        //空测试
        ToolView.setOnClickListener(null, textView1, textView2);
        //空测试
        ToolView.setOnClickListener(onClickListener);
        //正常测试
        ToolView.setOnClickListener(onClickListener, textView1, nullTextView, textView2);
        textView1.callOnClick();
        assertEquals("textView1", textView1.getText());
        textView2.callOnClick();
        assertEquals("textView2", textView2.getText());
    }

    /**
     * {@link ToolView#setText(TextView, Object)}单元测试
     */
    @Test
    public void setText() {
        String test = context.getResources().getString(R.string.test);
        String appName = context.getResources().getString(R.string.appName);
        CharSequence c = "test_charSequence";

        //空测试
        ToolView.setText(nullTextView, null);
        //空测试
        ToolView.setText(nullTextView, test);
        //空测试
        ToolView.setText(textView1, null);

        //正常测试:直接设值String资源文件
        ToolView.setText(textView1, R.string.test);
        assertEquals(test, textView1.getText());
        //正常测试:直接设值字符串
        ToolView.setText(textView2, appName);
        assertEquals(appName, textView2.getText());
        //正常测试:直接设值CharSequence
        ToolView.setText(textView1, c);
        assertEquals(c, textView1.getText());
    }

    /**
     * {@link ToolView#setTextColor(Context, int, TextView...)}单元测试
     */
    @Test
    public void setTextColor() {
        //空测试
        ToolView.setTextColor(null, R.color.colorAccent);
        //空测试
        ToolView.setTextColor(context, R.color.colorAccent);

        //正常测试
        int color = ContextCompat.getColor(context, R.color.colorAccent);
        ToolView.setTextColor(context, R.color.colorAccent, textView1, nullTextView, textView2);
        assertEquals(ColorStateList.valueOf(color), textView1.getTextColors());
        assertEquals(ColorStateList.valueOf(color), textView2.getTextColors());
    }

    /**
     * {@link ToolView#setTextColor(String, TextView...)}单元测试
     */
    @Test
    public void setTextColor1() {
        String color7 = "#FFFFFF";
        String color9 = "#FF3F51B5";
        //空测试
        ToolView.setTextColor(null, nullTextView);
        //空测试
        ToolView.setTextColor(color7, nullTextView);

        //正常测试:7位色值
        ToolView.setTextColor(color7, textView1, nullTextView, textView2);
        assertEquals(ColorStateList.valueOf(Color.parseColor(color7)), textView1.getTextColors());
        assertEquals(ColorStateList.valueOf(Color.parseColor(color7)), textView2.getTextColors());
        //正常测试:9位色值
        ToolView.setTextColor(color9, textView1, nullTextView, textView2);
        assertEquals(ColorStateList.valueOf(Color.parseColor(color9)), textView1.getTextColors());
        assertEquals(ColorStateList.valueOf(Color.parseColor(color9)), textView2.getTextColors());

        //异常数据测试：不以'#'开头
        ToolView.setTextColor("1234556", textView1);
        assertEquals(ColorStateList.valueOf(Color.parseColor(color9)), textView1.getTextColors());
        //异常数据测试：不满足色值位数
        ToolView.setTextColor("134556", textView1);
        assertEquals(ColorStateList.valueOf(Color.parseColor(color9)), textView1.getTextColors());
    }

    /**
     * {@link ToolView#showView(View...)}单元测试
     */
    @Test
    public void showView() {
        //空测试
        ToolView.showView(nullTextView);

        //正常测试
        ToolView.showView(textView1, nullTextView, textView2);
        assertTrue(textView1.getVisibility() == View.VISIBLE);
        assertTrue(textView2.getVisibility() == View.VISIBLE);
    }

    /**
     * {@link ToolView#hideView(View...)}单元测试
     */
    @Test
    public void hideView() {
        //空测试
        ToolView.hideView(nullTextView);

        //正常测试
        ToolView.hideView(textView1, nullTextView, textView2);
        assertTrue(textView1.getVisibility() == View.GONE);
        assertTrue(textView2.getVisibility() == View.GONE);
    }

    /**
     * {@link ToolView#setVisibility(int, View...)}单元测试
     */
    @Test
    public void setVisibility() {
        //空测试
        ToolView.setVisibility(View.GONE, nullTextView);
        //异常数据测试
        ToolView.setVisibility(11, textView1);

        //正常测试：View.VISIBLE
        ToolView.setVisibility(View.VISIBLE, textView1, nullTextView, textView2);
        assertTrue(textView1.getVisibility() == View.VISIBLE);
        assertTrue(textView2.getVisibility() == View.VISIBLE);
        //正常测试：View.GONE
        ToolView.setVisibility(View.GONE, textView1, nullTextView, textView2);
        assertTrue(textView1.getVisibility() == View.GONE);
        assertTrue(textView2.getVisibility() == View.GONE);
        //正常测试：View.INVISIBLE
        ToolView.setVisibility(View.INVISIBLE, textView1, nullTextView, textView2);
        assertTrue(textView1.getVisibility() == View.INVISIBLE);
        assertTrue(textView2.getVisibility() == View.INVISIBLE);
    }

    /**
     * {@link ToolView#setTag(View, Object)}单元测试
     */
    @Test
    public void setTag() {
        Object obj = String.valueOf("test");
        //空测试
        ToolView.setTag(nullTextView, obj);

        //空测试
        ToolView.setTag(textView1, null);
        assertNull(textView1.getTag());

        //正常测试
        ToolView.setTag(textView2, obj);
        assertEquals(obj, textView2.getTag());
    }

    /**
     * {@link ToolView#setTag(View, int, Object)}单元测试
     */
    @Test
    public void setTag1() {
        Object obj = String.valueOf("test");
        //空测试
        ToolView.setTag(nullTextView, R.id.tag_key_test, obj);

        //异常测试:key不是ids资源的中id值
        ToolView.setTag(textView1, 1, obj);
        assertNull(textView1.getTag(1));

        //异常测试:Tag值为空
        ToolView.setTag(textView1, R.id.tag_key_test, null);
        assertNull(textView1.getTag(R.id.tag_key_test));

        //正常测试
        ToolView.setTag(textView2, R.id.tag_key_test, obj);
        assertEquals(obj, textView2.getTag(R.id.tag_key_test));

    }

    /**
     * {@link ToolView#setBackgroundResource(View, int)}单元测试
     */
    @Test
    public void setBackgroundResource() {
        //空测试
        ToolView.setBackgroundResource(nullTextView, R.drawable.axess_card_logo);

        Drawable drawable = context.getDrawable(R.drawable.axess_card_logo);

        //正常测试
        ToolView.setBackgroundResource(textView1, R.drawable.axess_card_logo);
        assertEquals(drawable.getMinimumHeight(), textView1.getBackground().getMinimumHeight());
    }

    /**
     * {@link ToolView#setBackgroundColor(View, int)} 单元测试
     */
    @Test
    public void setBackgroundColor() {
        //空测试
        ToolView.setBackgroundColor(nullTextView, R.color.colorAccent);

        Drawable drawable = context.getDrawable(R.color.colorAccent);

        //正常测试
        ToolView.setBackgroundColor(textView1, R.color.colorAccent);
        assertEquals(drawable.getState(), textView1.getBackground().getState());
    }
}