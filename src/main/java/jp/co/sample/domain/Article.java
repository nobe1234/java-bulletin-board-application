package jp.co.sample.domain;

import java.util.List;

/**
 * 記事の情報を表すドメイン.
 * 
 * コメント型のリストを保有している.
 * 
 * @author soheinobe
 *
 */
public class Article {

	/** 主キー */
	private Integer id;
	/** 投稿者名 */
	private String name;
	/** 記事名 */
	private String content;
	/** コメントリスト */
	private List<Comment> commentList;
	
	/** コメントid */
	private Integer comId;
	/** コメント者名 */
	private String comName;
	/** コメント内容 */
	private String comContent;
	/** 記事ID ※主キー */
	private Integer articleId;

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", commentList=" + commentList
				+ ", comId=" + comId + ", comName=" + comName + ", comContent=" + comContent + ", articleId="
				+ articleId + "]";
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

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

}
