package com.z4.zhazha.forum.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.z4.zhazha.forum.pojo.Article;
import com.z4.zhazha.forum.pojo.Review;
import com.z4.zhazha.forum.service.IService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@FixMethodOrder
public class OneToManyTest {
	@Autowired
	@Qualifier("articleservice")
	IService<Article> articleService;
	
	@Autowired
	@Qualifier("reviewservice")
	IService<Review> reviewService;
	
	@Test
	public void testOneToMany() {
		Article article1 = new Article();
		article1.setArticleID(1);
		article1.setAuthorID(1);
		articleService.save(article1);
		
		Review review1 = new Review();
		review1.setReviewID(1);
		
		Review review2 = new Review();
		review2.setReviewID(2);
		
		article1 = articleService.get(Article.class, article1.getArticleID());
		article1.getReviews().add(review1);
		article1.getReviews().add(review2);
		articleService.update(article1);
		
		article1 = articleService.get(Article.class, article1.getArticleID());
		System.out.println(article1.getReviews().size());
	}
	
	
}
