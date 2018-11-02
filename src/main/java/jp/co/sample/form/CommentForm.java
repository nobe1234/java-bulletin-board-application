package jp.co.sample.form;

/**
 * コメントの情報を表すフォーム.
 * 
 * コメントを投稿した方の名前、コメント内容、記事と紐づいている記事idを
 * 
 * リクエストパラメータとして受け取る。
 * 
 * @author soheinobe
 *
 */
public class CommentForm {

	/** コメント投稿者名 */
	private String name;
	/** コメント内容 */
	private String content;
	/** 記事id */
	private Integer articleId;

	@Override
	public String toString() {
		return "CommentForm [name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}

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

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

}
