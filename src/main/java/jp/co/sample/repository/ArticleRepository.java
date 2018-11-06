package jp.co.sample.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Article;
import jp.co.sample.domain.Comment;

/**
 * 記事内容を操作するリポジトリ.
 * 
 * 全件検索、追加、削除機能を持つ。
 * 
 * @author soheinobe
 *
 */
@Repository
public class ArticleRepository {

	/** JDBC関連オブジェクトの注入 */
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 投稿記事およびコメントの全件検索を行うメソッド.
	 * 
	 * @return 全ての投稿記事
	 */
	public List<Article> findAll() {
		String sql = "Select A.id as a_id, a.name as a_name, A.content as a_content,"
				+ " com.id as com_id, com.name as com_name,com.content as com_content,Com.article_id as article_id "
				+ "From Articles a left outer join comments com on a.id = com.article_id Order by a.id";
		List<Article> articleList = template.query(sql, new ResultSetExtractor<List<Article>>() {

			/** 結果の操作を定義 */
			@Override
			public List<Article> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Article> articleList = new ArrayList<>();
				int previousId = 0;
				while (rs.next()) {
					int id = rs.getInt("a_id");
					List<Comment> commentList = new ArrayList<>();
					if (previousId != id) {
						Article article = new Article();
						article.setId(rs.getInt("a_id"));
						article.setName(rs.getString("a_name"));
						article.setCommentList(commentList);
						articleList.add(article);
					}
					Comment comment = new Comment();
					comment.setContent(rs.getString("a_content"));
					comment.setId(rs.getInt("com_id"));
					comment.setName(rs.getString("com_name"));
					comment.setContent(rs.getString("com_content"));
					comment.setArticleId(rs.getInt("article_id"));
					commentList.add(comment);
					previousId = id;
				}
				return articleList;

			}
		});
		return articleList;
	}

	/**
	 * 投稿された記事をDBに登録するメソッド.
	 * 
	 * @param 記事テーブル
	 */
	public void insert(Article article) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);

		String sql = "insert into articles (name,content) values(:name,:content)";
		template.update(sql, param);

	}

	/**
	 * 記事をDBから削除するメソッド.
	 * 
	 * @param 投稿記事のid(主キー)
	 */
	public void deleteById(Integer id) {
		String sql = "delete from articles where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);

	}
}