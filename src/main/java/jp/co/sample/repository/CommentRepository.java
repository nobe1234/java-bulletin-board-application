package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Comment;

/**
 * 投稿されたコメントに対する操作を定義したリポジトリ.
 * 
 * 記事と紐づいたコメントの検索、追加、削除機能をもつ。
 * 
 * @author soheinobe
 *
 */
@Repository
public class CommentRepository {

	/** JDBC関連オブジェクトの注入 */
	@Autowired
	private NamedParameterJdbcTemplate templete;

	/** 結果の操作を定義 */
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));

		return comment;
	};

	/**
	 * 記事IDから紐づいたコメントを検索する.
	 * 
	 * @param 記事ID
	 * @return コメントリスト
	 */
	public List<Comment> findByArticleID(int articleId) {
		String sql = "select id,name,content,article_id from comments where article_id = :articleId order by id desc";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);

		List<Comment> commentList = templete.query(sql, param, COMMENT_ROW_MAPPER);
		return commentList;
	}

	/**
	 * 受け取ったコメントオブジェクトをDBに追加するメソッド.
	 * 
	 * @param コメントオブジェクト
	 */
	public void insert(Comment comment) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		String sql = "insert into comments (name,content,article_id) values (:name,:content,:articleId)";
		templete.update(sql, param);

	}

}
