package cn.itcast.oa.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.util.QueryHelper;

public class QueryHelpTest {

	private boolean reverse = false;
	private int viewType = 1;
	private int orderBy = 0;
	private Forum forum = new Forum();

	@Test
	public void testQueryHelp() {
		// QueryHelper queryHelper = new QueryHelper(Topic.class, "t")//
		// .addWhereCondition("t.forum=?", forum)//
		// .addOrderCondition(//
		// "(CASE t.type WHEN 2 THEN 2 ELSE 0 END) ", reverse);
		QueryHelper queryHelper = new QueryHelper(Topic.class, "t")//
				.addWhereCondition(viewType == 1, "t.forum=?", forum)//
				.addOrderCondition(orderBy == 0, "t.postTime", reverse);

		System.out.println(queryHelper.getQueryList());
		System.out.println(queryHelper.getCountList());
		System.out.println(queryHelper.getParametes());
	}
}
