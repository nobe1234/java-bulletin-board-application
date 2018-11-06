package jp.co.sample.controller;

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
	 * DB上の記事とコメントを表示する。 投稿された記事には、同記事にIDで紐づいたコメントが新着順で表示される.
	 * 
	 * @param モデル
	 * @return 掲示板
	 */
	@RequestMapping("/index")
	public String index(Model model) {

		List<Article> articleList = articleRepository.findAll();
		model.addAttribute("articleList", articleList);

		return "bulletinBoard";
	}

	/**
	 * 記事の投稿を行うメソッド.
	 * 
	 * 投稿欄から送られたリクエストパラメーターをDBに格納する。 DBの内容を表示するため、indexメソッドを呼び出す。
	 * 
	 * @param 記事投稿フォームに入力されたリクエストパラメータ
	 * @param モデル
	 * @return ページ表示、およびDBからのデータ取り出しindexメソッドをリダイレクト.
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(ArticleForm articleForm, Model model) {
		Article article = new Article();
		article.setName(articleForm.getName());
		article.setContent(articleForm.getContent());
		articleRepository.insert(article);
		return "redirect:/article/index";
	}

	/**
	 * コメントフォームに入力された投稿内容をDBに登録するメソッド.
	 * 
	 * @param 投稿コメントに入力されたリクエストパラメータ
	 * @param モデル
	 * @return ページ表示、およびDBからのデータ取り出しindexメソッドをリダイレクト.
	 */
	@RequestMapping("/insertComment")
	public String insertComment(CommentForm commentForm, Model model) {
		Comment comment = new Comment();
		// BeanUtilで省略可
		comment.setName(commentForm.getName());
		comment.setContent(commentForm.getContent());
		comment.setArticleId(commentForm.getArticleId());
		System.out.println(comment);
		System.out.println(commentForm);
		commentRepository.insert(comment);
		return "redirect:/article/index";
	}

	/**
	 * 投稿された記事を削除をするメソッド.
	 * 
	 * @param 投稿記事id
	 * @param モデル
	 * @return ページ表示、およびDBからのデータ取り出しindexメソッドをリダイレクト.
	 */
	@RequestMapping("/delete")
	public String deleteArticle(Integer articleId, Model model) {
		commentRepository.deleteByArticleId(articleId);
		articleRepository.deleteById(articleId);
		System.out.println(articleId);
//		commentRepository.deleteByArticleId(comment.getArticleId());
		return "redirect:/article/index";
	}

}
