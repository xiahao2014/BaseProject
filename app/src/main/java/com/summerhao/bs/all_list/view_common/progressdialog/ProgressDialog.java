package com.summerhao.bs.all_list.view_common.progressdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.summerhao.bs.utils.ToolResource;


/**
 * 自定义对话框
 * @author 曾繁添
 * @version 1.0
 */
public class ProgressDialog extends Dialog {

	/**消息TextView*/
	private TextView tvMsg ;

	public ProgressDialog(Context context, String strMessage) {
		this(context, ToolResource.getStyleId("CustomProgressDialog"),strMessage);
	}

	public ProgressDialog(Context context, int theme, String strMessage) {
		super(context, theme);
		this.setContentView(ToolResource.getLayoutId("view_progress_dialog"));
		this.getWindow().getAttributes().gravity = Gravity.CENTER;
	    tvMsg = (TextView) this.findViewById(ToolResource.getIdId("tv_msg"));
	    setMessage(strMessage);
	}

	/**
	 * 设置进度条消息
	 * @param strMessage 消息文本
	 */
	public void setMessage(String strMessage){
		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}
	}
}