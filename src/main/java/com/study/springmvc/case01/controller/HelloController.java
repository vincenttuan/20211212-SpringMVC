package com.study.springmvc.case01.controller;

import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springmvc.case01.entity.User;

@Controller
// Base路徑：/case01/hello
@RequestMapping("/case01/hello")
public class HelloController {
	
	// 1. 取得字串資料
	// 路徑：/welcome
	@RequestMapping("/welcome")
	@ResponseBody // 直接根據回傳值型別回應
	public String welcome() {
		return "Hello SpringMVC " + new Date();
	}
	
	// 2. 帶參數(?xxx=xxx 配置 @RequestParam)
	// 路徑：/sayhi?name=Vincent&age=18
	@RequestMapping(value={"/sayhi", "/hi"}, method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String sayhi(@RequestParam(value = "name") String name,
						@RequestParam(value = "age", required = false, defaultValue = "0") Integer age) {
		return String.format("Hi %s %d", name, age);
	}
	
	/*
	 * 3. 帶參數 Lab 練習：?後帶入參數並計算
	 * 路徑：/bmi?h=170.0&w=60.0
	 * 可以使用：Optional<?> 來接參數
	 */
	// 請設計方法 api, 結果會得到 bmi = 20.76。若資訊不足則顯示 bmi = None!
	@GetMapping("/bmi")
	@ResponseBody
	public String bmi(@RequestParam(value = "h", required = false) Optional<Double> h,
					  @RequestParam(value = "w", required = false) Optional<Double> w) {
			String result = "None";
			if(h.isPresent() && w.isPresent()) {
				double bmi = w.get()/Math.pow(h.get()/100, 2);
				result = String.format("%.2f", bmi);
			}
		return String.format("bmi = %s", result);
	}
	
	/*
	 * 4. 路徑參數 @PathVariable
	 * 路徑：/exam/75 => score: 75 pass
	 * 路徑：/exam/45 => score: 45 fail
	 */
	@GetMapping("/exam/{score}")
	@ResponseBody
	public String examScore(@PathVariable("score") Integer score) {
		return String.format("score: %d %s", score, (score>=60)?"Pass":"Fail");
	}
	
	/*
	 * 5. @RequestParam + @PathVariable (Lab 練習)
	 * 路徑：/calc/add?x=30&y=20  -> Result：30 + 20 =  50
	 * 路徑：/calc/sub?x=30&y=20  -> Result：30 - 20 =  10
	 * 路徑：/calc/sub?y=20       -> Result： 0 - 20 = -20
	 * 路徑：/calc/add            -> Result： 0 +  0 =   0 
	 * 路徑：/calc/div            -> Result： exp value error
	 */
	// 請設計方法 api
	@GetMapping("/calc/{exp}")
	@ResponseBody
	public String calcExp(@PathVariable("exp") String exp,
						  @RequestParam(value = "x", required = false, defaultValue = "0") Integer x,
						  @RequestParam(value = "y", required = false, defaultValue = "0") Integer y) {
		int result = 0;
		switch (exp) {
			case "add":
				result = x + y;
				break;
			case "sub":
				result = x - y;
				break;	
			default:
				return "Result: exp value error";
			}
		return String.format("Result: %d %s %d = %d", x, (exp.equals("add"))?"+":"-", y, result);
	}
	
	/*
	 * 6. @PathVariable (萬用字元： * 任意多字、? 任意一字)
	 * 路徑：/path/namejo/java8
	 * 路徑：/path/nameTaipei/java7
	 * 路徑：/path/name1234/java6
	 */
	@GetMapping("/path/name*/java?")
	@ResponseBody
	public String path() {
		return "Path OK!";
	}
	
	/*
	 * 7. 多筆參數資料
	 * 子路徑：/age?age=18&age=19&age=20
	 * 並計算平均: avg of age = 19.0
	 */
	@GetMapping("/age")
	@ResponseBody
	public String age(@RequestParam("age") List<Integer> ageList) {
		double avgOfAge = ageList.stream().mapToInt(Integer::intValue).average().getAsDouble();
		return String.format("avg of age = %.1f", avgOfAge);
	}
	
	/* 
	 * 8. 得到多筆Java考試成績的資料 (Lab 練習)
     * 路徑：/javaexam?score=80&score=100&score=50
     * 求出最高分、最低分、平均與總分 ==> hight: 100, low: 50, avg: 76.67, sum: 230
    */
	@GetMapping("/javaexam")
	@ResponseBody
	public String javaexam(@RequestParam("score") List<Integer> scores) {
		IntSummaryStatistics stat = scores.stream().mapToInt(Integer::intValue).summaryStatistics();
		return String.format("hight: %d, low: %d, avg: %.2f, sum: %d",
				stat.getMax(), stat.getMin(), stat.getAverage(), stat.getSum());
	}
	
	/*
	 * 9. pojo(entity) 
	 * 有一個 User.java 裡面有name與age這二個物件屬性
	 * 子路徑：/user?name=John&age=18
	 * 可以進行參數自動匹配
	 * */
	
	@GetMapping(value = "/user")
	@ResponseBody
	public String getUser(User user) {
		return user.toString();
	}
	
	/*
	 * 10. Map 參數
	 * 路徑：/person?name=John&score=100&age=18&pass=true
	 * 路徑：/person?name=Mary&score=90&level=2
	 * 常與於各 form 表單傳來的不統一參數
	 * */
	@GetMapping(value = "/person")
	@ResponseBody
	//public String getPerson(User user, @RequestParam(value = "score", required = false) Integer score) {
	//	return user.toString() + score;
	//}
	public String getPerson(@RequestParam Map<String, String> person) {
		return person.toString();
	}
	
	/*
	 * 11. 獲取原生 HttpSession 的資料
	 * */
	@GetMapping(value = "/sessionInfo", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String getSessionInfo(HttpSession httpSession) {
		String sessionKey = "firstAccessTime";
		Object time = httpSession.getAttribute(sessionKey);
		if(time == null) {
			time = new Date();
			httpSession.setAttribute(sessionKey, time);
		}
		return String.format("firstAccessTime: %s\nsessionid: %s", time, httpSession.getId());
	}
	
	
	
	
	
	
	
	
	
	
	
}
