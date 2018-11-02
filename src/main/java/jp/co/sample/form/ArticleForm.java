package jp.co.sample.form;

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
	private String name;
	/** 記事名 */
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
