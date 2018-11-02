package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Article;

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

	/** 結果の操作を定義 */
	@Autowired
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article;

	};

	/**
	 * 投稿記事の全件検索を行うメソッド.
	 * 
	 * @return 全ての投稿記事
	 */
	public List<Article> findAll() {
		String sql = "select id,name,content from articles order by id desc";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
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

	public void deleteById(Integer id) {
		String sql = "delete from articles where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

}
