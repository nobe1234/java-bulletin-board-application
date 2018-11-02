package jp.co.sample.domain;

/**
 * 使用者が投稿するコメントを表すドメイン.
 * 
 * 記事と関連する記事idを保有.
 * 
 * @author soheinobe
 *
 */
public class Comment {

	/** 主キー */
	private Integer id;
	/** コメント投稿者名 */
	private String name;
	/** コメント内容 */
	private String content;
	/** 記事id */
	private Integer articleId;

	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}

	/** ゲッターとセッター */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
