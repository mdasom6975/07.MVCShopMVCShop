package com.model2.mvc.web.product;

import java.io.File;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.CookieGenerator;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

//==>판매관리 Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	public ProductController() {
		System.out.println(this.getClass());
	}

	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	// @RequestMapping("/addProductView.do")
	// public String addProduct() throws Exception {
	@RequestMapping(value = "addProduct", method = RequestMethod.GET)
	public String addProduct() throws Exception {

		System.out.println("/product/addProduct : GET");

		return "redirect:/product/addProductView.jsp";
	}

	// @RequestMapping("/addProduct.do")
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file)
			throws Exception {

		System.out.println("/product/addProduct : POST");

		File f = new File(
				"C:\\Users\\Bit\\git\\07.Model2MVCShop\\07.Model2MVCShop(URI,pattern)\\WebContent\\images\\uploadFiles\\"
						+ file.getOriginalFilename());
		file.transferTo(f); // 위의 경로에 파일 저장
		product.setFileName(file.getOriginalFilename()); // 프로덕트에 set

		// Business Logic
		productService.addProduct(product);

		return "forward:/product/addProduct.jsp";
	}

	// @RequestMapping("getProduct.do")
	@RequestMapping(value = "getProduct", method = RequestMethod.GET)
	public String getProduct(@RequestParam("prodNo") String prodNo, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("/product/getProduct : GET");
		// Business Logic
		Product product = productService.getProduct(Integer.parseInt(prodNo));

		String history = null;
		Cookie[] getCookie = request.getCookies();
		if (getCookie != null && getCookie.length > 0) {
			for (int i = 0; i < getCookie.length; i++) {
				Cookie cookie = getCookie[i];
				if (cookie.getName().equals("history")) {
					history = cookie.getValue();
				}
			}
		}

		history += "," + product.getProdNo();
		// history +=","+product.getProdName();
		CookieGenerator cg = new CookieGenerator();
		cg.setCookieName("history");
		cg.setCookieMaxAge(60*60*24);//쿠키 유효기간 설정 초단위 : 60*60*24= 1일 
		cg.addCookie(response, history);

		// Model과 View 연결
		model.addAttribute("product", product);

		if (request.getParameter("menu").equals("manage")) {
			System.out.println("menu :::::" + request.getParameter("menu"));
			return "forward:/product/updateProductView.jsp";
		} else {
			System.out.println("menu  ::::" + request.getParameter("menu"));
			return "forward:/product/getProduct.jsp";
		}
	}

	// @RequestMapping("/updateProductView.do")
	// public String updateProductView(@RequestParam("prodNo") String prodNo, Model
	// model) throws Exception {
	@RequestMapping(value = "updateProduct", method = RequestMethod.GET)
	public String updateProduct(@RequestParam("prodNo") String prodNo, Model model) throws Exception {

		System.out.println("/product/updateProductView : GET");
		// Business Logic
		Product product = productService.getProduct(Integer.parseInt(prodNo));
		// Model 과 View 연결
		model.addAttribute("product", product);

		return "forward:/product/updateProductView.jsp";
	}

	// @RequestMapping("updateProduct.do")
	@RequestMapping(value = "updateProduct", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product, Model model, @RequestParam("file") MultipartFile file) throws Exception {

		System.out.println("/product/updateProduct : POST");
		
		File f = new File(
				"C:\\Users\\Bit\\git\\07.Model2MVCShop\\07.Model2MVCShop(URI,pattern)\\WebContent\\images\\uploadFiles\\"
						+ file.getOriginalFilename());
		file.transferTo(f); // 위의 경로에 파일 저장
		product.setFileName(file.getOriginalFilename()); // 프로덕트에 set
		
		
		// Business Logic
		productService.updateProduct(product);

		return "forward:/product/getProduct.jsp";
	}

	// @RequestMapping("listProduct.do")
	@RequestMapping(value = "listProduct")
	public String listProduct(@ModelAttribute("search") Search search, @RequestParam("orderby") String orderby,
			Model model, HttpServletRequest request) throws Exception {

		System.out.println("/product/listProduct ");
		System.out.println("getCurrentPage" + search.getCurrentPage());

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));

		// Business logic 수행
		Map<String, Object> map = productService.getProductList(search, orderby);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		if (request.getParameter("menu").equals("manage")) {
			System.out.println("menu :::::" + request.getParameter("menu"));
			return "forward:/product/managerProduct.jsp";
		} else {
			System.out.println("menu  ::::" + request.getParameter("menu"));
			return "forward:/product/listProduct.jsp";
		}
	}

}
