package jp.co.sample.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 記事投稿の情報を表すフォーム.
 * 
 * 投稿者名、投稿された記事名をリクエストパラメータを受け取る。
 * 
 * @author soheinobe
 *
 */
public class ArticleForm {

	/** 投稿者名 */
	@NotBlank(message="投稿者名は必須です。")
	private String name;
	/** 記事名 */
	@NotBlank(message="投稿内容は必須です。")
	@Size(min=2,max=50,message="2文字以上、５０文字以下で入力してください。")
	private String content;

	/** ゲッターとセッター */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
