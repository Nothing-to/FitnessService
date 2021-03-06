package com.code.dao;

import com.code.entity.News;
import com.code.entity.NewsDetail;
import com.code.entity.NewsListForFound;
import com.code.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;


public class NewsDao {

	/** sql语句 */
	private String sql = "";

	/** QueryRunner */
	private QueryRunner queryRunner = JdbcUtils.getQueryRunnner();

	public boolean releaseNews(News news) {
		sql = "INSERT INTO news (userId, title, content, image) VALUES (?, ?, ?, ?);";
		try {
			return queryRunner.update(sql, news.getUserId(), news.getTitle(), news.getContent(), news.getImage()) > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<NewsListForFound> getNewsList(int rows) {
		sql = "SELECT newsId, title, if(sex='男', 1, 0) as sex, username FROM news a, user b WHERE a.userId = b.userId ORDER BY releaseTime DESC LIMIT 0, ?;";
		try {
			return queryRunner.query(sql, new BeanListHandler<NewsListForFound>(NewsListForFound.class), rows);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public NewsDetail getNewsDetail(String newsId) {
		sql = "SELECT newsId, username, title, content, image, releaseTime FROM news a, user b WHERE a.userId = b.userId AND newsId = ?;";
		try {
			return queryRunner.query(sql, new BeanHandler<NewsDetail>(NewsDetail.class), newsId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}
