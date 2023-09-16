/*
 * Copyright 2016-2023 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.teasoft.bee.osql.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * It is used to query and obtain record information, 
 * but not as a filter condition in WHERE part.
 * 
<p> eg:
<p> @&nbsp;JustFetch("CONCAT(fisrt_name,last_name)") //multiple tables maybe need bring the table name
<p> private String fullname;
<p> --> select CONCAT(fisrt_name,last_name) as fullname
  
<p>  map field name and column name,but can not transform to where part
<p>  eg:
<p> 	@&nbsp;JustFetch("name")
<p> 	private String name2;
<p> -->select name as name2	

<p> eg:(since 2.1.8)
<p>     TestUser.java
<p> 	@JustFetch() //Only obtain values, do not transfer fields to select/where.
<p>     private String count1;
<p> 
<p> 	Condition condition=BF.getCondition();
<p> 	condition.selectFun(FunctionType.COUNT, "*", "count1");
<p> 	condition.selectField("lastName");
<p> 	condition.groupBy("lastName");
<p> 	List<TestUser> list =suid.select(testUser,condition);  //select
<p> 

 * @author Kingstar
 * @since  1.11
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JustFetch {
	
	/**
	 *  Since V2.1.8,when value is empty, the corresponding field is parsed and only used to receive the result value
	 * @return
	 */
	String value() default "";  
}
