package jp.co.sample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Article;
import jp.co.sample.domain.Comment;
import jp.co.sample.form.ArticleForm;
import jp.co.sample.form.CommentForm;
import jp.co.sample.repository.ArticleRepository;
import jp.co.sample.repository.CommentRepository;

/**
 * ユーザーが記事を投稿した記事にコメントできる掲示板アプリ.
 * 
 * 各記事に紐づいたコメントが記事の下に表示される。
 * 
 * @author sohei.nobe
 *
 */
@Controller
@Transactional
@RequestMapping("/article")
public class ArticleController {

	/** 記事リポジトリを注入 */
	@Autowired
	private ArticleRepository articleRepository;

	/** コメントリポジトリを注入 */
	@Autowired
	private CommentRepository commentRepository;

	/**
	 * リクエストパラメーターとフォームを関連させる際に必要なオブジェクトを事前作成.
	 * 
	 * @return 空の記事フォームオブジェクト
	 */
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	/**
	 * リクエストパラメーターとフォームを関連させる際に必要なオブジェクトを事前作成.
	 * 
	 * @return 空のコメントフォームオブジェクト
	 */
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}

	/**
	 * 処理の起点となるメソッド.
	 * 
	 * DB上の記事とコメントを表示する。
	 * 
	 * @param モデル
	 * @return 掲示板
	 */
	@RequestMapping("/index")
	public String index(Model model) {

		List<Article> articleList = articleRepository.findAll();
		// 「空」のcommentListをインスタンス化する。
		List<Comment> commentList = new ArrayList<>();
		// ArticleListからarticleを取り出す。
		for (Article article : articleList) {
			// commentList に記事からとってきた記事のIDを入れて取り出す。
			commentList = commentRepository.findByArticleID(article.getId());

			// articleリストのフィールド変数にあるコメントリストにセットする。
			article.setCommentList(commentList);

			// 以後、jspのforeachの中のforeachでコメント取り出していく。
		}

		model.addAttribute("articleList", articleList);
//		model.addAttribute("commentList", commentList);
		return "bulletinBoard";
	}

	/**
	 * 記事の投稿を行うメソッド.
	 * 
	 * 投稿欄から送られたリクエストパラメーターをDBに格納する。 DBの内容を表示するため、indexメソッドを呼び出す。
	 * 
	 * @param articleForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(ArticleForm articleForm, Model model) {
		Article article = new Article();
		article.setName(articleForm.getName());
		article.setContent(articleForm.getContent());
		articleRepository.insert(article);
		return index(model);
	}

	/**
	 * コメントフォームに入力された投稿内容をDBに登録するメソッド.
	 * 
	 * @param 投稿コメントに入力されたリクエストパラメータ
	 * @param モデル
	 * @return ページ表示、およびDBからのデータ取り出しindexメソッド
	 */
	@RequestMapping("/insertComment")
	public String insertComment(CommentForm commentForm, Model model) {
		Comment comment = new Comment();
		comment.setName(commentForm.getName());
		comment.setContent(commentForm.getContent());
		comment.setArticleId(commentForm.getArticleId());
		System.out.println(comment);
		System.out.println(commentForm);
		commentRepository.insert(comment);
		return index(model);
	}

	@RequestMapping("/delete")
	public String deleteArticle(Integer articleId, Model model) {
		commentRepository.deleteByArticleId(articleId);
		articleRepository.deleteById(articleId);
		System.out.println(articleId);
//		commentRepository.deleteByArticleId(comment.getArticleId());
		return index(model);
	}

}
