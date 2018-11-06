package jp.co.sample.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

	@NotBlank(message = "コメント者名は必須です。")
	private String name;
	/** コメント内容 */
	@NotBlank(message = "コメントは必須です。")
	@Size(min=2,max=50,message="2文字以上、５０文字以下で入力してください。")
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
