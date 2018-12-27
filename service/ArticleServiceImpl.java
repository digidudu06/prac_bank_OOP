package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import domain.ArticleBean;

public class ArticleServiceImpl implements ArticleService {
	private HashMap<String, ArticleBean> map;
	private int seq;

	public ArticleServiceImpl() {
		map = new HashMap<>();
		seq = 1;
	}

	@Override
	public void creatBulletinBoard(String title, String content, String writer, String bulletinPass) {
		ArticleBean article = new ArticleBean();
		article.setTitleSeq(seq);
		article.setWriter(writer);
		article.setTitle(title);
		article.setContent(content);
		article.setBulletinPass(bulletinPass);
		article.setRegDate(findRegDate());
		map.put(String.valueOf(article.getTitleSeq()), article);
		seq++;
	}

	@Override
	public ArrayList<ArticleBean> findAll() {
		ArrayList<ArticleBean> list = new ArrayList<>();
		Set<String> set = map.keySet();
		for (String key : set) {
			list.add(map.get(key));
		}
		return list;
	}

	@Override
	public ArrayList<ArticleBean> findTitles(String writer) {
		ArrayList<ArticleBean> list = new ArrayList<>();
		Set<String> set = map.keySet();
		for (String key : set) {
			if (map.get(key).getWriter().equals(writer)) {
				list.add(map.get(key));
			}
		}
		return list;
	}

	@Override
	public ArticleBean findOne(int seq) {
		return map.get(seq);
	}

	@Override
	public int countTitle() {
		return map.size();
	}

	@Override
	public boolean existTitle(String seq, String title) {
		boolean exist = false;
		if(map.get(seq).getTitle().equals(title)) {
			exist = true;
		}
		return exist;
	}

	@Override
	public String findRegDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		return sdf.format(date);
	}

	@Override
	public void updateBulletin(String seq, String bulletinPass, String newContent) {
		if(map.containsKey(seq) && map.get(seq).getBulletinPass().equals(bulletinPass)) {
			map.get(seq).setContent(newContent);
		}
	}

	@Override
	public void deleteBulletin(String seq, String bulletinPass) {
		if(map.containsKey(seq) && map.get(seq).getBulletinPass().equals(bulletinPass)) {
			map.remove(seq);
		}
	}
}